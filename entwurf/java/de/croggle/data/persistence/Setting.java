package de.croggle.data.persistence;

/**
 * Represents the settings of a certain profile in the database.
 *
 */
public class Setting {
	
	/**
	 * The volume of the music played in the game.
	 */
	private float volumeMusic;
	
	/**
	 * The volume of the effects used in the game.
	 */
	private float volumeEffects;
	
	/**
	 * Determines whether the zoom button is enabled or not.
	 */
	private boolean zoomEnabled;
	
	/**
	 * Determines whether colorblind mode is enabled or not.
	 */	
	private boolean colorblindEnabled;
	
	/**
	 * 
	 * Construct a new LevelProgress based on it's properties.
	 *
	 * @param volumeMusic the volume of the music.
	 * @param volumeEffect the volume of the effects.
	 * @param zoomEnabled determines whether the zoom button is enabled or not.
	 * @param colorblindEnabled determines whether colorblind mode is enabled or not.
	 */
	public Setting(float volumeMusic, float volumeEffect, boolean zoomEnabled, boolean colorblindEnabled) {
		
	}
	
	/**
	 * Creates a new default setting.
	 */
	public Setting() {
		
	}
	
	/**
	 * Get the music volume.
	 * @return the volume of the music
	 */
	public float getVolumeMusic() {
		return volumeMusic;
	}
	
	/**
	 * Set the music volume.
	 * @param volumeMusic the volume of the music
	 */
	public void setVolumeMusic(float volumeMusic) {
		this.volumeMusic = volumeMusic;
	}

	/**
	 * Get the effect volume.
	 * @return the volume of the effects
	 */
	public float getVolumeEffects() {
		return volumeEffects;
	}

	/**
	 * Set the effect volume.
	 * @return the volume of the effects
	 */
	public void setVolumeEffects(float volumeEffects) {
		this.volumeEffects = volumeEffects;
	}

	/**
	 * Get whether zoom button is enabled or not.
	 * @return if the oom button is enabled true is returned, else false is returned
	 */
	public boolean isZoomEnabled() {
		return zoomEnabled;
	}

	/**
	 * Set the whether zoom button is enabled or not.
	 * @return true if zoom button is enabled, false otherwise
	 */
	public void setZoomEnabled(boolean zoomEnabled) {
		this.zoomEnabled = zoomEnabled;
	}

	/**
	 * Get whether the colorblind mode is enabled or not.
	 * @return if the colorblind mode is enabled true is returned, else false is returned
	 */
	public boolean isColorblindEnabled() {
		return colorblindEnabled;
	}

	/**
	 * Set the whether the colorblind mode is enabled or not.
	 * @return true if the colorblind mode is enabled, false otherwise
	 */
	public void setColorblindEnabled(boolean colorblindEnabled) {
		this.colorblindEnabled = colorblindEnabled;
	}

}
