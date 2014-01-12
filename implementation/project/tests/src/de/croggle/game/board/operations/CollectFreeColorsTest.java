package de.croggle.game.board.operations;

import junit.framework.Assert;
import junit.framework.TestCase;
import de.croggle.game.Color;
import de.croggle.game.board.Board;
import de.croggle.game.board.Egg;

public class CollectFreeColorsTest extends TestCase {
	public void testSimpleFreeColor() {
		final Board board = new Board();
		final Color color = new Color(1);
		final Egg egg = new Egg(false, false, color, false);
		board.addChild(egg);
		final Color[] freeColors = CollectFreeColors.collect(board);
		Assert.assertEquals(freeColors.length, 1);
		Assert.assertEquals(freeColors[0], color);
	}

	public void testRootFreeColor() {
		final Color color = new Color(1);
		final Egg egg = new Egg(false, false, color, false);
		final Color[] freeColors = CollectFreeColors.collect(egg);
		Assert.assertEquals(freeColors.length, 1);
		Assert.assertEquals(freeColors[0], color);
	}
}
