package dev.pschmalz.ultimate_tic_tac_toe.websocket.data;

public class Message {
	private String type;
	private int metaX, metaY, x, y;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMetaX() {
		return metaX;
	}
	public void setMetaX(int metaX) {
		this.metaX = metaX;
	}
	public int getMetaY() {
		return metaY;
	}
	public void setMetaY(int metaY) {
		this.metaY = metaY;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
}
