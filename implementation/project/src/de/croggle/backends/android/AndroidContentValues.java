package de.croggle.backends.android;

import de.croggle.backends.sqlite.ContentValues;

public class AndroidContentValues implements ContentValues {

	private final android.content.ContentValues content;

	public AndroidContentValues() {
		content = new android.content.ContentValues();
	}

	@Override
	public void put(String key, float value) {
		content.put(key, value);
	}

	@Override
	public void put(String key, String value) {
		content.put(key, value);
	}

	@Override
	public void put(String key, int value) {
		content.put(key, value);
	}

	@Override
	public void put(String key, boolean value) {
		content.put(key, value);
	}

	@Override
	public Object get() {
		return content;
	}
}
