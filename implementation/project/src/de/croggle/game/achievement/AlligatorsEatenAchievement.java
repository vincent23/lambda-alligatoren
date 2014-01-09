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
		while (alligatorsEaten >= getStage(index)) {
			index++;
		}
		// TODO: decide whether I have to correct the index of the Achievement
		// here.
		return index;
	}

	@Override
	public void initialize() {
		int[] stages = { 5, 20, 50, 100, 175, 250,
				500, 750, 1250, 2500 };
		String[] emblemPath = new String[1]; // TODO: Path zu den Emblems
												// reintun.
		String[] description = new String[10];
		for (int i = 0; i < 9; i++) {
			description[i] = stages[i]  + _("achievement_alligators_eaten");
			
		}
		description[9] = _("achievement_alligators_eaten_final");
		setDescription(description);
		setStages(stages);
		setEmblemPath(emblemPath);
		setId(-1); // TODO: IDs für alle achievements festlegen.
		
	}

}
