package de.croggle.util.convert;

import de.croggle.game.Color;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import junit.framework.TestCase;

public class AlligatorToLambdaTest extends TestCase {

	public void testSimpleTerm() {
		Board b = new Board();
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		b.addChild(e2);

		assertEquals("(λx.x) y", AlligatorToLambda.convert(b));
	}
}
