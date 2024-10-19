package dev.pschmalz.ultimate_tic_tac_toe.websocket.data;

import java.util.List;

public class ViolationMessage implements NetworkMessage {
	private String type = "violation";
	private List<String> symbols;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<String> symbols) {
		this.symbols = symbols;
	}
	
	
}
