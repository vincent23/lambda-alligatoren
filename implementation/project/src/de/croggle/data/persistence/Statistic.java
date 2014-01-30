package de.croggle.data.persistence;

/**
 * Represents - in the database - everything there is to know about the things a
 * user has done within the game .
 * 
 */
public class Statistic {

	/**
	 * The amount of time a user has played the game.
	 */
	private int playtime = 0;

	/**
	 * The number of used hints.
	 */
	private int usedHints = 0;

	/**
	 * The number of levels the user has completed.
	 */
	private int levelsComplete = 0;

	/**
	 * The number of packages the user has completed.
	 */
	private int packagesComplete = 0;

	/**
	 * The number of resets triggered by the user.
	 */
	private int resetsUsed = 0;

	/**
	 * The number of recoloring actions performed by the user.
	 */
	private int recolorings = 0;

	/**
	 * The number of alligators eaten during beta reductions.
	 */
	private int alligatorsEaten = 0;

	/**
	 * The number of alligators placed in the placement mode.
	 */
	private int alligatorsPlaced = 0;

	/**
	 * The number of eggs hatched during beta reductions.
	 */
	private int eggsHatched = 0;

	/**
	 * The number of eggs placed in the placement mode.
	 */
	private int eggsPlaced = 0;

	/**
	 * Creates a new default statistic.
	 */
	public Statistic() {

	}

	/**
	 *  Constructs a new Statistic based on its properties.
	 * 
	 * @param playtime
	 * 				the amount of time a user has played the game
	 * @param usedHints
	 * 				the number of used hints.
	 * @param resetsUsed
	 * 				the number of used resets.
	 * @param recolorings
	 * 				the number of recoloring actions performed
	 * @param levelsComplete
	 * 				the number of completed levels
	 * @param packagesComplete
	 * 				the number of completed packages
	 * @param alligatorsEaten
	 * 				the number of alligators eaten during beta reductions
	 * @param alligatorsPlaced
	 * 				the number of alligators placed in the placement mode
	 * @param eggsHatched
	 * 				the number of eggs hatched during beta reductions
	 * @param eggsPlaced
	 * 				the number of eggs placed in the placement mode
	 */
	public Statistic(int playtime, int usedHints, int resetsUsed,
			int recolorings, int levelsComplete, int packagesComplete,
			int alligatorsEaten, int alligatorsPlaced, int eggsHatched,
			int eggsPlaced) {
		this.playtime = playtime;
		this.usedHints = usedHints;
		this.resetsUsed = resetsUsed;
		this.recolorings = recolorings;
		this.levelsComplete = levelsComplete;
		this.packagesComplete = packagesComplete;
		this.alligatorsEaten = alligatorsEaten;
		this.alligatorsPlaced = alligatorsPlaced;
		this.eggsHatched = eggsHatched;
		this.eggsPlaced = eggsPlaced;

	}

	/**
	 * Gets the playtime.
	 * 
	 * @return the time spent playing
	 */
	public int getPlaytime() {
		return playtime;
	}

	/**
	 * Gets the number of hints used.
	 * 
	 * @return the number of hints used
	 */
	public int getUsedHints() {
		return usedHints;
	}

	/**
	 * Gets the number of completed levels.
	 * 
	 * @return the number of completed levels
	 */
	public int getLevelsComplete() {
		return levelsComplete;
	}

	/**
	 * Gets the number of completed packages.
	 * 
	 * @return the number of completed packages by the player
	 */
	public int getPackagesComplete() {
		return packagesComplete;
	}

	/**
	 * Gets the number of resets used.
	 * 
	 * @return the number of resets used by the player
	 */
	public int getResetsUsed() {
		return resetsUsed;
	}

	/**
	 * Gets the number of recoloring actions.
	 * 
	 * @return the number of recoloring actions
	 */
	public int getRecolorings() {
		return recolorings;
	}

	/**
	 * Gets the total number of alligators eaten during beta reductions.
	 * 
	 * @return the number of eaten alligators
	 */
	public int getAlligatorsEaten() {
		return alligatorsEaten;
	}

	/**
	 * Gets the total number of alligators placed in the placement mode.
	 * 
	 * @return the number of placed alligators
	 */
	public int getAlligatorsPlaced() {
		return alligatorsPlaced;
	}

	/**
	 * Gets the total number of eggs hatched during beta reductions.
	 * 
	 * @return the number of hatched eggs
	 */
	public int getEggsHatched() {
		return eggsHatched;
	}

	/**
	 * Gets the total number of eggs placed in the placement mode.
	 * 
	 * @return the number of placed eggs
	 */
	public int getEggsPlaced() {
		return eggsPlaced;
	}

	/**
	 * Sets the playtime.
	 * 
	 * @param playtime
	 *            the new playtime
	 */
	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}

	/**
	 * Sets the number of hints used.
	 * 
	 * @param usedHints
	 *            the new number of hints used
	 */
	public void setUsedHints(int usedHints) {
		this.usedHints = usedHints;
	}

	/**
	 * Sets the number of completed levels.
	 * 
	 * @param levelsComplete
	 *            the new number of completed levels
	 */
	public void setLevelsComplete(int levelsComplete) {
		this.levelsComplete = levelsComplete;
	}

	/**
	 * Sets the number of completed packages.
	 * 
	 * @param packagesComplete
	 *            the new number of completed packages
	 */
	public void setPackagesComplete(int packagesComplete) {
		this.packagesComplete = packagesComplete;
	}

	/**
	 * Sets the number of resets used.
	 * 
	 * @param resetsUsed
	 *            the new number of resets used
	 */
	public void setResetsUsed(int resetsUsed) {
		this.resetsUsed = resetsUsed;
	}

	/**
	 * Sets the number of recoloring actions.
	 * 
	 * @param recolorings
	 *            the new number of recoloring actions
	 */
	public void setRecolorings(int recolorings) {
		this.recolorings = recolorings;
	}

	/**
	 * Sets the number of eaten alligators.
	 * 
	 * @param alligatorsEaten
	 *            the new number of eaten alligators
	 */
	public void setAlligatorsEaten(int alligatorsEaten) {
		this.alligatorsEaten = alligatorsEaten;
	}

	/**
	 * Sets the number of placed alligators.
	 * 
	 * @param alligatorsPlaced
	 *            the new number of placed alligators
	 */
	public void setAlligatorsPlaced(int alligatorsPlaced) {
		this.alligatorsPlaced = alligatorsPlaced;
	}

	/**
	 * Sets the number of hatched eggs.
	 * 
	 * @param eggsHatched
	 *            the new number of hatched eggs
	 */
	public void setEggsHatched(int eggsHatched) {
		this.eggsHatched = eggsHatched;
	}

	/**
	 * Sets the number of placed eggs.
	 * 
	 * @param eggsPlaced
	 *            the new number of placed eggs
	 */
	public void setEggsPlaced(int eggsPlaced) {
		this.eggsPlaced = eggsPlaced;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Statistic other = (Statistic) obj;
		if (alligatorsEaten != other.alligatorsEaten)
			return false;
		if (alligatorsPlaced != other.alligatorsPlaced)
			return false;
		if (eggsHatched != other.eggsHatched)
			return false;
		if (eggsPlaced != other.eggsPlaced)
			return false;
		if (levelsComplete != other.levelsComplete)
			return false;
		if (packagesComplete != other.packagesComplete)
			return false;
		if (playtime != other.playtime)
			return false;
		if (recolorings != other.recolorings)
			return false;
		if (resetsUsed != other.resetsUsed)
			return false;
		if (usedHints != other.usedHints)
			return false;
		return true;
	}

}
