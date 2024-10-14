package dev.pschmalz.ultimate_tic_tac_toe.logic;

import java.util.Arrays;
import java.util.List;

import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;

public class MetaGame {
	private Symbol[] raw = new Symbol[81];
	
	public MetaGame() {
		for(int i = 0; i < 81; i++) {
			raw[i] = null;
		}
	}
	
	public void putSymbol(Symbol symbol, RegularCellCoordinates coords) {
		raw[coords.getX() + coords.getY()*3 + coords.getMetaX()*9 + coords.getMetaY()*27] = symbol;
	}
	
	public List<String> getListOfStrings() {
		return Arrays.asList(raw).stream().map(s -> s==null?"":s.toString()).toList();
	}
}
