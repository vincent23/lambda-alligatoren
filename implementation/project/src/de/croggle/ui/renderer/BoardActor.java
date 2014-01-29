package de.croggle.ui.renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.SettingChangeListener;
import de.croggle.game.ColorController;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.operations.FlattenTree;
import de.croggle.game.event.BoardEventListener;

/**
 * An actor used for representing a whole board, i.e. an alligator
 * constellation.
 */
public class BoardActor extends Group implements BoardEventListener, SettingChangeListener {

	private ActorLayout layout;

	private final ActorLayoutConfiguration config;
	private float posX;
	private float posY;
	private float maxX;
	private float maxY;
	private float minX;
	private float minY;
	private float maxZoom;
	private float minZoom;
	private final WorldPane world;
	private Vector2 point = new Vector2();
	private boolean colorBlind = false;

	private BoardActorGestureListener gestureListener;

	/**
	 * Creates a new BoardActor. The actor layout of the board's representation
	 * will be created using the given {@link ActorLayoutConfiguration}.
	 * 
	 * @param b
	 *            the board this {@link BoardActor} will represent
	 * @param config
	 *            an {@link ActorLayoutConfiguration} used for creating the
	 *            actor layout
	 */
	public BoardActor(Board b, ActorLayoutConfiguration config) {
		this.config = config;
		this.world = new WorldPane();
		super.addActor(world);
		onBoardRebuilt(b);

		maxX = Float.POSITIVE_INFINITY;
		maxY = Float.POSITIVE_INFINITY;
		minX = Float.NEGATIVE_INFINITY;
		minY = Float.NEGATIVE_INFINITY;

		calculateLimits();
		initializePosition();
	}

	/**
	 * Creates a new BoardActor. This is the simpler version of constructing a
	 * BoardActor, using most of the default {@link ActorLayoutConfiguration}
	 * properties, only requiring the {@link ColorController} to be set
	 * correctly.
	 * 
	 * @param board
	 * @param controller
	 */
	public BoardActor(Board board, ColorController controller) {
		this(board, new ActorLayoutConfiguration()
				.setColorController(controller));
	}
	
	/**
	 * An inner "pane" to have the world displayed on
	 * Only way to have the coordinate translation functions overridden at the correct place
	 */
	private class WorldPane extends Group {
		private Vector2 point = new Vector2();
		
		@Override
		public void draw(SpriteBatch batch, float parentAlpha) {
			float x = getX();
			float y = getY();
			
			setX(x + posX);
			setY(y + posY);

			super.draw(batch, parentAlpha);

			setX(x);
			setY(y);
		}
		
		@Override
		public Vector2 localToParentCoordinates (Vector2 localCoords) {
			return localToParentCoordinates(localCoords, getScaleX());
		}
		
