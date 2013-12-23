package de.croggle.game.achievement;

/**
 * A reward given to the player for completing a special feat, e.g. playing for
 * a certain amount of time or beating a certain amount of levels.
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
	private int index;

	/**
	 * Returns a description that describes how to reach the achievement.
	 * 
	 * @param index
	 *            the stage which the description should describe
	 * @return the text which is shown in order to describe the achievement
	 */
	public String getDescription(int index) {
		return null;
	}

	/**
	 * Returns the path to the picture that represents the achievement.
	 * 
	 * @param index
	 *            the stage index for which the emblem path should be returned
	 * @return the path leading to the emblem of the achievement
	 */
	public String getEmblemPath(int index) {
		return null;
	}

	/**
	 * Returns the id of the achievement that identifies it.
	 * 
	 * @return the achievement id
	 */
	public int getId() {
		return 0;
	}

	/**
	 * Calculates the index of the stage the achievement has reached, according
	 * to the current statistics.
	 * 
	 * @return the updated index
	 */
	public abstract int requirementsMet();

}
