package dev.pschmalz.ultimate_tic_tac_toe.logic.data;

public enum Symbol {
	O, X;
	
	public static Symbol other(Symbol symbol) {
		if(symbol == O)
			return X;
		else
			return O;
	}
	
	public String toString() {
		return name();
	}
}
