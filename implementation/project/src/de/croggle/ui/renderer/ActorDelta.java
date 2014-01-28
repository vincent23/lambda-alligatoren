package de.croggle.ui.renderer;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * A class representing a set of changes made or to be made to an {@link Actor}.
 * Used for example for just finding changes to be made to an
 * {@link ActorLayout} but leave it to the caller to actually apply those
 * changes, which allows for animating those.
 * 
 */
public class ActorDelta {

	private BoardObjectActor actor;
	
	private float newWidth;
	private float newHeight;
	private float newX;
	private float newY;
	private boolean xChanged;
	private boolean yChanged;
	private boolean widthChanged;
	private boolean heightChanged;
	private boolean created;

	public ActorDelta() {
		
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

	public boolean isxChanged() {
		return xChanged;
	}

	void setxChanged(boolean xChanged) {
		this.xChanged = xChanged;
	}

	public boolean isyChanged() {
		return yChanged;
	}

	void setyChanged(boolean yChanged) {
		this.yChanged = yChanged;
	}

	public boolean isWidthChanged() {
		return widthChanged;
	}

	void setWidthChanged(boolean widthChanged) {
		this.widthChanged = widthChanged;
	}

	public boolean isHeightChanged() {
		return heightChanged;
	}

	void setHeightChanged(boolean heightChanged) {
		this.heightChanged = heightChanged;
	}

	void reset() {
		xChanged = false;
		yChanged = false;
		widthChanged = false;
		heightChanged = false;
		created = false;
	}
	
	boolean anythingChanged() {
		return xChanged || yChanged || widthChanged || heightChanged || created;
	}

	public boolean isCreated() {
		return created;
	}

	public void setCreated(boolean created) {
		this.created = created;
	}
}
