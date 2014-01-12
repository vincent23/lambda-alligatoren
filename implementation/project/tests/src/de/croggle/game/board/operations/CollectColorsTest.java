package de.croggle.game.board.operations;

import junit.framework.TestCase;
import de.croggle.game.Color;

public abstract class CollectColorsTest extends TestCase {

	protected boolean arrayContainsColor(Color[] colorArray, Color color) {
		for (Color c : colorArray) {
			if (c.equals(color)) {
				return true;
			}
		}
		return false;
	}
}
