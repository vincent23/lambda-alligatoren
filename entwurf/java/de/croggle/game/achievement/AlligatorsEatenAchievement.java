package de.croggle.game.achievement;

/**
 * Achievements which are awarded for reaching certain, specified amounts of
 * eaten Alligators.
 */
public class AlligatorsEatenAchievement extends Achievement {

	/**
	 * Returns true if the achievement with the given index has been achieved,
	 * otherwise false.
	 * 
	 * @param index
	 *            specifies which stage out of this type of achievement should
	 *            be checked.
	 */
	@Override
	public int requirementsMet() {
		return 0;
	}

}