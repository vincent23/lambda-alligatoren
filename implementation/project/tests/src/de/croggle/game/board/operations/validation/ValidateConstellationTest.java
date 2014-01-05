package de.croggle.game.board.operations.validation;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.operations.validation.BoardErrorType;
import de.croggle.game.board.operations.validation.ValidateConstellation;
import junit.framework.TestCase;

public class ValidateConstellationTest extends TestCase {

	private Board b;

	protected void setUp() {
		b = new Board();
	}

	public void testUncoloredEgg() {
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				false);
		b.addChild(a);
		a.addChild(new Egg(true, true, Color.uncolored(), true));
		assertFalse(ValidateConstellation.isValid(b,
				new BoardErrorType[] { BoardErrorType.OBJECT_UNCOLORED }));
	}

	public void testUncoloredAlligator() {
		b.addChild(new ColoredAlligator(true, true, Color.uncolored(), false));
		assertFalse(ValidateConstellation.isValid(b,
				new BoardErrorType[] { BoardErrorType.OBJECT_UNCOLORED }));
	}

	public void testEmptyAgedAlligator() {
		b.addChild(new AgedAlligator(true, true));
		assertFalse(ValidateConstellation
				.isValid(
						b,
						new BoardErrorType[] { BoardErrorType.AGEDALLIGATOR_CHILDLESS }));
	}

	public void testEmptyBoard() {
		assertFalse(ValidateConstellation.isValid(b,
				new BoardErrorType[] { BoardErrorType.EMPTY_BOARD }));
	}

	public void testEmptyColoredAlligator() {
		b.addChild(new ColoredAlligator(true, true, new Color(0), false));
		assertFalse(ValidateConstellation
				.isValid(
						b,
						new BoardErrorType[] { BoardErrorType.COLOREDALLIGATOR_CHILDLESS }));
	}

	public void testNoUncolored() {
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				false);
		b.addChild(a);
		a.addChild(new Egg(true, true, new Color(1), true));
		assertTrue(ValidateConstellation.isValid(b,
				new BoardErrorType[] { BoardErrorType.OBJECT_UNCOLORED }));
	}

	public void testNoEmptyAgedAlligator() {
		AgedAlligator a = new AgedAlligator(true, true);
		a.addChild(new Egg(true, true, new Color(0), true));
		b.addChild(a);
		assertTrue(ValidateConstellation
				.isValid(
						b,
						new BoardErrorType[] { BoardErrorType.AGEDALLIGATOR_CHILDLESS }));
	}

	public void testNoEmptyBoard() {
		b.addChild(new Egg(true, true, new Color(1), true));
		assertTrue(ValidateConstellation.isValid(b,
				new BoardErrorType[] { BoardErrorType.EMPTY_BOARD }));
	}

	public void testNoEmptyColoredAlligator() {
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				true);
		a.addChild(new Egg(true, true, new Color(0), true));
		b.addChild(a);
		assertTrue(ValidateConstellation
				.isValid(
						b,
						new BoardErrorType[] { BoardErrorType.COLOREDALLIGATOR_CHILDLESS }));
	}
}
