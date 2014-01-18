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

	public void testSimpleRecolorFree() {
		// (\x . (\y . x)) y -> \z . y
		final Color colorX = new Color(0);
		final Color colorY = new Color(1);
		final Board board = new Board();
		final ColoredAlligator lambdaY = new ColoredAlligator(false, false,
				colorY, false);
		final Egg x = new Egg(false, false, colorX, false);
		final Egg y = new Egg(false, false, colorY, false);

		board.addChild(lambdaY);
		lambdaY.addChild(x);

		try {
			ReplaceEggs.replace(lambdaY, colorX, y, new ColorController());
		} catch (ColorOverflowException e) {
			fail();
		}

		assertEquals(1, lambdaY.getChildCount());
		assertTrue(y.match(lambdaY.getFirstChild()));
		assertFalse(lambdaY.getColor().equals(colorY));
	}

	public void testSimpleRecolorBound() {
		// this is not necessary in lambda calculus, but required by the game
		// (\x . (\y . x)) (\y . y) -> \y . (\z . z)
		final Color colorX = new Color(0);
		final Color colorY = new Color(1);
		final Board board = new Board();
		final ColoredAlligator lambdaY1 = new ColoredAlligator(false, false,
				colorY, false);
		final ColoredAlligator lambdaY2 = new ColoredAlligator(false, false,
				colorY, false);
		final Egg x = new Egg(false, false, colorX, false);
		final Egg y = new Egg(false, false, colorY, false);

		board.addChild(lambdaY1);
		lambdaY1.addChild(x);
		lambdaY2.addChild(y);

		try {
			ReplaceEggs.replace(lambdaY1, colorX, lambdaY2,
					new ColorController());
		} catch (ColorOverflowException e) {
			fail();
		}

		assertEquals(colorY, lambdaY1.getColor());
		assertEquals(1, lambdaY1.getChildCount());
		assertTrue(lambdaY1.getFirstChild() instanceof ColoredAlligator);
		final ColoredAlligator lambdaZ = (ColoredAlligator) lambdaY1
				.getFirstChild();
		assertEquals(1, lambdaZ.getChildCount());
		assertTrue(lambdaZ.getFirstChild() instanceof Egg);
		final Egg z = (Egg) lambdaZ.getFirstChild();
		assertEquals(z.getColor(), lambdaZ.getColor());
		assertFalse(z.getColor().equals(lambdaY1.getColor()));
	}
}
