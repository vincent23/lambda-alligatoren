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
	private int index;

	/**
	 * Returns a description that describes how to reach the achievement.
	 * @param index the stage which the description should describe 
     	 * @return the text which is shown in order to describe the achievement
	 */
	public String getDescription(int index) {
		return null;
	}

	/**
	 * Returns the path to the picture that represents the achievement.
	 * @param index the stage index for which the emblem path shoule be returned
	 * @return the path leading to the achievement's emblem
	 */
	public String getEmblemPath(int index) {
		return null;
	}

	/**
	 * Returns the achievement's id that identifies it.
     	 * @return the achievement id
	 */
	public int getId() {
		return 0;
	}
	
	/**
	 * Returns the index of the stage the achievement has reached.
	 * @return the stage index
	 */
	public int getIndex(){
		return 0;
	}
	
	/**
	 * Set the index of the stage the achievement has reached.
	 * @param index the index to set
	 */
	public void setIndex(int index){
	
	}

	/**
	 * Calculates the index of the stage the achievement has reached according to the currect statistics.
	 * @return the updated index
	 */
	public abstract int requirementsMet();

}
