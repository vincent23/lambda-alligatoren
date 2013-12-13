package de.croggle.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Represents a level package entry in the database.
 */
public class LevelPackage implements Persistable {

	private long id;
	private String path;

	/**
	 * Construct a new LevelPackage based on it's properties.
	 *
	 * @param path The path to the level package.
	 */
	public LevelPackage(String path) {
	}

	/**
	 * Contstruct a new Level using a cursor to the correct database row.
	 *
	 * @param cursor The cursor.
	 */
	protected LevelPackage(android.database.Cursor cursor) {
	}

	/**
	 * Get the id of the level package in the database.
	 *
	 * @return The id.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Get the path to the level package.
	 *
	 * @return The path.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Set the path to the level package.
	 *
	 * @param path The path.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public boolean persist(android.database.sqlite.SQLiteDatabase database) {
	}
}
