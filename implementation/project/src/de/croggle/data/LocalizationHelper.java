package de.croggle.data;

import java.util.Locale;

import de.croggle.backends.LocalizationBackend;

/**
 * Helper whose task it is to handle the translation of the app from one
 * language to another. Uses LocalizationBackends for portability. Must be set
 * before first usage.
 */
public class LocalizationHelper {

	private static LocalizationBackend backend = null;

	/**
	 * 
	 */
	private LocalizationHelper() {
	}

	/**
	 * Sets the backend to be used by the TranslationHelper.
	 * 
	 * @param b
	 *            a LocalizationBackend, such as AndroidLocalizationBackend
	 */
	public static void setBackend(LocalizationBackend b) {
		backend = b;
	}

	/**
	 * Loads and returns the string identified by the given id. The strings
	 * should be defined in the common Android language xml files
	 * (values/strings). The language is decided by the system. The identifier
	 * can be accessed by <code>R.string.identifier</code>.
	 * 
	 * @param s
	 *            the string to be translated
	 * @return the translated string
	 */
	public static String getLocalizedString(String s) {
		if (backend == null)
			throw new RuntimeException(
					"Localizationhelper needs to be initialized with a context");

		return backend.translate(s);
	}

	/**
	 * Shorthand for getLocalizedString. Best when imported statically:
	 * print(_("Hello world!"))
	 * 
	 * @see LocalizationHelper#getLocalizedString(String)
	 * 
	 * @param s
	 *            the string to be translated
	 * @return the translated string
	 */
	public static String _(String s) {
		return getLocalizedString(s);
	}

	/**
	 * Translates a given string into the currently set locale's language,
	 * respecting the specified multiplicity of its subject.
	 * 
	 * @param s
	 *            the string to be translated
	 * @param multiplicity
	 *            the quantity of the subject occurring in s
	 * @return the translated string
	 */
	public static String getLocalizedString(String s, int multiplicity) {
		if (backend == null)
			throw new RuntimeException(
					"Localizationhelper needs to be initialized with a context");

		return backend.translate(s, multiplicity);
	}

	/**
	 * Shorthand for getLocalizedString. Best when imported statically:
	 * print(_("Hello world!"), 3)
	 * 
	 * @see LocalizationHelper#getLocalizedString(String, int)
	 * 
	 * @param s
	 *            the string to be translated
	 * @param quantity
	 *            the quantity of the subject occurring in s
	 * @return the translated string
	 */
	public static String _(String s, int quantity) {
		return getLocalizedString(s, quantity);
	}

	/**
	 * Access a list of strings by its identifier, which are (supposedly)
	 * translated each into the currently active application locale.
	 * 
	 * @param identifier
	 *            the identifier of the string list
	 * @return the list of strings, or an empty array if the list was not found
	 *         by the given identifier
	 */
	public static String[] localizedList(String identifier) {
		if (backend == null)
			throw new RuntimeException(
					"Localizationhelper needs to be initialized with a context");
		return backend.getLocalizedStringList(identifier);
	}

	/**
	 * Access the currently active locale of the application.
	 * 
	 * @return the currently active locale
	 */
	public static Locale getApplicationLocale() {
		if (backend == null)
			throw new RuntimeException(
					"Localizationhelper needs to be initialized with a context");
		return backend.getApplicationLocale();
	}

	/**
	 * Sets the locale the application uses to translate strings.
	 * 
	 * @param l
	 *            the locale to be used
	 */
	public static void setApplicationLocale(Locale l) {
		if (backend == null)
			throw new RuntimeException(
					"Localizationhelper needs to be initialized with a context");
		backend.setApplicationLocale(l);
	}

	/**
	 * Return the default system locale. Since Locale.getDefault() is part of
	 * the java standard, most backends will simply return this.
	 * 
	 * @return the default system locale
	 */
	public static Locale getSystemLocale() {
		if (backend == null)
			throw new RuntimeException(
					"Localizationhelper needs to be initialized with a context");
		return backend.getSystemLocale();
	}

}
