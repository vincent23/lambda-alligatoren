package de.croggle.data.persistence.manager;

import java.util.List;

import android.content.Context;
import de.croggle.game.achievement.Achievement;

/**
 * A concrete table manager which is responsible for managing the SQLite table
 * that stores the unlocked achievements of the different profiles.
 * 
 * @navassoc 1 - * de.croggle.game.achievement.Achievement
 */
public class AchievementManager extends TableManager {

	/**
	 * Name of the column that stores the profile names. Those names are used as
	 * the primary key.
	 */
	static final String KEY_PROFILE_NAME = "profileName";

	/**
	 * Name of the column that stores the achievement IDs.
	 */
	static final String KEY_ACHIEVEMENT_ID = "achievementID";

	/**
	 * Name of the column that stores the achievement states.
	 */
	static final String KEY_ACHIEVEMENT_State = "achievementState";

	/**
	 * The name of the table.
	 */
	static final String TABLE_NAME = "AchievementTable";

	/**
	 * The string used for creating the achievement table via a sql query.
	 */
	static final String CREATE_TABLE = "null";

	/**
	 * Creates a new AchievementManager used for managing the achievement table.
	 * 
	 * @param context
	 *            the context used for accessing the database
	 */
	AchievementManager(Context context) {
		super(context);

	}

	/**
	 * Adds a new unlocked achievement to the table.
	 * 
	 * @param profileName
	 *            the name of the profile to which the unlocked achievement
	 *            belongs
	 * @param achievement
	 *            contains the values to be stored in the table
	 */
	void addUnlockedAchievement(String profileName, Achievement achievement) {

	}

	/**
	 * Returns all achievements stored in the table that were unlocked by the
	 * user with the given profile name.
	 * 
	 * @param profileName
	 *            the name of the user whose unlocked achievements are searched
	 *            for
	 * @return a list of all achievements unlocked by the user
	 */
	List<Achievement> getUnlockedAchievements(String profileName) {
		return null;
	}

	/**
	 * Deletes all achievements that were unlocked by the user with the given
	 * profile name from the table.
	 * 
	 * @param profileName
	 *            the name of the profile whose unlocked achievements are
	 *            deleted
	 */
	void deleteUnlockedAchievements(String profileName) {

	}

}
