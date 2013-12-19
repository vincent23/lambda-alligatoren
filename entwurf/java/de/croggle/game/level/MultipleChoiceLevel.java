package de.croggle.game.level;

import de.croggle.game.board.Board;

/**
 * A special type of level in which the player has to choose from several
 * options, one of which is the correct one.
 */
public class MultipleChoiceLevel extends Level {

	private Board[] answers;
	private int correctAnswer; // TODO: wollen wir speichern was das richtige
								// ist, oder auswerten und schauen ob der Term
								// upmatcht?

	/**
	 * Method to check whether the given answer was the correct one.
	 */
	public boolean validateAnswer(int selection) {
		return selection == correctAnswer;
	}

	@Override
	public boolean hasAnimation() {
		// TODO Auto-generated method stub
		return false;
	}
}