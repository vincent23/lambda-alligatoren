package de.croggle.game.achievement;

public class AchievementFactory {

	/**
	 * @param args
	 */
	public static Achievement createAchievement(int id) {
		Achievement achievement;
		switch(id) {
			case 0:	achievement = new AlligatorsEatenAchievement();
					break;
			case 1:	achievement = new AlligatorsEatenPerLevelAchievement();
					break;
			case 2: achievement = new AlligatorsPlacedAchievement();
					break;
			case 3: achievement = new AlligatorsPlacedPerLevelAchievement();
					break;
			case 4:	achievement = new HintPerLevelAchievement();
					break;
			case 5: achievement = new LevelAchievement();
					break;
			case 6: achievement = new TimeAchievement();
					break;
			default:	return null;
		}
		achievement.setId(id);
		return achievement;
			
				

	}
}
