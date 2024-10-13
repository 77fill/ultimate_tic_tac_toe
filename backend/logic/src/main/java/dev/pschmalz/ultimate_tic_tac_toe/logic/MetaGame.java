package dev.pschmalz.ultimate_tic_tac_toe.logic;

import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;

public class MetaGame {
	public void putSymbol(Symbol symbol, RegularCellCoordinates coords) {
		System.out.println("Symbol: "+symbol+"\nCoords: "+coords.toString());
	}
}
