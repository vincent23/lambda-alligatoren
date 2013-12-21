package de.croggle.data;

import android.content.Context;

/**
 * Manager, whose task is to handle the translation of the app from one language to another.
 * The strings should be defined in the usual android strings xml file.
 */
public class LocalizationManager {

	private Context context;
	
	/**
	* Creates the LocalizationManager that uses the given android context to achieve defined strings.
	* @param context the android activity's context
	*/
	public LocalizationManager(Context context){
	
	}

    /**
     * Loads and returns the String identified by the given id. 
     * The strings should be defined in the common android language xml files (values/strings). The language is decided by the system.
     * The identifier can be accessed by R.string.identifier.
     * @param indentifier the identifying number
     */
	public String getString(int identifier) {
		return "";
	}
	
}
