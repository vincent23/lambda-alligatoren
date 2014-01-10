package de.croggle.game.achievement;

import static de.croggle.data.LocalizationHelper._;
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
		while (index < getNumberOfStages() && newLevelsComplete >= getStage(index)) {
			index++;
		}
		// TODO: decide whether I have to correct the index of the Achievement
		// here.
		return index;
	}

	@Override
	public void initialize() {
		setIndex(0);
		int[] stages = { 1, 2, 4, 6, 8, 10, 12 };
		String[] emblemPath = new String[1]; // TODO: Path zu den Emblems
												// reintun.
		String[] description = new String[7];
		for (int i = 0; i < 9; i++) {
			description[i] = stages[i]  + _("achievement_level_completed");
			
		}
		setDescription(description);
		setStages(stages);
		setEmblemPath(emblemPath);
		setId(-1); // TODO: IDs für alle achievements festlegen.
		
		
	}

}
