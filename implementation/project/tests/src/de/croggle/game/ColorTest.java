package de.croggle.game;

import junit.framework.TestCase;

public class ColorTest extends TestCase {
	public void testMaxColorsEqualsColorStringLength() {
		assertEquals(Color.MAX_COLORS, Color.getRepresentations().length);
	}
}
