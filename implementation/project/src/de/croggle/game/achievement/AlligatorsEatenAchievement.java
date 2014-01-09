package de.croggle.game.achievement;

import de.croggle.data.persistence.Statistic;

/**
 * Achievements which are awarded for reaching certain, specified amounts of
 * eaten alligators.
 */
public class AlligatorsEatenAchievement extends Achievement {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int requirementsMet(Statistic statistic, Statistic statisticDelta) {
		int index = getIndex();
		int alligatorsEaten = statistic.getAlligatorsEaten();
		while ( alligatorsEaten >= getStage(index)) {
			index++;
		}
		//TODO: decide whether I have to correct the index of the Achievement here.
		return index;
	}

}
