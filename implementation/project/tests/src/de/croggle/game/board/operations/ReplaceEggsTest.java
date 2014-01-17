package de.croggle.game.board.operations;

import junit.framework.TestCase;
import de.croggle.game.Color;
import de.croggle.game.ColorController;
import de.croggle.game.ColorOverflowException;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;

public class ReplaceEggsTest extends TestCase {
	public void testSimple() {
		Board b = new Board();
		ColoredAlligator a1 = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a1);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a1.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		b.addChild(e2);

		ColoredAlligator a2 = new ColoredAlligator(true, true, new Color(2),
				true);
		Egg e3 = new Egg(true, true, new Color(2), true);
		a2.addChild(e3);

		try {
			ReplaceEggs.replace(b, e1.getColor(), a2);
		} catch (ColorOverflowException e) {
			fail();
		}
		assertEquals(1, a1.getChildCount());
		assertTrue(a1.getFirstChild().match(a2));
	}

	public void testSimpleRecolor() {
		// (\x . (\y . x)) y -> \z . y
		final Color colorX = new Color(1);
		final Color colorY = new Color(2);
		final ColorController colorController = new ColorController();
		final Board board = new Board();
		final ColoredAlligator lambdaY = new ColoredAlligator(false, false,
				colorY, false);
		final Egg x = new Egg(false, false, colorX, false);
		final Egg y = new Egg(false, false, colorY, false);

		board.addChild(lambdaY);
		lambdaY.addChild(x);

		try {
			ReplaceEggs.replace(lambdaY, colorX, y, colorController);
		} catch (ColorOverflowException e) {
			fail();
		}

		assertEquals(1, lambdaY.getChildCount());
		assertTrue(y.match(lambdaY.getFirstChild()));
		assertFalse(lambdaY.getColor().equals(colorY));
	}
}
