package de.croggle.data.persistence.manager;

import android.content.Context;
import android.database.SQLException;

public abstract class TableManager {

	/**
	 * The DatabaseHelper is used to access the database in which the table is stored.
	 */
	DatabaseHelper databaseHelper;
	
	TableManager(Context context) {
		
	}
	
	void open() throws SQLException {
		
	}
	
	void close() throws SQLException {
		
	}
}
