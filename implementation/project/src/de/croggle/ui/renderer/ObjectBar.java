package de.croggle.ui.renderer;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.ui.StyleHelper;

/**
 * The bar to drag alligators and eggs from onto the screen.
 **/
public class ObjectBar extends Table {

	private final AgedAlligatorActor agedAlligator;
	private final ColoredAlligatorActor coloredAlligator;
	private final EggActor egg;

	/**
	 * Creates an object bar with the ui elements the user can drag to the
	 * screen per default.
	 */
	public ObjectBar(boolean colorBlind) {
		agedAlligator = new AgedAlligatorActor(new AgedAlligator(false, false));
		coloredAlligator = new ColoredAlligatorActor(new ColoredAlligator(
				false, false, Color.uncolored(), false), colorBlind);
		egg = new EggActor(new Egg(false, false, Color.uncolored(), false),
				colorBlind);

		setBackground(StyleHelper.getInstance().getDrawable("widgets/button"));

		// TODO size guessed and hardcoded, add way to get preferred aspect
		// ratio
		add(coloredAlligator).size(200, 110).row();
		add(agedAlligator).size(200, 95).row();
		add(egg).size(140, 100).row();
	}

}
