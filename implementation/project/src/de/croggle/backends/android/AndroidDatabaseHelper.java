package de.croggle.backends.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import de.croggle.backends.sqlite.Database;
import de.croggle.backends.sqlite.DatabaseHelper;
import de.croggle.data.persistence.manager.AchievementManager;
import de.croggle.data.persistence.manager.LevelProgressManager;
import de.croggle.data.persistence.manager.ProfileManager;
import de.croggle.data.persistence.manager.SettingManager;
import de.croggle.data.persistence.manager.StatisticManager;

public class AndroidDatabaseHelper implements DatabaseHelper {
	private final Helper helper;

	/**
	 * The name of the database.
	 */
	public static final String DATABASE_NAME = "persistenceDB";

	/**
	 * The version number of the database.
	 */
	private static final int DATABASE_Version = 2;

	/**
	 * Creates a new DatabaseHelper which is used for managing the database.
	 * 
	 * @param context
	 *            the context used to create the database
	 */
	public AndroidDatabaseHelper(Context context) {
		helper = new Helper(context);
	}

	@Override
	public Database getWritableDatabase() {
		return new AndroidDatabase(helper.getWritableDatabase());
	}

	@Override
	public void close() {
		helper.close();
	}

	private class Helper extends SQLiteOpenHelper {
		public Helper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_Version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(AchievementManager.CREATE_TABLE);
			db.execSQL(LevelProgressManager.CREATE_TABLE);
			db.execSQL(ProfileManager.CREATE_TABLE);
			db.execSQL(SettingManager.CREATE_TABLE);
			db.execSQL(StatisticManager.CREATE_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + AchievementManager.TABLE_NAME);
			db.execSQL("DROP TABLE IF EXISTS "
					+ LevelProgressManager.TABLE_NAME);
			db.execSQL("DROP TABLE IF EXISTS " + ProfileManager.TABLE_NAME);
			db.execSQL("DROP TABLE IF EXISTS " + SettingManager.TABLE_NAME);
			db.execSQL("DROP TABLE IF EXISTS " + StatisticManager.TABLE_NAME);

			onCreate(db);
		}
	}
}
