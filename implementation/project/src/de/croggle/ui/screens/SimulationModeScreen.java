package de.croggle.ui.screens;

import com.badlogic.gdx.math.Vector2;

import de.croggle.AlligatorApp;
import de.croggle.game.Color;
import de.croggle.game.ColorController;
import de.croggle.game.ColorOverflowException;
import de.croggle.game.GameController;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.ui.renderer.BoardActor;
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
		
		ColorController cctrlr = new ColorController();
		Board b = new Board();
		Color c0;
		Color c1;
		try {
			c0 = cctrlr.requestColor();
			c1 = cctrlr.requestColor();
		} catch (ColorOverflowException e) {
			throw new RuntimeException("Test failed");
		}
		ColoredAlligator a1 = new ColoredAlligator(false, false, c0, false);
		ColoredAlligator a2 = new ColoredAlligator(false, false, c1, false);
		Egg e1 = new Egg(false, false, c0, false);
		Egg e2 = new Egg(false, false, c1, false);
		b.addChild(a1);
		b.addChild(a2);
		a1.addChild(e1);
		a2.addChild(e2);
		
		BoardActor ba = new BoardActor(b, cctrlr);
		ba.getLayoutConfiguration().setTreeOrigin(new Vector2(0, 400));
		ba.onBoardRebuilt(b);
		
		this.stage.addActor(ba);
	}
}
