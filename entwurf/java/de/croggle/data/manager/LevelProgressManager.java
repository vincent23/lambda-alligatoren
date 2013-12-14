package de.croggle.data.manager;


import de.croggle.data.LevelProgress;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @navassoc 1 - 1 de.croggle.data.model.LevelProgress
 */
public class LevelProgressManager extends SQLiteOpenHelper{
		
		public static final String KEY_PROFILE_NAME = "profileName";
		public static final String KEY_LEVEL_ID = "levelId";
		public static final String KEY_SOLVED = "solved";
		public static final String KEY_CURRENT_BOARD = "currentBoard";
		public static final String KEY_USED_RESETS = "usedResets";
		public static final String KEY_USED_HINTS = "usedHints";
		public static final String KEY_USED_TIME = "usedTime";
			
		public static final String DATABASE_NAME = "levelProgressDB";
		public static final int DATABASE_VERSION = 1;
		
		private static final String CREATE_DATABASE = "null";
		
		public LevelProgressManager(Context context) {
			
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			
			
		}



		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			
		}
		
		public boolean addLevelProgress(String profileName, LevelProgress levelProgress) {
			/*TODO*/
			return false;
		}
		
		public long getLevelProgress(String profileName, long levelId)  {
			/*TODO*/
			return 0;
		}
		
		
		public boolean updateLevelProgress(String profileName, LevelProgress LevelProgress) {
			/*TODO*/
			return false;
		}
		
		public boolean deleteLevelProgress(String profileName, long levelId) {
			/*TODO*/
			return false;
		}
		
		
		
		

	}

