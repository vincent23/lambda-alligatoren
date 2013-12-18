package de.croggle.game.level;

import de.croggle.game.ColorController;

/**
 * A special type of level in which the player has to change the color of the
 * given elements in order for the simulation to reach a certain outcome.
 */
public class ColorEditLevel extends Level {
	ColorController colorController;

	@Override
	public boolean hasAnimation() {
		// TODO Auto-generated method stub
		return false;
	}
}
