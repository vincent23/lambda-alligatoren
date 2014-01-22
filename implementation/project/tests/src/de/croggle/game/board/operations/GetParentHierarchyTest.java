package de.croggle.game.board.operations;

import java.util.List;

import junit.framework.TestCase;
import de.croggle.game.Color;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Parent;

public class GetParentHierarchyTest extends TestCase {

	public void testSimpleTree() {
		Board b = new Board();
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		b.addChild(e2);

		List<Parent> l1 = GetParentHierarchy.get(e1);
		assertEquals(2, l1.size());
		assertTrue(l1.get(1).getClass() == Board.class);
		assertTrue(l1.get(0).getClass() == ColoredAlligator.class);

		List<Parent> l2 = GetParentHierarchy.get(e2);
		assertEquals(1, l2.size());
		assertTrue(l2.get(0).getClass() == Board.class);
	}

}
