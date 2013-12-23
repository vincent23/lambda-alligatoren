package de.croggle.game.level;

import com.badlogic.gdx.graphics.g2d.Animation;

import de.croggle.game.Color;
import de.croggle.game.board.Board;

/**
 * This class represents the concept of a level within the game.
 * 
 * @composed 1 - 3 de.croggle.game.board.Board
 */
public abstract class Level {
	private int levelIndex;
	private int packageIndex;

	private Board initialBoard;
	private Board goalBoard;
	private Animation animation;

	private Color[] userColors;
	private String hint;
	private String description;
	private int abortSimulationAfter;

	/**
	 * Gets the index of the level package this level belongs to.
	 * 
	 * @return the index of the level package
	 */

	public int getPackageIndex() {
		return 0;
	}

	/**
	 * Gets the index of the level in the level package.
	 * 
	 * @return the index of the level
	 */
	public int getLevelIndex() {
		return 0;
	}

	/**
	 * Gets the board the level starts with.
	 * 
	 * @return the initial board
	 */
	public Board getInitialBoard() {
		return null;
	}

	/**
	 * Gets the board, which has to be reached to win the level.
	 * 
	 * @return the board which is the goal of the level
	 */
	public Board getGoalBoard() {
		return null;
	}

	/**
	 * Checks whether this level has a simulation or not.
	 * 
	 * @return true if the level has a simulation, otherwise false
	 */
	public boolean hasAnimation() {
		return false;
	}

	/**
	 * Gets the path to the animation of the level if it has one.
	 * 
	 * @return the path to the animation of the level
	 */
	public Animation getAnimation() {
		return null;
	}

	/**
	 * Gets the path to the hint of the level.
	 * 
	 * @return the path to the hint of the level
	 */
	public String gethint() {
		return null;
	}

	/**
	 * Gets the description of the level.
	 * 
	 * @return the description of the level
	 */
	public String getDescription() {
		return null;
	}

	/**
	 * Gets the colors usable by the user.
	 * 
	 * @return an array of colors
	 */
	public Color[] getUserColors() {
		return null;
	}

	/**
	 * Gets number of steps the simulation runs before it is aborted.
	 * 
	 * @return the number of steps the simulation runs
	 */
	public int getAbortSimulationAfter() {
		return 0;
	}
}
