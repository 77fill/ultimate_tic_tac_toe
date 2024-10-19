package dev.pschmalz.ultimate_tic_tac_toe.logic.match_management;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import dev.pschmalz.ultimate_tic_tac_toe.logic.MetaField;
import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;
import dev.pschmalz.ultimate_tic_tac_toe.logic.CellCoordinates;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;
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
			
			while(!events.isEmpty()) {
				var event = events.poll();
				var coordsData = event.getCoords();
				var coords = new CellCoordinates(
						coordsData.metaX(),
						coordsData.metaY(),
						coordsData.x(),
						coordsData.y());
				
				
				if(players.get(currentTurn) == event.getSource()) {
					metaGame.putSymbol(currentTurn, coords);
					
					var otherPlayer = players.get(Symbol.other(currentTurn));
					
					otherPlayer.gameState(metaGame);
					otherPlayer.itsYourTurn();
				}
			}
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
