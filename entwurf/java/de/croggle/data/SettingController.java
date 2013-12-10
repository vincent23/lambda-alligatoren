package de.croggle.data;

import java.util.List;

import de.croggle.data.model.Setting;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
* @navassoc 1 - * de.croggle.data.model.Setting
*/

public class SettingController extends SQLiteOpenHelper {
	
		public static final String KEY_ID = "id";
		public static final String KEY_VOLUME_MUSIC = "volumeMusic";
		public static final String KEY_VOLUME_EFFECTS = "volumeEffects";
		public static final String KEY_ZOOM_ENABLED = "zoomEnabled";
		public static final String KEY_COLORBLIND_ENABLED = "colorblindEnabled";
		
		public static final String DATABASE_NAME = "SettingDB";
		public static final int DATABASE_VERSION = 1;
		
		private static final String CREATE_DATABASE = "null";
		
		public SettingController(Context context) {
			
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			
			
		}



		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			
		}
		
		public boolean addSetting(Setting setting) {
			/*TODO*/
			return false;
		}
		
		public long getSetting(long id) {
			/*TODO*/
			return 0;
		}
		
		/**
		 * 
		 */
		public boolean updateSetting(long id, Setting setting) {
			/*TODO*/
			return false;
		}
		
		public boolean deleteSetting(long id) {
			/*TODO*/
			return false;
		}
		
		public List<Setting> getAllSettings() {
			/*TODO*/
			return null;
		}
		
		

}
