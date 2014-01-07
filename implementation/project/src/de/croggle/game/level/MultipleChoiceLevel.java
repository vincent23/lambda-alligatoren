package de.croggle.game.level;

import de.croggle.game.board.Board;

/**
 * A special type of level in which the player has to choose from several
 * options, one of which is the correct one.
 */
public class MultipleChoiceLevel extends Level {

	private Board[] answers;
	private int correctAnswer;

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

}
