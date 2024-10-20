package dev.pschmalz.ultimate_tic_tac_toe.logic.match_management;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import dev.pschmalz.ultimate_tic_tac_toe.logic.MetaField;
import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.CellCoordinates;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.RuleViolation;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;
import dev.pschmalz.ultimate_tic_tac_toe.logic.match_management.data.CellCoordinatesData;
import dev.pschmalz.ultimate_tic_tac_toe.logic.match_management.events.GameEvent;

public class Match implements Runnable {
	private Map<Symbol, Player> players = new HashMap<>();
	private Symbol currentTurn = Symbol.X;
	private Queue<GameEvent> events = new ConcurrentLinkedQueue<>();
	private boolean running = true;
	private MetaField metaField = new MetaField();
	private boolean theEnd = false;
	private CellCoordinates metaCoords, fieldCoords;
	private Optional<CellCoordinates> currentFieldCoords;
	
	public Match(Player player1, Player player2) {
		super();
		
		players.put(Symbol.X, player1);
		players.put(Symbol.O, player2);
	}

	@Override
	public void run() {
		players.get(Symbol.X).itsYourTurnAnyField();
		
		while(running) {
			while(!events.isEmpty()) 
				handleEvent();
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void handleEvent() {
		var event = events.poll();
		
		if(eventComesFromCurrentPlayer(event)) {
			executeTurn(event.getCoords());
		}
	}
	
	private void executeTurn(CellCoordinatesData coordsData) {
		metaCoords = new CellCoordinates(coordsData.metaX(), coordsData.metaY());
		fieldCoords = new CellCoordinates(coordsData.x(), coordsData.y());
		
		var ruleViolation = 
				metaField.putSymbol(
						currentTurn, 
						metaCoords, 
						fieldCoords);
		
		if(ruleViolation == RuleViolation.NONE) {
			var otherPlayer = players.get(Symbol.other(currentTurn));
			
			otherPlayer.gameState(metaField);
			currentFieldCoords = metaField.getCurrentFieldCoords();
			
			processVictory();
			if(theEnd)
				return;
			
			changeTurns();
		}
		else
			players.get(currentTurn).violation(metaField);
		
	}
	
	private void processVictory() {
		var player = players.get(currentTurn);
		var otherPlayer = players.get(Symbol.other(currentTurn));
		
		var victoriousSymbol = metaField
				.getField(
						metaCoords.getX(), 
						metaCoords.getY())
				.getVictoriousSymbol();

		victoriousSymbol.ifPresent(symbol -> {
			System.out.println("[field victory] symbol:"+symbol);
			
			var cellCoords = new CellCoordinates(metaCoords.getX(), metaCoords.getY());
			player.victory(symbol, cellCoords);
			otherPlayer.victory(symbol, cellCoords);
			
			var victoriousSymbol2 = metaField.getVictoriousSymbol();
			if(victoriousSymbol2.isPresent()) {
				player.victory(victoriousSymbol2.get());
				otherPlayer.victory(victoriousSymbol2.get());
				theEnd = true;
			}
		});		
	}
	
	private void changeTurns() {
		var otherPlayer = players.get(Symbol.other(currentTurn));
		
		if(currentFieldCoords.isEmpty())
			otherPlayer.itsYourTurnAnyField();
		else
			otherPlayer.itsYourTurn(
					currentFieldCoords.get().getX(), 
					currentFieldCoords.get().getY());
		
		currentTurn = Symbol.other(currentTurn);
	}
	
	private boolean eventComesFromCurrentPlayer(GameEvent event) {
		return players.get(currentTurn) == event.getSource();
	}
	
	public void destroy() {
		running = false;
	}
	
	public Player getOtherPlayer(Player player) {
		if(player == players.get(Symbol.X))
			return players.get(Symbol.O);
		else
			return players.get(Symbol.X);
	}
	
	public void addEvent(GameEvent event) {
		events.add(event);
	}

}
