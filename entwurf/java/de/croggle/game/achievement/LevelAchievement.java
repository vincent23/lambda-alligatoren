package de.croggle.game.achievement;

/**
 * An achievement that rewards completing given amounts of levels.
 */
public class LevelAchievement extends Achievement {

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