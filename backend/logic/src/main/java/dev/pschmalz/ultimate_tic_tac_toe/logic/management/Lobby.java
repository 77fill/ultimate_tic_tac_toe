package dev.pschmalz.ultimate_tic_tac_toe.logic.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;
import dev.pschmalz.ultimate_tic_tac_toe.logic.management.data.LobbyEvent;

public class Lobby implements Runnable {
	public static Lobby instance;
	private Queue<LobbyEvent> events = new ConcurrentLinkedQueue<>();
	private boolean running = true;
	private Map<Player, GameRoom> rooms = new HashMap<>();
	private List<Player> idlePlayers = new ArrayList<>();
	private Executor executor;
	
	public Lobby(Executor executor) {
		this.executor = executor;
	}
	
	@Override
	public void run() {
		while(running) {
			
			while(!events.isEmpty()) {
				var event = events.poll();
				
				idlePlayers.add(event.getPlayer());
			}
			
			while(idlePlayers.size() > 1) {
				var player1 = idlePlayers.remove(0);
				var player2 = idlePlayers.remove(1);
				
				var gameRoom = new GameRoom(player1, player2);
				
				rooms.put(player1, gameRoom);
				rooms.put(player2, gameRoom);
				
				executor.execute(gameRoom);
			}
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addEvent(LobbyEvent event) {
		events.add(event);
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public Optional<GameRoom> whereIs(Player player) {
		if(rooms.containsKey(player))
			return Optional.of(rooms.get(player));
		
		return Optional.empty();
	}
}
