package dev.pschmalz.ultimate_tic_tac_toe.logic.match_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;
import dev.pschmalz.ultimate_tic_tac_toe.logic.match_management.events.MatchMakerEvent;

public class MatchMaker implements Runnable {
	private Queue<MatchMakerEvent> events = new ConcurrentLinkedQueue<>();
	private boolean running = true;
	private Map<Player, Match> rooms = new Hashtable<>();
	private List<Player> idlePlayers = new ArrayList<>();
	private Executor executor;
	
	public MatchMaker(Executor executor) {
		this.executor = executor;
	}
	
	@Override
	public void run() {
		while(running) {
			
			while(!events.isEmpty()) {
				var event = events.poll();
				
				switch(event.getType()) {
				case ENTER:
					idlePlayers.add(event.getPlayer());
					break;
				case LEAVE:
					searchAndDestroy(event.getPlayer());
					break;
				}
				
			}
			
			while(idlePlayers.size() > 1) {
				var player1 = idlePlayers.remove(0);
				var player2 = idlePlayers.remove(0);
				
				var match = new Match(player1, player2);
				
				rooms.put(player1, match);
				rooms.put(player2, match);
				
				player1.setSymbol(Symbol.X);
				player2.setSymbol(Symbol.O);
				
				executor.execute(match);
				
				System.out.println("Players matched!");
			}
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void searchAndDestroy(Player player) {
		if(idlePlayers.contains(player))
			idlePlayers.remove(player);
		else if(rooms.containsKey(player)) {
			var room = rooms.get(player);
			
			var otherPlayer = room.getOtherPlayer(player);
			
			rooms.remove(player);
			rooms.remove(otherPlayer);
			
			room.destroy();
		}
	}
	
	public void addEvent(MatchMakerEvent event) {
		events.add(event);
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public Optional<Match> roomOf(Player player) {
		if(rooms.containsKey(player))
			return Optional.of(rooms.get(player));
		
		return Optional.empty();
	}
}
