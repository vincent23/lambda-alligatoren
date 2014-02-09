package de.croggle.game.level;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;

import de.croggle.game.Color;
import de.croggle.game.board.Board;

/**
 * A special type of level in which the player has to change the color of the
 * given elements in order for the simulation to reach a certain outcome.
 */
public class ColorEditLevel extends EditLevel {

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
			Board goalBoard, List<String> animation, Color[] userColors,
			Color[] blockedColors, String hint, String description,
			int abortSimulationAfter) {
		super(levelIndex, packageIndex, initialBoard, goalBoard, animation,
				userColors, blockedColors, hint, description,
				abortSimulationAfter, false);
	}

}
