package de.croggle.game.achievement;

import de.croggle.data.persistence.Statistic;

/**
 * Achievements which are awarded for reaching certain, specified amounts of
 * placed alligators.
 */
public class AlligatorsPlacedAchievement extends Achievement {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int requirementsMet(Statistic statistic, Statistic statisticDelta) {
		int index = getIndex();
		int alligatorsPlaced = statistic.getAlligatorsPlaced();
		while ( alligatorsPlaced >= getStage(index)) {
			index++;
		}
		//TODO: decide whether I have to correct the index of the Achievement here.
		return index;
	}

}
