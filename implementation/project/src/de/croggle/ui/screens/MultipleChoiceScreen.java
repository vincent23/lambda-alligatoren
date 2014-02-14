package de.croggle.ui.screens;

import static de.croggle.data.LocalizationHelper._;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.croggle.AlligatorApp;
import de.croggle.data.AssetManager;
import de.croggle.game.ColorController;
import de.croggle.game.MultipleChoiceGameController;
import de.croggle.game.board.Board;
import de.croggle.game.board.IllegalBoardException;
import de.croggle.game.level.LevelPackage;
import de.croggle.game.level.LevelPackagesController;
import de.croggle.game.level.MultipleChoiceLevel;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.IngameMenuDialog;
import de.croggle.ui.actors.NotificationDialog;
import de.croggle.ui.renderer.ActorLayoutConfiguration;
import de.croggle.ui.renderer.BoardActor;
import de.croggle.util.BackendHelper;

/**
 * Screen which the player sees when entering Multiple choice levels.
 */
public class MultipleChoiceScreen extends AbstractScreen {

	private final MultipleChoiceGameController gameController;
	private BoardActor boardActor;
	private CheckBox checkboxes[];

	/**
	 * Creates the base screen of a multiple choice level, which is shown to the
	 * player upon entering a multiple choice level.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the game controller responsible for the multiple choice level
	 */
	public MultipleChoiceScreen(AlligatorApp game,
			MultipleChoiceGameController controller) {
		super(game);
		gameController = controller;

		AssetManager assetManager = AssetManager.getInstance();
		assetManager.load("textures/pack.atlas", TextureAtlas.class);

		fillTable();

		final int packageIndex = gameController.getLevel().getPackageIndex();
		final LevelPackagesController packagesController = game
				.getLevelPackagesController();
		final LevelPackage pack = packagesController.getLevelPackages().get(
				packageIndex);
		setBackground(pack.getDesign());

		// load graphics for animation/tutorial
		if (gameController.getLevel().hasAnimation()) {
			List<String> animations = gameController.getLevel().getAnimation();
			for (String animation : animations) {
				assetManager.load(animation, Texture.class);
			}
		}
	}

	@Override
	protected void onShow() {
		BackendHelper.acquireWakeLock();

		gameController.setTimeStamp();
		gameController.enterPlacement();

		// simply open all tutorials over each other, beginning with the last
		if (gameController.getLevel().hasAnimation()) {
			List<String> animations = gameController.getLevel().getAnimation();
			for (int i = animations.size() - 1; i >= 0; i--) {
				buildTutorialDialog(animations.get(i));
			}
		}
	}

	@Override
	public void hide() {
		super.hide();
		BackendHelper.releaseWakeLock();
		gameController.updateTime();
		gameController.setTimeStamp();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();

		Table widgetTable = new Table();
		ImageButton menu = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-menu"));
		ImageButton startSimulation = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/icon-next"));

		// add listeners
		menu.addListener(new MenuClickListener());
		startSimulation.addListener(new StartSimulationListener());

		widgetTable.pad(30);
		widgetTable.defaults().space(30);
		widgetTable.add(menu).size(100).top().left();
		// widgetTable.row();
		widgetTable.add(startSimulation).size(200).top().right().expand();

		final ColorController colorController = gameController
				.getColorController();

		final ActorLayoutConfiguration config = new ActorLayoutConfiguration();
		config.setColorController(colorController);

		MultipleChoiceLevel level = (MultipleChoiceLevel) gameController
				.getLevel();

		Table answerTable = new Table();
		// PagedScrollPane scrollPane = new PagedScrollPane();
		ScrollPane scrollPane = new ScrollPane(answerTable);

		checkboxes = new CheckBox[3];

		for (int i = 0; i < level.getAnswers().length; i++) {
			Board answer = level.getAnswers()[i];
			Table boardTable = new Table();
			Table pageTable = new Table();
			checkboxes[i] = new CheckBox("", helper.getCheckBoxStyle());
			boardActor = new BoardActor(answer, config);
			boardActor.setColorBlindEnabled(game.getSettingController()
					.getCurrentSetting().isColorblindEnabled());
			game.getSettingController().addSettingChangeListener(boardActor);
			boardActor.setZoomAndPanEnabled(false);
			boardTable.add(boardActor).expand().fill();
			pageTable.add(checkboxes[i]).size(70).top().center()
					.pad(20, 0, 5, 0);
			pageTable.row();
			pageTable.add(boardTable).center().expand().fill();

			// scrollPane.addPage(pageTable);
			answerTable.add(pageTable).size(getViewportWidth() / 3).padTop(10);
		}

		// TODO remove Simulationbutton and checkboxes and add simulationbutton
		// on each page.

		scrollPane.setFlingTime(0.3f);
		scrollPane.setScrollingDisabled(false, true);

		BoardActor goalBoard = new BoardActor(gameController.getLevel()
				.getInitialBoard(), config);
		goalBoard.setZoomAndPanEnabled(true);
		goalBoard.setColorBlindEnabled(game.getSettingController()
				.getCurrentSetting().isColorblindEnabled());
		game.getSettingController().addSettingChangeListener(goalBoard);
		Table goalTable = new Table();
		goalTable.add(goalBoard).size(getViewportWidth() * 1.5f,
				getViewportHeight());

		SplitPane splitPane = new SplitPane(goalBoard, scrollPane, true,
				helper.getSplitPaneStyle());
		table.stack(splitPane, widgetTable).expand().fill();
	}

	private void buildTutorialDialog(String animationPath) {
		AssetManager manager = AssetManager.getInstance();
		StyleHelper helper = StyleHelper.getInstance();

		final Dialog tutorial = new Dialog("", StyleHelper.getInstance()
				.getDialogStyle());
		tutorial.clear();
		tutorial.fadeDuration = 0f;

		Table buttonTable = new Table();
		Drawable drawable = new TextureRegionDrawable(new TextureRegion(
				manager.get(animationPath, Texture.class)));
		// used image button here because it keeps the ratio of the texture
		ImageButton tutorialImage = new ImageButton(drawable);
		ImageButton okay = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-check"));

		okay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				tutorial.hide();
			}
		});

		buttonTable.add(okay).size(100).bottom().right().expand().pad(30);
		tutorial.stack(tutorialImage, buttonTable).height(500).width(800);
		tutorial.show(stage);
	}

	private class MenuClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			Dialog menuDialog = new IngameMenuDialog(game, gameController);
			menuDialog.show(stage);
		}
	}

	private class StartSimulationListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			super.clicked(event, x, y);
			int answer = -1;
			for (int i = 0; i < checkboxes.length; i++) {
				if (checkboxes[i].isChecked()) {
					if (answer == -1) {
						answer = i;
					} else {
						Dialog dialog = new NotificationDialog(
								_("multiple_choice_dialog"));
						dialog.show(stage);
						return;
					}
				}
			}
			gameController.setSelection(answer);
			try {
				game.showSimulationModeScreen(gameController);
			} catch (IllegalBoardException e) {
				// This can't happen in a MC Level
			}
		}
	}
}
