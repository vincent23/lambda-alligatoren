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
		while (index < (getNumberOfStages() - 1) && alligatorsEatenPerLevel >= getStage(index + 1)) {
			index++;
		}
		// TODO: decide whether I have to correct the index of the Achievement
		// here.
	
		return index;

	}

	@Override
	public void initialize() {
		setIndex(0);
		int[] stages = {0, 1, 2, 5, 10, 20};
		String[] emblemPath = new String[1]; // TODO: Path to the Emblems
												// reintun.
		String[] description = new String[6];
		for (int i = 1; i < 5; i++) { //TODO: Fix Localization stuff. 
			description[i] = stages[i] + _("achievement_alligators_eaten_per_level");
		}
		description[0] = "initial state, do not show this stage as achievement";
		description[5] = _("achievement_alligators_eaten_per_level_final");
		setDescription(description);
		setStages(stages);
		setEmblemPath(emblemPath);
		
	}

}
