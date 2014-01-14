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
		while (index < (getNumberOfStages() - 1) && newLevelsComplete >= getStage(index + 1)) {
			index++;
		}
		// TODO: decide whether I have to correct the index of the Achievement
		// here.
		return index;
	}

	@Override
	public void initialize() {
		setIndex(0);
		int[] stages = {0, 1, 2, 4, 6, 8, 10, 12 };
		String[] emblemPath = new String[1]; // TODO: Path zu den Emblems
												// reintun.
		String[] description = new String[8];
		for (int i = 1; i < 8; i++) {
			description[i] = stages[i]  + _("achievement_level_completed");
			
		}
		description[0] = "initial state, do not show this stage as achievement";
		setDescription(description);
		setStages(stages);
		setEmblemPath(emblemPath);
		setId(-1); // TODO: IDs für alle achievements festlegen.
		
		//TODO: Achievment für alle level im spiel gelöst.
		
		
	}

}
