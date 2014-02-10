package de.croggle.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

/**
 * A class to help with additional capabilities of the different backends,
 * without directly referencing them (using reflection). By doing so, the helper
 * can be part of every platform's build without pulling in dependencies to the
 * different backends.
 * 
 * The class's methods degrade gracefully, meaning that they silently ignore if
 * a certain functionality is currently unavailable.
 */
public class BackendHelper {
	private BackendHelper() {
	}

	public static Object getWakeLock() {
		Application app = Gdx.app;
		Method getWakeLock = null;
		try {
			getWakeLock = app.getClass().getMethod("getWakeLock");
		} catch (NoSuchMethodException e) {
		}
		if (getWakeLock != null) {
			try {
				return getWakeLock.invoke(app);
			} catch (IllegalAccessException e) {
				return null;
			} catch (InvocationTargetException e) {
				return null;
			} catch (IllegalArgumentException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static boolean acquireWakeLock() {
		Object wakeLock = getWakeLock();
		if (wakeLock != null) {
			Method acquire = null;
			try {
				acquire = wakeLock.getClass().getMethod("acquire");
			} catch (NoSuchMethodException e) {
			}
			if (acquire != null) {
				try {
					acquire.invoke(wakeLock);
					return true;
				} catch (IllegalAccessException e) {
				} catch (IllegalArgumentException e) {
				} catch (InvocationTargetException e) {
				}
			}
		}
		return false;
	}

	public static boolean releaseWakeLock() {
		Object wakeLock = getWakeLock();
		if (wakeLock != null) {
			Method release = null;
			try {
				release = wakeLock.getClass().getMethod("release");
			} catch (NoSuchMethodException e) {
			}
			if (release != null) {
				try {
					release.invoke(wakeLock);
					return true;
				} catch (IllegalAccessException e) {
				} catch (IllegalArgumentException e) {
				} catch (InvocationTargetException e) {
				}
			}
		}
		return false;
	}
}
