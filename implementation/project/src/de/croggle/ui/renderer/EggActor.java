package de.croggle.ui.renderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.croggle.data.AssetManager;
import de.croggle.game.board.Egg;

/**
 * An actor used for representing an egg.
 */
public class EggActor extends BoardObjectActor {

	Texture tex;
	TextureRegion texreg;
	AssetManager assetManager;
	
	/**
	 * Creates a new actor.
	 * 
	 * @param egg
	 *            The egg represented by the actor
	 */
	public EggActor(Egg egg) {
		super(egg);
		
		assetManager = AssetManager.getInstance();
	}

	/**
	 * Draws the actor. The sprite batch is configured to draw in he parent's
	 * coordinate system.
	 * 
	 * @param batch
	 *            The sprite batch specifies where to draw into.
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
	 *            Time in seconds since the last update.
	 */
	@Override
	public void act(float delta) {
	}

	/**
	 * Signals the actor to (re-)enter the normal rendering state. That is, an
	 * egg with a specific color.
	 */
	public void enterNormalState() {

	}

	/**
	 * Signals the actor to enter the hatching rendering state. That is,
	 * scattered eggshell with the specific color. Will initiate a transition
	 * animation from a normal egg to the broken eggshell.
	 */
	public void enterHatchingState() {

	}
}
