package de.croggle.game.achievement;

/**
 * An achievement the player gets for having at least a certain amount of
 * alligators eaten in any level.
 * 
 */
public class AlligatorsEatenPerLevelAchievement extends PerLevelAchievement {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int requirementsMet() {
		int index = getIndex();
		int oldVal = 0; //TODO: where do i get this?
		int newVal = 0;
		int diff = newVal - oldVal;
		while (diff >= getStage(index) ) {
			index++;
		}
		
		return index;
		
	}

}
