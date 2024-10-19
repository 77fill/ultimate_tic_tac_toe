package dev.pschmalz.ultimate_tic_tac_toe.logic.match_management.events;

import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;
import dev.pschmalz.ultimate_tic_tac_toe.logic.match_management.data.CellCoordinatesData;

public class GameEvent {
	private CellCoordinatesData coords;
	private Player source;
	
	public Player getSource() {
		return source;
	}

	public void setSource(Player source) {
		this.source = source;
	}

	public CellCoordinatesData getCoords() {
		return coords;
	}

	public void setCoords(CellCoordinatesData coords) {
		this.coords = coords;
	}
}
