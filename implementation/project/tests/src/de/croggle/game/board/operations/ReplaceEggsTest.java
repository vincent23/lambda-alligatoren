package de.croggle.game.board.operations;

import de.croggle.game.Color;
import de.croggle.game.ColorOverflowException;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import junit.framework.TestCase;

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
}
