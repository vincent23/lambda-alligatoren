package de.croggle.data;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract.Profile;


/**
 * @navassoc 1 - * de.croggle.data.model.Profile
 */
public class ProfileController extends SQLiteOpenHelper{
	
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_PICTUREPATH = "picturepath";
	
	public static final String DATABASE_NAME = "profileDB";
	public static final int DATABASE_VERSION = 1;
	
	private static final String CREATE_DATABASE = "null";
	
	public ProfileController(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
	}



	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}
	
	public boolean addProfile(Profile profile) {
		/*TODO*/
		return false;
	}
	
	public long getProfile(long id) {
		/*TODO*/
		return 0;
	}
	
	
	public boolean updateProfile(Profile profile) {
		/*TODO*/
		return false;
	}
	
	public boolean deleteProfile(long id) {
		/*TODO*/
		return false;
	}
	
	public List<Profile> getAllProfiles() {
		/*TODO*/
		return null;
	}
	
	

}