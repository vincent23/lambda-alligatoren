package de.croggle.data.persistence.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * This class is responsible for creating and managing the database with its different tables.
 * 
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.ProfileManager
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.SettingManager
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.StatisticManager
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.LevelProgressManager
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.AchievementManager
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	/**
	 * The name of the database.
	 */
	private static final String DATABASE_NAME = "persistenceDB";
	
	/**
	 * The version number of the database.
	 */
	private static final int DATABASE_Version = 1;
	
	/**
	 * Creates a new DatabaseHelper which is used manages the database.
	 * @param context
	 */
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_Version);
	}

	/**
	 * Creates all tables if the dont already exist.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	/**
	 * Deletes the old database and creates a new one.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	

}
