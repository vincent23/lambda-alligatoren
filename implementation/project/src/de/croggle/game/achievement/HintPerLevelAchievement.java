package de.croggle.game.achievement;

import de.croggle.data.persistence.Statistic;

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
	public int requirementsMet(Statistic statistic, Statistic statisticDelta) {
		int index = getIndex();
		int hintsUsed = statisticDelta.getUsedHints();
		while (hintsUsed <= getStage(index)) { // careful: this time its <
												// instead of >
			index++;
		}
		// TODO: decide whether I have to correct the index of the Achievement
		// here.
		return index;
	}

}
