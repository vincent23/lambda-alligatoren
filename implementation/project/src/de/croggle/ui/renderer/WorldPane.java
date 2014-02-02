package de.croggle.ui.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

/**
 * An inner "pane" to have the world displayed on Only way to have the
 * coordinate translation functions overridden at the correct place
 */
class WorldPane extends Group {
	private final BoardActor b;

	WorldPane(BoardActor b) {
		this.b = b;
	}

	private Vector2 point = new Vector2();

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		float x = getX();
		float y = getY();

		setX(x + b.getWorldX());
		setY(y + b.getWorldY());

		super.draw(batch, parentAlpha);

		setX(x);
		setY(y);
	}

	@Override
	public Vector2 localToParentCoordinates(Vector2 localCoords) {
		return localToParentCoordinates(localCoords, getScaleX());
	}

	public Vector2 localToParentCoordinates(Vector2 localCoords, float scale) {
		final float rotation = -getRotation();
		final float scaleX = scale;
		final float scaleY = scale;
		final float x = getX();
		final float y = getY();
		if (rotation == 0) {
			if (scaleX == 1 && scaleY == 1) {
				localCoords.x += x;
				localCoords.y += y;
			} else {
				final float originX = getOriginX();
				final float originY = getOriginY();
				localCoords.x = (localCoords.x - originX) * scaleX + originX
						+ x;
				localCoords.y = (localCoords.y - originY) * scaleY + originY
						+ y;
			}
		} else {
			final float cos = (float) Math.cos(rotation
					* MathUtils.degreesToRadians);
			final float sin = (float) Math.sin(rotation
					* MathUtils.degreesToRadians);
			final float originX = getOriginX();
			final float originY = getOriginY();
			final float tox = localCoords.x - originX;
			final float toy = localCoords.y - originY;
			localCoords.x = (tox * cos + toy * sin) * scaleX + originX + x;
			localCoords.y = (tox * -sin + toy * cos) * scaleY + originY + y;
		}
		localCoords.x += b.getWorldX();
		localCoords.y += b.getWorldY();
		return localCoords;
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		// TODO custom implementation still necessary with bug free coordination
		// converter methods?
		if (touchable && getTouchable() == Touchable.disabled)
			return null;
		Array<Actor> children = getChildren();
		for (int i = children.size - 1; i >= 0; i--) {
			Actor child = children.get(i);
			if (!child.isVisible()) {
				continue;
			}
			child.parentToLocalCoordinates(point.set(x, y));
			Actor hit = child.hit(point.x, point.y, touchable);
			if (hit != null) {
				return hit;
			}
		}
		localToParentCoordinates(point.set(x, y));
		if (point.x <= this.b.getWidth() && point.y <= this.b.getHeight()) {
			return this;
		}
		return null;
	}

	@Override
	public Vector2 parentToLocalCoordinates(Vector2 parentCoords) {
		return parentToLocalCoordinates(parentCoords, getScaleX());
	}

	public Vector2 parentToLocalCoordinates(Vector2 parentCoords, float scale) {
		final float rotation = getRotation();
		final float scaleX = scale;
		final float scaleY = scale;
		final float childX = getX();
		final float childY = getY();
		parentCoords.x -= b.getWorldX();
		parentCoords.y -= b.getWorldY();
		if (rotation == 0) {
			if (scaleX == 1 && scaleY == 1) {
				parentCoords.x -= childX;
				parentCoords.y -= childY;
			} else {
				final float originX = getOriginX();
				final float originY = getOriginY();
				parentCoords.x = (parentCoords.x - childX - originX) / scaleX
						+ originX;
				parentCoords.y = (parentCoords.y - childY - originY) / scaleY
						+ originY;
			}
		} else {
			final float cos = (float) Math.cos(rotation
					* MathUtils.degreesToRadians);
			final float sin = (float) Math.sin(rotation
					* MathUtils.degreesToRadians);
			final float originX = getOriginX();
			final float originY = getOriginY();
			final float tox = parentCoords.x - childX - originX;
			final float toy = parentCoords.y - childY - originY;
			parentCoords.x = (tox * cos + toy * sin) / scaleX + originX;
			parentCoords.y = (tox * -sin + toy * cos) / scaleY + originY;
		}
		return parentCoords;
	}

	@Override
	public void setScale(float s) {
		super.setScale(s);
		syncBounds();
	}

	public void syncBounds() {
		float s = getScaleX();
		// keep the world actor bounds in sync with BoardActor
		setWidth(b.getWidth() / s);
		setHeight(b.getHeight() / s);
	}
}
