package dev.pschmalz.ultimate_tic_tac_toe.logic;

import java.util.Optional;

import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;

public class MetaVictoryJudge {
	private MetaField metaField;
	
	public MetaVictoryJudge(MetaField metaField) {
		this.metaField = metaField;
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
	
	public Optional<Symbol> getSymbolWithVictoryRow() {
		for(int y = 0; y < 3; y++) {
			var first = metaField.fields[0][y].getVictoriousSymbol();
			var second = metaField.fields[1][y].getVictoriousSymbol();
			var third = metaField.fields[2][y].getVictoriousSymbol();
			
			if(first.isPresent()
					&& second.isPresent()
					&& third.isPresent()
					&& first.get() == second.get() 
					&& second.get() == third.get())
				return first;
		}
		return Optional.empty();
	}
	
	public Optional<Symbol> getSymbolWithVictoryColumn() {
		for(int x = 0; x < 3; x++) {
			var first = metaField.fields[x][0].getVictoriousSymbol();
			var second = metaField.fields[x][1].getVictoriousSymbol();
			var third = metaField.fields[x][2].getVictoriousSymbol();
			
			if(first.isPresent()
					&& second.isPresent()
					&& third.isPresent()
					&& first.get() == second.get() 
					&& second.get() == third.get())
				return first;
		}
		return Optional.empty();
	}

	public Optional<Symbol> getSymbolWithVictoryDiagonal() {
		var center = metaField.fields[1][1].getVictoriousSymbol();
		if(center.isEmpty())
			return Optional.empty();
		
		var upperLeft = metaField.fields[0][0].getVictoriousSymbol();
		var upperRight = metaField.fields[2][0].getVictoriousSymbol();
		var lowerLeft = metaField.fields[0][2].getVictoriousSymbol();
		var lowerRight = metaField.fields[2][2].getVictoriousSymbol();
		
		if(upperLeft.isPresent() && lowerRight.isPresent()
				&& upperLeft.get() == lowerRight.get() && upperLeft.get() == center.get()
			||
			upperRight.isPresent() && lowerLeft.isPresent()
				&& upperRight.get() == lowerLeft.get() && upperRight.get() == center.get())
			
				return center;
		
		return Optional.empty();
	}
}
