package de.croggle.data.persistence.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import de.croggle.data.persistence.Statistic;

/**
 * A concrete table manager is responsible for managing the SQLite table that
 * stores the statistics of the different profiles.
 */
public class StatisticManager extends TableManager {

	/**
	 * Name of the column that stores the profile names. The names are used as
	 * the primary key.
	 */
	static final String KEY_PROFILE_NAME = "profileName";

	/**
	 * Name of the column that stores the playtimes.
	 */
	static final String KEY_PLAYTIME = "playtime";

	/**
	 * Name of the column that stores the number of used hints.
	 */
	static final String KEY_USED_HINTS = "usedHints";

	/**
	 * Name of the column that stores the number of used resets.
	 */
	static final String KEY_USED_RESETS = "usedResets";

	/**
	 * Name of the column that stores the number of recoloring actions.
	 */
	static final String KEY_RECOLORINGS = "recolorings";

	/**
	 * Name of the column that stores the number of completed levels.
	 */
	static final String KEY_LEVELS_COMPLETE = "levelsComplete";

	/**
	 * Name of the column that stores the number of completed packages.
	 */
	static final String KEY_PACKAGES_COMPLETE = "packagesComplete";

	/**
	 * Name of the column that stores the number of eaten alligators.
	 */
	static final String KEY_ALLIGATORS_EATEN = "alligatorsEaten";

	/**
	 * Name of the column that stores the number of placed alligators.
	 */
	static final String KEY_ALLIGATORS_PLACED = "alligatorsPlaced";

	/**
	 * Name of the column that stores the number of hatched eggs.
	 */
	static final String KEY_EGGS_HATCHED = "eggsHatched";

	/**
	 * Name of the column that stores the number of placed eggs.
	 */
	static final String KEY_EGGS_PLACED = "eggsPlaced";

	/**
	 * The name of the table.
	 */
	static final String TABLE_NAME = "StatisticTable";

	/**
	 * The string used for creating the statistic table via a sql query.
	 */
	static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
			+ KEY_PROFILE_NAME + " text not null, " + KEY_PLAYTIME + " int, "
			+ KEY_USED_HINTS + " int, " + KEY_USED_RESETS + " int, "
			+ KEY_RECOLORINGS + " int, " + KEY_LEVELS_COMPLETE + " int, "
			+ KEY_PACKAGES_COMPLETE + " int, " + KEY_ALLIGATORS_EATEN
			+ " int, " + KEY_ALLIGATORS_PLACED + " int, " + KEY_EGGS_HATCHED
			+ " int, " + KEY_EGGS_PLACED + " int, " + "FOREIGN KEY("
			+ KEY_PROFILE_NAME + ") REFERENCES " + ProfileManager.TABLE_NAME
			+ "(" + ProfileManager.KEY_PROFILE_NAME
			+ ") ON UPDATE CASCADE ON DELETE CASCADE )";

	/**
	 * Creates a new StatisticManager which manages the statistic table.
	 * 
	 * @param context
	 *            used for accessing the database
	 */
	StatisticManager(Context context) {
		super(context);

	}

	/**
	 * Adds a new statistic to the table.
	 * 
	 * @param profileName
	 *            the name of the profile whose statistic is added to the table
	 * @param statistic
	 *            contains the values to be stored in the table
	 */
	void addStatistic(String profileName, Statistic statistic) {

		ContentValues values = new ContentValues();

		values.put(KEY_PROFILE_NAME, profileName);
		values.put(KEY_PLAYTIME, statistic.getPlaytime());
		values.put(KEY_USED_HINTS, statistic.getUsedHints());
		values.put(KEY_USED_RESETS, statistic.getResetsUsed());
		values.put(KEY_RECOLORINGS, statistic.getRecolorings());
		values.put(KEY_LEVELS_COMPLETE, statistic.getLevelsComplete());
		values.put(KEY_PACKAGES_COMPLETE, statistic.getPackagesComplete());
		values.put(KEY_ALLIGATORS_EATEN, statistic.getAlligatorsEaten());
		values.put(KEY_ALLIGATORS_PLACED, statistic.getAlligatorsPlaced());
		values.put(KEY_EGGS_HATCHED, statistic.getEggsHatched());
		values.put(KEY_EGGS_PLACED, statistic.getEggsPlaced());

		database.insert(TABLE_NAME, null, values);

	}

