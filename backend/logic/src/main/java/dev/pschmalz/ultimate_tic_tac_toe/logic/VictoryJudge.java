package dev.pschmalz.ultimate_tic_tac_toe.logic;

import java.util.Optional;

import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;

public class VictoryJudge {
	private Field field;
	
	public VictoryJudge(Field field) {
		this.field = field;
	}
	
	public Optional<Symbol> getSymbolWithVictoryRow() {
		for(int y = 0; y < 3; y++)
			if(field.cells[0][y] == field.cells[1][y] 
					&& field.cells[1][y] == field.cells[2][y]
					&& field.cells[0][y] != null)
				return Optional.of(field.cells[0][y]);
		
		return Optional.empty();
	}
	public Optional<Symbol> getSymbolWithVictoryColumn() {
		for(int x = 0; x < 3; x++)
			if(field.cells[x][0] == field.cells[x][1] 
					&& field.cells[x][1] == field.cells[x][2]
					&& field.cells[x][0] != null)
				return Optional.of(field.cells[x][0]);
		
		return Optional.empty();
	}
	public Optional<Symbol> getSymbolWithVictoryDiagonal() {
		var center = field.cells[1][1];
		
		if(center == null)
			return Optional.empty();
		
		if(field.cells[0][0] == center && field.cells[2][2] == center
				|| field.cells[2][0] == center && field.cells[0][2] == center)
			return Optional.of(center);
		
		return Optional.empty();
			
	}
}
