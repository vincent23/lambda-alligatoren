package de.croggle.ui.screens;

import static de.croggle.backends.BackendHelper.getAssetDirPath;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

import de.croggle.AlligatorApp;
import de.croggle.backends.BackendHelper;
import de.croggle.data.AssetManager;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.SettingChangeListener;
import de.croggle.game.ColorController;
import de.croggle.game.ColorOverflowException;
import de.croggle.game.GameController;
import de.croggle.game.board.AlligatorOverflowException;
import de.croggle.game.board.Board;
import de.croggle.game.board.IllegalBoardException;
import de.croggle.game.level.LevelPackage;
import de.croggle.game.level.LevelPackagesController;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.IngameMenuDialog;
import de.croggle.ui.renderer.ActorLayoutConfiguration;
import de.croggle.ui.renderer.BoardActor;

/**
 * Screen which is shown during the evaluation-phase of a level. For reference
 * see ``Pflichtenheft 10.5.5 / Abbildung 14''.
 */
public class SimulationModeScreen extends AbstractScreen implements
		SettingChangeListener {

	private static final float ZOOM_RATE = 3f;

	private final GameController gameController;
	private Table controlTable;
	private BoardActor boardActor;

	private ImageButton zoomIn;
	private ImageButton zoomOut;
	private ImageButton play;

	private Timer timer;
	private boolean isSimulating;
	private long automaticSimulationFrequency = 3000;

	private static final long MAX_AUTOMATIC_SIMULATION_DELAY = 6000;
	private static final long MIN_AUTOMATIC_SIMULATION_DELAY = 1000;

	private long lastSimulation = TimeUtils.millis();

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
	 * @throws IllegalBoardException
	 */
	public SimulationModeScreen(AlligatorApp game, GameController controller)
			throws IllegalBoardException {
		super(game);
		this.gameController = controller;
		gameController.enterSimulation();

		// load the texture atlas
		AssetManager assetManager = AssetManager.getInstance();
		assetManager.load(getAssetDirPath() + "textures/pack.atlas",
				TextureAtlas.class);

		final int packageIndex = gameController.getLevel().getPackageIndex();
		final LevelPackagesController packagesController = game
				.getLevelPackagesController();
		final LevelPackage pack = packagesController.getLevelPackages().get(
				packageIndex);
		setBackground(pack.getDesign());

		fillTable();

		game.getSettingController().addSettingChangeListener(this);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		checkZoom();
	}

	@Override
	protected void onShow() {
		BackendHelper.acquireWakeLock();

		ColorController cctrlr = gameController.getColorController();
		gameController.setTimeStamp();
		Board b = gameController.getShownBoard();

		ActorLayoutConfiguration config = new ActorLayoutConfiguration();
		config.setColorController(cctrlr);
		boardActor = new BoardActor(b, config);
		gameController.registerSimulationBoardEventListener(boardActor
				.getBoardEventListener());
		boardActor.setColorBlindEnabled(game.getSettingController()
				.getCurrentSetting().isColorblindEnabled());
		game.getSettingController().addSettingChangeListener(boardActor);

		table.clearChildren();
		table.stack(boardActor, controlTable).expand().fill();

		onSettingChange(game.getSettingController().getCurrentSetting());
	}

	@Override
	public void hide() {
		stopAutomaticSimulation();
		BackendHelper.releaseWakeLock();
		gameController.updateTime();
		gameController.setTimeStamp();
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

		menu.addListener(new MenuClickListener());
		backToPlacement.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stopAutomaticSimulation();
				showLogicalPredecessor();
			}
		});
		ImageButton stepForward = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-step-next"));
		stepForward.addListener(new StepForwardListener());
		ImageButton stepBackward = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-step-back"));
		stepBackward.addListener(new StepBackwardListener());
		play = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-next"));

		play.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (isSimulating) {
					stopAutomaticSimulation();
				} else {
					startAutomaticSimulation(0);
				}
			}
		});

		Slider delaySlider = new Slider(-MAX_AUTOMATIC_SIMULATION_DELAY,
				-MIN_AUTOMATIC_SIMULATION_DELAY, 1000, false,
				helper.getSliderStyle());
		delaySlider.setValue(-automaticSimulationFrequency);
		delaySlider.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Slider slider = (Slider) actor;
				if (!slider.isDragging()) {
					long newFrequency = (long) -slider.getValue();
					if (newFrequency != automaticSimulationFrequency) {
						automaticSimulationFrequency = newFrequency;
						if (isSimulating) {
							stopAutomaticSimulation();
							long delay = automaticSimulationFrequency
									- (TimeUtils.millis() - lastSimulation);
							startAutomaticSimulation(Math.max(0, delay));
						}
					}
				}

			}
		});

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
		controlTable.add(delaySlider).width(300).pad(30).bottom();
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
			stopAutomaticSimulation();
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
			stopAutomaticSimulation();
			if (gameController.canUndo()) {
				gameController.undo();
			}
		}
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

	private void startAutomaticSimulation(long delay) {
		if (timer == null) {
			play.setStyle(StyleHelper.getInstance().getImageButtonStyleRound(
					"widgets/icon-pause"));
			timer = new Timer();
			timer.schedule(new StepTimer(), delay, automaticSimulationFrequency);
			isSimulating = true;
		}

	}

	private void stopAutomaticSimulation() {
		if (timer != null) {
			play.setStyle(StyleHelper.getInstance().getImageButtonStyleRound(
					"widgets/icon-next"));
			timer.cancel();
			timer = null;
			isSimulating = false;

		}

	}

	private class StepTimer extends TimerTask {

		@Override
		public void run() {
			try {
				gameController.evaluateStep();
				lastSimulation = TimeUtils.millis();
			} catch (ColorOverflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AlligatorOverflowException e) {
				// TODO Auto-generated catch block//
				e.printStackTrace();
			}
		}
	}

	private class MenuClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			Dialog menuDialog = new IngameMenuDialog(game, gameController);
			menuDialog.show(stage);
		}
	}

}
