package de.croggle.game.achievement;

import de.croggle.data.persistence.Statistic;

/**
 * An achievement the player gets for having at least a certain amount of
 * alligators eaten in any level.
 * 
 */
public class AlligatorsEatenPerLevelAchievement extends PerLevelAchievement {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int requirementsMet(Statistic statistic, Statistic statisticDelta) {
		int index = getIndex();
		int alligatorsEatenPerLevel = statisticDelta.getAlligatorsEaten();
		while (alligatorsEatenPerLevel >= getStage(index)) {
			index++;
		}
		// TODO: decide whether I have to correct the index of the Achievement
		// here.
		return index;

	}

	@Override
	public void initialize() {
		int[] stages = { 1, 2, 5, 10, 20};
		String[] emblemPath = new String[1]; // TODO: Path to the Emblems
												// reintun.
		String[] description = new String[5];
		for (int i = 0; i < 9; i++) { //TODO: Fix Localization stuff. 
			description[i] = "Herzlichen Glückwunsch! Du hast jetzt "
						+ stages[i] / 60 + " Minuten Spielzeit erreicht.";
		}
		description[9] = "Herzlichen Glückwunsch! Du hast nun alle zeitbasierten Errungenschaften geschafft.";
		setDescription(description);
		setStages(stages);
		setEmblemPath(emblemPath);
		setId(-1); // TODO: decide on Ids for the different Achievements. 
		
	}

}
