package de.croggle.ui.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.croggle.game.board.AgedAlligator;

/**
 * An actor used for representing an aged alligator.
 */
public class AgedAlligatorActor extends BoardObjectActor {

	/**
	 * Creates a new actor.
	 * 
	 * @param alligator
	 *            the AgedAlligator represented by this actor
	 */
	public AgedAlligatorActor(AgedAlligator alligator) {
		super(alligator);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void act(float delta) {
	}
}
