package de.croggle.ui.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.AlligatorApp;
import de.croggle.data.AssetManager;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.SettingChangeListener;
import de.croggle.game.ColorController;
import de.croggle.game.GameController;
import de.croggle.game.level.LevelPackage;
import de.croggle.game.level.LevelPackagesController;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.HintDialog;
import de.croggle.ui.actors.IngameMenuDialog;
import de.croggle.ui.actors.ObjectBar;
import de.croggle.ui.renderer.ActorLayoutConfiguration;
import de.croggle.ui.renderer.BoardActor;

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
	private final Dialog dialog;

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
		dialog = new Dialog("", StyleHelper.getInstance().getDialogStyle());

		fillTable();
		final int packageIndex = gameController.getLevel().getPackageIndex();
		final LevelPackagesController packagesController = game
				.getLevelPackagesController();
		final LevelPackage pack = packagesController.getLevelPackages().get(
				packageIndex);
		setBackground(pack.getDesign());
		game.getSettingController().addSettingChangeListener(this);
	}

	@Override
	protected void onShow() {
		gameController.setTimeStamp();
		gameController.enterPlacement();
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
				helper.getImageButtonStyleRound("widgets/icon-trophy"));
		ObjectBar objectBar = new ObjectBar(game, gameController);

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
		boardActor.enableLayoutEditing(gameController
				.getPlacmentBoardEventListener());
		game.getSettingController().addSettingChangeListener(boardActor);
		// used to make resetting the board via game controller possible
		gameController.registerPlacementBoardEventListener(boardActor
				.getBoardEventListener());
		final Table boardTable = new Table();
		boardTable.add(boardActor).fill().expand();

		final Table controlTable = new Table();
		controlTable.add(leftTable).expand().fill();
		controlTable.add(objectBar).padLeft(30);
		table.stack(boardTable, controlTable).fill().expand();
		onSettingChange(game.getSettingController().getCurrentSetting());

		BoardActor goalBoard = new BoardActor(gameController.getLevel()
				.getGoalBoard(), config);
		goalBoard.setZoomAndPanEnabled(false);
		Table goalTable = new Table();
		goalTable.add(goalBoard).size(getViewportHeight());
		goal.addListener(new GoalClickListener());

		dialog.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				dialog.hide();
			}
		});

		dialog.add(goalTable).width(getViewportWidth() - 250)
				.height(getViewportHeight());

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
			Dialog dialog = new HintDialog(gameController.getLevel());
			dialog.show(stage);
		}
	}

	private class GoalClickListener extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			dialog.show(stage);
		}
	}

}
