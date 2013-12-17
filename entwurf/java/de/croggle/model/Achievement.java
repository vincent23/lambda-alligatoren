package de.croggle.model;

/**
 * A reward given to the player for completing a special feat, e.g. playing for a certain amount of time or beaten a certain amount of levels.
 */
public abstract class Achievement {
    private String description;
    private String emblemPath;
    private int id;
    /**
     * Array, which describes the different stages of the achievement and which time requirements have to be met in order to fulfill this achievement.
     */
    private int[] stages;
    /**
     * Index, which specifies on which stage this achievement is;
     */
    private int index;
    public String getDescription();
	public String getEmblemPath();
	public int getId();
    public abstract boolean requirementsMet();
	
	// private int unlockValue; ???

}
