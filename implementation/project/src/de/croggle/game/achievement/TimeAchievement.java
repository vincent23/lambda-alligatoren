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
		int index = getIndex();
		int newTime =  0; //TODO: were do i get this?
		while(newTime >= getStage(index)) {
			index++;
		}
		
		return index;
	}
	
}
