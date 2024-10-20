package dev.pschmalz.ultimate_tic_tac_toe.websocket.data;

import java.util.ArrayList;
import java.util.List;

public class VictoryMessage implements NetworkMessage {
	private String type = "victory";
	private List<Integer> metaCoords = new ArrayList<>();
	private String symbol;
	
	public void addCoords(int metaX, int metaY) {
		metaCoords.add(metaX);
		metaCoords.add(metaY);
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
