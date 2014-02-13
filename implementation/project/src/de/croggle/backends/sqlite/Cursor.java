package de.croggle.backends.sqlite;

public interface Cursor {
	boolean moveToFirst();

	int getColumnIndex(String name);

	String getString(int index);

	int getInt(int columnIndex);

	boolean moveToNext();

	float getFloat(int column);
}
