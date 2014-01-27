package de.croggle.ui.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.AlligatorApp;
import de.croggle.game.GameController;
import de.croggle.game.board.AgedAlligator;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.renderer.AgedAlligatorActor;
import de.croggle.ui.renderer.ColoredAlligatorActor;
import de.croggle.ui.renderer.EggActor;

/**
 * The bar to drag alligators and eggs from onto the screen.
 **/
public class ObjectBar extends Table {

	private AgedAlligatorActor agedAlligator;
	private ColoredAlligatorActor coloredAlligator;
	private EggActor egg;
	private GameController gameController;
	private AlligatorApp game;

	/**
	 * Creates an object bar with the ui elements the user can drag to the
	 * screen per default.
	 */
	public ObjectBar(AlligatorApp game, GameController gameController) {
		this.game = game;
		this.gameController = gameController;
		agedAlligator = new AgedAlligatorActor(new AgedAlligator(true, false));
		// coloredAlligator = new ColoredAlligatorActor(new
		// ColoredAlligator(true,
		// false, new Color(1), false), new ColorController());
		// egg = new EggActor(new Egg(true, false, new Color(1), false),
		// new ColorController());

		ImageButton startSimulation = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/dummy-button"));
		startSimulation.addListener(new StartSimulationListener());

		setBackground(StyleHelper.getInstance().getDrawable(
				"widgets/dummy-button"));

		add(coloredAlligator).row();
		// add(agedAlligator).row();
		// add(egg).row();
		add(startSimulation);
	}

	private class StartSimulationListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			super.clicked(event, x, y);
			game.showSimulationModeScreen(gameController);
		}
	}

}
