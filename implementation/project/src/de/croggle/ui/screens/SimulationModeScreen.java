package de.croggle.ui.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.AlligatorApp;
import de.croggle.data.AssetManager;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.SettingChangeProcessor;
import de.croggle.game.ColorController;
import de.croggle.game.ColorOverflowException;
import de.croggle.game.GameController;
import de.croggle.game.board.AlligatorOverflowException;
import de.croggle.game.board.Board;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.renderer.ActorLayoutConfiguration;
import de.croggle.ui.renderer.BoardActor;

/**
 * Screen which is shown during the evaluation-phase of a level. For reference
 * see ``Pflichtenheft 10.5.5 / Abbildung 14''.
 */
public class SimulationModeScreen extends AbstractScreen implements
		SettingChangeProcessor {

	private static final float ZOOM_RATE = 3f;

	private final GameController gameController;
	private Table controlTable;
	private BoardActor boardActor;

	private ImageButton zoomIn;
	private ImageButton zoomOut;

	/**
	 * Creates the screen of a level within the simulation mode. This is the
	 * screen which is presented to the user upon pressing the
	 * "start simulation button" within the placement mode screen within a
	 * recoloring or term edit level.
	 * 
	 * @param game
	 *            the back reference to the central game
	 * @param controller
	 *            the game controller, which is responsible for the played level
	 */
	public SimulationModeScreen(AlligatorApp game, GameController controller) {
		super(game);
		this.gameController = controller;
		gameController.enterSimulation();

		// load the texture atlas
		AssetManager assetManager = AssetManager.getInstance();
		assetManager.load("textures/pack.atlas", TextureAtlas.class);
		this.setBackground("textures/swamp.png");

		fillTable();

		game.getSettingController().registerSettingChangeProcessor(this);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (boardActor != null) {
			checkZoom();
		}
	}

	protected void onShow() {
		ColorController cctrlr = gameController.getColorController();

		Board b = gameController.getShownBoard();

		ActorLayoutConfiguration config = new ActorLayoutConfiguration();
		config.setColorController(cctrlr);
		BoardActor boardActor = new BoardActor(b, config);
		gameController.registerBoardEventListener(boardActor);

		table.clearChildren();
		table.stack(boardActor, controlTable).expand().fill();

		processSettingChange(game.getSettingController().getCurrentSetting());
	}

	public void hide() {
		table.clear();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();

		controlTable = new Table();
		Table controlPanelTable = new Table();
		Table leftTable = new Table();
		ImageButton menu = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-menu"));
		zoomIn = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-plus"));
		zoomOut = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-minus"));

		ImageButton backToPlacement = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-back"));
		ImageButton stepForward = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-step-next"));
		stepForward.addListener(new StepForwardListener());
		ImageButton stepBackward = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-step-back"));
		stepBackward.addListener(new StepBackwardListener());
		ImageButton play = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-next"));

		controlPanelTable.setBackground(helper.getDrawable("widgets/button"));
		controlPanelTable.add(backToPlacement).colspan(2).size(120);
		controlPanelTable.row();
		controlPanelTable.add(stepBackward).size(120);
		controlPanelTable.add(stepForward).size(120);
		controlPanelTable.row();
		controlPanelTable.add(play).colspan(2).size(200);

		leftTable.add(menu).size(100).expand().left().top().row();
		leftTable.add(zoomIn).size(70).space(30).left().row();
		leftTable.add(zoomOut).size(70).space(30).left();

		controlTable.pad(30).padRight(0);
		controlTable.add(leftTable).expand().fill();
		controlTable.add(controlPanelTable);
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

	private class StepForwardListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			super.clicked(event, x, y);
			try {
				gameController.evaluateStep();
			} catch (ColorOverflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AlligatorOverflowException e) {
				// TODO Auto-generated catch block//
				e.printStackTrace();
			}
		}
	}

	private class StepBackwardListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			super.clicked(event, x, y);
			if (gameController.canUndo()) {
				gameController.undo();
			}
		}
	}

	@Override
	public void processSettingChange(Setting setting) {
		if (setting.isZoomEnabled()) {
			zoomIn.setVisible(true);
			zoomOut.setVisible(true);
		} else {
			zoomIn.setVisible(false);
			zoomOut.setVisible(false);
		}

	}

}
