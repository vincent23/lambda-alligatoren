package de.croggle.game.achievement;

import de.croggle.data.persistence.Statistic;
import static de.croggle.data.LocalizationHelper._;

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
	public int requirementsMet(Statistic statistic, Statistic statisticDelta) {
		int index = getIndex();
		int alligatorsEatenPerLevel = statisticDelta.getAlligatorsEaten();
		while (index < (getNumberOfStages() - 1)
				&& alligatorsEatenPerLevel >= getStage(index + 1)) {
			index++;
		}

		return index;

	}

	@Override
	public void initialize() {
		setIndex(0);
		int[] stages = { 0, 1, 2, 5, 10, 20 };
		String[] emblemPathachieved = new String[6];
		String[] emblemPathnotachieved = new String[6];
		String[] description = new String[6];
		for (int i = 1; i < 5; i++) { 
			description[i] = stages[i] + " "
					+ _("achievement_alligators_eaten_per_level");
		}
		for( int j = 1; j < stages.length; j++) {
			emblemPathachieved[j] = "emblems/alligatorsEatenPerLevel/" + String.format("%02d", j) + "a";
			emblemPathnotachieved[j] = "emblems/alligatorsEatenPerLevel/" + String.format("%02d", j) + "n";
		}
		description[0] = "initial state, do not show this stage as achievement";
		description[5] = _("achievement_alligators_eaten_per_level_final");
		emblemPathachieved[0] = null;
		emblemPathnotachieved[0] = null;
		setDescription(description);
		setStages(stages);
		setEmblemPathachieved(emblemPathachieved);
		setEmblemPathnotachieved(emblemPathnotachieved);

	}

}
