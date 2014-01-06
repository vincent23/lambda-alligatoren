package de.croggle.game.board.operations;

import java.util.Map;

import de.croggle.game.Color;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import junit.framework.TestCase;

public class CreateWidthMapTest extends TestCase {
	public void testSimple() {
		Board b = new Board();
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		b.addChild(e2);

		Map<BoardObject, Double> map = CreateWidthMap.create(b);
		assertEquals(2.0, map.get(b));
		assertEquals(1.0, map.get(a));
		assertEquals(1.0, map.get(e1));
		assertEquals(1.0, map.get(e2));
	}
}