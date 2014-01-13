package de.croggle.game.achievement;

import static de.croggle.data.LocalizationHelper._;
import de.croggle.data.persistence.Statistic;

/**
 * An achievement the player gets for having at least a certain amount of
 * alligators placed in any level.
 * 
 */
public class AlligatorsPlacedPerLevelAchievement extends PerLevelAchievement {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int requirementsMet(Statistic statistic, Statistic statisticDelta) {
		int index = getIndex();
		int alligatorsPlacedPerLevel = statisticDelta.getAlligatorsPlaced();
		while (index < (getNumberOfStages() - 1) && alligatorsPlacedPerLevel >= getStage(index)) {
			index++;
		}
		// TODO: decide whether I have to correct the index of the Achievement
		// here.
		return index;

	}

	@Override
	public void initialize() {
		setIndex(0);
		int[] stages = {0, 2, 5, 10, 15, 25};
		String[] emblemPath = new String[1]; // TODO: Path to the Emblems
												// reintun.
		String[] description = new String[6];
		for (int i = 1; i < 5; i++) { //TODO: Fix Localization stuff. 
			description[i] = stages[i] / 60 + _("achievement_alligators_placed_per_level");
		}
		description[0] = "initial state, do not show this stage as achievement";
		description[5] = _("achievement_alligators_placed_per_level_final");
		setDescription(description);
		setStages(stages);
		setEmblemPath(emblemPath);
		setId(-1); // TODO: decide on Ids for the different Achievements. 
		
		
	}

}
