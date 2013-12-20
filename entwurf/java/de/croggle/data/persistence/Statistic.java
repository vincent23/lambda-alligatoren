package de.croggle.data.persistence;

/**
 * Represents everything there is to know about the things a user has done within the game in the database.
 *
 */
public class Statistic {
	
	/**
	 * The amount of time a user has played the game.
	 */
	private int playtime;
	
	/**
	 * The number of hints used by the user.
	 */
	private int usedHints;
	
	/**
	 * The number of levels the user has completed.
	 */
	private int levelsComplete;
	
	/**
	 * The number of packages the user has completed.
	 */
	private int packagesComplete;
	
	/**
	 * The number of resets done by the user.
	 */
	private int resetsUsed;
	
	/**
	 * The number of recoloring actions performed by the user.
	 */
    private int recolorings;

    /**
	 * The number of alligators eaten during beta reductions.
	 */
	private int alligatorsEaten;
	
	/**
	 * The number of alligators placed in the placement mode.
	 */
	private int alligatorsPlaced;
	
	/**
	 * The number of eggs hatched during beta reductions.
	 */
	private int eggsHatched;
	
	/**
	 * The number of eggs placed in the placement mode.
	 */
	private int eggsPlaced;

	/**
	 * Creates a new default statistic.
	 */
	public Statistic() {
		
	}

	/**
	 * Get the playtime.
	 * @return The playtime is returned.
	 */
	public int getPlaytime() {
		return playtime;
	}

	/**
	 * Get the number of hints used.
	 * @return The number of hints used.
	 */
	public int getUsedHints() {
		return usedHints;
	}

	/**
	 * Get the number of completed levels.
	 * @return The number of completed levels.
	 */
	public int getLevelsComplete() {
		return levelsComplete;
	}

	/**
	 * Get the number of completed packages.
	 * @return The number of completed packages.
	 */
	public int getPackagesComplete() {
		return packagesComplete;
	}

	/**
	 * Get the number of resets used.
	 * @return The number of used resets.
	 */
	public int getResetsUsed() {
		return resetsUsed;
	}

	/**
	 * Get the number of recoloring actions.
	 * @return The number of recoloring actions.
	 */
	public int getRecolorings() {
		return recolorings;
	}

	/**
	 * Get the total number of alligators eaten during beta reductions.
	 * @return The number of eaten alligators.
	 */
	public int getAlligatorsEaten() {
		return alligatorsEaten;
	}

	/**
	 * Get the total number of alligators placed in the placement mode.
	 * @return The number of eaten alligators.
	 */
	public int getAlligatorsPlaced() {
		return alligatorsPlaced;
	}

	/**
	 * Get the total number of eggs hatched during beta reductions.
	 * @return The number of eaten alligators.
	 */
	public int getEggsHatched() {
		return eggsHatched;
	}
	
	/**
	 * Get the total number of eggs placed in the placement mode.
	 * @return The number of eaten alligators.
	 */
	public int getEggsPlaced() {
		return eggsPlaced;
	}

	/**
	 * Set the playtime.
	 * @param playtime The new playtime.
	 */
	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}

	/**
	 * Set the number of hints used.
	 * @param playtime The new number of hints used.
	 */
	public void setUsedHints(int usedHints) {
		this.usedHints = usedHints;
	}

	/**
	 * Set the number of completed levels.
	 * @param playtime The new number of completed levels.
	 */
	public void setLevelsComplete(int levelsComplete) {
		this.levelsComplete = levelsComplete;
	}

	/**
	 * Set the number of completed packages.
	 * @param playtime The new number of completed packages.
	 */
	public void setPackagesComplete(int packagesComplete) {
		this.packagesComplete = packagesComplete;
	}

	/**
	 * Set the number of resets used.
	 * @param playtime The new amount of resets used.
	 */
	public void setResetsUsed(int resetsUsed) {
		this.resetsUsed = resetsUsed;
	}

	/**
	 * Set the number of recoloring actions.
	 * @param playtime The new amount of recoloring actions.
	 */
	public void setRecolorings(int recolorings) {
		this.recolorings = recolorings;
	}

	/**
	 * Set the number of eaten alligators.
	 * @param playtime The new number of eaten alligators.
	 */
	public void setAlligatorsEaten(int alligatorsEaten) {
		this.alligatorsEaten = alligatorsEaten;
	}

	/**
	 * Set the number of placed alligators.
	 * @param playtime The new number of placed alligators.
	 */
	public void setAlligatorsPlaced(int alligatorsPlaced) {
		this.alligatorsPlaced = alligatorsPlaced;
	}

	/**
	 * Set the number of hatched eggs.
	 * @param playtime The new number of hatched eggs.
	 */
	public void setEggsHatched(int eggsHatched) {
		this.eggsHatched = eggsHatched;
	}

	/**
	 * Set the number of hatched placed.
	 * @param playtime The new number of hatched placed.
	 */
	public void setEggsPlaced(int eggsPlaced) {
		this.eggsPlaced = eggsPlaced;
	}



	


	
	
}
