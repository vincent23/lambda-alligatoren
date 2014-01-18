package de.croggle.game.board.operations;

import java.util.Map;

import de.croggle.game.Color;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.util.convert.LambdaToAlligator;
import junit.framework.TestCase;

public class CreateHeightMapTest extends TestCase {
	public void testSimple() {
		Board b = new Board();
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		b.addChild(e2);

		Map<BoardObject, Float> map = CreateHeightMap.create(b);
		assertEquals(2.f, map.get(b));
		assertEquals(2.f, map.get(a));
		assertEquals(1.f, map.get(e2));
		assertEquals(1.f, map.get(e1));
	}
	
	public void testCase0() {
		Board b = LambdaToAlligator.convert("(λx.x) ((λy.y) (λz.z))");
		Map<BoardObject, Float> map = CreateHeightMap.create(b, 100, .5f, 2);
		//   100 for aged alligator
		// +   2 padding
		// +  50 y and z abstractions
		// +   1 padding
		// +  25 y/z eggs
		assertEquals(178.f, map.get(b));
	}
}
