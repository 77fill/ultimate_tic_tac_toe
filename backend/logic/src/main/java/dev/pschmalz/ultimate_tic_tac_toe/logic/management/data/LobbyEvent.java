package dev.pschmalz.ultimate_tic_tac_toe.logic.management.data;

import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;

public class LobbyEvent {
	private Player player;
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
}
