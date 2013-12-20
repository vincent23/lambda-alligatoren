package de.croggle.data.persistence.manager;


import java.util.List;

import de.croggle.game.achievement.Achievement;

import android.content.Context;


/**
 * A concrete table manager which is responsible for managing the sqlite-table that stores the unlocked achievements of the different profiles.
 * 
 * @navassoc 1 - * de.croggle.game.achievement.Achievement
 */
public class AchievementManager extends TableManager {
	
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
	 * The string used to create the achievement table via a sql query.
	 */
	static final String CREATE_TABLE = "null";
	
	/**
	 * Creates a new AchievementManager used to manage the achievement table.
	 * @param context Used to access the database.
	 */
	AchievementManager(Context context) {
		super(context);
		
	}

		
	/**
	 * Adds a new unlocked achievement to the table, the parameter profileName identifies the profile to which the unlocked achievement belongs.
	 * @param profileName The name of the profile to which the unlocked achievement belongs.
	 * @param achievement Contains the values to be stored in the table.
	 */
	void addUnlockedAchievement(String profileName, Achievement achievement) {
		
	}

	/**
	 * Returns all achievements stored in the table that were unlocked by the user with the profile name profileName.
	 * @param profileName The name of the user which unlocked achievements are searched-for.
	 * @return Returns A list of all achievements unlocked by the user with the name profileName. 
	 */
	List<Achievement> getAllUnlockedAchievements(String profileName) {
		/* TODO */
		return null;
	}

	/**
	 * Deletes all achievements that were unlocked by the user with the profile name profileName from the table.
	 * @param profileName The name of the profile which unlocked achievements are deleted.
	 */
	void deleteAllUnlockedAchievements(String profileName) {
		
	}

}
