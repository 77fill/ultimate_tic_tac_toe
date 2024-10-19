package dev.pschmalz.ultimate_tic_tac_toe.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.pschmalz.ultimate_tic_tac_toe.logic.data.CellCoordinates;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;

public class MetaField {
	private Field[][] fields;
	private Validator validator = Validator.instance;
	
	public MetaField() {
		
		fields = new Field[3][];
		for(int metaX = 0; metaX < 3; metaX++) {
			fields[metaX] = new Field[3];
			for(int metaY = 0; metaX < 3; metaY++)
				fields[metaX][metaY] = new Field();
		}
	}
	
	public void putSymbol(Symbol symbol, CellCoordinates coords) {
		var field = fields[coords.getMetaX()][coords.getMetaY()];
		field.putSymbol(symbol, coords.getX(), coords.getY());
	}
	
	public List<String> getListOfStrings() {
		var list = new ArrayList<String>();
		for(int metaY = 0; metaY < 3; metaY++)
			for(int metaX = 0; metaX < 3; metaX++)
				list.addAll(fields[metaX][metaY].getListOfStrings());
		
		return list;
	}
}
