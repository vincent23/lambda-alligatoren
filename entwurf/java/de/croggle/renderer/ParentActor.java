package de.croggle.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * An actor used for representing a parent object.
 * 
 * @navassoc 1 - 1 de.croggle.model.Parent
 */
public class ParentActor extends Actor implements ChildChangedListener {
	// causes error, wtf?
	// private ParentActor() {
	// }

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

	@Override
	public void act(float delta) {
	}
}
