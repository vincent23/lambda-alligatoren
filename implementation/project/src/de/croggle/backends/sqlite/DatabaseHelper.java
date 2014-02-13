package de.croggle.backends.sqlite;

/**
 * This class is responsible for creating and managing the database with its
 * different tables.
 */
public interface DatabaseHelper {

	/**
	 * The name of the database.
	 */
	public static final String DATABASE_NAME = "persistenceDB";

	/**
	 * Creates all tables if they don't already exist.
	 */
	public void onCreate(Database db);

	/**
	 * Deletes the old database and creates a new one.
	 */
	public void onUpgrade(Database db, int oldVersion, int newVersion);

	Database getWritableDatabase();

	void close();
}
