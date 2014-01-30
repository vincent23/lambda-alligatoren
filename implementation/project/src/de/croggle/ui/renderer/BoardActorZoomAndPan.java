package de.croggle.ui.renderer;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;

class BoardActorZoomAndPan extends ActorGestureListener {
	private final BoardActor b;
	
	private float maxX;
	private float maxY;
	private float minX;
	private float minY;
	private float maxZoom;
	private float minZoom;
	private Vector2 point = new Vector2();
	
	public BoardActorZoomAndPan(BoardActor b) {
		this.b = b;
		maxX = Float.POSITIVE_INFINITY;
		maxY = Float.POSITIVE_INFINITY;
		minX = Float.NEGATIVE_INFINITY;
		minY = Float.NEGATIVE_INFINITY;

		calculateLimits();
	}
	
	@Override
	public void pan(InputEvent event, float x, float y, float deltaX,
			float deltaY) {
		Vector2 delta = new Vector2(deltaX, deltaY);
		
		float posX = b.getWorldX();
		if (posX + delta.x >= minX && posX + delta.x <= maxX) {
			b.setWorldX(posX + delta.x);
		}
		float posY = b.getWorldY();
		if (posY + delta.y >= minY && posY + delta.y <= maxY) {
			b.setWorldY(posY + delta.y);
		}
	}

	@Override
	public void pinch(InputEvent event, Vector2 initialPointer1,
			Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		float density = Gdx.graphics.getDensity();
		float dist = initialPointer1.dst(initialPointer2);
		float newdist = pointer1.dst(pointer2);
		float delta = newdist - dist;
		float percent = delta / density / 10;
		float pointX = initialPointer1.x
				+ (initialPointer2.x - initialPointer1.x) / 2;
		float pointY = initialPointer1.y
				+ (initialPointer2.y - initialPointer1.y) / 2;
		zoomIn(percent, pointX, pointY);
	}
	
	void calculateLimits() {
		calculateZoomLimits();
		calculatePanLimits(getZoom());
	}

	private void calculateZoomLimits() {
		ActorLayout layout = this.b.getLayout();
		ActorLayoutConfiguration config = layout.getLayoutConfiguration();
		
		Board b = layout.getBoard();
		Map<BoardObject, Float> heightMap = layout.getLayoutStatistics()
				.getHeightMap();
		float boardHeight = heightMap.get(b);

		// zoom limits
		float lowestScale = 0.2373046875f; // 0.75^5 TODO
		// allow maximum enlargement to have the smallest object being displayed
		// with half the screen size
		maxZoom = this.b.getWidth()
				/ (config.getUniformObjectWidth() * 2 * lowestScale);
		// allow maximum the whole tree times 1.2 fitting on the screen
		minZoom = Math.min(this.b.getHeight() / (boardHeight * 1.2f), 1.f);
	}

	private void calculatePanLimits(float zoom) {
		ActorLayout layout = this.b.getLayout();
		ActorLayoutConfiguration config = layout.getLayoutConfiguration();
		
		Board b = layout.getBoard();
		Map<BoardObject, Float> heightMap = layout.getLayoutStatistics()
				.getHeightMap();
		Map<BoardObject, Float> widthMap = layout.getLayoutStatistics()
				.getWidthMap();

		Vector2 origin = config.getTreeOrigin();

		float boardHeight = heightMap.get(b);
		float boardWidth = widthMap.get(b);

		// pan limits
		maxX = this.b.getWidth() - origin.x * zoom;
		minX = -(origin.x + boardWidth) * zoom;
		maxY = this.b.getHeight() + (boardHeight - origin.y) * zoom;
		minY = -origin.y * zoom;

		// prevent locking in
		if (this.b.getWorldX() >= maxX) {
			this.b.setWorldX(maxX);
		} else if (this.b.getWorldX() < minX) {
			this.b.setWorldX(minX);
		}
		if (this.b.getWorldY() >= maxY) {
			this.b.setWorldY(maxY);
		} else if (this.b.getWorldY() < minY) {
			this.b.setWorldY(minY);
		}
	}
	
	private float getZoom() {
		return b.getZoom();
	}
	
	/**
	 * 
	 * @param percent
	 *            the percentage of how much the actor's zoom is to be increased
	 * @param pointX
	 *            x coordinate of the point to be zoomed onto (actor
	 *            coordinates)
	 * @param pointY
	 *            y coordinate of the point to be zoomed onto (actor
	 *            coordinates)
	 * @return true if zoom was successful, false if the zoom limits were
	 *         exceeded and nothing was changed
	 */
	public boolean zoomIn(float percent, float pointX, float pointY) {
		if (percent < 0) {
			return zoomOut(-percent, pointX, pointY);
		}
		float factor = 1 + percent / 100;
		float zoom = getZoom();
		float newZoom = zoom * factor;
		if (setZoom(newZoom, false)) {
			zoomOnPoint(pointX, pointY, zoom, newZoom);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param percent
	 *            the percentage of how much the actor's zoom is to be decreased
	 * @param pointX
	 *            x coordinate of the point to be zoomed onto (actor
	 *            coordinates)
	 * @param pointY
	 *            y coordinate of the point to be zoomed onto (actor
	 *            coordinates)
	 * @return true if zoom was successful, false if the zoom limits were
	 *         exceeded and nothing was changed
	 */
	public boolean zoomOut(float percent, float pointX, float pointY) {
		if (percent < 0) {
			return zoomIn(-percent, pointX, pointY);
		}
		float factor = 1 - percent / 100;
		float zoom = getZoom();
		float newZoom = zoom * factor;
		if (setZoom(newZoom, false)) {
			zoomOnPoint(pointX, pointY, zoom, newZoom);
			return true;
		}
		return false;
	}

	private boolean setZoom(float zoom, boolean calculatePanLimits) {
		if (zoom > minZoom && zoom <= maxZoom) {
			b.getWorld().setScale(zoom);
			if (calculatePanLimits) {
				calculatePanLimits(zoom);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param pointX
	 *            the x coordinate to zoom onto in actor coordinates (offset,
	 *            scaling etc)
	 * @param pointY
	 *            the x coordinate to zoom onto in actor coordinates (offset,
	 *            scaling etc)
	 * @param oldZoom
	 * @param newZoom
	 */
	private void zoomOnPoint(float pointX, float pointY, float oldZoom,
			float newZoom) {
		/*
		 * Without this method, zooming would be relative to the actor's origin
		 * so we will have to shift it away from the actual zoom point when
		 * zooming in and pull closer if we are zooming out
		 */
		point.x = pointX;
		point.y = pointY;
		point = b.getWorld().parentToLocalCoordinates(point, oldZoom);
		point = b.getWorld().localToParentCoordinates(point, newZoom);
		float newPointX = point.x;
		float newPointY = point.y;

		float dx = -(newPointX - pointX);
		float dy = -(newPointY - pointY);
		
		float posX = b.getWorldX();
		float posY = b.getWorldY();
		b.setWorldX(posX + dx);
		b.setWorldY(posY + dy);
		calculatePanLimits(newZoom);
	}
}