package de.croggle.ui.screens;

import de.croggle.AlligatorApp;
import de.croggle.game.level.LevelPackagesController;

/**
 * Screen, in which one can select the levelpackage. For reference see
 * ``Pflichtenheft 10.5.2 / Abbildung 10''.
 */
public class LevelPackagesScreen extends AbstractScreen {
	/**
	 * Creates the level package overview screen that uses the level package
	 * controller to display the different level packages.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the level package controller
	 */
	public LevelPackagesScreen(AlligatorApp game,
			LevelPackagesController controller) {
		super(game);
	}
}
