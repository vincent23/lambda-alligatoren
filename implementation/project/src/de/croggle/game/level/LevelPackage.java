package de.croggle.game.level;

import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Compilation of several, thematically linked levels.
 */
public class LevelPackage {
	private int levelPackageId;
	private String name;
	private String description;
	private String emblemPath;
	private boolean hasAnimation;
	private String animation;
	private String design;

	/**
	 * Creates a level package with default values.
	 * 
	 * @param levelPackageId
	 *            the id of this package
	 * @param name
	 *            the name of this package
	 * @param description
	 *            the description of this package
	 * @param emblemPath
	 *            the file path to the emblem of this package
	 * @param hasAnimation
	 *            an boolean which is true if this package has an animation
	 * @param animation
	 *            the animation of this package
	 * @param design
	 *            the file path to the desing of this package
	 */
	public LevelPackage(int levelPackageId, String name, String description,
			String emblemPath, boolean hasAnimation, String animation,
			String design) {
		this.levelPackageId = levelPackageId;
		this.name = name;
		this.description = description;
		this.emblemPath = emblemPath;
		this.hasAnimation = hasAnimation;
		this.animation = animation;
		this.design = design;
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

	/**
	 * Gets the Animation of this levelPackage if there is one.
	 * 
	 * @return the Animation if there is one else null is returned
	 */
	public String getAnimation() {
		if (this.hasAnimation()) {
			return this.animation;
		} else {
			return null;
		}
	}

	/**
	 * gets the path to the design of this level.
	 * 
	 * @return the design of this level
	 */
	public String getDesign() {
		return this.design;
	}
}
