package de.croggle.ui.screens;

import de.croggle.AlligatorApp;
import de.croggle.game.GameController;

/**
 * First screen seen after completing a level. For reference see ``Pflichtenheft
 * 10.5.6 / Abbildung 15''.
 * 
 * @depend - <uses> - de.croggle.game.GameController
 */
public class LevelTerminatedScreen extends AbstractScreen {
	/**
	 * Creates the level terminated screen that is shown to the player after the
	 * completion of a level.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the game controller, who is responsible for the completed
	 *            level
	 */
	public LevelTerminatedScreen(AlligatorApp game, GameController controller) {
		super(game);
	}
}
