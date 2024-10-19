package dev.pschmalz.ultimate_tic_tac_toe.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.pschmalz.ultimate_tic_tac_toe.logic.data.RuleViolation;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;

public class Field {
	Symbol[][] cells;
	private Validator validator = Validator.instance;
	private VictoryJudge victoryJudge = new VictoryJudge(this);
	
	public Field() {
		
		cells = new Symbol[3][];
		for(int x = 0; x < 3; x++) {
			cells[x] = new Symbol[3];
			for(int y = 0; y < 3; y++)
				cells[x][y] = null;
		}
	}
	
	public RuleViolation putSymbol(Symbol symbol, int x, int y) {
		if(!validator.areCoordinatesValid(x, y))
			return RuleViolation.INVALID_COORDINATES;
		
		if(cells[x][y] != null)
			return RuleViolation.OCCUPIED_CELL;
		
		cells[x][y] = symbol;
		return RuleViolation.NONE;
	}
	
	public List<String> getListOfStrings() {
		var list = new ArrayList<String>();
		for(int y = 0; y < 3; y++)
			for(int x = 0; x < 3; x++) {
				var cell = "";
				if(cells[x][y] != null)
					cell = cells[x][y].toString();
				
				list.add(cell);
			}
		return list;
	}
	
	public Optional<Symbol> getVictoriousSymbol() {
		Optional<Symbol> victoriousSymbol;
		var judge = victoryJudge;
		
		victoriousSymbol = judge.getSymbolWithVictoryRow();
		if(victoriousSymbol.isPresent())
			return victoriousSymbol;
		
		victoriousSymbol = judge.getSymbolWithVictoryColumn();
		if(victoriousSymbol.isPresent())
			return victoriousSymbol;
		
		victoriousSymbol = judge.getSymbolWithVictoryDiagonal();
		if(victoriousSymbol.isPresent())
			return victoriousSymbol;
		
		return Optional.empty();
	}
	
}
