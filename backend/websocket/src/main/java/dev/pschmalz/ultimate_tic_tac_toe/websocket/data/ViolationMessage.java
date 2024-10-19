package dev.pschmalz.ultimate_tic_tac_toe.websocket.data;

public class ViolationMessage implements NetworkMessage {
	private String type = "violation";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
