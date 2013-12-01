package de.croggle.data;

import com.example.tracker.database.DBAdapter;
import com.example.tracker.database.DBAdapter.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * @opt all
 */
public class ProfileController {
	
	public static final String KEY_ROWID;
	public static final String KEY_PROFIL_NAME;
	public static final String KEY_PROFIL_PICTURE;
	public static final String KEY_MAX_LEVEL;
	
	private static final String DATABASE_NAME ;
	private static final String DATABASE_TABLE;
	private static final int DATABASE_VERSION;
	
	private static final String DATABASE_CREATE;
	
	private final Context context;
	
	private SQLiteDatabase db;
	private DatabaseHelper DBHelper;
	
	public ProfileController(Context context) {
		
	}
	
	public ProfileController open() throws SQLException {
		
	}
	
	public void close() {
		
	}
	
	public long insertUser(long id, String profile_name, String profile_picture, int max_level) {
		
	}
	
	/**
	 * 
	 */
	public boolean modifyUser(long id, String profile_name, String profile_picture, int max_level) {
		
	}
	
	public boolean deleteUser(long id) {
		
	}
	
	public Cursor getUser(long id) {
		
	}
	
	public Cursor getAllUsers() {
		
	}
	
	public void delete() {
		
	}
	
	
}
