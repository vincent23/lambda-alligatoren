package de.croggle.backends.android;

import de.croggle.backends.sqlite.Cursor;

public class AndroidCursor implements Cursor {

	private final android.database.Cursor cursor;

	public AndroidCursor(android.database.Cursor cursor) {
		this.cursor = cursor;
	}

	@Override
	public boolean moveToFirst() {
		return cursor.moveToFirst();
	}

	@Override
	public int getColumnIndex(String name) {
		return cursor.getColumnIndex(name);
	}

	@Override
	public String getString(int index) {
		return cursor.getString(index);
	}

	@Override
	public int getInt(int columnIndex) {
		return cursor.getInt(columnIndex);
	}

	@Override
	public boolean moveToNext() {
		return cursor.moveToNext();
	}

	@Override
	public float getFloat(int column) {
		return cursor.getFloat(column);
	}

}
