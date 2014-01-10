package de.croggle.game.board;

import junit.framework.Assert;
import junit.framework.TestCase;
import de.croggle.game.Color;

public class ColoredBoardObjectTest extends TestCase {

	public void testGetSetColor() {
		final Color color1 = new Color(1);
		final Color color2 = new Color(2);
		final Egg egg = new Egg(false, false, color1, false);
		final ColoredAlligator coloredAlligator = new ColoredAlligator(false,
				false, color1, false);
		Assert.assertTrue(egg.getColor() == color1);
		Assert.assertTrue(coloredAlligator.getColor() == color1);

		egg.setColor(color2);
		Assert.assertTrue(egg.getColor() == color2);
		coloredAlligator.setColor(color2);
		Assert.assertTrue(coloredAlligator.getColor() == color2);
	}

	public void testIsRecolorable() {
		final Color color = new Color(1);
		final Egg nonRecolorableEgg = new Egg(false, false, color, false);
		final ColoredAlligator nonRecolorableColoredAlligator = new ColoredAlligator(
				false, false, color, false);
		final Egg recolorableEgg = new Egg(false, false, color, true);
		final ColoredAlligator recolorableColoredAlligator = new ColoredAlligator(
				false, false, color, true);
		Assert.assertFalse(nonRecolorableEgg.isRecolorable());
		Assert.assertFalse(nonRecolorableColoredAlligator.isRecolorable());
		Assert.assertTrue(recolorableEgg.isRecolorable());
		Assert.assertTrue(recolorableColoredAlligator.isRecolorable());
	}
}
