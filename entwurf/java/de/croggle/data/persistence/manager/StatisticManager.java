package de.croggle.data.persistence.manager;

import de.croggle.data.persistence.Statistic;
import android.content.Context;




/**
 * A concrete table manager is responsible for managing the sqlite-table that stores the statistics of the different profiles.
 * 
 * @navassoc 1 - * de.croggle.data.persistence.Statistic
 */
public class StatisticManager extends TableManager {


	/**
	 * Name for the column that stores the profile names. The names are used as the primary key.
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
	static final String TABLE_NAME = "StatisticTable";
	
	/**
	 * The string used to create the statistic table via a sql query.
	 */
	static final String CREATE_TABLE = "null";
	
	/**
	 * Creates a new StatisticManager which manages the statistic table.
	 * @param context Used to access the database.
	 */
	StatisticManager(Context context) {
		super(context);
		
	}
	
	/**
	 * Adds a new statistic to the table, the parameter profileName identifies the profile to which the statistic belongs.
	 * @param profileName The name of the profile which statistic is added to the table.
	 * @param statistic Contains the values to be stored in the table.
	 */
	void addStatistic(String profileName, Statistic statistic) {
		
		
	}

	/**
	 * Searches the table for a statistic which belongs to the profile identified by profile name.
	 * @param profileName The name of the profile which statistic is loaded.
	 * @return Returns the found statistic, if no statistic is found, null is returned.
	 */
	Statistic getStatistic(String profileName) {
	
		return null;
	}

	/**
	 * Searches the table for a statistic which belongs to the profile identified by profileName and overwrites its values with the values stored in the parameter statistic.
	 * @param profileName The name of the profile which statistic is updated.
	 * @param statistic The statistic which values are used to overwrite the old statistic.
	 */
	void updateStatistic(String profileName, Statistic statistic) {
		
	}

	/**
	 * Deletes the statistic which belongs to the profile identified by profileName from the table.
	 * @param profileName The name of the profile which statistic is deleted.
	 */
	void deleteStatistics(String profileName) {
	
	}

}
