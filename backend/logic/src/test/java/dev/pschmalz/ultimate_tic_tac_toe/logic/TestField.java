package dev.pschmalz.ultimate_tic_tac_toe.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.pschmalz.ultimate_tic_tac_toe.logic.data.RuleViolation;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;

public class TestField {
	private Field example_field1;
	
	@BeforeEach
	public void setup() {
		example_field1 = new Field();
		example_field1.putSymbol(Symbol.X, 0, 0);
		example_field1.putSymbol(Symbol.X, 0, 1);
		example_field1.putSymbol(Symbol.O, 2, 0);
		example_field1.putSymbol(Symbol.O, 2, 1);
	}
	
	@Test
	public void check_example_field_state() {
		assertEquals(
				List.of("X","","O",
						"X","","O",
						"","",""), 
				example_field1.toListOfStrings());
	}
	
	@Test
	public void win_with_x() {
		example_field1.putSymbol(Symbol.X, 0, 2);
		var victoriousSymbol = example_field1.getVictoriousSymbol();
		
		if(victoriousSymbol.isEmpty())
			fail();
		
		assertEquals(
				Symbol.X,
				victoriousSymbol.get());
	}
	
	@Test
	public void violation_overwrite_occupied_cell() {
		assertEquals(
				RuleViolation.OCCUPIED_CELL,
				example_field1.putSymbol(Symbol.X, 0, 0));
	}
	
	@Test
	public void violation_coordinates_out_of_bounds() {
		assertEquals(
				RuleViolation.INVALID_COORDINATES,
				example_field1.putSymbol(Symbol.X, 3, 0));
	}
}
