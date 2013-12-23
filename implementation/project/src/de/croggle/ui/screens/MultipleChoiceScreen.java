package de.croggle.ui.screens;

import de.croggle.AlligatorApp;
import de.croggle.game.GameController;

/**
 * Screen which is shown while playing a multiple choice level.
 * 
 * @depend - <uses> - de.croggle.game.GameController
 */
public class MultipleChoiceScreen extends AbstractScreen {
	/**
	 * Creates the base screen of a multiple choice level, which is shown to the
	 * player upon entering a multiple choice level.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the game controller responsible for the multiple choice level
	 */
	public MultipleChoiceScreen(AlligatorApp game, GameController controller) {
		super(game);
	}
}
