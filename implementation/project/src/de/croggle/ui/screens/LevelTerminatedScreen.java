package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import de.croggle.AlligatorApp;
import de.croggle.game.GameController;
import de.croggle.game.achievement.AchievementController;
import de.croggle.ui.StyleHelper;

/**
 * First screen seen after completing a level. For reference see ``Pflichtenheft
 * 10.5.6 / Abbildung 15''.
 */
public class LevelTerminatedScreen extends AbstractScreen {

	private GameController gameController;
	private AchievementController achievementController;

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
		gameController = controller;
		achievementController = game.getAchievementController();

		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();

		Image image = new Image(helper.getDrawable("widgets/icon-play"));
		ImageButton next = new ImageButton(
				helper.getImageButtonStyleRound("widgets/dummy-icon"));
		ImageButton levelOverview = new ImageButton(
				helper.getImageButtonStyleRound("widgets/dummy-icon"));
		ImageButton replay = new ImageButton(
				helper.getImageButtonStyleRound("widgets/dummy-icon"));
		ImageButton achievements = new ImageButton(
				helper.getImageButtonStyleRound("widgets/dummy-icon"));

		table.pad(30);
		table.add(image).colspan(4).expand();
		table.row();
		table.add(achievements).expandX().left().size(150);
		table.add(replay).size(100).bottom().space(30);
		table.add(levelOverview).size(100).bottom().space(30);
		table.add(next).size(150);
	}
}
