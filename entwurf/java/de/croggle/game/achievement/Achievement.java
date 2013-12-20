package de.croggle.game.achievement;

/**
 * A reward given to the player for completing a special feat, e.g. playing for
 * a certain amount of time or beaten a certain amount of levels.
 */
public abstract class Achievement {
	private String[] description;
	private String[] emblemPath;
	private int id;
	/**
	 * Array, which describes the different stages of the achievement and which
	 * requirements have to be met in order to fulfill this achievement.
	 */
	private int[] stages;
	/**
	 * Index, which specifies on which stage this achievement is;
	 */
	private int index;

	/**
	 * Getter for description.
     * @return the text which is shown in order to describe the achievement.
	 */
	public String getDescription(int index) {
		return null;
	}

	/**
	 * Getter for the emblem path.
     * @return the path leading to the location of the picture, which is used as the achievement's emblem.
	 */
	public String getEmblemPath(int index) {
		return null;
	}

	/**
	 * Getter for id.
     * @return the achievement id.
	 */
	public int getId() {
		return 0;
	}

	/**
	 * Returns true if the achievement has been achieved, otherwise false.
	 */
	public abstract boolean requirementsMet();

	// private int unlockValue; ???

}
