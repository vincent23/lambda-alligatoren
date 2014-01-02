package de.croggle.util.convert;

import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import junit.framework.TestCase;

public class JsonToAlligatorTest extends TestCase {

	public void testManualSimple() {
		String json = "{ " + "\"families\" : [" + "{" + "\"type\" : \"egg\","
				+ "\"movable\" : true," + "\"removable\" : true,"
				+ "\"color\" : 0," + "\"recolorable\" : true" + "}" + "]}";
		BoardObject bo = JsonToAlligator.convert(json);
		assertTrue(bo.getClass() == Board.class);
		Board b = (Board) bo;
		assertEquals(1, b.getChildCount());
		InternalBoardObject ibo = b.getFirstChild();
		assertTrue(ibo.getClass() == Egg.class);
		Egg e = (Egg) ibo;
		assertTrue(e.isMovable());
		assertTrue(e.isRecolorable());
		assertTrue(e.isRemovable());
		assertEquals(0, e.getColor().getId());
	}

}
