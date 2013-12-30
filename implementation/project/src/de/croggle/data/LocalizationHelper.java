package de.croggle.data;

import java.lang.reflect.Field;

import android.content.Context;
import de.croggle.R;

/**
 * Manager whose task it is to handle the translation of the app from one
 * language to another. The strings should be defined in the usual Android
 * strings xml file.
 */
public class LocalizationHelper {

	private static Context context = null;
	
	/**
	 * 
	 */
	private LocalizationHelper() {
	}
	
	public static void setContext(Context c) {
		context = c;
	}

	/**
	 * Loads and returns the string identified by the given id. The strings
	 * should be defined in the common Android language xml files
	 * (values/strings). The language is decided by the system. The identifier
	 * can be accessed by <code>R.string.identifier</code>.
	 * 
	 * @param indentifier
	 *            the name of the string to be translated
	 */
	public static String getLocalizedString(String identifier) {
		if (context == null)
			throw new RuntimeException("Localizationhelper needs to be initialized with a context");
		
		try {
			Class<?> c = R.string.class;
			Field idField = c.getDeclaredField(identifier);
			if (int.class.isAssignableFrom(idField.getType())) {
				idField.setAccessible(true);
				if (idField.isAccessible()) {
					Integer id = (Integer) idField.get(null);
					String str = context.getResources().getString(id);
					return str;
				} else {
					throw new RuntimeException("Cannot access string \"" + identifier + "\"");
				}
			} else {
				throw new RuntimeException("The identifier \"" + identifier + "\" does not denote a field of type int");
			}
		} catch (NoSuchFieldException x) {
			throw new RuntimeException("Cannot localize string \"" + identifier + "\"");
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Cannot access string \"" + identifier + "\"");
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("This should not habben EVER");
		}
	}
	
	public static String _(String identifier) {
		return getLocalizedString(identifier);
	}

}
