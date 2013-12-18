package de.croggle.data.persistence;

public class Setting {
	
	private float volumeMusic;
	private float volumeEffects;
	private boolean zoomEnabled;
	private boolean colorblindEnabled;
	
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
