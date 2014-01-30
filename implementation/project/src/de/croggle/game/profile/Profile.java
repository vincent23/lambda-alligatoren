package de.croggle.game.profile;

import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.Statistic;

/**
 * @navassoc 1 Has 1 de.croggle.data.persistence.Statistic
 * @navassoc 1 Has 1 de.croggle.data.persistence.Setting
 */

/**
 * Represents a profile with its settings and statistics.
 */
public class Profile {

	/**
	 * The profile's name.
	 */
	private String name;

	/**
	 * The path to the place where the profile's picture is stored.
	 */
	private String picturePath;

	/**
	 * The profile's setting.
	 */
	private Setting setting;

	/**
	 * The profile's statistic.
	 */
	private Statistic statistic;

	/**
	 * Creates a new profile with default settings and statistics.
	 * 
	 * @param name
	 *            the name of the profile
	 * @param picturePath
	 *            the path to the profiles' picture
	 */
	public Profile(String name, String picturePath) {
		this.name = name;
		this.picturePath = picturePath;
		this.setting = new Setting();
		this.statistic = new Statistic();

	}

	/**
	 * Get the profile's name.
	 * 
	 * @return the profile's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the profile's name.
	 * 
	 * @param name
	 *            the new name of the profile
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the path to where the profile's picture is stored.
	 * 
	 * @return
	 *            the path to the location where the profile's picture is stored
	 */
	public String getPicturePath() {
		return picturePath;
	}

	/**
	 * Set the profile's picture path.
	 * 
	 * @param picturePath
	 *            The new path to the profile's picture
	 */
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	/**
	 * Get the profile's setting.
	 * 
	 * @return
	 *            the profile's setting
	 */
	public Setting getSetting() {
		return setting;
	}

	/**
	 * Set the profile's setting.
	 * 
	 * @param setting
	 *            the new setting of the profile
	 */
	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	/**
	 * Get the profile's statistic.
	 * 
	 * @return
	 * 		 the profile's statistic
	 */
	public Statistic getStatistic() {
		return statistic;
	}

	/**
	 * Set the profile's statistic.
	 * 
	 * @param statistic
	 *            the new statistic of the profile
	 */
	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (picturePath == null) {
			if (other.picturePath != null)
				return false;
		} else if (!picturePath.equals(other.picturePath))
			return false;
		return true;
	}

}
