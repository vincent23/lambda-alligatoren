package de.croggle.game.level;

import com.badlogic.gdx.graphics.g2d.Animation;

import de.croggle.game.Color;
import de.croggle.game.ColorController;
import de.croggle.game.board.Board;
import de.croggle.game.board.operations.MatchWithRenaming;

/**
 * A special type of level in which the player has to change the color of the
 * given elements in order for the simulation to reach a certain outcome.
 */
public class ColorEditLevel extends Level {

	private Color[] userColors;
	private Color[] blockedColors;
	private ColorController colorController;

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
	 * @param userColors
	 *            the colors given to the user to color BoardObjects in
	 * @param blockedColors
	 *            Colors the user musn't use
	 * @param hint
	 *            the hint given to the user if he pushes the hint button
	 * @param description
	 *            the description of the level
	 * @param abortSimulationAfter
	 *            number of evaluation steps the simulation is aborted after
	 */
	public ColorEditLevel(int levelIndex, int packageIndex, Board initialBoard,
			Board goalBoard, Animation animation, Color[] userColors,
			Color[] blockedColors, String hint, String description,
			int abortSimulationAfter) {
		super(levelIndex, packageIndex, initialBoard, goalBoard, animation,
				hint, description, abortSimulationAfter);
		this.blockedColors = blockedColors;
		this.userColors = userColors;
	}

	/**
	 * Method to get the userColors of the level.
	 * 
	 * @return the user colors of this level
	 */
	public Color[] getUserColor() {
		return this.userColors;
	}

	/**
	 * Method to get the blocked colors of the level.
	 * 
	 * @return the blocked colors of this level
	 */
	public Color[] getBlockedColor() {
		return this.blockedColors;
	}

	/**
	 * Method to get the ColorController of the level
	 * 
	 * @return the Colorcontroller of this level.
	 */
	public ColorController getColorController() {
		return this.colorController;
	}

	@Override
	public boolean isLevelSolved(Board solution, int steps) {
		boolean stepsReached = false;
		boolean rightBoard = false;
		if (this.getAbortSimulationAfter() != 0
				&& steps == this.getAbortSimulationAfter()) {
			stepsReached = true;
		} else if (this.getAbortSimulationAfter() == 0) {
			stepsReached = true;
		}

		if (MatchWithRenaming.match(solution, this.getGoalBoard())) {
			rightBoard = true;
		}
		if (stepsReached && rightBoard) {
			this.setSolvedTrue();
		}

		return stepsReached && rightBoard;
	}

}
