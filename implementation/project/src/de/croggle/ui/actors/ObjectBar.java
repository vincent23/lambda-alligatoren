package de.croggle.ui.actors;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

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

	/**
	 * Creates an object bar with the ui elements the user can drag to the
	 * screen per default.
	 */
	public ObjectBar() {
		agedAlligator = new AgedAlligatorActor(new AgedAlligator(true, false));
		// coloredAlligator = new ColoredAlligatorActor(new
		// ColoredAlligator(true,
		// false, new Color(1), false), new ColorController());
		// egg = new EggActor(new Egg(true, false, new Color(1), false),
		// new ColorController());

		ImageButton startSimulation = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/dummy-button"));

		setBackground(StyleHelper.getInstance().getDrawable(
				"widgets/dummy-button"));

		add(coloredAlligator).row();
		// add(agedAlligator).row();
		// add(egg).row();
		add(startSimulation);
	}

}
