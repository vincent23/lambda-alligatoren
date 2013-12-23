package de.croggle.data.persistence.manager;

import de.croggle.data.persistence.Statistic;
import android.content.Context;




/**
 * A concrete table manager is responsible for managing the SQLite table that stores the statistics of the different profiles.
 * 
 * @navassoc 1 - * de.croggle.data.persistence.Statistic
 */
public class StatisticManager extends TableManager {


	/**
	 * Name of the column that stores the profile names. The names are used as the primary key.
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
	static final String CREATE_TABLE = "null";
	
	/**
	 * Creates a new StatisticManager which manages the statistic table.
	 * @param context used for accessing the database
	 */
	StatisticManager(Context context) {
		super(context);
		
	}
	
	/**
	 * Adds a new statistic to the table.
	 * @param profileName the name of the profile whose statistic is added to the table
	 * @param statistic contains the values to be stored in the table
	 */
	void addStatistic(String profileName, Statistic statistic) {
		
		
	}

	/**
	 * Searches the table for a statistic which belongs to the profile identified by the given profile name.
	 * @param profileName the name of the profile whose statistic is loaded
	 * @return the found statistic, null if no statistic is found
	 */
	Statistic getStatistic(String profileName) {
	
		return null;
	}

	/**
	 * Searches the table for a statistic which belongs to the profile identified by the given profile name and overwrites its values with the values of the new statistic.
	 * @param profileName the name of the profile whose statistic is edited
	 * @param statistic the statistic whose values are used for overwriting the old statistic
	 */
	void editStatistic(String profileName, Statistic statistic) {
		
	}

	/**
	 * Deletes the statistic which belongs to the profile identified by the given profile name from the table.
	 * @param profileName the name of the profile whose statistic is deleted
	 */
	void deleteStatistics(String profileName) {
	
	}

}
