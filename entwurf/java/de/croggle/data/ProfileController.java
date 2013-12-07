package de.croggle.data;

import java.util.List;

import de.croggle.trash.DBController;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * @navassoc 1 - * de.croggle.data.model.Profile
 */
public class ProfileController {
	
	public static final String KEY_ROWID = "id";
	public static final String KEY_PROFILE_ID = "profile_id";
	public static final String KEY_PROFILE_NAME = "profile_name";
	public static final String KEY_PROFILE_PICTURE = "profile_picture";
	public static final String KEY_MAX_LEVEL = "max_level";
	
	private Context context;
	private SQLiteDatabase db;
	private DatabaseHelper DBHelper;
	
	public ProfileController(Context context) {
		
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DBController.DATABASE_NAME, null, DBController.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {        
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
        int newVersion) 
        {               
        }
    }
	
	public ProfileController open() throws SQLException {
		/*TODO*/
		return null;
	}
	
	public void close() {
		
	}
	
	public long insertProfile(de.croggle.data.model.Profile profile) {
		/*TODO*/
		return 0;
	}
	
	/**
	 * 
	 */
	public boolean modifyProfile(de.croggle.data.model.Profile profile) {
		/*TODO*/
		return false;
	}
	
	public boolean deleteProfile(de.croggle.data.model.Profile profile) {
		/*TODO*/
		return false;
	}
	
	public List<de.croggle.data.model.Profile> getAllProfiles() {
		/*TODO*/
		return null;
	}
	
	public void delete() {
		
	}

}
