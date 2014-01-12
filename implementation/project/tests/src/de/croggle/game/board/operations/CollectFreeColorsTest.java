package de.croggle.game.board.operations;

import junit.framework.Assert;
import de.croggle.game.Color;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;

public class CollectFreeColorsTest extends CollectColorsTest {
	public void testSimpleFreeColor() {
		final Board board = new Board();
		final Color color = new Color(1);
		final Egg egg = new Egg(false, false, color, false);
		board.addChild(egg);
		final Color[] freeColors = CollectFreeColors.collect(board);
		Assert.assertEquals(1, freeColors.length, 1);
		Assert.assertEquals(color, freeColors[0]);
	}

	public void testSimpleNonFreeColor() {
		final Board board = new Board();
		final Color color = new Color(1);
		final ColoredAlligator colored = new ColoredAlligator(false, false,
				color, false);
		final Egg egg = new Egg(false, false, color, false);
		board.addChild(colored);
		colored.addChild(egg);
		final Color[] freeColors = CollectFreeColors.collect(colored);
		Assert.assertEquals(0, freeColors.length);
	}

	public void testRootFreeColor() {
		final Board board = new Board();
		final Color color = new Color(1);
		final Egg egg = new Egg(false, false, color, false);
		board.addChild(egg);
		final Color[] freeColors = CollectFreeColors.collect(egg);
		Assert.assertEquals(freeColors.length, 1);
		Assert.assertEquals(freeColors[0], color);
	}

	public void testFreeColorWithParent() {
		final Board board = new Board();
		final Color color1 = new Color(1);
		final Color color2 = new Color(2);
		final Egg egg = new Egg(false, false, color1, false);
		final ColoredAlligator colored1 = new ColoredAlligator(false, false,
				color1, false);
		final ColoredAlligator colored2 = new ColoredAlligator(false, false,
				color2, false);
		board.addChild(colored1);
		colored1.addChild(colored2);
		colored2.addChild(egg);
		final Color[] freeColors = CollectFreeColors.collect(colored2);
		Assert.assertEquals(1, freeColors.length);
		Assert.assertTrue(arrayContainsColor(freeColors, color1));
		Assert.assertFalse(arrayContainsColor(freeColors, color2));
	}
}
