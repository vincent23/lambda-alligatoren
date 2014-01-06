package de.croggle.game.board.operations;

import java.util.Map;

import de.croggle.game.Color;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import junit.framework.TestCase;

public class CreateDepthMapTest extends TestCase {
	public void testSimple() {
		Board b = new Board();
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		b.addChild(e2);

		Map<BoardObject, Integer> map = CreateDepthMap.create(b);
		assertEquals(0, (int) map.get(b));
		assertEquals(1, (int) map.get(a));
		assertEquals(1, (int) map.get(e2));
		assertEquals(2, (int) map.get(e1));
	}
}
