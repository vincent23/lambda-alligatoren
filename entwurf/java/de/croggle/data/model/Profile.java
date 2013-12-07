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
	private float volumeMusic;
	private float volumeEffects;
	private boolean zoomEnabled;
	private boolean colorblindEnabled;

	public Profile(String name, String picturePath, float volumeMusic, float volumeEffects, boolean zoomEnabled, boolean colorblindEnabled) {
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

	public float getVolumeMusic() {
		return volumeMusic;
	}

	public void setVolumeMusic(float volumeMusic) {
		this.volumeMusic = volumeMusic;
	}

	public float getVolumeEffects() {
		return volumeEffects;
	}

	public void setVolumeEffects(float volumeEffects) {
		this.volumeEffects = volumeEffects;
	}

	public boolean isZoomEnabled() {
		return zoomEnabled;
	}

	public void setZoomEnabled(boolean zoomEnabled) {
		this.zoomEnabled = zoomEnabled;
	}

	public boolean isColorblindEnabled() {
		return colorblindEnabled;
	}

	public void setColorblindEnabled(boolean colorblindEnabled) {
		this.colorblindEnabled = colorblindEnabled;
	}
}
