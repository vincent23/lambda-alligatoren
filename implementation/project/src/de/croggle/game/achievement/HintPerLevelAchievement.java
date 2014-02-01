package de.croggle.game.achievement;

import static de.croggle.data.LocalizationHelper._;
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
		while (index < (getNumberOfStages() - 1)
				&& hintsUsed <= getStage(index + 1)) { // careful: this time its
														// <
			// instead of >
			index++;
		}
		
		return index;
	}

	@Override
	public void initialize() {
		setIndex(0);
		int[] stages = { 1, 0 };
		String[] emblemPathachieved = new String[2]; 
		String[] emblemPathnotachieved = new String[2];
		String[] description = new String[2];
		description[0] = "initial state, do not show this stage as achievement";
		description[1] = _("achievement_hints_used_per_level");
		emblemPathachieved[0] = null;
		emblemPathnotachieved[0] = null;
		emblemPathachieved[1] = "emblems/hintsUsedPerLevel/01a";
		emblemPathnotachieved[1] = "emblems/hintsUsedPerLevel/01n";
		setDescription(description);
		setStages(stages);
		setEmblemPathachieved(emblemPathachieved);
		setEmblemPathnotachieved(emblemPathnotachieved);

	}

}
