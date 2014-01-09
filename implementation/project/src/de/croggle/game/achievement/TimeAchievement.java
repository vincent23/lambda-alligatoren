package de.croggle.game.achievement;

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
		while (newTime >= getStage(index)) {
			index++;
		}
		// TODO: decide whether I have to correct the index of the Achievement
		// here.
		return index;
	}

	@Override
	public void initialize() {
		int[] stages = { 5 * 60, 10 * 60, 20 * 60, 45 * 60, 60 * 60, 120 * 60,
				180 * 60, 300 * 60, 600 * 60, 1000 * 60 };
		String[] emblemPath = new String[1]; // TODO: Path zu den Emblems
												// reintun.
		String[] description = new String[10];
		for (int i = 0; i < 9; i++) {
			if (i < 4) {
				description[i] = "Herzlichen Glückwunsch! Du hast jetzt "
						+ stages[i] / 60 + " Minuten Spielzeit erreicht.";
			} else {
				description[i] = "Herzlichen Glückwunsch! Du hast jetzt "
						+ stages[i] / 360 + " Stunden Spielzeit erreicht.";
			}
		}
		description[9] = "Herzlichen Glückwunsch! Du hast nun alle zeitbasierten Errungenschaften geschafft.";
		setDescription(description);
		setStages(stages);
		setEmblemPath(emblemPath);
		setId(-1); // TODO: IDs für alle achievements festlegen.

	}

}
