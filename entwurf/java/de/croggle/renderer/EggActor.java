package de.croggle.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * An actor used for representing an egg.
 */
public class EggActor extends BoardObjectActor {

	/**
	 * Creates a new actor.
	 */
	public EggActor() {
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

	@Override
	public void act(float delta) {
	}
	
	/**
	 * Signals the actor to (re-)enter the normal rendering state.
	 * That is, an egg with a specific color.
	 */
	public void enterNormalState() {
		
	}
	
	/**
	 * Signals the actor to enter the hatching rendering state.
	 * That is, scattered eggshell with the specific color.
	 * Will initiate a transition animation from a normal egg to the broken eggshell.
	 */
	public void enterHatchingState() {
		
	}
}
