package de.croggle.game.achievement;

import de.croggle.data.persistence.Statistic;

/**
 * A reward given to the player for completing a special feat, e.g. playing for
 * a certain amount of time or beating a certain amount of levels.
 */
public abstract class Achievement {
	// Array because the different stages have different descriptions!
	private String[] description;
	private String[] emblemPathAchieved;
	private String[] emblemPathNotAchieved;
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
		return description[index];
	}

	/**
	 * Returns the path to the picture that represents the achievement in its achieved state.
	 * 
	 * @param index
	 *            the stage index for which the emblem path should be returned
	 * @return the path leading to the emblem of the achievement
	 */
	public String getEmblemPathAchieved(int index) {
		return emblemPathAchieved[index];
	}
	
	/**
	 * Returns the path to the picture that represents the achievement in its unachieved state.
	 * 
	 * @param index
	 *            the stage index for which the emblem path should be returned
	 * @return the path leading to the emblem of the achievement
	 */
	public String getEmblemPathNotAchieved(int index) {
		return emblemPathNotAchieved[index];
	}

	/**
	 * Returns the id of the achievement that identifies it.
	 * 
	 * @return the achievement id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the requirement for the given stage.
	 * 
	 * @param index
	 *            the stage index for which the requirement should be returned
	 * @return the requirement
	 */
	public int getStage(int index) {
		return stages[index];
	}

	/**
	 * Returns the index of the Achievement.
	 * 
	 * @return
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the achievement's index.
	 * 
	 * @param index
	 *            the new index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Calculates the index of the stage the achievement has reached, according
	 * to the current statistics.
	 * 
	 * @param statistic
	 *            the profiles statistic after the level was completed
	 * @param statisticDelta
	 *            the change that occured during the level
	 * 
	 * @return the updated index
	 */
	public abstract int requirementsMet(Statistic statistic,
			Statistic statisticDelta);

	/**
	 * Initializes the achievement.
	 */
	public abstract void initialize();

	public void setDescription(String[] description) {
		this.description = description;
	}

	public void setEmblemPathachieved(String[] emblemPath) {
		this.emblemPathAchieved = emblemPath;
	}

	public void setEmblemPathnotachieved(String[] emblemPath) {
		this.emblemPathNotAchieved = emblemPath;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void setStages(int[] stages) {
		this.stages = stages;
	}

	public int getNumberOfStages() {
		return stages.length;
	}

}
