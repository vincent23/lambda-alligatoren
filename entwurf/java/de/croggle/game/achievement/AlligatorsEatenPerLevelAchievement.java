package de.croggle.game.achievement;

/**
 * An achievements the player gets for having at least a certain amount of
 * alligators eaten in any level.
 * 
 */
public class AlligatorsEatenPerLevelAchievement extends PerLevelAchievement {

	/**
	 * Calculates the index of the stage the achievement has reached according
	 * to the statistics of a completed level.
	 * 
	 * @return the updated index
	 */
	@Override
	public int requirementsMet() {
		return 0;
	}

}
