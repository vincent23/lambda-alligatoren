package de.croggle.game.level;

import com.badlogic.gdx.graphics.g2d.Animation;

import de.croggle.game.Color;
import de.croggle.game.board.Board;

/**
 * A special type of level in which the player has to choose from several
 * options, one of which is the correct one.
 */
public class MultipleChoiceLevel extends Level {

	private Board[] answers;
	private int correctAnswer;


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
	 * @param hint
	 *            the hint given to the user if he pushes the hint button
	 * @param description
	 *            the description of the level
	 * @param abortSimulationAfter
	 *            number of evaluation steps the simulation is aborted after
	 */
	public MultipleChoiceLevel(int levelIndex, int packageIndex,
			Board initialBoard, Board goalBoard, Animation animation,
			Color[] userColors, String hint, String description,
			int abortSimulationAfter, Board[] answers, int correctAnswer) {
		super(levelIndex, packageIndex, initialBoard, goalBoard, animation, userColors,
				hint, description, abortSimulationAfter);
		this.answers = answers;
		this.correctAnswer = correctAnswer;
	}

	
	/**
	 * Method to check whether the given answer was the correct one.
	 * 
	 * @param selection
	 *            the index of the selected answer
	 * @return true if the answer was right, false otherwise
	 */
	public boolean validateAnswer(int selection) {
		return selection == correctAnswer;
	}
	
	/**
	 * Method to get the ansers od this level.
	 * 
	 * @return the possible answers of this level.
	 */
	public Board[] getAnswers(){
		return answers;
	}

}
