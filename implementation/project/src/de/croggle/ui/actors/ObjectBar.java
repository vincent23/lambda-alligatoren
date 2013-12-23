package de.croggle.ui.actors;

import de.croggle.ui.renderer.AgedAlligatorActor;
import de.croggle.ui.renderer.ColoredAlligatorActor;
import de.croggle.ui.renderer.EggActor;

/**
 * The bar to drag alligators and eggs from onto the screen.
 **/
public class ObjectBar extends com.badlogic.gdx.scenes.scene2d.Actor {

	private AgedAlligatorActor agedAlligator;
	private ColoredAlligatorActor coloredAlligator;
	private EggActor egg;

	/**
	 * Creates an object bar with the ui elements the user can drag to the
	 * screen per default.
	 */
	public ObjectBar() {
	}

}
