package de.croggle.ui.screens;

import de.croggle.AlligatorApp;
import de.croggle.game.ColorOverflowException;
import de.croggle.game.GameController;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.ui.renderer.ColoredAlligatorActor;

/**
 * Screen which is shown during the evaluation-phase of a level. For reference
 * see ``Pflichtenheft 10.5.5 / Abbildung 14''.
 */
public class SimulationModeScreen extends AbstractScreen {
	
	private final GameController gameController;
	
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
		try {
			this.stage.addActor(new ColoredAlligatorActor(new ColoredAlligator(true, true, controller.getColorController().requestColor(), true), gameController.getColorController()));
		} catch (ColorOverflowException e) {
			//TODO silly exception
		}
	}
}
