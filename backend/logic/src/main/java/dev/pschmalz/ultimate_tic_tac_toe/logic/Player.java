package dev.pschmalz.ultimate_tic_tac_toe.logic;

import dev.pschmalz.ultimate_tic_tac_toe.logic.data.CellCoordinates;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;

public interface Player {
	public void itsYourTurnAnyField();
	public void itsYourTurn(int metaX, int metaY);
	public void gameState(MetaField metaField);
	public void setSymbol(Symbol symbol);
	public void violation(MetaField metaField);
	public void victory(Symbol symbol, CellCoordinates metaCoords);
	public void victory(Symbol symbol);
}
