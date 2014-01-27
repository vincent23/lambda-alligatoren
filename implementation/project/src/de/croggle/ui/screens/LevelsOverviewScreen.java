package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.AlligatorApp;
import de.croggle.game.GameController;
import de.croggle.game.level.Level;
import de.croggle.game.level.LevelController;
import de.croggle.ui.StyleHelper;

/**
 * Screen, in which one can choose to play different levels within the selected
 * levelpackage. For reference see ``Pflichtenheft 10.5.3 / Abbildung 11''.
 */
public class LevelsOverviewScreen extends AbstractScreen {

	private final static int LEVELS_PER_ROW = 4;
	private LevelController levelController;

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
		levelController = controller;

		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();
		Table levelTable = new Table();
		ImageButton back = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-back"));

		for (int i = 0; i < levelController.getPackageSize(); i++) {
			Level level = levelController.getLevel(i);

			TextButton levelButton = new TextButton(Integer.toString(level
					.getLevelIndex()), helper.getTextButtonStyle());
			levelButton
					.addListener(new StartGameListener(level.getLevelIndex()));
			levelTable.add(levelButton).size(120).space(40, 80, 40, 80);
			if (i % LEVELS_PER_ROW == LEVELS_PER_ROW - 1) {
				levelTable.row();
			}
		}

		back.addListener(new LogicalPredecessorListener());

		table.pad(30);
		table.add(back).size(100).top().left();
		table.add(levelTable).expand().fill();
	}

	@Override
	protected void showLogicalPredecessor() {
		game.showLevelPackagesScreen();
	}

	private class StartGameListener extends ClickListener {
		private final int levelId;

		public StartGameListener(int levelId) {
			this.levelId = levelId;
		}

		@Override
		public void clicked(InputEvent event, float x, float y) {
			super.clicked(event, x, y);
			final GameController gameController = new GameController(
					levelController.getLevel(levelId));
			game.showPlacementModeScreen(gameController);
		}
	}
}
