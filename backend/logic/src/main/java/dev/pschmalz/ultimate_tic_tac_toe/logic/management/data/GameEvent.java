package dev.pschmalz.ultimate_tic_tac_toe.logic.management.data;

import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;

public class GameEvent {
	private RegularCellCoordinatesData coords;
	private Player source;
	
	public Player getSource() {
		return source;
	}

	public void setSource(Player source) {
		this.source = source;
	}

	public RegularCellCoordinatesData getCoords() {
		return coords;
	}

	public void setCoords(RegularCellCoordinatesData coords) {
		this.coords = coords;
	}
}
