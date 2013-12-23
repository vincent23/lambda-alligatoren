package de.croggle.data;

import android.content.Context;

/**
 * Manager whose task it is to handle the translation of the app from one
 * language to another. The strings should be defined in the usual Android
 * strings xml file.
 */
public class LocalizationManager {

	private Context context;

	/**
	 * Creates the LocalizationManager that uses the given Android context to
	 * achieve defined strings.
	 * 
	 * @param context
	 *            the context of the Android application
	 */
	public LocalizationManager(Context context) {

	}

	/**
	 * Loads and returns the string identified by the given id. The strings
	 * should be defined in the common Android language xml files
	 * (values/strings). The language is decided by the system. The identifier
	 * can be accessed by <code>R.string.identifier</code>.
	 * 
	 * @param indentifier
	 *            the identifying number
	 */
	public String getString(int identifier) {
		return "";
	}

}
