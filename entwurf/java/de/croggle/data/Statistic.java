package de.croggle.data;

public class Statistic {
	
	private int playtime;
	private int usedHints;
	private int levelsComplete;
	private int packagesComplete;
	
	// ...
	
	public Statistic() {
		
	}

	public int getPlaytime() {
		return playtime;
	}

	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}

	public int getUsedHints() {
		return usedHints;
	}

	public void setUsedHints(int hints) {
		this.usedHints = hints;
	}

	public int getLevelsComplete() {
		return levelsComplete;
	}

	public void setLevelsComplete(int levelsComplete) {
		this.levelsComplete = levelsComplete;
	}

	public int getPackagesComplete() {
		return packagesComplete;
	}

	public void setPackagesComplete(int packagesComplete) {
		this.packagesComplete = packagesComplete;
	}
	
	
}
