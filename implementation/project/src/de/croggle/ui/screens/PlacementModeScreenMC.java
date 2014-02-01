package de.croggle.ui.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;


import de.croggle.AlligatorApp;
import de.croggle.data.AssetManager;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.SettingChangeListener;
import de.croggle.game.ColorController;
import de.croggle.game.GameController;
import de.croggle.game.board.Board;
import de.croggle.game.board.IllegalBoardException;
import de.croggle.game.level.LevelPackage;
import de.croggle.game.level.LevelPackagesController;
import de.croggle.game.level.MultipleChoiceLevel;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.IngameMenuDialog;
import de.croggle.ui.actors.PagedScrollPane;
import de.croggle.ui.renderer.ActorLayoutConfiguration;
import de.croggle.ui.renderer.BoardActor;

/**
 * Screen within which the player can manipulate the board by moving alligators
 * and eggs. For reference see ``Pflichtenheft 10.5.4 / Abbildungen 12 und 1''.
 */
public class PlacementModeScreenMC extends AbstractScreen implements
		SettingChangeListener {

	private GameController gameController;
	private BoardActor boardActor;

	private ImageButton zoomIn;
	private ImageButton zoomOut;
	private CheckBox checkboxes[];

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
	public PlacementModeScreenMC(AlligatorApp game, GameController controller) {
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
		try {
			setBackground(pack.getDesign());
		} catch (GdxRuntimeException ex) {
			setBackground("textures/swamp.png");
		}
		game.getSettingController().addSettingChangeListener(this);
	}
	
	protected void onShow() {
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
		ObjectBarMC objectBar = new ObjectBarMC(game, gameController);

		// add listeners
		menu.addListener(new MenuClickListener());

		leftTable.pad(30);
		leftTable.defaults().space(30);
		leftTable.add(menu).size(100).top().left();
		leftTable.row();
		// TODO only activated after some time
		leftTable.add(hint).expand().size(100).top().left();
		leftTable.row();

		leftTable.add(zoomIn).size(70).left();
		leftTable.row();
		leftTable.add(zoomOut).size(70).left();

		final ColorController colorController = gameController
				.getColorController();

		final ActorLayoutConfiguration config = new ActorLayoutConfiguration();
		config.setColorController(colorController);
		
		MultipleChoiceLevel level = (MultipleChoiceLevel)gameController.getLevel();
		
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
		
		for(int i = 0; i < level.getAnswers().length; i++){
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
		table.add(leftTable).expand().fill();
		table.add(pager).expand().fill();
		table.add(objectBar).padLeft(30);

		onSettingChange(game.getSettingController().getCurrentSetting());
		
		pager.setFlingTime(0.3f);
		pager.setPageSpacing(75);
		pager.setWidth(getViewportWidth() * 0.7f);
		pager.setScrollingDisabled(false, true);
	}

	private void checkZoom() {
		//In a MC Level there is no zoom function
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
	


	/**
	 * The bar to drag alligators and eggs from onto the screen.
	 **/
	private class ObjectBarMC extends Table {

		private GameController gameController;
		private AlligatorApp game;

		/**
		 * Creates an object bar with the ui elements the user can drag to the
		 * screen per default.
		 */
	private ObjectBarMC(AlligatorApp game, GameController gameController) {
			this.game = game;
			this.gameController = gameController;

			ImageButton startSimulation = new ImageButton(StyleHelper.getInstance()
					.getImageButtonStyleRound("widgets/icon-next"));
			startSimulation.addListener(new StartSimulationListener());

			setBackground(StyleHelper.getInstance().getDrawable("widgets/button"));
			add(startSimulation).size(200);
		}

		private class StartSimulationListener extends ClickListener {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				boolean[] answer = new boolean[3];
				for(int i = 0; i < checkboxes.length; i++ ){
					answer[i] = checkboxes[i].isChecked();
				}
				gameController.setMCSelection(answer);
				
				if( gameController.getAnswerMcIsValid()){
					try {
						game.showSimulationModeScreen(gameController);
					} catch (IllegalBoardException e) {
						// TODO handle invalid board
					}
				}
			}
		}

	}
}
