package de.croggle.renderer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.croggle.game.model.ColoredAlligator;

/**
 * An actor used for representing a parent object.
 *
 * @navassoc 1 - 1 Parent
 */
class ParentActor extends Actor {
	private ParentActor() {
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

	@Override
	public void act(float delta) {
	}
}
