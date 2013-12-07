package de.croggle.data.model;

import android.database.Cursor;

class LevelPackage {

	private long id;
	private String path;

	public LevelPackage(String path) {
	}

	protected LevelPackage(android.database.Cursor cursor) {
	}

	public String getPath() {
		return path;
	}

	public void setPath() {
		this.path = path;
	}
}
