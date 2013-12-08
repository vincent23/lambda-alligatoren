package de.croggle.data.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LevelPackage implements Persistable {

	private long id;
	private String path;

	public LevelPackage(String path) {
	}

	protected LevelPackage(android.database.Cursor cursor) {
	}

	public long getId() {
		return id;
	}

	public String getPath() {
		return path;
	}

	public void setPath() {
		this.path = path;
	}

	public boolean persist(android.database.sqlite.SQLiteDatabase database) {
	}
}
