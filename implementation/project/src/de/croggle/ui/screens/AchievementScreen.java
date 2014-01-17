package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import de.croggle.AlligatorApp;
import de.croggle.game.achievement.AchievementController;
import de.croggle.ui.StyleHelper;

/**
 * Screen listing the achievements, both achieved and unachieved, in a sorted
 * way. For reference see ``Pflichtenheft 10.5.8 / Abbildung 17''.
 */
public class AchievementScreen extends AbstractScreen {

	private AchievementController achievementController;

	/**
	 * Creates the achievement overview screen that uses the given achievement
	 * controller to display the current achievement progress.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param achievment
	 *            the achievement controller
	 */
	public AchievementScreen(AlligatorApp game) {
		super(game);
		achievementController = game.getAchievementController();

		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();
		ImageButton back = new ImageButton(
				helper.getImageButtonStyleRound("widgets/dummy-icon"));
		Label achievements = new Label("Achievements", helper.getLabelStyle());

		table.pad(30);
		table.add(back);
		table.add(achievements);
	}
}
