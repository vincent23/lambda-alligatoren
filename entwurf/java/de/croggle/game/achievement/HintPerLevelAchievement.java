package de.croggle.game.achievement;

/**
 * An achievement the player gets for using no hints (or a certain number) in a
 * level.
 * 
 */
public class HintPerLevelAchievement extends PerLevelAchievement {

	/**
	 * Calculates the index of the stage the achievement has reached according
	 * to the statistics of a completed level.
	 * 
	 * @return the update index
	 */
	@Override
	public int requirementsMet() {
		return 0;
	}

}
