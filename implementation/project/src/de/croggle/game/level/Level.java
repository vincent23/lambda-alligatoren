package de.croggle.game.level;


import java.util.List;

import de.croggle.AlligatorApp;
import de.croggle.game.GameController;
import de.croggle.game.board.Board;

/**
 * This class represents the concept of a level within the game.
 */
public abstract class Level {
	private int levelIndex;
	private int packageIndex;

	private Board initialBoard;
	private Board goalBoard;
	private List<String> animation;
	private String hint;
	private String description;
	private int abortSimulationAfter;
	private boolean unlocked;
	private boolean solved;
	private boolean showObjectBar;

	/**
	 * Creates a new level with the given parameters.
	 * 
	 * @param levelIndex
	 *            the level index of the level in the package
	 * @param packageIndex
	 *            the index of the levels package
	 * @param initialBoard
	 *            the initial Board the level starts with.
	 * @param goalBoard
	 *            the board, which have to be achieved to complete the level
	 * @param animation
	 *            the path to the animation of the level
	 * @param hint
	 *            the hint given to the user if he pushes the hint button
	 * @param description
	 *            the description of the level
	 * @param abortSimulationAfter
	 *            number of evaluation steps the simulation is aborted after
	 */

	public Level(int levelIndex, int packageIndex, Board initialBoard,
			Board goalBoard, List<String> animation, String hint,
			String description, int abortSimulationAfter, boolean showObjectBar) {
		this.levelIndex = levelIndex;
		this.packageIndex = packageIndex;
		this.initialBoard = initialBoard;
		this.goalBoard = goalBoard;
		this.animation = animation;
		this.hint = hint;
		this.description = description;
		this.abortSimulationAfter = abortSimulationAfter;
		this.solved = false;
		this.unlocked = false;
		this.showObjectBar = showObjectBar;
	}

	/**
	 * Gets the index of the level package this level belongs to.
	 * 
	 * @return the index of the level package
	 */

	public int getPackageIndex() {
		return packageIndex;
	}

	/**
	 * Gets the index of the level in the level package.
	 * 
	 * @return the index of the level
	 */
	public int getLevelIndex() {
		return levelIndex;
	}

	/**
	 * Gets the board the level starts with.
	 * 
	 * @return the initial board
	 */
	public Board getInitialBoard() {
		return initialBoard;
	}

	/**
	 * Gets the board, which has to be reached to win the level.
	 * 
	 * @return the board which is the goal of the level
	 */
	public Board getGoalBoard() {
		return goalBoard;
	}

	/**
	 * Checks whether this level has a simulation or not.
	 * 
	 * @return true if the level has a simulation, otherwise false
	 */
	public boolean hasAnimation() {
		return(!animation.get(0).equals(""));
	}

	/**
	 * Gets the path to the animation of the level if it has one.
	 * 
	 * @return the path to the animation of the level
	 */

	public List<String> getAnimation() {
		return animation;
	}

	/**
	 * Gets the path to the hint of the level.
	 * 
	 * @return the path to the hint of the level
	 */
	public String getHint() {
		return hint;
	}

	/**
	 * Gets the description of the level.
	 * 
	 * @return the description of the level
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets number of steps the simulation runs before it is aborted.
	 * 
	 * @return the number of steps the simulation runs
	 */
	public int getAbortSimulationAfter() {
		return abortSimulationAfter;
	}

	/**
	 * Gets whether this level is unlocked or not.
	 * 
	 * @return whether this level is unlocked or not
	 */
	public boolean getUnlocked() {
		return this.unlocked;
	}

	/**
	 * Sets the Parameter unlocked.
	 * 
	 * @param unlocked
	 *            the value unlocked should be set to.
	 */
	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}

	/**
	 * Method to determine whether this level is solved or not during the
	 * simulation.
	 * 
	 * @param solution
	 *            the current Board the user has entered after a number of
	 *            evaluation steps.
	 * @param steps
	 *            the number of evaluation steps.
	 * @return if the current level is solved or not
	 */
	abstract public boolean isLevelSolved(Board solution, int steps);

	/**
	 * Method to check if the level is still solvable according to the number of
	 * evaluation steps or not. This method should be called after checking
	 * whether the level is solved or not.
	 * 
	 * @param steps
	 *            the number of evaluation steps
	 * @return whether the level is still solvable or not
	 */
	public boolean isSolveable(int steps) {
		boolean solvable = true;
		if (this.abortSimulationAfter < 0
				&& Math.abs(this.abortSimulationAfter) <= steps) {
			solvable = false;
		}
		return solvable;
	}

	/**
	 * Method to return the Parameter solved.
	 * 
	 * @return if the Level is solved.
	 */
	public boolean isSolved() {
		return solved;
	}

	/**
	 * Sets the attribute solved to true.
	 */
	protected void setSolvedTrue() {
		this.solved = true;
	}

	public GameController createGameController(AlligatorApp app) {
		return new GameController(app, this);
	}

	/**
	 * Get a unique identifier for this level.
	 * 
	 * @return A unique value to identify this level, e.g. in the database
	 */
	public int getLevelId() {
		final int index = getLevelIndex();
		final int pack = getPackageIndex();
		// TODO 100 level / package limit
		return pack * 100 + index;
	}

	public boolean getShowObjectBar() {
		return this.showObjectBar;
	}

}
