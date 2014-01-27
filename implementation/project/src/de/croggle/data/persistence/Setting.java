package de.croggle.data.persistence;

/**
 * Represents the settings of a certain profile in the database.
 * 
 */
public class Setting {

	/**
	 * The volume of the music played in the game.
	 */
	private float volumeMusic = 0.5f;

	/**
	 * The volume of the effects used in the game.
	 */
	private float volumeEffects = 0.5f;

	/**
	 * Determines whether the zoom button is enabled or not.
	 */
	private boolean zoomEnabled = false;

	/**
	 * Determines whether colorblind mode is enabled or not.
	 */
	private boolean colorblindEnabled = false;

	/**
	 * 
	 * Constructs a new LevelProgress based on its properties.
	 * 
	 * @param volumeMusic
	 *            the volume of the music.
	 * @param volumeEffects
	 *            the volume of the effects.
	 * @param zoomEnabled
	 *            determines whether the zoom button is enabled or not.
	 * @param colorblindEnabled
	 *            determines whether colorblind mode is enabled or not.
	 */
	public Setting(float volumeMusic, float volumeEffects, boolean zoomEnabled,
			boolean colorblindEnabled) {
		this.volumeMusic = volumeMusic;
		this.volumeEffects = volumeEffects;
		this.zoomEnabled = zoomEnabled;
		this.colorblindEnabled = colorblindEnabled;

	}

	/**
	 * Creates a new default setting.
	 */
	public Setting() {

	}

	/**
	 * Gets the music volume.
	 * 
	 * @return the volume of the music
	 */
	public float getVolumeMusic() {
		return volumeMusic;
	}

	/**
	 * Sets the music's volume.
	 * 
	 * @param volumeMusic
	 *            the volume of the music
	 */
	public void setVolumeMusic(float volumeMusic) {
		this.volumeMusic = volumeMusic;
	}

	/**
	 * Gets the effect volume.
	 * 
	 * @return the volume of the effects
	 */
	public float getVolumeEffects() {
		return volumeEffects;
	}

	/**
	 * Sets the effect volume.
	 * 
	 * @param volumeEffects
	 *            the volume of the effects
	 */
	public void setVolumeEffects(float volumeEffects) {
		this.volumeEffects = volumeEffects;
	}

	/**
	 * Gets whether zoom button is enabled or disabled.
	 * 
	 * @return true if the zoom button is enabled, false otherwise
	 */
	public boolean isZoomEnabled() {
		return zoomEnabled;
	}

	/**
	 * Sets zoom button to enabled or disabled.
	 * 
	 * @param zoomEnabled
	 *            true for enabling zoom, false for disabling it
	 */
	public void setZoomEnabled(boolean zoomEnabled) {
		this.zoomEnabled = zoomEnabled;
	}

	/**
	 * Gets whether the colorblind mode is enabled or disabled.
	 * 
	 * @return true if the colorblind mode is enabled, false otherwise
	 */
	public boolean isColorblindEnabled() {
		return colorblindEnabled;
	}

	/**
	 * Sets the colorblind mode to enabled or disabled.
	 * 
	 * @param colorblindEnabled
	 *            true for enabling colorblind mode, false for disabling it
	 */
	public void setColorblindEnabled(boolean colorblindEnabled) {
		this.colorblindEnabled = colorblindEnabled;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setting other = (Setting) obj;
		if (colorblindEnabled != other.colorblindEnabled)
			return false;
		if (Float.floatToIntBits(volumeEffects) != Float
				.floatToIntBits(other.volumeEffects))
			return false;
		if (Float.floatToIntBits(volumeMusic) != Float
				.floatToIntBits(other.volumeMusic))
			return false;
		if (zoomEnabled != other.zoomEnabled)
			return false;
		return true;
	}

}
