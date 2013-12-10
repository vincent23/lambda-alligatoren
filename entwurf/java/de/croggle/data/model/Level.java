package de.croggle.data.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Represents a level entry in the database.
 */
public class Level implements Persistable {

	private long id;
	private long packageId;
	private String path;

	/**
	 * Construct a new Level based on it's properties.
	 *
	 * @param packageId The id of the package the level belongs to.
	 * @param path The path to the level file.
	 */
	public Level(long packageId, String path) {
	}

	/**
	 * Contstruct a new Level using a cursor to the correct database row.
	 *
	 * @param cursor The cursor.
	 */
	protected Level(android.database.Cursor cursor) {
	}

	/**
	 * Get the id of the level in the database.
	 *
	 * @return The id.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Get the id of the level's package in the database.
	 *
	 * @return The package id.
	 */
	public long getPackageId() {
		return packageId;
	}

	/**
	 * Set the id of the level's package/
	 *
	 * @param packageId The package id.
	 */
	public void setPackageId(long packageId) {
		this.packageId = packageId;
	}

	/**
	 * Get the path to the level file.
	 *
	 * @return The path.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Set the path to the level file.
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
