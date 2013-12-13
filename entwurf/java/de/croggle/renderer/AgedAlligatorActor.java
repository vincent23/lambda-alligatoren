package de.croggle.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.croggle.model.AgedAlligator;

/**
 * An actor used for representing an aged alligator.
 *
 * @navassoc 1 - 1 AgedAlligator
 */
public class AgedAlligatorActor extends ParentActor {

	/**
	 * Creates a new actor.
	 *
	 * @param agedAlligator AgedAlligator to draw.
	 */
	public AgedAlligatorActor(AgedAlligator agedAlligator) {
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

	@Override
	public void act(float delta) {
	}
}
