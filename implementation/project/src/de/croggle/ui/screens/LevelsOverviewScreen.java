package de.croggle.ui.screens;

import de.croggle.AlligatorApp;
import de.croggle.game.level.LevelController;

/**
 * Screen, in which one can choose to play different levels within the selected
 * levelpackage. For reference see ``Pflichtenheft 10.5.3 / Abbildung 11''.
 */
public class LevelsOverviewScreen extends AbstractScreen {
	/**
	 * Creates the level overview screen that uses the given level controller to
	 * display the levels within the selected level package.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the level controller
	 */
	public LevelsOverviewScreen(AlligatorApp game, LevelController controller) {
		super(game);
	}
}
