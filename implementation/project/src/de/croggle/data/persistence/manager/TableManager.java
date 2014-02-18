package de.croggle.data.persistence.manager;

import de.croggle.backends.BackendHelper;
import de.croggle.backends.sqlite.Database;
import de.croggle.backends.sqlite.DatabaseHelper;
import de.croggle.backends.sqlite.SQLException;

/**
 * An abstract superclass for all classes which manage tables.
 * 
 */
public abstract class TableManager {

	/**
	 * The DatabaseHelper is used for accessing the database in which the table
	 * is stored.
	 */
	protected DatabaseHelper databaseHelper;

	/**
	 * The database in which the table is stored.
	 */
	protected Database database;

	/**
	 * Creates a new TableManager, which manages a specific table from the
	 * database that belongs to the given context.
	 * 
	 * @param context
	 *            the context that is used for opening or, if needed, creating
	 *            the database
	 */
	TableManager() {
		databaseHelper = BackendHelper.getNewDatabaseHelper();
	}

	/**
	 * Prepares the table for read and write operations. Must be called before
	 * every access to the table.
	 * 
	 * @throws SQLException
	 *             the exception is thrown if the database could not be accessed
	 */
	void open() throws SQLException {
		database = databaseHelper.getWritableDatabase();
		if (!database.isReadOnly()) {
			database.execSQL("PRAGMA foreign_keys = ON;");
		}
	}

	/**
	 * Closes the open table. Must be called at the end of reading and writing
	 * operations.
	 * 
	 * @throws SQLException
	 *             the exception is thrown if the database could not be accessed
	 */
	void close() throws SQLException {
		databaseHelper.close();
	}

	/**
	 * Clears the table.
	 */

	// NEW
	abstract void clearTable();

	/**
	 * Get the number of rows stored in the table
	 */
	// NEW
	abstract long getRowCount();

}
