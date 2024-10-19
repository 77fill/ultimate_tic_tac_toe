package dev.pschmalz.ultimate_tic_tac_toe.logic.data;

public class CellCoordinates {
	private int metaX, metaY, x, y;

	public CellCoordinates(int metaX, int metaY, int x, int y) {
		super();
		this.metaX = metaX;
		this.metaY = metaY;
		this.x = x;
		this.y = y;
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
