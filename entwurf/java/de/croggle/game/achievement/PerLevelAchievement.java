package de.croggle.game.achievement;

/**
 * Achievement for passing certain, specified goals within a level, e.g. placing
 * more than 10 Alligators within one level or 5 eggs hatched within one level.
 */
public class PerLevelAchievement extends Achievement {

	/**
	 * Calculates the index of the stage the achievement has reached according
	 * to the current statistics.
	 * 
	 * @return the update index
	 */
	@Override
	public int requirementsMet() {
		return 0;
	}

}
