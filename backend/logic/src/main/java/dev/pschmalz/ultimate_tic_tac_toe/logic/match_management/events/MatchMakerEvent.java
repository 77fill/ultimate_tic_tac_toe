package dev.pschmalz.ultimate_tic_tac_toe.logic.match_management.events;

import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;

public class MatchMakerEvent {
	public static enum MatchMakerEventType {
		ENTER, LEAVE
	}
	
	private Player player;
	private MatchMakerEventType type;
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	public MatchMakerEventType getType() {
		return type;
	}

	public void setType(MatchMakerEventType type) {
		this.type = type;
	}
	
}
