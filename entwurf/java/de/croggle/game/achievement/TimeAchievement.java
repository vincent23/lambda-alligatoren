package de.croggle.game.achievement;

/**
 * Achievements which are awarded for reaching certain, specified amounts of
 * time spent playing the game.
 */
public class TimeAchievement extends Achievement {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int requirementsMet() {
		return 0;
	}

}