	/**
	 * Searches the table for a statistic which belongs to the profile
	 * identified by the given profile name.
	 * 
	 * @param profileName
	 *            the name of the profile whose statistic is loaded
	 * @return the found statistic, null if no statistic is found
	 */
	Statistic getStatistic(String profileName) {

		String selectQuery = "select * from " + TABLE_NAME + " where "
				+ KEY_PROFILE_NAME + " = " + "'" + profileName + "'";

		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			int playtime = cursor.getInt(cursor.getColumnIndex(KEY_PLAYTIME));
			int usedHints = cursor
					.getInt(cursor.getColumnIndex(KEY_USED_HINTS));
			int resetsUsed = cursor.getInt(cursor
					.getColumnIndex(KEY_USED_RESETS));
			int recolorings = cursor.getInt(cursor
					.getColumnIndex(KEY_RECOLORINGS));
			int levelsComplete = cursor.getInt(cursor
					.getColumnIndex(KEY_LEVELS_COMPLETE));
			int packagesComplete = cursor.getInt(cursor
					.getColumnIndex(KEY_PACKAGES_COMPLETE));
			int alligatorsEaten = cursor.getInt(cursor
					.getColumnIndex(KEY_ALLIGATORS_EATEN));
			int alligatorsPlaced = cursor.getInt(cursor
					.getColumnIndex(KEY_ALLIGATORS_PLACED));
			int eggsHatched = cursor.getInt(cursor
					.getColumnIndex(KEY_EGGS_HATCHED));
			int eggsPlaced = cursor.getInt(cursor
					.getColumnIndex(KEY_EGGS_PLACED));
			return new Statistic(playtime, usedHints, resetsUsed, recolorings,
					levelsComplete, packagesComplete, alligatorsEaten,
					alligatorsPlaced, eggsHatched, eggsPlaced);
		}

		return null;
	}

	/**
	 * Searches the table for a statistic which belongs to the profile
	 * identified by the given profile name and overwrites its values with the
	 * values of the new statistic.
	 * 
	 * @param profileName
	 *            the name of the profile whose statistic is edited
	 * @param statistic
	 *            the statistic whose values are used for overwriting the old
	 *            statistic
	 */
	void editStatistic(String profileName, Statistic statistic) {

		ContentValues values = new ContentValues();

		values.put(KEY_PROFILE_NAME, profileName);
		values.put(KEY_PLAYTIME, statistic.getPlaytime());
		values.put(KEY_USED_HINTS, statistic.getUsedHints());
		values.put(KEY_USED_RESETS, statistic.getResetsUsed());
		values.put(KEY_RECOLORINGS, statistic.getRecolorings());
		values.put(KEY_LEVELS_COMPLETE, statistic.getLevelsComplete());
		values.put(KEY_PACKAGES_COMPLETE, statistic.getPackagesComplete());
		values.put(KEY_ALLIGATORS_EATEN, statistic.getAlligatorsEaten());
		values.put(KEY_ALLIGATORS_PLACED, statistic.getAlligatorsPlaced());
		values.put(KEY_EGGS_HATCHED, statistic.getEggsHatched());
		values.put(KEY_EGGS_PLACED, statistic.getEggsPlaced());

		database.update(TABLE_NAME, values, KEY_PROFILE_NAME + " = ?",
				new String[] { profileName });
	}



	void clearTable() {
		database.execSQL("delete from " + TABLE_NAME);
	}

	@Override
	long getRowCount() {
		return DatabaseUtils.queryNumEntries(database, TABLE_NAME);

	}

}
