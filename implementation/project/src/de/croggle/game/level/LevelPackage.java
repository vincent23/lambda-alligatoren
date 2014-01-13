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
	private Animation animation;
	private String design;

	/**
	 * Creates a level package with default values.
	 */
	public LevelPackage(int levelPackageId, String name, String description, String emblemPath, boolean hasAnimation, Animation animation, String design){
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
}
