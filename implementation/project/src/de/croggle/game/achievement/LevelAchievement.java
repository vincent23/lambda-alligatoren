package de.croggle.game.achievement;

import de.croggle.data.persistence.Statistic;

/**
 * An achievement that rewards completing a given amount of levels.
 */
public class LevelAchievement extends Achievement {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int requirementsMet(Statistic statistic, Statistic statisticDelta) {
		int index = getIndex();
		int newLevelsComplete = statistic.getLevelsComplete();
		while (newLevelsComplete >= getStage(index)) {
			index++;
		}
		// TODO: decide whether I have to correct the index of the Achievement
		// here.
		return index;
	}

}
