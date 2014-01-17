package de.croggle.game.achievement;

import de.croggle.data.persistence.Statistic;
import static de.croggle.data.LocalizationHelper._;

/**
 * Achievements which are awarded for reaching certain, specified amounts of
 * time spent playing the game.
 */
public class TimeAchievement extends Achievement {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int requirementsMet(Statistic statistic, Statistic statisticDelta) {
		int index = getIndex();
		int newTime = statistic.getPlaytime();
		while (index < (getNumberOfStages() - 1)
				&& newTime >= getStage(index + 1)) {
			index++;
		}
		// TODO: decide whether I have to correct the index of the Achievement
		// here
		return index;
	}

	@Override
	public void initialize() {
		setIndex(0);
		int[] stages = { 0, 5 * 60, 10 * 60, 20 * 60, 45 * 60, 60 * 60,
				120 * 60, 180 * 60, 300 * 60, 600 * 60, 6000 * 60 };
		String[] emblemPath = new String[1]; // TODO: Path zu den Emblems
												// reintun.
		String[] description = new String[11];
		for (int i = 1; i < 10; i++) {
			if (i < 6) {
				description[i] = stages[i] / 60
						+ _("achievement_minutes_played");
			} else {
				description[i] = stages[i] / 360
						+ _("achievement_hours_played");
			}
		}
		description[0] = "initial state, do not show this stage as achievement";
		description[10] = _("achievement_time_final");
		setDescription(description);
		setStages(stages);
		setEmblemPath(emblemPath);

	}

}
