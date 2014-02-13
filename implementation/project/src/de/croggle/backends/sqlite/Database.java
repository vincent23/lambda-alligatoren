package de.croggle.backends.sqlite;

public interface Database {
	void execSQL(String sql);

	boolean isReadOnly();

	Cursor rawQuery(String selection, String[] args);

	int update(String table, ContentValues values, String whereClause,
			String[] whereArgs);

	int delete(String table, String whereClause, String[] whereArgs);

	long insert(String table, String nullColumnHack, ContentValues values);

	long queryForLong(String selection, String[] args);
}
