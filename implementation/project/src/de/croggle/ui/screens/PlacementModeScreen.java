package de.croggle.ui.screens;

import static de.croggle.data.LocalizationHelper._;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.croggle.AlligatorApp;
import de.croggle.backends.BackendHelper;
import de.croggle.data.AssetManager;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.SettingChangeListener;
import de.croggle.game.ColorController;
import de.croggle.game.GameController;
import de.croggle.game.board.IllegalBoardException;
import de.croggle.game.level.LevelPackage;
import de.croggle.game.level.LevelPackagesController;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.HintDialog;
import de.croggle.ui.actors.IngameMenuDialog;
import de.croggle.ui.actors.NotificationDialog;
import de.croggle.ui.renderer.ActorLayoutConfiguration;
import de.croggle.ui.renderer.BoardActor;
import de.croggle.ui.renderer.ObjectBar;

/**
 * Screen within which the player can manipulate the board by moving alligators
 * and eggs. For reference see ``Pflichtenheft 10.5.4 / Abbildungen 12 und 1''.
 */
public class PlacementModeScreen extends AbstractScreen implements
		SettingChangeListener {

	private static final float ZOOM_RATE = 3f;

	private final GameController gameController;
	private BoardActor boardActor;

	private ImageButton zoomIn;
	private ImageButton zoomOut;
	private final Dialog goalDialog;

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
		gameController = controller;

		AssetManager assetManager = AssetManager.getInstance();
		assetManager.load("textures/pack.atlas", TextureAtlas.class);
		goalDialog = new Dialog("", StyleHelper.getInstance().getDialogStyle());

		fillTable();
		final int packageIndex = gameController.getLevel().getPackageIndex();
		final LevelPackagesController packagesController = game
				.getLevelPackagesController();
		final LevelPackage pack = packagesController.getLevelPackages().get(
				packageIndex);
		setBackground(pack.getDesign());
		game.getSettingController().addSettingChangeListener(this);

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
	public void render(float delta) {
		super.render(delta);
		checkZoom();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();

		Table leftTable = new Table();
		ImageButton menu = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-menu"));
		ImageButton hint = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-hint"));
		zoomIn = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-plus"));
		zoomOut = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-minus"));
		Button goal = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-goal"));
		ImageButton startSimulation = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/icon-next"));
		startSimulation.addListener(new StartSimulationListener());

		// add listeners
		menu.addListener(new MenuClickListener());
		hint.addListener(new HintClickListener());

		leftTable.pad(30);
		leftTable.defaults().space(30);
		leftTable.add(menu).size(100).top().left();
		leftTable.row();
		// TODO only activated after some time
		leftTable.add(hint).expand().size(100).top().left();
		leftTable.row();
		leftTable.add(goal).expand().size(100).top().left();
		leftTable.row();

		leftTable.add(zoomIn).size(70).left();
		leftTable.row();
		leftTable.add(zoomOut).size(70).left();

		final ColorController colorController = gameController
				.getColorController();

		final ActorLayoutConfiguration config = new ActorLayoutConfiguration();
		config.setColorController(colorController);
		boardActor = new BoardActor(gameController.getShownBoard(), config);
		boardActor.setColorBlindEnabled(game.getSettingController()
				.getCurrentSetting().isColorblindEnabled());

		game.getSettingController().addSettingChangeListener(boardActor);
		// used to make resetting the board via game controller possible
		gameController.registerPlacementBoardEventListener(boardActor
				.getBoardEventListener());
		final Table boardTable = new Table();
		boardTable.add(boardActor).fill().expand();

		final Table controlTable = new Table();
		controlTable.add(leftTable).expand().fill();

		if (gameController.getLevel().getShowObjectBar()) {

			boardActor.enableLayoutEditing(
					gameController.getPlacmentBoardEventListener(), true);
			ObjectBar objectBar = boardActor.getObjectBar();
			objectBar.add(startSimulation).size(200);
			controlTable.add(objectBar).padLeft(30);

		} else {
			boardActor.enableLayoutEditing(
					gameController.getPlacmentBoardEventListener(), false);
			controlTable.add(startSimulation).bottom().right().size(200)
					.pad(30);
		}

		table.stack(boardTable, controlTable).fill().expand();
		onSettingChange(game.getSettingController().getCurrentSetting());

		BoardActor goalBoard = new BoardActor(gameController.getLevel()
				.getGoalBoard(), config);
		goalBoard.setZoomAndPanEnabled(false);
		goalBoard.setColorBlindEnabled(game.getSettingController()
				.getCurrentSetting().isColorblindEnabled());
		game.getSettingController().addSettingChangeListener(goalBoard);
		Table goalTable = new Table();
		goalTable.add(goalBoard).size(getViewportHeight());
		goal.addListener(new GoalClickListener());

		goalDialog.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				goalDialog.hide();
			}
		});

		goalDialog.add(goalTable).width(getViewportWidth() - 250)
				.height(getViewportHeight());

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

	private void checkZoom() {
		if (zoomIn.isPressed() && !zoomIn.isDisabled()) {
			zoomOut.setDisabled(false);
			boolean canZoom = boardActor.zoomIn(ZOOM_RATE);
			if (!canZoom) {
				zoomIn.setDisabled(true);
			}
		}

		if (zoomOut.isPressed() && !zoomOut.isDisabled()) {
			zoomIn.setDisabled(false);
			boolean canZoom = boardActor.zoomOut(ZOOM_RATE);
			if (!canZoom) {
				zoomOut.setDisabled(true);
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

	@Override
	public void onSettingChange(Setting setting) {
		if (setting.isZoomEnabled()) {
			zoomIn.setVisible(true);
			zoomOut.setVisible(true);
		} else {
			zoomIn.setVisible(false);
			zoomOut.setVisible(false);
		}

	}

	private class MenuClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			Dialog menuDialog = new IngameMenuDialog(game, gameController);
			menuDialog.show(stage);
		}
	}

	private class HintClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			gameController.onUsedHint();
			Dialog dialog = new HintDialog(gameController.getLevel());
			dialog.show(stage);
		}
	}

	private class GoalClickListener extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			goalDialog.show(stage);
		}
	}

	private class StartSimulationListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			super.clicked(event, x, y);
			try {
				game.showSimulationModeScreen(gameController);
			} catch (IllegalBoardException e) {
				Dialog dialog = new NotificationDialog(
						_("invalid_board_dialog"));
				dialog.show(stage);
			}
		}
	}

}
