package de.croggle.data.persistence.manager;


import de.croggle.data.persistence.LevelProgress;

import android.content.Context;


/**
 * A concrete table manager which is responsible for managing the SQLite table that stores the level progresses of the different profiles.
 * 
 * @navassoc 1 - * de.croggle.data.persistence.LevelProgress
 */

public class LevelProgressManager extends TableManager {
	
	/**
	 * Name for the column that stores the profile names. The names are used as the primary key.
	 */
	static final String KEY_PROFILE_NAME = "profileName";
	
	/**
	 * Name for the column that stores the level id. The IDs are used as the secondary key.
	 */
	static final String KEY_LEVEL_ID = "levelId";
	
	/**
	 * Name for the column that stores the information whether the level is solved or not.
	 */
	static final String KEY_SOLVED = "solved";
	
	/**
	 * Name for the column that stores the current board.
	 */
	static final String KEY_CURRENT_BOARD = "currentBoard";
	
	/**
	 * Name for the column that stores the number of used resets.
	 */
	static final String KEY_USED_RESETS = "usedResets";
	
	/**
	 * Name for the column that stores the number of used hints.
	 */
	static final String KEY_USED_HINTS = "usedHints";
	
	/**
	 * Name for the column that stores the amount of used time.
	 */
	static final String KEY_USED_TIME = "usedTime";
		
	/**
	 * The name of the table.
	 */
	static final String TABLE_NAME = "levelProgressTable";
	

	/**
	 * The string used for creating the level progress table via a sql query.
	 */
	static final String CREATE_TABLE = "null";
	

	
	/**
	 * Creates a new LevelProgressManager which manages the level progress table.
	 * @param context the context used for accessing the database
	 */
	LevelProgressManager(Context context) {
		super(context);
	}
	
	/**
	 * Adds a new level progress to the table.
	 * @param profileName the name of the profile to which the level progress belongs
	 * @param levelProgress the level progress contains the values to be stored in the table
	 */
	void addLevelProgress(String profileName, LevelProgress levelProgress) {
				
	}
	
	/**
	 * Searches the table for a level progress that belongs to the profile identified by the profile name and whose level ID matches the level ID stored in level progress. 
	 * @param profileName the name of the profile to which the level progresses belongs
	 * @param levelId the level ID of the searched-for level progress
	 * @return the found level progress, null if no level progress is found
	 */
	LevelProgress getLevelProgress(String profileName, long levelId)  {
		return null;
	}
	
	
	/**
	 * Searches the table for a level progress that belongs to the profile identified by profile name and whose level ID matches the level ID stored in levelProgress. 
	 * The values of the found level progress are overwritten by the new level progress.
	 * @param profileName the name of the profile to which the level progresses belongs
	 * @param levelProgress the level progress whose values are used for overwriting the old level progress
	 */
	void updateLevelProgress(String profileName, LevelProgress levelProgress) {
		/*TODO*/

	}
	
	/**
	 * Deletes all level progresses which belong to the profile identified by the profile name from the table.
	 * @param profileName the name of the profile to which the level progresses belongs
	 */
	void deleteLevelProgresses(String profileName) {
		
	
	}
	
	
	
	

}

