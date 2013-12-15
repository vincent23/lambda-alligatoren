package de.croggle.model;

/**
 * A reward given to the player for completing a special feat, e.g. playing for a certain amount of time or beaten a certain amount of levels.
 */
public interface Achievement {
	public String getDescription();
	public String getEmblemPath();
	public int getId();
	
	// private int unlockValue; ???

}
