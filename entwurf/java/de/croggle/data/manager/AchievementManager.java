package de.croggle.data.manager;


import java.util.List;

import de.croggle.model.Achievement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AchievementManager extends SQLiteOpenHelper {
	
	public static final String KEY_PROFILE_NAME = "profileName";
	
	public static final String KEY_ACHIEVEMENT_ID = "achievementID";
	public static final String KEY_ACHIEVEMENT_State = "achievementState";
	
	public static final String DATABASE_NAME = "AchievementDB";
	public static final int DATABASE_VERSION = 1;
	
	private static final String CREATE_DATABASE = "null";


	public AchievementManager(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
	public boolean addUnlockedAchievement(String profileName, Achievement achievement) {
		/* TODO */
		return false;
	}

	public List<Achievement> getAllUnlockedAchievements(String profileName) {
		/* TODO */
		return null;
	}

	public boolean deleteAllUnlockedAchievements(String profileName) {
		/* TODO */
		return false;
	}

}
