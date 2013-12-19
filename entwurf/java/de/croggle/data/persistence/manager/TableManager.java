package de.croggle.data.persistence.manager;

import android.content.Context;
import android.database.SQLException;

public abstract class TableManager {

	/**
	 * The DatabaseHelper is used to access the database in which the table is stored.
	 */
	DatabaseHelper databaseHelper;
	
	public TableManager(Context context) {
		
	}
	
	public void open() throws SQLException {
		
	}
	
	public void close() throws SQLException {
		
	}
}
