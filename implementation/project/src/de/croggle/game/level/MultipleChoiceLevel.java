package de.croggle.game.level;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;

import de.croggle.AlligatorApp;
import de.croggle.game.Color;
import de.croggle.game.MultipleChoiceGameController;
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
	 * @param hint
	 *            the hint given to the user if he pushes the hint button
	 * @param description
	 *            the description of the level
	 * @param abortSimulationAfter
	 *            number of evaluation steps the simulation is aborted after
	 * @param answers
	 *            an array of Boards representing the possible answers
	 * @param correctAnswer
	 *            the index of the correct answer
	 */
	public MultipleChoiceLevel(int levelIndex, int packageIndex,
			Board initialBoard, Board goalBoard, List<String> animation,
			String hint, String description, int abortSimulationAfter,
			Board[] answers, int correctAnswer) {
		super(levelIndex, packageIndex, initialBoard, goalBoard, animation,
				hint, description, abortSimulationAfter, false);
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
	 * Method to get the answers of this level.
	 * 
	 * @return the possible answers of this level.
	 */
	public Board[] getAnswers() {
		return answers;
	}

	@Override
	public boolean isLevelSolved(Board solution, int steps) {
		if (solution.matchWithRecoloring(answers[correctAnswer],
				new HashMap<Color, Color>())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public MultipleChoiceGameController createGameController(AlligatorApp app) {
		return new MultipleChoiceGameController(app, this);
	}

}
