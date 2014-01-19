package de.croggle.game.achievement;

import de.croggle.data.persistence.Statistic;
import static de.croggle.data.LocalizationHelper._;

/**
 * Achievements which are awarded for reaching certain, specified amounts of
 * eaten alligators.
 */
public class AlligatorsEatenAchievement extends Achievement {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int requirementsMet(Statistic statistic, Statistic statisticDelta) {
		int index = getIndex();
		int alligatorsEaten = statistic.getAlligatorsEaten();
		while (index < (getNumberOfStages() - 1) && alligatorsEaten >= getStage(index + 1)) {
			index++;
		}
		// TODO: decide whether I have to correct the index of the Achievement
		// here.: I did: I ll do it
		return index;
	}

	@Override
	public void initialize() {
		setIndex(0);
		int[] stages = { 0, 5, 20, 50, 100, 175, 250,
				500, 750, 1250, 2500 };
		String[] emblemPath = new String[11];
		String[] description = new String[11];
		for (int i = 1; i < 10; i++) {
			description[i] = stages[i]  + _("achievement_alligators_eaten");
			emblemPath[i] = "emblems/alligatorsEaten/0" + i;
		}
		description[0] = "initial state, do not show this stage as achievement";
		description[10] = _("achievement_alligators_eaten_final");
		emblemPath[0] = null;
		emblemPath[10] = "emblems/alligatorsEaten/10";
		setDescription(description);
		setStages(stages);
		setEmblemPath(emblemPath);
		
	}

}
