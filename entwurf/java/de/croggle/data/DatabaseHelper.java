package de.croggle.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 */
public class DatabaseHelper extends SQLiteOpenHelper {
		
	public DatabaseHelper(Context context, String DATABASE_NAME, String DATABASE_VERSION) {
		
	}

	@Override
	public void onCreate(SQLiteDatabase db, String DATABASE_CREATE) {
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	}
}
