package de.croggle.ui.screens;

import de.croggle.AlligatorApp;
import de.croggle.game.achievement.AchievementController;

/**
 * Screen listing the achievements, both achieved and unachieved, in a sorted
 * way. For reference see ``Pflichtenheft 10.5.8 / Abbildung 17''.
 */
public class AchievementScreen extends AbstractScreen {
	/**
	 * Creates the achievement overview screen that uses the given achievement
	 * controller to display the current achievement progress.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param achievment
	 *            the achievement controller
	 */
	public AchievementScreen(AlligatorApp game,
			AchievementController achievement) {
		super(game);
	}
}
