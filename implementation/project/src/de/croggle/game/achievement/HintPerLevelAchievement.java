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
		while (index < getNumberOfStages() && hintsUsed <= getStage(index)) { // careful: this time its <
												// instead of >
			index++;
		}
		// TODO: decide whether I have to correct the index of the Achievement
		// here.
		return index;
	}

	@Override
	public void initialize() {
		setIndex(0);
		int[] stages = {0};
		String[] emblemPath = new String[1]; // TODO: Path to the Emblems
												// reintun.
		String[] description = new String[1];
		description[0] = _("achievement_hints_used_per_level");
	
		setDescription(description);
		setStages(stages);
		setEmblemPath(emblemPath);
		setId(-1); // TODO: decide on Ids for the different Achievements. 
		
		
		
	}

}
