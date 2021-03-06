package de.croggle.game.level;

import de.croggle.game.ColorController;

/**
 * A special type of level in which the player has to position and color
 * alligators and eggs into a constellation that evaluates into the given goal
 * term.
 */
public class TermEditLevel extends Level {
	ColorController colorController;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasAnimation() {
		return false;
	}
}
