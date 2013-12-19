package de.croggle.data.persistence.manager;


import de.croggle.data.persistence.LevelProgress;

import android.content.Context;


/**
 * This class is responsible for managing the sqlite-table that stores the level progresses of the different profiles.
 * 
 * @navassoc 1 - * de.croggle.data.persistence.LevelProgress
 */

public class LevelProgressManager extends TableManager {
	
	/**
	 * Name for the column that stores the profile names. Those names are used as the primary key.
	 */
	static final String KEY_PROFILE_NAME = "profileName";
	
	/**
	 * Name for the column that stores the levelId. The IDs are used as the secondary key.
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
	 * The string used to create the level progress table via a sql query.
	 */
	static final String CREATE_TABLE = "null";
	

	
	/**
	 * Creates a new LevelProgressManager used to manage the level progress table.
	 * @param context
	 */
	LevelProgressManager(Context context) {
		super(context);
	}
	
	/**
	 * Adds a new level progress to the table, the parameter profileName is used as the primary key.
	 * @param profileName This string is used as the primary key for the level progress.
	 * @param levelProgress Contains the values to be stored in the table.
	 */
	void addLevelProgress(String profileName, LevelProgress levelProgress) {
				
	}
	
	/**
	 * Searches the table for a level progress which primary key matches profileName and secondary key matches levelID.
	 * @param profileName The primary key for the sought level progress.
	 * @param levelId The secondary key for the sought level progress.
	 * @return Returns the found level progress, if no level progress is found, null is returned.
	 */
	LevelProgress getLevelProgress(String profileName, long levelId)  {
		/*TODO*/
		return null;
	}
	
	
	/**
	 * Searches the table for a levelProgress which primary key matches profileName and secondary key matches the level ID stored in levelProgress. 
	 * The values of the found levelProgress are overwritten by the values stored in the parameter levelProgress.
	 * @param profileName The primary key for the sought level progress.
	 * @param levelProgress The level progress which values are used to overwrite the old level progress.
	 */
	void updateLevelProgress(String profileName, LevelProgress levelProgress) {
		/*TODO*/

	}
	
	/**
	 * Deletes the table entry which primary and secondary key match profileName and levelID.
	 * @param profileName The key of the entry which is to be deleted.
	 */
	void deleteLevelProgress(String profileName, long levelId) {
		/*TODO*/
	
	}
	
	
	
	

}

