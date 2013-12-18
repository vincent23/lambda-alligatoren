package de.croggle.data.persistence.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import de.croggle.data.persistence.Statistic;

/**
 * @navassoc 1 - * de.croggle.data.persistence.Statistic
 */

public class StatisticManager extends SQLiteOpenHelper {

	public static final String KEY_PROFILE_NAME = "profileName";
	
	public static final String KEY_PLAYTIME = "playtime";
	
	public static final String KEY_USED_HINTS = "usedHints";
	public static final String KEY_USED_RESETS = "usedResets";
	public static final String KEY_RECOLORINGS = "recolorings";
		
	public static final String KEY_LEVELS_COMPLETE = "levelsComplete";
	public static final String KEY_PACKAGES_COMPLETE = "packagesComplete";
	
	public static final String KEY_ALLIGATORS_EATEN = "alligatorsEaten";
	public static final String KEY_ALLIGATORS_PLACED = "alligatorsPlaced";
	
	public static final String KEY_EGGS_HATCHED = "eggsHatched";
	public static final String KEY_EGGS_PLACED = "eggsPlaced";

	public static final String DATABASE_NAME = "StatisticDB";
	public static final int DATABASE_VERSION = 1;
	
	

	private static final String CREATE_DATABASE = "null";

	public StatisticManager(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public boolean addStatistic(String profileName, Statistic statistic) {
		/* TODO */
		return false;
	}

	public long getStatistic(String profileName) {
		/* TODO */
		return 0;
	}

	public boolean updateStatistic(String profileName, Statistic statistic) {
		/* TODO */
		return false;
	}

	public boolean deleteStatistics(String profileName) {
		/* TODO */
		return false;
	}

}
