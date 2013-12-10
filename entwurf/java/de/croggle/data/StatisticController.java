package de.croggle.data;

import java.util.List;

import de.croggle.data.model.Statistic;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
* @navassoc 1 - * de.croggle.data.model.Statistic
*/

public class StatisticController extends SQLiteOpenHelper {
	
		public static final String KEY_ID = "id";
		public static final String KEY_PLAYTIME = "playtime";
		public static final String KEY_HINTS = "hints";
		public static final String KEY_LEVELS_COMPLETE = "levelsComplete";
		public static final String KEY_PACKAGES_COMPLETE = "packagesComplete";
		
		public static final String DATABASE_NAME = "StatisticDB";
		public static final int DATABASE_VERSION = 1;
		
		private static final String CREATE_DATABASE = "null";

		public StatisticController(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			
			
		}



		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			
		}
		
		public boolean addStatistic(Statistic statistic) {
			/*TODO*/
			return false;
		}
		
		public long getStatistic(long id) {
			/*TODO*/
			return 0;
		}
				

		public boolean updateStatistic(long id, Statistic statistic) {
			/*TODO*/
			return false;
		}
		
		public boolean deleteStatistic(long id) {
			/*TODO*/
			return false;
		}
		
		public List<Statistic> getAllStatistics() {
			/*TODO*/
			return null;
		}
		
		

}
