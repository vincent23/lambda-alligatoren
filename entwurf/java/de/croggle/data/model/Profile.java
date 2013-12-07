package de.croggle.data.model;

import android.database.Cursor;

/**
 * @navassoc 1 Has 1 de.croggle.data.model.UserData
 * @navassoc 1 Has 1 de.croggle.data.model.Settings
 */

public class Profile {

	private long id;
	private String name;
	private String picturePath;

	public Profile(String name, String picturePath) {
	}

	protected Profile(Cursor cursor) {
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
}
