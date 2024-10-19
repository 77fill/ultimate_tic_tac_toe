package dev.pschmalz.ultimate_tic_tac_toe.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.pschmalz.ultimate_tic_tac_toe.logic.data.CellCoordinates;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;

public class TestMetaField {
	private MetaField example_meta_field;
	private List<String> upperLeft = List.of(
			"","","",
			"","","X",
			"","","");
	private List<String> upperCenter = List.of(
			"","","",
			"","","",
			"","","");
	private List<String> upperRight = List.of(
			"","","",
			"","","",
			"","","");
	private List<String> centerLeft = List.of(
			"","","",
			"","","",
			"","","");
	private List<String> centerCenter = List.of(
			"","","",
			"","","",
			"","","");
	private List<String> centerRight = List.of(
			"","","",
			"","O","",
			"","","");
	private List<String> lowerLeft = List.of(
			"","","",
			"","","",
			"","","");
	private List<String> lowerCenter = List.of(
			"","","",
			"","","",
			"","","");
	private List<String> lowerRight = List.of(
			"","","",
			"","","",
			"","","");
	
	@BeforeEach
	public void setup() {
		example_meta_field = new MetaField();
		example_meta_field.putSymbol(Symbol.X, new CellCoordinates(0, 0), new CellCoordinates(2, 1));
		example_meta_field.putSymbol(Symbol.O, new CellCoordinates(2, 1), new CellCoordinates(1, 1));
	}
	
	@Test
	public void check_example_meta_field_state() {
		var state = new ArrayList<String>();
		state.addAll(upperLeft);
		state.addAll(upperCenter);
		state.addAll(upperRight);
		
		state.addAll(centerLeft);
		state.addAll(centerCenter);
		state.addAll(centerRight);
		
		state.addAll(lowerLeft);
		state.addAll(lowerCenter);
		state.addAll(lowerRight);
		
		assertEquals(state, example_meta_field.toListOfStrings());
	}
}
