package de.croggle.backends.sqlite;

public class DatabaseUtils {
	private DatabaseUtils() {

	}

	public static long queryNumEntries(Database db, String table) {
		return db.queryForLong("select count(*) from " + table, null);
	}
}
