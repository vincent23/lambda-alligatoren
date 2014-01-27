package de.croggle.ui.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import de.croggle.AlligatorApp;
import de.croggle.data.AssetManager;
import de.croggle.game.ColorController;
import de.croggle.game.ColorOverflowException;
import de.croggle.game.GameController;
import de.croggle.game.board.Board;
import de.croggle.ui.renderer.ActorLayoutConfiguration;
import de.croggle.ui.renderer.BoardActor;
import de.croggle.util.convert.LambdaToAlligator;

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
		
		// load the texture atlas 
		AssetManager assetManager = AssetManager.getInstance();
		assetManager.load("textures/pack.atlas", TextureAtlas.class);
		this.setBackground("textures/swamp.png");
	}
	
	protected void onShow() {
		ColorController cctrlr = new ColorController();
		
		
		Board b = LambdaToAlligator.convert("(λa.λb.λs.λz.(a s (b s z))) (λs.λz.(s (s (s z)))) (λs.λz.(s (s (s (s z)))))");
		//Board b = LambdaToAlligator.convert("(λx.x) ((λy.y) (λz.z))");
		//Board b = LambdaToAlligator.convert("x");
		for (int i = 0; i < 6; i++) {
			// tell the colorcontroller that we need some colors instantiated
			try {
				cctrlr.requestColor();
			} catch (ColorOverflowException e) {
				throw new RuntimeException("Test failed");
			}
		}
		/*
		Color c0;
		Color c1;
		try {
			c0 = cctrlr.requestColor();
			c1 = cctrlr.requestColor();
		} catch (ColorOverflowException e) {
			throw new RuntimeException("Test failed");
		}
		ColoredAlligator a00 = new ColoredAlligator(false, false, c0, false);
		ColoredAlligator a01 = new ColoredAlligator(false, false, c0, false);
		ColoredAlligator a10 = new ColoredAlligator(false, false, c1, false);
		Egg e00 = new Egg(false, false, c0, false);
		Egg e01 = new Egg(false, false, c0, false);
		Egg e10 = new Egg(false, false, c1, false);
		b.addChild(a00);
		a00.addChild(a01);
		a00.addChild(a10);
		a01.addChild(e00);
		a01.addChild(e01);
		a10.addChild(e10);
		*/
		
		ActorLayoutConfiguration config = new ActorLayoutConfiguration();
		config.setColorController(cctrlr);
		BoardActor ba = new BoardActor(b, config);
		ba.setColor(new com.badlogic.gdx.graphics.Color(1, 1, 1, .5f));
		//ba.setScale(.5f); // TODO test this/ get it to work later
		
		this.table.add(ba).fill().expand()
			.width(getViewportWidth() * 0.5f).height(getViewportHeight() * 0.5f);
		return;
	}
	
	public void hide() {
		table.clear();
	}
	
	public void dispose() {
		super.dispose();
	}
	
	
}
