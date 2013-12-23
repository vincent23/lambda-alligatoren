package de.croggle.game.achievement;

/**
 * An achievement the player gets for using no hints (or a certain number of
 * them) in a level.
 * 
 */
public class HintPerLevelAchievement extends PerLevelAchievement {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int requirementsMet() {
		return 0;
	}

}
