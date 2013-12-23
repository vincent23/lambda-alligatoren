package de.croggle.game.achievement;

/**
 * Achievements which are awarded for reaching certain, specified amounts of
 * eaten Alligators.
 */
public class AlligatorsEatenAchievement extends Achievement {

	/**
	 * Calculates the index of the stage the achievement has reached according
	 * to the statistics of a completed level.
	 *
	 * @return the updated index
     *
	 */
	@Override
	public int requirementsMet() {
		return 0;
	}

}