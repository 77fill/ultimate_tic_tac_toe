package dev.pschmalz.ultimate_tic_tac_toe.logic;

import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;

public interface Player {
	public void itsYourTurn();
	public void gameState(MetaField metaField);
	public void setSymbol(Symbol symbol);
	public void violation(MetaField metaField);
}
