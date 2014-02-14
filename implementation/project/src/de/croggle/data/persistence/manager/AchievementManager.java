package de.croggle.data.persistence.manager;

import de.croggle.backends.BackendHelper;
import de.croggle.backends.sqlite.ContentValues;
import de.croggle.backends.sqlite.Cursor;
import de.croggle.backends.sqlite.DatabaseUtils;
import de.croggle.game.achievement.Achievement;
import de.croggle.util.Tuple2;

/**
 * A concrete table manager which is responsible for managing the SQLite table
 * that stores the unlocked achievements of the different profiles.
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
	static final String KEY_ACHIEVEMENT_INDEX = "achievementIndex";

	/**
	 * The name of the table.
	 */
	public static final String TABLE_NAME = "AchievementTable";

	/**
	 * The string used for creating the achievement table via a sql query.
	 */
	public static final String CREATE_TABLE = "create table " + TABLE_NAME
			+ "(" + KEY_PROFILE_NAME + " text not null, " + KEY_ACHIEVEMENT_ID
			+ " integer, " + KEY_ACHIEVEMENT_INDEX + " integer, "
			+ "FOREIGN KEY(" + KEY_PROFILE_NAME + ") REFERENCES "
			+ ProfileManager.TABLE_NAME + "(" + ProfileManager.KEY_PROFILE_NAME
			+ ") ON UPDATE CASCADE ON DELETE CASCADE )";;

	/**
	 * Searches the table for a unlocked achievement that belongs to the profile
	 * identified by the profile name and whose achievement ID matches the
	 * achievement ID stored in achievement. The values of the found achievement
	 * are overwritten by the new achievement.
	 * 
	 * @param profileName
	 *            the name of the profile to which the achievement belongs
	 * @param achievement
	 *            the achievement whose values are used for overwriting the old
	 *            achievement
	 */
	void updateUnlockedAchievement(String profileName, Achievement achievement) {

		ContentValues values = BackendHelper.getNewContentValues();

		values.put(KEY_PROFILE_NAME, profileName);
		values.put(KEY_ACHIEVEMENT_INDEX, achievement.getIndex());

		database.update(TABLE_NAME, values, KEY_PROFILE_NAME + " = '"
				+ profileName + "' and " + KEY_ACHIEVEMENT_ID + " = "
				+ achievement.getId(), null);
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

		ContentValues values = BackendHelper.getNewContentValues();

		values.put(KEY_PROFILE_NAME, profileName);
		values.put(KEY_ACHIEVEMENT_ID, achievement.getId());
		values.put(KEY_ACHIEVEMENT_INDEX, achievement.getIndex());

		database.insert(TABLE_NAME, null, values);
	}

	/**
	 * Returns all achievements stored in the table that were unlocked by the
	 * user with the given profile name.
	 * 
	 * @param profileName
	 *            the name of the user whose unlocked achievements are searched
	 *            for
	 * @return a sparseIntArray containing the ids and states of all
	 *         achievements unlocked by the user
	 */
	Tuple2<Integer, Integer>[] getUnlockedAchievements(String profileName) {
		int achivementc = (int) DatabaseUtils.queryNumEntries(database,
				TABLE_NAME);
		Tuple2<Integer, Integer>[] unlockedAchievements = new Tuple2[achivementc];

		String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE "
				+ KEY_PROFILE_NAME + " = '" + profileName + "'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			int i = 0;

			do {
				int achievementId = cursor.getInt(cursor
						.getColumnIndex(KEY_ACHIEVEMENT_ID));
				int index = cursor.getInt(cursor
						.getColumnIndex(KEY_ACHIEVEMENT_INDEX));
				unlockedAchievements[i] = new Tuple2<Integer, Integer>(
						achievementId, index);
				i++;
			} while (cursor.moveToNext());
		}
		return unlockedAchievements;
	}

	@Override
	void clearTable() {
		database.execSQL("delete from " + TABLE_NAME);
	}

	@Override
	long getRowCount() {
		return DatabaseUtils.queryNumEntries(database, TABLE_NAME);

	}

}