		public Vector2 localToParentCoordinates (Vector2 localCoords, float scale) {
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
					localCoords.x = (localCoords.x - originX) * scaleX + originX + x;
					localCoords.y = (localCoords.y - originY) * scaleY + originY + y;
				}
			} else {
				final float cos = (float)Math.cos(rotation * MathUtils.degreesToRadians);
				final float sin = (float)Math.sin(rotation * MathUtils.degreesToRadians);
				final float originX = getOriginX();
				final float originY = getOriginY();
				final float tox = localCoords.x - originX;
				final float toy = localCoords.y - originY;
				localCoords.x = (tox * cos + toy * sin) * scaleX + originX + x;
				localCoords.y = (tox * -sin + toy * cos) * scaleY + originY + y;
			}
			localCoords.x += posX;
			localCoords.y += posY;
			return localCoords;
		}
		
		@Override		
		public Actor hit(float x, float y, boolean touchable) {
			if (touchable && getTouchable() == Touchable.disabled) return null;
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
			if (point.x <= BoardActor.this.getWidth() && point.y <= BoardActor.this.getHeight()) {
				return this;
			}
			return null;
		}
		
		@Override
		public Vector2 parentToLocalCoordinates (Vector2 parentCoords) {
			return parentToLocalCoordinates(parentCoords, getScaleX());
		}
		
		public Vector2 parentToLocalCoordinates (Vector2 parentCoords, float scale) {
			final float rotation = getRotation();
			final float scaleX = scale;
			final float scaleY = scale;
			final float childX = getX();
			final float childY = getY();
			parentCoords.x -= posX;
			parentCoords.y -= posY;
			if (rotation == 0) {
				if (scaleX == 1 && scaleY == 1) {
					parentCoords.x -= childX;
					parentCoords.y -= childY;
				} else {
					final float originX = getOriginX();
					final float originY = getOriginY();
					parentCoords.x = (parentCoords.x - childX - originX) / scaleX + originX;
					parentCoords.y = (parentCoords.y - childY - originY) / scaleY + originY;
				}
			} else {
				final float cos = (float)Math.cos(rotation * MathUtils.degreesToRadians);
				final float sin = (float)Math.sin(rotation * MathUtils.degreesToRadians);
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
			setWidth(BoardActor.this.getWidth() / s);
			setHeight(BoardActor.this.getHeight() / s);
		}
	}
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (clipBegin()) {
			super.draw(batch, parentAlpha);
			clipEnd();
		}
	}

	private void calculateLimits() {
		calculateZoomLimits();
		calculatePanLimits(getZoom());
	}

	private void calculateZoomLimits() {
		Board b = layout.getBoard();
		Map<BoardObject, Float> heightMap = layout.getLayoutStatistics()
				.getHeightMap();
		float boardHeight = heightMap.get(b);

		// zoom limits
		float lowestScale = 0.2373046875f; // 0.75^5 TODO
		// allow maximum enlargement to have the smallest object being displayed
		// with half the screen size
		maxZoom = getWidth()
				/ (config.getUniformObjectWidth() * 2 * lowestScale);
		// allow maximum the whole tree times 1.2 fitting on the screen
		minZoom = Math.min(getHeight() / (boardHeight * 1.2f), 1.f);
	}

	private void calculatePanLimits(float zoom) {
		Board b = layout.getBoard();
		Map<BoardObject, Float> heightMap = layout.getLayoutStatistics()
				.getHeightMap();
		Map<BoardObject, Float> widthMap = layout.getLayoutStatistics()
				.getWidthMap();

		Vector2 origin = config.getTreeOrigin();

		float boardHeight = heightMap.get(b);
		float boardWidth = widthMap.get(b);

		// pan limits
		maxX = getWidth() - origin.x * zoom;
		minX = -(origin.x + boardWidth) * zoom;
		maxY = getHeight() + (boardHeight - origin.y) * zoom;
		minY = -origin.y * zoom;
		
		// prevent locking in
		if (posX >= maxX) {
			posX = maxX;
		} else if (posX < minX) {
			posX = minX;
		}
		if (posY >= maxY) {
			posY = maxY;
		} else if (posY < minY) {
			posY = minY;
		}
	}

	private void initializePosition() {
		// have the tree displayed horizontally centered and with its top at the
		// upper edge
		ActorLayoutStatistics stats = layout.getLayoutStatistics();
		Vector2 orig = config.getTreeOrigin();
		float zoom = getZoom();
		float treeMidX = orig.x + stats.getWidthMap().get(layout.getBoard())
				/ 2;
		float treeTop = orig.y;
		posX = -(treeMidX - getWidth() * zoom / 2);
		posY = -(treeTop - getHeight() * zoom);
	}

	/**
	 * GestureListener for the BoardActor
	 * 
	 */
	private class BoardActorGestureListener extends ActorGestureListener {

		@Override
		public void pan(InputEvent event, float x, float y, float deltaX,
				float deltaY) {
			Vector2 delta = new Vector2(deltaX, deltaY);
			if (posX + delta.x >= minX && posX + delta.x <= maxX) {
				posX += delta.x;
			}
			if (posY + delta.y >= minY && posY + delta.y <= maxY) {
				posY += delta.y;
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
	}

	public boolean zoomIn(float percent) {
		return zoomIn(percent, getWidth() / 2, getHeight() / 2);
	}

	/**
	 * 
	 * @param percent
	 *            the percentage of how much the actor's zoom is to be
	 *            increased
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

	public boolean zoomOut(float percent) {
		return zoomOut(percent, getWidth() / 2, getHeight() / 2);
	}

	/**
	 * 
	 * @param percent
	 *            the percentage of how much the actor's zoom is to be
	 *            decreased
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
		point = world.parentToLocalCoordinates(point, oldZoom);
		point = world.localToParentCoordinates(point, newZoom);
		float newPointX = point.x;
		float newPointY = point.y;

		float dx = -(newPointX - pointX);
		float dy = -(newPointY - pointY);

		posX += dx;
		posY += dy;
		calculatePanLimits(newZoom);
	}
	
	private boolean setZoom(float zoom, boolean calculatePanLimits) {
		if (zoom > minZoom && zoom <= maxZoom) {
			world.setScale(zoom);
			if (calculatePanLimits) {
				calculatePanLimits(zoom);
			}
			return true;
		}
		return false;
	}
	
	private float getZoom() {
		return world.getScaleX();
	}

	/**
	 * Visualizes the recoloring of an object on the board.
	 * 
	 * @param recoloredObject
	 *            the object that has been recolored
	 */
	@Override
	public void onObjectRecolored(ColoredBoardObject recoloredObject) {
		// TODO have actor updated
		layout.getActor(recoloredObject);
	}

	/**
	 * Visualizes the process of one alligator eating another and its children,
	 * or just an egg, on the board.
	 * 
	 * @param eater
	 *            the alligator which eats the other alligator
	 * @param eatenFamily
	 *            the family which is eaten by the other alligator
	 */
	@Override
	public void onEat(final ColoredAlligator eater,
			final InternalBoardObject eatenFamily, int eatenParentPosition) {
		ColoredAlligatorActor eaterActor = ((ColoredAlligatorActor) layout
				.getActor(eater));
		eaterActor.enterEatingState();
		final List<InternalBoardObject> eatenLst = FlattenTree
				.toList(eatenFamily);

		final float animDuration = 0.2f;

		BoardObjectActor actor;
		for (InternalBoardObject eaten : eatenLst) {
			actor = layout.getActor(eaten);
			// automatically pooled actions, sooo convenient...
			MoveToAction moveAction = Actions.moveTo(eaterActor.getX(), eaterActor.getY(), animDuration);
			actor.addAction(moveAction);
			ScaleToAction scaleAction = Actions.scaleTo(0, 0, animDuration);
			actor.addAction(scaleAction);
		}

		this.addAction(new TemporalAction() {
			protected void begin() {
				setDuration(animDuration);
			}

			@Override
			protected void update(float percent) {
				// do nothing
			}

			protected void end() {
				BoardObjectActor eatenActor;
				for (InternalBoardObject eaten : eatenLst) {
					eatenActor = layout.getActor(eaten);
					layout.removeActor(eatenActor);
					world.removeActor(eatenActor);
				}
				removeObjectAnimated(eater);
				applyDeltasAnimated(layout.getDeltasToFix());
			}
		});
	}

	/**
	 * Removes
	 * 
	 * @param object
	 */
	private void removeObjectAnimated(final InternalBoardObject object) {
		final float fadingtime = .3f;
		// TODO make sure, fading out even works on our custom actors
		this.addAction(Actions.fadeOut(fadingtime));
		this.addAction(new TemporalAction() {
			protected void begin() {
				setDuration(fadingtime);
			}

			@Override
			protected void update(float percent) {
				// do nothing
			}

			protected void end() {
				BoardObjectActor actor = layout.getActor(object);
				layout.removeActor(actor);
				world.removeActor(actor);
				applyDeltasAnimated(layout.getDeltasToFix());
			}
		});
	}

	/**
	 * Visualizes the disappearance of an aged alligator on the board.
	 * 
	 * @param alligator
	 *            the alligator which disappeared
	 */
	@Override
	public void onAgedAlligatorVanishes(AgedAlligator alligator,
			int positionInParent) {
		removeObjectAnimated(alligator);
	}

	/**
	 * Completely rebuilds the board as it is seen on the screen.
	 * 
	 * @param board
	 *            the board that is going to replace the board that was seen
	 *            previously
	 */
	@Override
	public final void onBoardRebuilt(Board board) {
		world.clearChildren();
		layout = ActorLayoutBuilder.build(board, config);
		for (BoardObjectActor actor : layout) {
			world.addActor(actor);
		}
		registerLayoutListeners();
	}

	/**
	 * Visualizes the process of replacing an egg within a family with the
	 * family the protecting alligator has eaten.
	 * 
	 * @param replacedEgg
	 *            the hatching egg
	 * @param bornFamily
	 *            the family that hatches from that egg
	 */
	@Override
	public void onReplace(Egg replacedEgg, InternalBoardObject bornFamily) {
		EggActor eggActor = (EggActor) layout.getActor(replacedEgg);
		eggActor.enterHatchingState();
		removeObjectAnimated(replacedEgg);
	}
	
	private void applyDeltasAnimated(List<ActorDelta> deltas) {
		List<ActorDelta> created = new ArrayList<ActorDelta>();
		for (ActorDelta delta : deltas) {
			if (delta.isCreated()) {
				created.add(delta);
				continue;
			}
			applyDeltaAnimated(delta);
		}
		applyCreationDeltas(created);
	}
	
	private void applyDeltaAnimated(ActorDelta delta) {
		final float moveToDuration = 0.3f;
		final float sizeToDuration = 0.3f;
		
		Actor actor = delta.getActor();
		if (delta.isxChanged()) {
			MoveToAction moveTo;
			if (delta.isyChanged()) {
				moveTo = Actions.moveTo(delta.getNewX(), delta.getNewY(), moveToDuration);
			} else {
				moveTo = Actions.moveTo(delta.getNewX(), actor.getY(), moveToDuration);
			}
			actor.addAction(moveTo);
		} else if (delta.isyChanged()) {
			MoveToAction moveTo = Actions.moveTo(actor.getX(), delta.getNewY(), moveToDuration);
			actor.addAction(moveTo);
		}
		
		if (delta.isWidthChanged()) {
			SizeToAction sizeTo;
			if (delta.isHeightChanged()) {
				sizeTo = Actions.sizeTo(delta.getNewWidth(), delta.getNewHeight(), sizeToDuration);
			} else {
				sizeTo = Actions.sizeTo(delta.getNewWidth(), actor.getHeight(), sizeToDuration);
			}
			actor.addAction(sizeTo);
		} else if (delta.isyChanged()) {
			SizeToAction sizeTo = Actions.sizeTo(actor.getWidth(), delta.getNewHeight(), sizeToDuration);
			actor.addAction(sizeTo);
		}
	}
	
	private void applyCreationDeltas(final List<ActorDelta> deltas) {
		float animDuration = 0.2f;
		
		BoardObjectActor actor;
		for (ActorDelta delta : deltas) {
			actor = delta.getActor();
			actor.setScale(0.f);
			layout.addActor(actor);
			world.addActor(actor);
			ScaleToAction scaleAction = Actions.scaleTo(1, 1, animDuration);
			actor.addAction(scaleAction);
		}
	}

	@Override
	protected void sizeChanged() {
		world.syncBounds();
		calculateLimits();
		initializePosition();

		this.removeListener(gestureListener);
		gestureListener = new BoardActorGestureListener();
		this.addListener(gestureListener);
	}

	private void registerLayoutListeners() {
		for (BoardObjectActor child : layout) {
			if (child.getBoardObject() instanceof ColoredBoardObject) {
				ColoredBoardObject o = (ColoredBoardObject) child
						.getBoardObject();
				// if (o.isRecolorable()) {
				child.addListener(new RecolorPopupListener(o));
				// }
			}
		}
	}

	private class RecolorPopupListener extends ClickListener {
		private ColoredBoardObject o;

		public RecolorPopupListener(ColoredBoardObject o) {
			this.o = o;
		}

		@Override
		public void clicked(InputEvent event, float x, float y) {
			System.out.println("Click: " + x + ", " + y);
			// TODO
		}
	}

	public void setColorBlindEnabled(boolean enabled) {
		if (enabled == colorBlind) {
			return;
		} else {
			colorBlind = enabled;
			for (Actor actor : world.getChildren()) {
				if (actor instanceof ColoredBoardObjectActor) {
					((ColoredBoardObjectActor) actor).setColorBlindEnabled(enabled);
				}
			}
		}
	}
	
	public boolean getColorBlindEnabled() {
		return colorBlind;
	}
	
	@Override
	public void onSettingChange(Setting setting) {
		if (setting.isColorblindEnabled() != colorBlind) {
			setColorBlindEnabled(setting.isColorblindEnabled());
		}
	}
	
	// stuff inherited from Group that is not necessary
	/**
	 * @deprecated
	 */
	public void addActor(Actor actor) {
	}
	/**
	 * @deprecated
	 */
	public void addActorAfter(Actor a, Actor b) {
	}
	/**
	 * @deprecated
	 */
	public void addActorAt(int index, Actor actor) {
	}
	/**
	 * @deprecated
	 */
	public void addActorBefore(Actor a, Actor b) {
	}
	/**
	 * @deprecated
	 */
	public void clearChildren() {
		
	}
	/**
	 * @deprecated
	 */
	public boolean removeActor(Actor a) {
		return false;
	}
	/**
	 * @deprecated
	 */
	public boolean swapActor(Actor a, Actor b) {
		return false;
	}
	/**
	 * @deprecated
	 */
	public boolean swapActor(int x, int y) {
		return false;
	}
}
