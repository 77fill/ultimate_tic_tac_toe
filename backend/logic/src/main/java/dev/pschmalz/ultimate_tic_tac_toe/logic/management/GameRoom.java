package dev.pschmalz.ultimate_tic_tac_toe.logic.management;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import dev.pschmalz.ultimate_tic_tac_toe.logic.MetaGame;
import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;
import dev.pschmalz.ultimate_tic_tac_toe.logic.RegularCellCoordinates;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;
import dev.pschmalz.ultimate_tic_tac_toe.logic.management.data.GameEvent;

public class GameRoom implements Runnable {
	private Map<Symbol, Player> players = new HashMap<>();
	private Symbol currentTurn = Symbol.X;
	private Queue<GameEvent> events = new ConcurrentLinkedQueue<>();
	private boolean running = true;
	private MetaGame metaGame = new MetaGame();
	
	public GameRoom(Player player1, Player player2) {
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
				var coords = new RegularCellCoordinates(
						coordsData.getMetaX(),
						coordsData.getMetaY(),
						coordsData.getX(),
						coordsData.getY());
				
				
				if(players.get(currentTurn) == event.getSource()) {
					metaGame.putSymbol(currentTurn, coords);
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
	
	public void addEvent(GameEvent event) {
		events.add(event);
	}

}
