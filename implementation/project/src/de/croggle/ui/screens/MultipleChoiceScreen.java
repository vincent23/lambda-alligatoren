package de.croggle.ui.screens;

import static de.croggle.data.LocalizationHelper._;

import android.util.Log;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.AlligatorApp;
import de.croggle.data.AssetManager;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.SettingChangeListener;
import de.croggle.game.ColorController;
import de.croggle.game.MultipleChoiceGameController;
import de.croggle.game.board.Board;
import de.croggle.game.board.IllegalBoardException;
import de.croggle.game.level.LevelPackage;
import de.croggle.game.level.LevelPackagesController;
import de.croggle.game.level.MultipleChoiceLevel;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.HintDialog;
import de.croggle.ui.actors.IngameMenuDialog;
import de.croggle.ui.actors.NotificationDialog;
import de.croggle.ui.actors.PagedScrollPane;
import de.croggle.ui.renderer.ActorLayoutConfiguration;
import de.croggle.ui.renderer.BoardActor;

/**
 * Screen which the player sees when entering Multiple choice levels.
 */
public class MultipleChoiceScreen extends AbstractScreen implements
		SettingChangeListener {

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
		game.getSettingController().addSettingChangeListener(this);
	}

	@Override
	protected void onShow() {
		gameController.setTimeStamp();
		gameController.enterPlacement();
	}
	
	@Override
	public void hide() {
		super.hide();
		gameController.updateTime();
		gameController.setTimeStamp();
		//Log.d("check time", "elapsedTime: " + gameController.getElapsedTime() + " ms");
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
		leftTable.add(startSimulation).size(200).center().right();

		final ColorController colorController = gameController
				.getColorController();

		final ActorLayoutConfiguration config = new ActorLayoutConfiguration();
		config.setColorController(colorController);

		MultipleChoiceLevel level = (MultipleChoiceLevel) gameController
				.getLevel();

		PagedScrollPane pager = new PagedScrollPane();

		checkboxes = new CheckBox[3];
		boardActor = new BoardActor(level.getInitialBoard(), config);
		boardActor.setColorBlindEnabled(game.getSettingController()
				.getCurrentSetting().isColorblindEnabled());
		game.getSettingController().addSettingChangeListener(boardActor);
		boardActor.setZoomAndPanEnabled(false);
		Table boardTable = new Table();
		boardTable.add(boardActor).expand().fill();
		pager.addPage(boardTable);

		for (int i = 0; i < level.getAnswers().length; i++) {
			Board answer = level.getAnswers()[i];
			boardTable = new Table();
			Table pageTable = new Table();
			checkboxes[i] = new CheckBox("", helper.getCheckBoxStyle());
			boardActor = new BoardActor(answer, config);
			boardActor.setColorBlindEnabled(game.getSettingController()
					.getCurrentSetting().isColorblindEnabled());
			game.getSettingController().addSettingChangeListener(boardActor);
			boardActor.setZoomAndPanEnabled(false);
			boardTable.add(boardActor).expand().fill();
			pageTable.add(checkboxes[i]).top().center();
			pageTable.row();
			pageTable.add(boardTable).center().expand().fill();

			pager.addPage(pageTable);

		}

		table.stack(pager, leftTable).expand().fill();

		// TODO remove Simulationbutton and checkboxes and add simulationbutton
		// on each page.
		onSettingChange(game.getSettingController().getCurrentSetting());

		pager.setFlingTime(0.3f);
		pager.setPageSpacing(5);
		pager.setWidth(getViewportWidth());
		pager.setScrollingDisabled(false, true);
	}

	private void checkZoom() {
		// In a MC Level there is no zoom function
	}

	@Override
	public void onSettingChange(Setting setting) {
		// In a MC Level there is no zoom function

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

	private class HintClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			Dialog dialog = new HintDialog(gameController.getLevel());
			dialog.show(stage);
		}
	}
}
