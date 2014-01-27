package de.croggle.ui.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.croggle.AlligatorApp;
import de.croggle.data.AssetManager;
import de.croggle.game.ColorController;
import de.croggle.game.GameController;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.ObjectBar;
import de.croggle.ui.renderer.ActorLayoutConfiguration;
import de.croggle.ui.renderer.BoardActor;

/**
 * Screen within which the player can manipulate the board by moving alligators
 * and eggs. For reference see ``Pflichtenheft 10.5.4 / Abbildungen 12 und 1''.
 */
public class PlacementModeScreen extends AbstractScreen {

	private GameController gameController;

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
		gameController.enterPlacement();

		AssetManager assetManager = AssetManager.getInstance();
		assetManager.load("textures/pack.atlas", TextureAtlas.class);

		fillTable();
		setBackground("textures/swamp.png");
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();

		Table leftTable = new Table();
		ImageButton menu = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-menu"));
		ImageButton hint = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-hint"));
		ImageButton zoomIn = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-plus"));
		ImageButton zoomOut = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-minus"));
		ObjectBar objectBar = new ObjectBar(game, gameController);

		leftTable.pad(30);
		leftTable.defaults().space(30);
		leftTable.add(menu).size(100).top().left();
		leftTable.row();
		// TODO only activated after some time
		leftTable.add(hint).expand().size(100).top().left();
		leftTable.row();
		// TODO only if zoom buttons are enabled
		leftTable.add(zoomIn).size(70).left();
		leftTable.row();
		leftTable.add(zoomOut).size(70).left();

		final ColorController colorController = gameController
				.getColorController();

		final ActorLayoutConfiguration config = new ActorLayoutConfiguration();
		config.setColorController(colorController);
		final BoardActor boardActor = new BoardActor(
				gameController.getShownBoard(), config);
		boardActor.setColor(new com.badlogic.gdx.graphics.Color(1, 1, 1, .5f));
		final Table boardTable = new Table();
		boardTable.add(boardActor).fill().expand();

		final Table controlTable = new Table();
		controlTable.add(leftTable).expand().fill();
		controlTable.add(objectBar).padLeft(30);

		table.stack(boardTable, controlTable).fill().expand();
	}
}
