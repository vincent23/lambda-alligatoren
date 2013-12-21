package de.croggle.game.achievement;

/**
 * Achievement for passing certain, specified goals within a level, e.g. placing
 * more than 10 Alligators within one level or 5 eggs hatched within one level.
 */
public abstract class PerLevelAchievement extends Achievement {

	/**
	 * Calculates the index of the stage the achievement has reached according
	 * to the statistics of a completed level.
	 * 
	 * @return the update index
	 */
	@Override
	public abstract int requirementsMet();

}
