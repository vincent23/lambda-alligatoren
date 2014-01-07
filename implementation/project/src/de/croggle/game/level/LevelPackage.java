package de.croggle.game.level;

/**
 * Compilation of several, thematically linked levels.
 */
public class LevelPackage {
	private int size;
	private int levelPackageId;
	private String name;
	private String description;
	private String emblemPath;
	private boolean hasAnimation;

	/**
	 * Creates a level package with default values.
	 */
	public LevelPackage() {
	}

	/**
	 * Creates a level package with the given id. All other fields have the
	 * default values.
	 * 
	 * @param levelPackageId
	 *            the folder id of the levelPackage
	 */
	public LevelPackage(int levelPackageId) {
		this.levelPackageId = levelPackageId;
	}

	/**
	 * Gets the level controller which is responsible for handling the levels
	 * within the level package.
	 * 
	 * @return the level controller one must use to handle the levels within the
	 *         level package
	 */
	public LevelController getLevelController() {
		return null;
	}

	/**
	 * Returns the amount of levels in this package.
	 * 
	 * @return the size of the package
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns the unique identifier of the package, which is defined as the
	 * name of the folder the package represents.
	 * 
	 * @return the package id
	 */
	public int getLevelPackageId() {
		return levelPackageId;
	}

	/**
	 * Returns the packages name.
	 * 
	 * @return the package name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns a textual description of the package.
	 * 
	 * @return the package description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the file path to the graphical representation of the package.
	 * 
	 * @return the emblem path
	 */
	public String getEmblemPath() {
		return emblemPath;
	}

	/**
	 * Indicates whether the package has an animation, which is shown when it is
	 * started for the first time (like a little story that is told).
	 * 
	 * @return true, if the package has an animation defined, false otherwise
	 */
	public boolean hasAnimation() {
		return hasAnimation;
	}
}
