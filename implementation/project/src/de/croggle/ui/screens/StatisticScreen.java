package de.croggle.ui.screens;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.StatisticController;

/**
 * Screen which enables the teacher or parent to control the progress of every
 * user. For reference see ``Pflichtenheft 10.5.11 / Abbildung 20''.
 */
public class StatisticScreen extends AbstractScreen {
	/**
	 * Creates the screen within which a parent or teacher can control the
	 * player's progress and statistics.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the statistic controller, which is responsible for the
	 *            statistics
	 */
	public StatisticScreen(AlligatorApp game, StatisticController controller) {
		super(game);
	}
}
