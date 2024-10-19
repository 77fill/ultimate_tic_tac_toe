package dev.pschmalz.ultimate_tic_tac_toe.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.pschmalz.ultimate_tic_tac_toe.logic.data.RuleViolation;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;

public class Field {
	Optional<Symbol>[][] cells;
	private Validator validator = Validator.instance;
	private VictoryJudge victoryJudge = new VictoryJudge(this);
	private boolean victoryDecided = false;
	
	@SuppressWarnings("unchecked")
	public Field() {
		
		cells = new Optional[3][];
		for(int x = 0; x < 3; x++) {
			cells[x] = new Optional[3];
			for(int y = 0; y < 3; y++)
				cells[x][y] = Optional.empty();
		}
	}
	
	public RuleViolation putSymbol(Symbol symbol, int x, int y) {
		if(!validator.areCoordinatesValid(x, y))
			return RuleViolation.INVALID_COORDINATES;
		
		if(cells[x][y].isPresent())
			return RuleViolation.OCCUPIED_CELL;
		
		cells[x][y] = Optional.of(symbol);
		return RuleViolation.NONE;
	}
	
	public List<String> toListOfStrings() {
		var list = new ArrayList<String>();
		for(int y = 0; y < 3; y++)
			for(int x = 0; x < 3; x++) {
				if(cells[x][y].isPresent())
					list.add(cells[x][y].get().toString());
				else
					list.add("");
			}
		return list;
	}
	
	public Optional<Symbol> getVictoriousSymbol() {
		return victoryJudge.getVictoriousSymbol();
	}
	
	public boolean isVictoryDecided() {
		if(victoryDecided)
			return true;
		
		if(victoryJudge.getVictoriousSymbol().isPresent())
			victoryDecided = true;
		
		return victoryDecided;
	}
	
}
