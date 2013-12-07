package de.croggle.data.model;

import android.database.Cursor;

public class Level {

	private long id;
	private long packageId;
	private String path;

	public Level(long packageId, String path) {
	}

	protected Level(android.database.Cursor cursor) {
	}

	public long getPackageId() {
		return packageId;
	}

	public void setPackageId(long packageId) {
		this.packageId = packageId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
