package de.croggle.game.achievement;

/**
 * Achievements which are awarded for reaching certain, specified amounts of
 * time spent playing the game.
 */
public class TimeAchievement extends Achievement {

	/**
	 * Calculates the index of the stage the achievement has reached according
	 * to the current statistics.
	 * 
	 * @return the updated index
	 */
	@Override
	public int requirementsMet() {
		return 0;
	}

}