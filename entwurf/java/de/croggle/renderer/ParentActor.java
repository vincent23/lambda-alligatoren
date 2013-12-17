package de.croggle.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * An actor used for representing a parent object.
 */
public abstract class ParentActor extends Actor {
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

	@Override
	public void act(float delta) {
	}
}
