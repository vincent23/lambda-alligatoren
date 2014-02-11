package de.croggle.data.persistence.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import de.croggle.data.persistence.LevelProgress;

/**
 * A concrete table manager which is responsible for managing the SQLite table
 * that stores the level progresses of the different profiles.
 */

public class LevelProgressManager extends TableManager {

	/**
	 * Name of the column that stores the profile names. The names are used as
	 * the primary key.
	 */
	static final String KEY_PROFILE_NAME = "profileName";

	/**
	 * Name of the column that stores the level id. The IDs are used as the
	 * secondary key.
	 */
	static final String KEY_LEVEL_ID = "levelId";

	/**
	 * Name of the column that stores whether the level has been solved or not.
	 */
	static final String KEY_SOLVED = "solved";

	/**
	 * Name of the column that stores the current board.
	 */
	static final String KEY_CURRENT_BOARD = "currentBoard";


	/**
	 * Name of the column that stores the amount of used time.
	 */
	static final String KEY_USED_TIME = "usedTime";

	/**
	 * The name of the table.
	 */
	static final String TABLE_NAME = "levelProgressTable";

	/**
	 * The string used for creating the level progress table via a sql query.
	 */
	static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
			+ KEY_PROFILE_NAME + " text not null, " + KEY_LEVEL_ID
			+ " integer, " + KEY_SOLVED + " boolean, " + KEY_CURRENT_BOARD
			+ " text not null, "  + KEY_USED_TIME + " int, " + "FOREIGN KEY("
			+ KEY_PROFILE_NAME + ") REFERENCES " + ProfileManager.TABLE_NAME
			+ "(" + ProfileManager.KEY_PROFILE_NAME
			+ ") ON UPDATE CASCADE ON DELETE CASCADE )";

	/**
	 * Creates a new LevelProgressManager which manages the level progress
	 * table.
	 * 
	 * @param context
	 *            the context used for accessing the database
	 */
	LevelProgressManager(Context context) {
		super(context);
	}

	/**
	 * Adds a new level progress to the table.
	 * 
	 * @param profileName
	 *            the name of the profile to which the level progress belongs
	 * @param levelProgress
	 *            the level progress contains the values to be stored in the
	 *            table
	 */
	void addLevelProgress(String profileName, LevelProgress levelProgress) {

		ContentValues values = new ContentValues();

		values.put(KEY_PROFILE_NAME, profileName);
		values.put(KEY_LEVEL_ID, levelProgress.getLevelId());
		values.put(KEY_SOLVED, levelProgress.isSolved());
		values.put(KEY_CURRENT_BOARD, levelProgress.getCurrentBoard());
		values.put(KEY_USED_TIME, levelProgress.getUsedTime());

		database.insert(TABLE_NAME, null, values);
	}

	/**
	 * Searches the table for a level progress that belongs to the profile
	 * identified by the profile name and whose level ID matches the level ID
	 * stored in level progress.
	 * 
	 * @param profileName
	 *            the name of the profile to which the level progresses belong
	 * @param levelId
	 *            the level ID of the searched-for level progress
	 * @return the found level progress, null if no level progress is found
	 */
	LevelProgress getLevelProgress(String profileName, long levelId) {

		String selectQuery = "select * from " + TABLE_NAME + " where "
				+ KEY_PROFILE_NAME + " = " + "'" + profileName + "' and "
				+ KEY_LEVEL_ID + " = " + levelId;

		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			int levelID = cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_ID));
			boolean solved = (cursor.getInt(cursor.getColumnIndex(KEY_SOLVED)) == 1) ? true
					: false;
			String currentBoard = cursor.getString(cursor
					.getColumnIndex(KEY_CURRENT_BOARD));
			int usedTime = cursor.getInt(cursor.getColumnIndex(KEY_USED_TIME));
			return new LevelProgress(levelID, solved, currentBoard, usedTime);
		}

		return null;
	}

	/**
	 * Searches the table for a level progress that belongs to the profile
	 * identified by the profile name and whose level ID matches the level ID
	 * stored in levelProgress. The values of the found level progress are
	 * overwritten by the new level progress.
	 * 
	 * @param profileName
	 *            the name of the profile to which the level progresses belong
	 * @param levelProgress
	 *            the level progress whose values are used for overwriting the
	 *            old level progress
	 */
	void updateLevelProgress(String profileName, LevelProgress levelProgress) {

		ContentValues values = new ContentValues();

		values.put(KEY_SOLVED, levelProgress.isSolved());
		values.put(KEY_CURRENT_BOARD, levelProgress.getCurrentBoard());
		values.put(KEY_USED_TIME, levelProgress.getUsedTime());

		database.update(TABLE_NAME, values,
				KEY_PROFILE_NAME + " = '" + profileName + "' and "
						+ KEY_LEVEL_ID + " = " + levelProgress.getLevelId(),
				null);

	}


	void clearTable() {
		database.execSQL("delete from " + TABLE_NAME);
	}

	@Override
	long getRowCount() {
		return DatabaseUtils.queryNumEntries(database, TABLE_NAME);

	}

}
