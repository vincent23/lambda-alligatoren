package de.croggle.data.model;

import android.database.sqlite.SQLiteDatabase;

/**
 * Interface for objects which can be saved in the database.
 */
public interface Persistable {
	/**
	 * Saves the objects state in the database.
	 *
	 * @param database A writeable database object.
	 * @return True if the object was persisted, false otherwise.
	 */
	public boolean persist(android.database.sqlite.SQLiteDatabase database);
}
