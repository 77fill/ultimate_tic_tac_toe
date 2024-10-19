package dev.pschmalz.ultimate_tic_tac_toe.websocket.data;

import java.util.ArrayList;
import java.util.List;

public class YourTurnMessage implements NetworkMessage {
	private String type = "itsYourTurn";
	private List<Integer> currentFieldCoords = new ArrayList<>();
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void addCurrentFieldCoord(int coord) {
		currentFieldCoords.add(coord);
	}
	
	
}
