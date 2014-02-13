package de.croggle.backends.sqlite;

public interface ContentValues {
	void put(String key, float value);

	void put(String key, String value);

	void put(String key, int value);

	void put(String key, boolean value);
}
