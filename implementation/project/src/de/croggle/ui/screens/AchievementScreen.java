package de.croggle.ui.screens;

import static de.croggle.data.LocalizationHelper._;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import de.croggle.AlligatorApp;
import de.croggle.game.achievement.Achievement;
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
				helper.getImageButtonStyleRound("widgets/icon-back"));
		Label achievements = new Label(_("screen_title_achievements"),
				helper.getLabelStyle());
		ImageButton next = new ImageButton(helper.getDrawable("widget/icon-next"));
		ImageButton previous = new ImageButton(helper.getDrawable("widget/icon-back"));
		
		List<Achievement> availableAchievements = achievementController.getAvailableAchievements();
		for( Achievement achievement : availableAchievements) {
			for( int i = 0; i < achievement.getNumberOfStages(); i++) {
				ImageButton achievementButton;
				if (i <= achievement.getIndex()) {
					achievementButton = new ImageButton(helper.getDrawable(achievement.getEmblemPathachieved(i)));
				}
				else {
					achievementButton = new ImageButton(helper.getDrawable(achievement.getEmblemPathnotachieved(i)));
				}
				table.add(achievementButton);
			}
		}
		

		// add listeners
		back.addListener(new LogicalPredecessorListener());

		table.pad(30);
		table.add(back).size(100);
		table.add(achievements);
	}
}
