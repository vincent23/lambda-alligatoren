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

		// Board b = LambdaToAlligator
		// .convert("(λa.λb.λs.λz.(a s (b s z))) (λs.λz.(s (s (s z)))) (λs.λz.(s (s (s (s z)))))");
		Board b = gameController.getShownBoard();
		// Board b = LambdaToAlligator.convert("(λx.x) ((λy.y) (λz.z))");
		// Board b = LambdaToAlligator.convert("x");
		// for (int i = 0; i < 30; i++) {
		// // tell the colorcontroller that we need some colors instantiated
		// try {
		// cctrlr.requestColor();
		// } catch (ColorOverflowException e) {
		// throw new RuntimeException("Test failed");
		// }
		// }
		/*
		 * Color c0; Color c1; try { c0 = cctrlr.requestColor(); c1 =
		 * cctrlr.requestColor(); } catch (ColorOverflowException e) { throw new
		 * RuntimeException("Test failed"); } ColoredAlligator a00 = new
		 * ColoredAlligator(false, false, c0, false); ColoredAlligator a01 = new
		 * ColoredAlligator(false, false, c0, false); ColoredAlligator a10 = new
		 * ColoredAlligator(false, false, c1, false); Egg e00 = new Egg(false,
		 * false, c0, false); Egg e01 = new Egg(false, false, c0, false); Egg
		 * e10 = new Egg(false, false, c1, false); b.addChild(a00);
		 * a00.addChild(a01); a00.addChild(a10); a01.addChild(e00);
		 * a01.addChild(e01); a10.addChild(e10);
		 */

		ActorLayoutConfiguration config = new ActorLayoutConfiguration();
		config.setColorController(cctrlr);
		BoardActor boardActor = new BoardActor(b, config);
		// ba.setColor(new com.badlogic.gdx.graphics.Color(1, 1, 1, .5f));
		// ba.setScale(.5f); // TODO test this/ get it to work later

		// / this.table.add(ba).fill().expand();
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
			onShow();
		}
	}

	private class StepBackwardListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			super.clicked(event, x, y);
			gameController.undo();
			onShow();
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
