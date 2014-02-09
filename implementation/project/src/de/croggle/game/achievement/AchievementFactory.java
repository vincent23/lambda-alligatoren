package de.croggle.game.achievement;

import java.util.ArrayList;
import java.util.List;

public class AchievementFactory {

	private final static int maxId = 6;

	/**
	 * @param args
	 */
	public static Achievement createAchievement(int id) {
		Achievement achievement;
		switch (id) {
		case 0:
			achievement = new AlligatorsEatenAchievement();
			break;
		case 1:
			achievement = new AlligatorsEatenPerLevelAchievement();
			break;
		case 2:
			achievement = new AlligatorsPlacedAchievement();
			break;
		case 3:
			achievement = new AlligatorsPlacedPerLevelAchievement();
			break;
		case 4:
			achievement = new HintPerLevelAchievement();
			break;
		case 5:
			achievement = new LevelAchievement();
			break;
		case 6:
			achievement = new TimeAchievement();
			break;
		default:
			return null;
		}
		achievement.setId(id);
		achievement.initialize();
		return achievement;

	}

	public static List<Achievement> createListOfAchievementTypes() {
		List<Achievement> types = new ArrayList<Achievement>();
		for (int i = 0; i < maxId + 1; i++) {
			types.add(createAchievement(i));
		}
		return types;

	}
}
