package de.croggle.ui.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * An actor used for representing a parent object.
 */
public abstract class ParentActor extends BoardObjectActor {

	/**
	 * Draws the actor. The sprite batch is configured to draw in he parent's coordinate system.
	 * @param batch The sprite batch specifies where to draw into.
	 * @param parentAlpha
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

	/**
	 * Updates the actor based on time.
	 * @param delta Time in seconds since the last update.
	 */
	@Override
	public void act(float delta) {
	}
}
