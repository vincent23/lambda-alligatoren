package de.croggle.data.persistence.manager;


import java.util.List;

import de.croggle.game.achievement.Achievement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * This class is responsible for managing the sqlite-table that stores the unlocked achievements of the different profiles.
 * 
 * @navassoc 1 - * de.croggle.game.achievement.Achievement
 */
public class AchievementManager extends SQLiteOpenHelper {
	
	/**
	 * Name for the column that stores the profile names. Those names are used as the primary key.
	 */
	static final String KEY_PROFILE_NAME = "profileName";
	
	/**
	 * Name for the column that stores the achievement IDs.
	 */	
	static final String KEY_ACHIEVEMENT_ID = "achievementID";
	
	/**
	 * Name for the column that stores the achievement states.
	 */
	static final String KEY_ACHIEVEMENT_State = "achievementState";
	
	/**
	 * The name of the table.
	 */
	static final String TABLE_NAME = "AchievementTable";
	
	/**
	 * The version number of the table.
	 */
	static final int TABLE_VERSION = 1;
	
	/**
	 * The string used to create the achievement table via a sql query.
	 */
	static final String CREATE_TABLE = "null";

	/**
	 * Creates an new AchievementManager and the achievement table if it does not already exists.
	 * @param context
	 */
	public AchievementManager(Context context) {
		super(context, TABLE_NAME, null, TABLE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
	/**
	 * Adds a new unlocked achievement to the table, the parameter profileName is used as the primary key.
	 * @param profileName This string is used as the primary key for the achievement.
	 * @param achievement Contains the values to be stored in the table.
	 */
	public void addUnlockedAchievement(String profileName, Achievement achievement) {
		
	}

	/**
	 * Returns all unlocked achievements stored in the table that were unlocked by the user which profile name is equal to profileName.
	 * @param profileName This string is used to find all achievements by the specific user.
	 * @return Returns a List of all achievements by the user with the name profileName. 
	 */
	public List<Achievement> getAllUnlockedAchievements(String profileName) {
		/* TODO */
		return null;
	}

	/**
	 * Deletes all entries which primary key is equal to profileName.
	 * @param profileName This String is used to identify all entries which are to be deleted.
	 */
	public void deleteAllUnlockedAchievements(String profileName) {
		
	}

}
