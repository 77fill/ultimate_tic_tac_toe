package dev.pschmalz.ultimate_tic_tac_toe.logic;

public class Validator {
	public static Validator instance = new Validator();
	
	private Validator() {}
	
	public boolean isCoordinateValid(int coord) {
		return 0 <= coord && coord <= 2;
	}
	
	public boolean areCoordinatesValid(int...coords) {
		var valid = true;
		for(var coord : coords) 
			if(!isCoordinateValid(coord))
				valid = false;
		
		return valid;
	}
}
