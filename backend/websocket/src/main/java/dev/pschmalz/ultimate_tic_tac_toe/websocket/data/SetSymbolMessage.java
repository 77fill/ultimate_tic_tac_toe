package dev.pschmalz.ultimate_tic_tac_toe.websocket.data;

public class SetSymbolMessage implements NetworkMessage {
	private String type = "setSymbol";
	private String symbol;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	
}
