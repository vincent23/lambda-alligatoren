package de.croggle.data;

/**
 * @navassoc 1 Has 1 de.croggle.data.manager.Statistic
 * @navassoc 1 Has 1 de.croggle.data.manager.Setting
 */

public class Profile {

	private long id;
	private String name;
	private String picturePath;

	private Setting setting;
	private Statistic statistic;

	public Profile(long id, String name, String picturePath) {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public Statistic getStatistic() {
		return statistic;
	}

	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

}
