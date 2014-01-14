package de.croggle.data.persistence.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import de.croggle.game.achievement.Achievement;
import de.croggle.game.profile.Profile;

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
	static final String TABLE_NAME = "AchievementTable";

	/**
	 * The string used for creating the achievement table via a sql query.
	 */
	static final String CREATE_TABLE = "create table " + TABLE_NAME
			+ "(" + KEY_PROFILE_NAME + " text not null, "
            + KEY_ACHIEVEMENT_ID + " integer, "
            + KEY_ACHIEVEMENT_INDEX + " integer, "
            + "FOREIGN KEY(" + KEY_PROFILE_NAME + ") REFERENCES " + ProfileManager.TABLE_NAME + "(" + ProfileManager.KEY_PROFILE_NAME + ") ON UPDATE CASCADE ON DELETE CASCADE )";
 ;

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
		
		ContentValues values = new ContentValues();
		
		values.put(KEY_PROFILE_NAME, profileName);
		values.put(KEY_ACHIEVEMENT_ID, achievement.getId());
		values.put(KEY_ACHIEVEMENT_INDEX, achievement.getId());
		
		database.insert(TABLE_NAME, null, values);
		}

	/**
	 * Returns all achievements stored in the table that were unlocked by the
	 * user with the given profile name.
	 * 
	 * @param profileName
	 *            the name of the user whose unlocked achievements are searched
	 *            for
	 * @return a map containing the ids and states of all achievements unlocked by the user
	 */
	Map<Integer, Integer> getUnlockedAchievements(String profileName) {
		
		Map<Integer, Integer> unlockedAchievements = new HashMap<Integer, Integer> ();
		
		String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + KEY_PROFILE_NAME + " = '" + profileName + "'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		 if (cursor.moveToFirst()) {
			 do {
				 int achievementId = cursor.getInt(cursor.getColumnIndex(KEY_ACHIEVEMENT_ID));
				 int index = cursor.getInt(cursor.getColumnIndex(KEY_ACHIEVEMENT_INDEX));
				 unlockedAchievements.put(achievementId, index);
		        } while (cursor.moveToNext());
		}
		return unlockedAchievements;
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
		database.delete(TABLE_NAME, KEY_PROFILE_NAME + " = ?",
	            new String[] { profileName });
	}
	
	void clearTable() {
		database.execSQL("delete from "+ TABLE_NAME);
	}
	
	@Override
	long getRowCount() {
		return DatabaseUtils.queryNumEntries(database, TABLE_NAME);
		
	}

}
