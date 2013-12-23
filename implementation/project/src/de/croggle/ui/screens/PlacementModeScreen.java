package de.croggle.ui.screens;

import de.croggle.AlligatorApp;
import de.croggle.game.GameController;

/**
 * Screen within which the player can manipulate the board by moving alligators
 * and eggs. For reference see ``Pflichtenheft 10.5.4 / Abbildungen 12 und 1''.
 * 
 * @assoc 1 - 1 de.croggle.ui.actors.ObjectBar
 * 
 * @depend - <uses> - de.croggle.game.GameController
 */
public class PlacementModeScreen extends AbstractScreen {

	/**
	 * Creates the screen of a level within the placement mode. This is the
	 * screen which is presented to the user upon entering a recoloring or
	 * termedit level.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the game controller, which is responsible for the played level
	 */
	public PlacementModeScreen(AlligatorApp game, GameController controller) {
		super(game);
	}
}
