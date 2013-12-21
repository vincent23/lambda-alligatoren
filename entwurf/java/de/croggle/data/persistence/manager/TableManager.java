package de.croggle.data.persistence.manager;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Table Manager is an abstract superclass for all classes which manage tables.
 *
 */
public abstract class TableManager {

	/**
	 * The DatabaseHelper is used to access the database in which the table is stored.
	 */
	DatabaseHelper databaseHelper;
	
	/**
	 * The database in which the table is stored.
	 */
	SQLiteDatabase databse;
	
		
	/**
	 * Creates a new Table Manager which manages a specific table form the database that belongs to the Context context.
	 * @param context the context that is used to open or, if needed, create the database
	 */
	TableManager(Context context) {
		
	}
	
	/**
	 * Prepares the table for read and write operations. Must be called before every access to the table.
	 * @throws SQLException
	 */
	void open() throws SQLException {
		
	}
	
	/**
	 * Closes the open table. Must be called at the end of reading and writing operations.
	 * @throws SQLException
	 */
	void close() throws SQLException {
		
	}
}
