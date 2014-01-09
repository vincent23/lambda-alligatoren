package de.croggle.game.achievement;

import static de.croggle.data.LocalizationHelper._;
import de.croggle.data.persistence.Statistic;

/**
 * Achievements which are awarded for reaching certain, specified amounts of
 * placed alligators.
 */
public class AlligatorsPlacedAchievement extends Achievement {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int requirementsMet(Statistic statistic, Statistic statisticDelta) {
		int index = getIndex();
		int alligatorsPlaced = statistic.getAlligatorsPlaced();
		while (alligatorsPlaced >= getStage(index)) {
			index++;
		}
		// TODO: decide whether I have to correct the index of the Achievement
		// here.
		return index;
	}

	@Override
	public void initialize() {
		int[] stages = { 10, 25, 50,  100, 200, 300, 500, 750, 1000, 2000 };
		String[] emblemPath = new String[1]; // TODO: Path zu den Emblems
												// reintun.
		String[] description = new String[10];
		for (int i = 0; i < 9; i++) {
			description[i] = stages[i] / 60 + _("achievement_alligators_placed");
		}
		description[9] = _("achievement_alligators_placed_final");
		setDescription(description);
		setStages(stages);
		setEmblemPath(emblemPath);
		setId(-1); // TODO: IDs für alle achievements festlegen.
		
	}

}
