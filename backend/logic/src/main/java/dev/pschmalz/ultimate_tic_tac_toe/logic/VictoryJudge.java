package dev.pschmalz.ultimate_tic_tac_toe.logic;

import java.util.Optional;

import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;

public class VictoryJudge {
	private Field field;
	
	public VictoryJudge(Field field) {
		this.field = field;
	}
	
	public Optional<Symbol> getVictoriousSymbol() {
		Optional<Symbol> victoriousSymbol;
		
		victoriousSymbol = getSymbolWithVictoryRow();
		if(victoriousSymbol.isPresent())
			return victoriousSymbol;
		
		victoriousSymbol = getSymbolWithVictoryColumn();
		if(victoriousSymbol.isPresent())
			return victoriousSymbol;
		
		victoriousSymbol = getSymbolWithVictoryDiagonal();
		if(victoriousSymbol.isPresent())
			return victoriousSymbol;
		
		return Optional.empty();
	}
	
	private Optional<Symbol> getSymbolWithVictoryRow() {
		for(int y = 0; y < 3; y++)
			if(field.cells[0][y].isPresent()
					&& field.cells[1][y].isPresent()
					&& field.cells[2][y].isPresent()
					&& field.cells[0][y].get() == field.cells[1][y].get() 
					&& field.cells[1][y].get() == field.cells[2][y].get())
				return field.cells[0][y];
		
		return Optional.empty();
	}
	private Optional<Symbol> getSymbolWithVictoryColumn() {
		for(int x = 0; x < 3; x++)
			if(field.cells[x][0].isPresent()
					&& field.cells[x][1].isPresent()
					&& field.cells[x][2].isPresent()
					&& field.cells[x][0].get() == field.cells[x][1].get() 
					&& field.cells[x][1].get() == field.cells[x][2].get())
				return field.cells[x][0];
		
		return Optional.empty();
	}
	private Optional<Symbol> getSymbolWithVictoryDiagonal() {
		var center = field.cells[1][1];
		
		if(center.isEmpty())
			return Optional.empty();
		
		if(field.cells[0][0].isPresent() && field.cells[2][2].isPresent() 
				&& field.cells[0][0].get() == center.get() && field.cells[2][2].get() == center.get()
				|| 
				field.cells[2][0].isPresent() && field.cells[0][2].isPresent() 
				&& field.cells[2][0].get() == center.get() && field.cells[0][2].get() == center.get())
			return center;
		
		return Optional.empty();
			
	}
}
