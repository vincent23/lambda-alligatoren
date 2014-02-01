package de.croggle.game.achievement;

import de.croggle.data.persistence.Statistic;
import static de.croggle.data.LocalizationHelper._;

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
		while (index < (getNumberOfStages() - 1)
				&& alligatorsEaten >= getStage(index + 1)) {
			index++;
		}
		// done: decision against updating index 
		return index;
	}

	@Override
	public void initialize() {
		setIndex(0);
		int[] stages = { 0, 5, 20, 50, 100, 175, 250, 500, 750, 1250, 2500 };
		String[] emblemPathachieved = new String[11];
		String[] emblemPathnotachieved = new String[11];
		String[] description = new String[11];
		for (int i = 1; i < 10; i++) {
			description[i] = stages[i] + " "
					+ _("achievement_alligators_eaten");
		}
		for( int j = 1; j < stages.length; j++) {
			emblemPathachieved[j] = "emblems/alligatorsEaten/" + String.format("%02d", j) + "a";
			emblemPathnotachieved[j] = "emblems/alligatorsEaten/" + String.format("%02d", j) + "n";
		}
		
		description[0] = "initial state, do not show this stage as achievement";
		description[10] = _("achievement_alligators_eaten_final");
		emblemPathachieved[0] = null;
		emblemPathnotachieved[0] = null;
		setDescription(description);
		setStages(stages);
		setEmblemPathachieved(emblemPathachieved);
		setEmblemPathnotachieved(emblemPathnotachieved);

	}

}
