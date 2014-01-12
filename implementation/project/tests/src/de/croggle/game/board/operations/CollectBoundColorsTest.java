package de.croggle.game.board.operations;

import junit.framework.Assert;
import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;

public class CollectBoundColorsTest extends CollectColorsTest {

	public void testSimpleBoundColor() {
		final Board board = new Board();
		final Color color = new Color(1);
		final ColoredAlligator coloredAlligator = new ColoredAlligator(false,
				false, color, false);
		final Egg egg = new Egg(false, false, color, false);
		board.addChild(coloredAlligator);
		coloredAlligator.addChild(egg);
		final Color[] boundColors = CollectBoundColors.collect(board);
		Assert.assertEquals(1, boundColors.length);
		Assert.assertEquals(color, boundColors[0]);
	}

	public void testRootBoundColor() {
		final Color color = new Color(1);
		final ColoredAlligator coloredAlligator = new ColoredAlligator(false,
				false, color, false);
		final Egg egg = new Egg(false, false, color, false);
		coloredAlligator.addChild(egg);
		final Color[] boundColors = CollectBoundColors
				.collect(coloredAlligator);
		Assert.assertEquals(1, boundColors.length);
		Assert.assertEquals(color, boundColors[0]);
	}

	public void testMultipleBoundColors() {
		final Color color1 = new Color(1);
		final Color color2 = new Color(2);
		final Color color3 = new Color(3);
		final Color color4 = new Color(4);
		final AgedAlligator aged = new AgedAlligator(false, false);
		final ColoredAlligator colored1 = new ColoredAlligator(false, false,
				color1, false);
		final ColoredAlligator colored2 = new ColoredAlligator(false, false,
				color2, false);
		final ColoredAlligator colored3 = new ColoredAlligator(false, false,
				color3, false);
		final Egg egg1 = new Egg(false, false, color4, false);
		final Egg egg2 = new Egg(false, false, color4, false);

		aged.addChild(colored1);
		aged.addChild(colored2);
		colored1.addChild(egg1);
		colored2.addChild(colored3);
		colored3.addChild(egg2);

		final Color[] boundColors = CollectBoundColors.collect(aged);
		Assert.assertEquals(3, boundColors.length);
		Assert.assertTrue(arrayContainsColor(boundColors, color1));
		Assert.assertTrue(arrayContainsColor(boundColors, color2));
		Assert.assertTrue(arrayContainsColor(boundColors, color3));
		Assert.assertFalse(arrayContainsColor(boundColors, color4));
	}

	public void testBoundColorsMultipleOccurences() {
		final Color color1 = new Color(1);
		final Color color2 = new Color(2);
		final Color color3 = new Color(3);

		final AgedAlligator aged = new AgedAlligator(false, false);
		final ColoredAlligator colored1 = new ColoredAlligator(false, false,
				color1, false);
		final ColoredAlligator colored2 = new ColoredAlligator(false, false,
				color1, false);
		final ColoredAlligator colored3 = new ColoredAlligator(false, false,
				color1, false);
		final ColoredAlligator colored4 = new ColoredAlligator(false, false,
				color2, false);
		final Egg egg1 = new Egg(false, false, color3, false);
		final Egg egg2 = new Egg(false, false, color1, false);
		final Egg egg3 = new Egg(false, false, color3, false);

		aged.addChild(colored1);
		aged.addChild(colored3);
		aged.addChild(colored4);
		colored1.addChild(colored2);
		colored2.addChild(egg1);
		colored3.addChild(egg2);
		colored4.addChild(egg3);

		final Color[] boundColors = CollectBoundColors.collect(aged);
		Assert.assertEquals(2, boundColors.length);
		Assert.assertTrue(arrayContainsColor(boundColors, color1));
		Assert.assertTrue(arrayContainsColor(boundColors, color2));
		Assert.assertFalse(arrayContainsColor(boundColors, color3));
	}
}
