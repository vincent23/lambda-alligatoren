package de.croggle.backends;

import java.util.Locale;

public interface LocalizationBackend {

	/**
	 * Translate a given string into the currently set locale's language.
	 * Depending on the platform and the backend used, the string may serve as
	 * an identifier of the desired string. On Android, for example, localized
	 * strings are accessible via names, which need to be valid java
	 * identifiers. Backends , though, can try to hide this by performing custom
	 * substitutions or lookups at runtime. An example would be, if the
	 * localized string "hello world" was stored with name "hello_world", that
	 * the translate method could substitute spaces with underscores to obtain
	 * the actual identifier. This way, programmers could simply call
	 * translate("hello world").
	 * 
	 * @param s
	 *            the string to be translated
	 * @return the localized counterpart of the given string
	 */
	public String translate(String s);

	/**
	 * Translate the given string into the currently set locale, respecting the
	 * given multiplicity.
	 * 
	 * @param s
	 *            the string to be translated
	 * @param multiplicity
	 *            the multiplicity of the subject to be translated
	 * @return the translated string
	 */
	public String translate(String s, int multiplicity);

	/**
	 * Returns a list of localized strings, denoted by the given string
	 * identifier.
	 * 
	 * @param identifier
	 *            the identifier of the string list to be obtained
	 * @return the requested list of localized strings
	 */
	public String[] getLocalizedStringList(String identifier);

	/**
	 * Sets the locale of the whole application.
	 * 
	 * @param locale
	 *            a string containing a valid locale, such as en_GB
	 * @return true if the locale was successfully set, false otherwise
	 */
	public void setApplicationLocale(Locale locale);

	/**
	 * Returns the currently active application locale
	 * 
	 * @return the currently set application locale
	 */
	public Locale getApplicationLocale();

	/**
	 * Returns the system wide active locale.
	 * 
	 * @return the system wide active locale
	 */
	public Locale getSystemLocale();
}
