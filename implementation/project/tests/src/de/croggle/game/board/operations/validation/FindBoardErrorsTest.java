package de.croggle.game.board.operations.validation;

import java.util.List;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.operations.validation.BoardError;
import de.croggle.game.board.operations.validation.BoardErrorType;
import de.croggle.game.board.operations.validation.FindBoardErrors;
import junit.framework.TestCase;

public class FindBoardErrorsTest extends TestCase {

	private Board b;

	protected void setUp() {
		b = new Board();
	}

	public void testUncoloredEgg() {
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				false);
		b.addChild(a);
		a.addChild(new Egg(true, true, Color.uncolored(), true));
		List<BoardError> result = FindBoardErrors.find(b,
				new BoardErrorType[] { BoardErrorType.OBJECT_UNCOLORED });
		assertEquals(1, result.size());
		assertEquals(ObjectUncoloredError.class, result.get(0).getClass());
	}

	public void testUncoloredAlligator() {
		b.addChild(new ColoredAlligator(true, true, Color.uncolored(), false));
		List<BoardError> result = FindBoardErrors.find(b,
				new BoardErrorType[] { BoardErrorType.OBJECT_UNCOLORED });
		assertEquals(1, result.size());
		assertEquals(ObjectUncoloredError.class, result.get(0).getClass());
	}

	public void testEmptyAgedAlligator() {
		b.addChild(new AgedAlligator(true, true));
		List<BoardError> result = FindBoardErrors
				.find(b,
						new BoardErrorType[] { BoardErrorType.AGEDALLIGATOR_CHILDLESS });
		assertEquals(1, result.size());
		assertEquals(AgedAlligatorChildlessError.class, result.get(0)
				.getClass());
	}

	public void testEmptyBoard() {
		List<BoardError> result = FindBoardErrors.find(b,
				new BoardErrorType[] { BoardErrorType.EMPTY_BOARD });
		assertEquals(1, result.size());
		assertEquals(EmptyBoardError.class, result.get(0).getClass());
	}

	public void testEmptyColoredAlligator() {
		b.addChild(new ColoredAlligator(true, true, new Color(0), false));
		List<BoardError> result = FindBoardErrors
				.find(b,
						new BoardErrorType[] { BoardErrorType.COLOREDALLIGATOR_CHILDLESS });
		assertEquals(1, result.size());
		assertEquals(ColoredAlligatorChildlessError.class, result.get(0)
				.getClass());
	}

	public void testNoUncolored() {
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				false);
		b.addChild(a);
		a.addChild(new Egg(true, true, new Color(1), true));
		List<BoardError> result = FindBoardErrors.find(b,
				new BoardErrorType[] { BoardErrorType.OBJECT_UNCOLORED });
		assertEquals(0, result.size());
	}

	public void testNoEmptyAgedAlligator() {
		AgedAlligator a = new AgedAlligator(true, true);
		a.addChild(new Egg(true, true, new Color(0), true));
		b.addChild(a);
		List<BoardError> result = FindBoardErrors
				.find(b,
						new BoardErrorType[] { BoardErrorType.AGEDALLIGATOR_CHILDLESS });
		assertEquals(0, result.size());
	}

	public void testNoEmptyBoard() {
		b.addChild(new Egg(true, true, new Color(1), true));
		List<BoardError> result = FindBoardErrors.find(b,
				new BoardErrorType[] { BoardErrorType.EMPTY_BOARD });
		assertEquals(0, result.size());
	}

	public void testNoEmptyColoredAlligator() {
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				true);
		a.addChild(new Egg(true, true, new Color(0), true));
		b.addChild(a);
		List<BoardError> result = FindBoardErrors
				.find(b,
						new BoardErrorType[] { BoardErrorType.COLOREDALLIGATOR_CHILDLESS });
		assertEquals(0, result.size());
	}

	public void testMultipleErrors() {
		b.addChild(new AgedAlligator(true, true)); // AgedAlligatorChildless
		b.addChild(new ColoredAlligator(true, true, new Color(0), true)); // ColoredAlligatorChildless
		b.addChild(new Egg(true, true, Color.uncolored(), true)); // ObjectUncolored

		List<BoardError> result = FindBoardErrors.find(b, BoardErrorType.all());
		assertEquals(3, result.size());
	}
}
