package dev.pschmalz.ultimate_tic_tac_toe.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import dev.pschmalz.ultimate_tic_tac_toe.logic.data.CellCoordinates;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.RuleViolation;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;

public class MetaField {
	Field[][] fields;
	private Validator validator = Validator.instance;
	private Optional<Field> currentField = Optional.empty();
	private MetaVictoryJudge metaVictoryJudge = new MetaVictoryJudge(this);
	
	public MetaField() {
		
		fields = new Field[3][];
		for(int metaX = 0; metaX < 3; metaX++) {
			fields[metaX] = new Field[3];
			for(int metaY = 0; metaY < 3; metaY++)
				fields[metaX][metaY] = new Field();
		}
	}
	
	public RuleViolation putSymbol(Symbol symbol, CellCoordinates metaCoords, CellCoordinates fieldCoords) {
		if(!validator.areCoordinatesValid(metaCoords.getX(), metaCoords.getY()))
			return RuleViolation.INVALID_COORDINATES;
		
		var field = fields[metaCoords.getX()][metaCoords.getY()];
		
		if(currentField.isPresent() && currentField.get() != field)
			return RuleViolation.WRONG_FIELD;
		
		var ruleViolation = 
				field.putSymbol(
						symbol, 
						fieldCoords.getX(), 
						fieldCoords.getY());
		
		if(ruleViolation == RuleViolation.NONE)
			setCurrentField(fieldCoords);
		
		return ruleViolation;
	}
	
	public List<String> toListOfStrings() {
		var list = new ArrayList<String>();
		for(int metaY = 0; metaY < 3; metaY++)
			for(int metaX = 0; metaX < 3; metaX++)
				list.addAll(fields[metaX][metaY].toListOfStrings());
		
		return list;
	}
	
	private void setCurrentField(CellCoordinates lastFieldCoords) {
		var potentialCurrentField = fields[lastFieldCoords.getX()][lastFieldCoords.getY()];
		
		if(potentialCurrentField.isVictoryDecided())
			currentField = Optional.empty();
		else
			currentField = Optional.of(potentialCurrentField);
	}
	
	public Optional<Field> getCurrentField() {
		return currentField;
	}
	
	public Optional<CellCoordinates> getCurrentFieldCoords() {
		if(currentField.isEmpty())
			return Optional.empty();
		
		for(int x = 0; x < 3; x++)
			for(int y = 0; y < 3; y++)
				if(fields[x][y] == currentField.get())
					return Optional.of(new CellCoordinates(x, y));
		
		return Optional.empty();
	}
	
	public Optional<Symbol> getVictoriousSymbol() {
		return metaVictoryJudge.getVictoriousSymbol();
	}
	
	public Field getField(int metaX, int metaY) {
		return fields[metaX][metaY];
	}
}
