package de.croggle.game.achievement;

import static de.croggle.data.LocalizationHelper._;
import de.croggle.data.persistence.Statistic;

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
		return index;
	}

	@Override
	public void initialize() {
		setIndex(0);
		int[] stages = { 0, 5 * 60, 10 * 60, 20 * 60, 45 * 60, 60 * 60,
				120 * 60, 180 * 60, 300 * 60, 600 * 60, 6000 * 60 };
		String[] emblemPathachieved = new String[11];
		String[] emblemPathnotachieved = new String[11];
		String[] description = new String[11];
		for (int i = 1; i < 10; i++) {
			if (i < 6) {
				description[i] = stages[i] / 60 + " "
						+ _("achievement_minutes_played");
			} else {
				description[i] = stages[i] / 3600 + " "
						+ _("achievement_hours_played");
			}
			emblemPathachieved[i] = "emblems/time/0" + i + "a";
			emblemPathnotachieved[i] = "emblems/time/0" + i + "n";
		}
		for (int j = 1; j < stages.length; j++) {
			emblemPathachieved[j] = "emblems/time/" + String.format("%02d", j)
					+ "a";
			emblemPathnotachieved[j] = "emblems/time/"
					+ String.format("%02d", j) + "n";
			// Log.d("test", "that works");
		}
		description[0] = "initial state, do not show this stage as achievement";
		description[10] = _("achievement_time_final");
		emblemPathachieved[0] = null;
		emblemPathnotachieved[0] = null;
		setDescription(description);
		setStages(stages);
		setEmblemPathachieved(emblemPathachieved);
		setEmblemPathnotachieved(emblemPathnotachieved);

	}

}
