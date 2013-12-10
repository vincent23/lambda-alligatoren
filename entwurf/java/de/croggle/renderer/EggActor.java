package de.croggle.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.croggle.game.model.Egg;

/**
 * An actor used for representing an egg.
 *
 * @navassoc 1 - 1 Egg
 */
public class EggActor extends com.badlogic.gdx.scenes.scene2d.Actor {

	/**
	 * Creates a new actor.
	 *
	 * @param egg Egg to draw.
	 */
	public EggActor(Egg egg) {
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

	@Override
	public void act(float delta) {
	}
}
