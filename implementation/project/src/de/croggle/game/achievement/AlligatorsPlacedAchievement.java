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
		while (index < (getNumberOfStages() - 1)
				&& alligatorsPlaced >= getStage(index + 1)) {
			index++;
		}
		// TODO: decide whether I have to correct the index of the Achievement
		// here.
		return index;
	}

	@Override
	public void initialize() {
		setIndex(0);
		int[] stages = { 0, 10, 25, 50, 100, 200, 300, 500, 750, 1000, 2000 };
		String[] emblemPathachieved = new String[11]; // TODO: Path zu den Emblems
												// reintun.
		String[] emblemPathnotachieved = new String[11];
		String[] description = new String[11];
		for (int i = 1; i < 10; i++) {
			description[i] = stages[i] + " "
					+ _("achievement_alligators_placed");
			emblemPathachieved[i] = "emblems/alligatorsPlaced/0" + i + "a";
			emblemPathnotachieved[i] = "emblems/alligatorsPlaced/0" + i + "n";
		}
		description[0] = "initial state, do not show this stage as achievement";
		description[10] = _("achievement_alligators_placed_final");
		emblemPathachieved[0] = null;
		emblemPathnotachieved[0] = null;
		emblemPathachieved[10] = "emblems/alligatorsPlaced/10a";
		emblemPathnotachieved[10] = "emblems/alligatorsPlaced/10n";
		setDescription(description);
		setStages(stages);
		setEmblemPathachieved(emblemPathachieved);
		setEmblemPathnotachieved(emblemPathnotachieved);

	}

}
