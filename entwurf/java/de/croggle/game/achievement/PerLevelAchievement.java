package de.croggle.game.achievement;

/**
 * Achievement for passing certain, specified goals within a level, e.g. placing
 * more than 10 Alligators within one level or 5 eggs hatched within one level.
 */
public abstract class PerLevelAchievement extends Achievement {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract int requirementsMet();

}
