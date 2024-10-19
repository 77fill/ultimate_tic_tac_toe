package dev.pschmalz.ultimate_tic_tac_toe.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import dev.pschmalz.ultimate_tic_tac_toe.logic.data.CellCoordinates;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.RuleViolation;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;

public class MetaField {
	private Field[][] fields;
	private Validator validator = Validator.instance;
	private Optional<Field> currentField = Optional.empty();
	
	public MetaField() {
		
		fields = new Field[3][];
		for(int metaX = 0; metaX < 3; metaX++) {
			fields[metaX] = new Field[3];
			for(int metaY = 0; metaX < 3; metaY++)
				fields[metaX][metaY] = new Field();
		}
	}
	
	public RuleViolation putSymbol(Symbol symbol, CellCoordinates coords) {
		var field = fields[coords.getMetaX()][coords.getMetaY()];
		
		if(currentField.isPresent() && currentField.get() != field)
			return RuleViolation.WRONG_FIELD;
		
		field.putSymbol(symbol, coords.getX(), coords.getY());
		return RuleViolation.NONE;
	}
	
	public List<String> toListOfStrings() {
		var list = new ArrayList<String>();
		for(int metaY = 0; metaY < 3; metaY++)
			for(int metaX = 0; metaX < 3; metaX++)
				list.addAll(fields[metaX][metaY].toListOfStrings());
		
		return list;
	}
}
