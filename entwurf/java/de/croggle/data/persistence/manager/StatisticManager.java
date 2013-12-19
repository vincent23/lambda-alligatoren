package de.croggle.data.persistence.manager;

import de.croggle.data.persistence.Statistic;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * This class is responsible for managing the sqlite-table that stores the statistics of the different profiles.
 * 
 * @navassoc 1 - * de.croggle.data.persistence.Statistic
 */
public class StatisticManager extends SQLiteOpenHelper {

	/**
	 * Name for the column that stores the profile names. Those names are used as the primary key.
	 */
	static final String KEY_PROFILE_NAME = "profileName";
	
	/**
	 * Name for the column that stores the playtimes.
	 */
	static final String KEY_PLAYTIME = "playtime";
	
	/**
	 * Name for the column that stores the number of used hints.
	 */
	static final String KEY_USED_HINTS = "usedHints";
	
	/**
	 * Name for the column that stores the number of used resets.
	 */
	static final String KEY_USED_RESETS = "usedResets";
	
	/**
	 * Name for the column that stores the number of recoloring actions.
	 */
	static final String KEY_RECOLORINGS = "recolorings";
	
	/**
	 * Name for the column that stores the number of completed levels.
	 */
	static final String KEY_LEVELS_COMPLETE = "levelsComplete";
	
	/**
	 * Name for the column that stores the number of completed packages.
	 */
	static final String KEY_PACKAGES_COMPLETE = "packagesComplete";
	
	/**
	 * Name for the column that stores the number of eaten alligators.
	 */
	static final String KEY_ALLIGATORS_EATEN = "alligatorsEaten";
	
	/**
	 * Name for the column that stores the number of eaten alligators.
	 */
	static final String KEY_ALLIGATORS_PLACED = "alligatorsPlaced";
	
	/**
	 * Name for the column that stores the number of hatched eggs.
	 */
	static final String KEY_EGGS_HATCHED = "eggsHatched";
	
	/**
	 * Name for the column that stores the number of placed eggs.
	 */
	static final String KEY_EGGS_PLACED = "eggsPlaced";

	/**
	 * The name of the table.
	 */
	private static final String TABLE_NAME = "StatisticTable";
	
	/**
	 * The version number of the table.
	 */
	private static final int TABLE_VERSION = 1;
	
	
	/**
	 * The string used to create the statistic table via a sql query.
	 */
	private static final String TABLE_DATABASE = "null";

	/**
	 * Creates an new StatisticManager and the statistic table if it does not already exists.
	 * @param context
	 */
	public StatisticManager(Context context) {
		super(context, TABLE_NAME, null, TABLE_VERSION);
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * Adds a new statistic to the table, the parameter profileName is used as the primary key.
	 * @param profileName This string is used as the primary key for the statistic.
	 * @param statistic Contains the values to be stored in the table.
	 */
	public void addStatistic(String profileName, Statistic statistic) {
		/* TODO */
		
	}

	/**
	 * Searches the table for a statistic which key matches profileName.
	 * @param profileName The key for the sought statistic.
	 * @return Returns the found statistic, if no statistic is found, null is returned.
	 */
	public Statistic getStatistic(String profileName) {
		/* TODO */
		return null;
	}

	/**
	 * Searches the table for a statistic which key matches profileName and overwrites its values with the values stored in the parameter statistic.
	 * @param profileName The key for the sought statistic.
	 * @param statistic The statistic which values are used to overwrite the old statistic.
	 */
	public void updateStatistic(String profileName, Statistic statistic) {
		
	}

	/**
	 * Deletes the table entry which key matches profileName.
	 * @param profileName The key of the entry which is to be deleted.
	 */
	public void deleteStatistics(String profileName) {
		/* TODO */
	}

}
