package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.croggle.AlligatorApp;
import de.croggle.game.GameController;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.ObjectBar;

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

		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();

		Table leftTable = new Table();
		ImageButton menu = new ImageButton(
				helper.getImageButtonStyleRound("widgets/dummy-icon"));
		ImageButton hint = new ImageButton(
				helper.getImageButtonStyleRound("widgets/dummy-icon"));
		ImageButton zoomIn = new ImageButton(
				helper.getImageButtonStyleRound("widgets/dummy-icon"));
		ImageButton zoomOut = new ImageButton(
				helper.getImageButtonStyleRound("widgets/dummy-icon"));
		ObjectBar objectBar = new ObjectBar();

		leftTable.pad(30);
		leftTable.defaults().space(30);
		leftTable.add(menu).size(100).top().left();
		leftTable.row();
		// TODO only activated after some time
		leftTable.add(hint).expand().size(100).top().left();
		leftTable.row();
		// TODO only if zoom buttons are enabled
		leftTable.add(zoomIn).size(50).left();
		leftTable.row();
		leftTable.add(zoomOut).size(50).left();

		table.add(leftTable).expand().fill();
		table.add(objectBar).padLeft(30);
	}
}
