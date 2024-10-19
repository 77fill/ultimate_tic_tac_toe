package dev.pschmalz.ultimate_tic_tac_toe.logic.match_management;

import java.util.HashMap;
import java.util.Map;
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
	private MetaField metaGame = new MetaField();
	
	public Match(Player player1, Player player2) {
		super();
		
		players.put(Symbol.X, player1);
		players.put(Symbol.O, player2);
	}

	@Override
	public void run() {
		players.get(Symbol.X).itsYourTurn();
		
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
		var metaCoords = new CellCoordinates(coordsData.metaX(), coordsData.metaY());
		var fieldCoords = new CellCoordinates(coordsData.x(), coordsData.y());
		
		var ruleViolation = 
				metaGame.putSymbol(
						currentTurn, 
						metaCoords, 
						fieldCoords);
		
		if(ruleViolation == RuleViolation.NONE) {
			var otherPlayer = players.get(Symbol.other(currentTurn));
			
			otherPlayer.gameState(metaGame);
			otherPlayer.itsYourTurn();
			
			currentTurn = Symbol.other(currentTurn);
		}
		else
			players.get(currentTurn).violation();
		
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
