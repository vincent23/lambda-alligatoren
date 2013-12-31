package de.croggle.data;

import java.lang.reflect.Field;
import java.util.Locale;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import de.croggle.R;

/**
 * LocalizationBackend for android. It expects at least the R class to be
 * available. The subclasses "string", "plurals", "array" are being looked for
 * via reflection.
 * 
 * The backend pursues the "no exceptions" strategy. That means, that if
 * reflection errors occur or there are other errors, the backend will log those
 * using System.err, and return the requested string or an empty array of
 * strings. The latter bears the risk of IndexOutOfBounsExceptions.
 * 
 * Unfortunately this backend needs a lot of reflection to accomplish its task.
 */
public class AndroidLocalizationBackend implements LocalizationBackend {

	private Context context;

	/**
	 * Creates a new LocalizationBackend using the given android context to
	 * access string resources.
	 * 
	 * @param c
	 *            the android context to access string resources
	 */
	public AndroidLocalizationBackend(Context c) {
		this.context = c;
	}

	@Override
	public String translate(String s) {
		Class<?> container = getRContainer("string");
		if (container == null) {
			return s;
		}

		int id = getId(s, container);
		if (id != -1) {
			return context.getResources().getString(id);
		} else {
			return s;
		}
	}

	@Override
	public String translate(String s, int multiplicity) {
		Class<?> container = getRContainer("plurals");
		if (container == null) {
			return s;
		}

		int id = getId(s, container);
		if (id != -1) {
			return context.getResources().getQuantityString(id, multiplicity);
		} else {
			return s;
		}
	}

	@Override
	public String[] getLocalizedStringList(String identifier) {
		Class<?> container = getRContainer("array");
		if (container == null) {
			return new String[0];
		}

		int id = getId(identifier, container);
		if (id != -1) {
			return context.getResources().getStringArray(id);
		} else {
			return new String[0];
		}
	}

	@Override
	public void setApplicationLocale(Locale locale) {
		Resources res = context.getResources();
		// Change locale settings in the app.
		DisplayMetrics dm = res.getDisplayMetrics();
		android.content.res.Configuration conf = res.getConfiguration();
		conf.locale = locale;
		res.updateConfiguration(conf, dm);
	}

	@Override
	public Locale getApplicationLocale() {
		Resources res = context.getResources();
		android.content.res.Configuration conf = res.getConfiguration();
		return conf.locale;
	}

	@Override
	public Locale getSystemLocale() {
		return Locale.getDefault();
	}

	/**
	 * Gets a container class in R, like for example string, plurals or array.
	 * This is done via reflection, for the android sdk does not ensure any of
	 * those classes to be present, which would lead to compile errors. If the
	 * class being accessed is not found, null is returned.
	 * 
	 * @param name
	 *            the name of the subclass of R
	 * @return the inner class of R with the given name
	 */
	private Class<?> getRContainer(String name) {
		Class<?> clazz = R.class;
		Class<?>[] containerClasses = clazz.getDeclaredClasses();
		for (Class<?> c : containerClasses) {
			if (c.getSimpleName().equals(name)) {
				return c;
			}
		}
		System.err.println("Cannot localize container \"" + name + "\"");
		return null;
	}

	/**
	 * Extracts the resource id for a given resource name (xml "name" attribute)
	 * using reflection. This then can be used to retrieve a string using
	 * context.getResources.getXXX(id)
	 * 
	 * @param name
	 *            the name of the string resource
	 * @param container
	 *            the inner class of R which contains the desired resource
	 * @return the android resource id matching the given resource name
	 */
	private int getId(String name, Class<?> container) {
		try {
			Class<?> clazz = container;
			Field idField = clazz.getDeclaredField(name);
			if (int.class.isAssignableFrom(idField.getType())) {
				idField.setAccessible(true);
				if (idField.isAccessible()) {
					Integer id = (Integer) idField.get(null);
					return id;
				} else {
					System.err.println("Cannot access string \"" + name + "\"");
					return -1;
				}
			} else {
				System.err.println("The identifier \"" + name
						+ "\" does not denote a field of type int");
				return -1;
			}
		} catch (NoSuchFieldException x) {
			System.err.println("Cannot localize string \"" + name + "\"");
			return -1;
		} catch (IllegalAccessException e) {
			System.err.println("Cannot access string \"" + name + "\"");
			return -1;
		} catch (IllegalArgumentException e) {
			System.err.println("This should not habben EVER");
			return -1;
		}
	}
}
