package de.croggle.data.model;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class Settings implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private float volume_music;
	private float volume_effects;
	private boolean zoom_enabled;
	private boolean colorblind_enabled;
	
	public Settings(float volume_music, float volume_effects, boolean zoom_enabled, boolean colorblind_enabled) {
		
	}

	public float getVolume_music() {
		return volume_music;
	}

	public void setVolume_music(float volume_music) {
		this.volume_music = volume_music;
	}

	public float getVolume_effects() {
		return volume_effects;
	}

	public void setVolume_effects(float volume_effects) {
		this.volume_effects = volume_effects;
	}

	public boolean isZoom_enabled() {
		return zoom_enabled;
	}

	public void setZoom_enabled(boolean zoom_enabled) {
		this.zoom_enabled = zoom_enabled;
	}

	public boolean isColorblind_enabled() {
		return colorblind_enabled;
	}

	public void setColorblind_enabled(boolean colorblind_enabled) {
		this.colorblind_enabled = colorblind_enabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub
		
	}



   

}
