package de.croggle.ui.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.croggle.game.board.InternalBoardObject;

/**
 * An actor used for representing a parent object.
 */
public abstract class ParentActor extends BoardObjectActor {

	/**
	 * Superconstructor for all render actors.
	 * 
	 * @param object the object represented by this actor
	 */
	public ParentActor(InternalBoardObject object) {
		super(object);
	}

	/**
	 * Draws the actor. The sprite batch is configured to draw in the parent's
	 * coordinate system.
	 * 
	 * @param batch
	 *            the sprite batch specifies where to draw into
	 * @param parentAlpha
	 *            the parent's alpha value
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

	/**
	 * Updates the actor based on time.
	 * 
	 * @param delta
	 *            time in seconds since the last update
	 */
	@Override
	public void act(float delta) {
	}
}
