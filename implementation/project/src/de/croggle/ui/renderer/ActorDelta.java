package de.croggle.ui.renderer;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * A class representing a set of changes made or to be made to an {@link Actor}.
 * Used for example for just finding changes to be made to an {@link ActorLayout} but leave it to the
 * caller to actually apply those changes, which allows for animating those.
 * 
 */
public class ActorDelta {
	
	private BoardObjectActor actor;
	private float newWidth;
	private float newHeight;
	private float newX;
	private float newY;

	/**
	 * 
	 * @param actor
	 */
	private ActorDelta(BoardObjectActor actor) {
		if (actor == null) {
			throw new NullPointerException("Cannot instantiate ActorDeltas without an actor");
		}
		setActor(actor);
		newWidth = actor.getWidth();
		newHeight = actor.getHeight();
	}
	
	/**
	 * 
	 * Note: This constructor does not have an explicit access modifier since
	 * only the renderer package is supposed to create BoardObjectActorDeltas.
	 * 
	 * @param actor
	 * @param newX
	 * @param newY
	 * @param newWidth
	 * @param newHeight
	 */
	ActorDelta(BoardObjectActor actor, float newX, float newY, float newWidth, float newHeight) {
		this(actor);
		
		setNewX(newX);
		setNewY(newY);
		setNewWidth(newWidth);
		setNewHeight(newHeight);
	}
	
	/**
	 * 
	 * @param actor
	 * @param newX
	 * @param newY
	 * @param newSize can be null.
	 */
	ActorDelta(BoardObjectActor actor, float newX, float newY, Vector2 newSize) {
		this(actor);
		
		setNewX(newX);
		setNewY(newY);
		if (newSize == null) {
			setNewWidth(actor.getWidth());
			setNewHeight(actor.getHeight());
		} else {
			setNewWidth(newSize.x);
			setNewHeight(newSize.y);
		}
	}
	
	/**
	 * 
	 * @param actor
	 * @param newPosition can be null.
	 * @param newWidth
	 * @param newHeight
	 */
	ActorDelta(BoardObjectActor actor, Vector2 newPosition, float newWidth, float newHeight) {
		this(actor);
		
		if (newPosition == null) {
			setNewX(actor.getX());
			setNewY(actor.getY());
		} else {
			setNewX(newPosition.x);
			setNewY(newPosition.y);
		}
		
		setNewWidth(newWidth);
		setNewHeight(newHeight);
	}

	public BoardObjectActor getActor() {
		return actor;
	}

	/**
	 * Note: This method is only available to allow for pooling
	 * BoarcObjectActorDelta pooling and thus is only visible within the
	 * renderer package.
	 * 
	 * @param actor
	 */
	void setActor(BoardObjectActor actor) {
		this.actor = actor;
	}

	public float getNewWidth() {
		return newWidth;
	}

	/**
	 * Note: This method is only available to allow for pooling
	 * BoarcObjectActorDelta pooling and thus is only visible within the
	 * renderer package.
	 * 
	 * @param newWidth
	 */
	void setNewWidth(float newWidth) {
		if (newWidth < 0) {
			return;
		}
		this.newWidth = newWidth;
	}

	public float getNewHeight() {
		return newHeight;
	}

	/**
	 * 
	 * Note: This method is only available to allow for pooling
	 * BoarcObjectActorDelta pooling and thus is only visible within the
	 * renderer package.
	 * 
	 * @param newHeight
	 */
	void setNewHeight(float newHeight) {
		if (newHeight < 0) {
			return;
		}
		this.newHeight = newHeight;
	}

	public float getNewX() {
		return newX;
	}

	/**
	 * 
	 * Note: This method is only available to allow for pooling
	 * BoarcObjectActorDelta pooling and thus is only visible within the
	 * renderer package.
	 * 
	 * @param newX
	 */
	void setNewX(float newX) {
		this.newX = newX;
	}

	public float getNewY() {
		
		return newY;
	}
	
	/**
	 * 
	 * Note: This method is only available to allow for pooling
	 * BoarcObjectActorDelta pooling and thus is only visible within the
	 * renderer package.
	 * 
	 * @param newY
	 */
	void setNewY(float newY) {
		this.newY = newY;
	}

}
