package de.croggle.ui.renderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.transition.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import de.croggle.game.ColorController;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.operations.CreateHeightMap;
import de.croggle.game.board.operations.CreateWidthMap;
import de.croggle.game.board.operations.FlattenTree;
import de.croggle.game.event.BoardEventListener;

/**
 * An actor used for representing a whole board, i.e. an alligator
 * constellation.
 */
public class BoardActor extends Actor implements BoardEventListener {

	private ActorLayout layout;
	private OrthographicCamera camera;

	private final Vector2 CAMERA_SIZE = new Vector2(1024, 600);
	private Vector2 cameraPosMax;
	private Vector2 cameraPosMin;
	private float maxZoom;
	private float minZoom;
	
	private BoardActorGestureListener gestureListener;

	public BoardActor(Board b, ActorLayoutConfiguration config) {
		layout = ActorLayoutBuilder.build(b, config);
		camera = new OrthographicCamera(CAMERA_SIZE.x, CAMERA_SIZE.y);
		camera.position.set(CAMERA_SIZE.x / 2 - getX(), CAMERA_SIZE.y / 2
				- getY(), 0);
		camera.update();
		// TODO implement smart boundary calculation
		cameraPosMax = new Vector2(Float.POSITIVE_INFINITY,
				Float.POSITIVE_INFINITY);
		cameraPosMin = new Vector2(Float.NEGATIVE_INFINITY,
				Float.NEGATIVE_INFINITY);

		calculatePositionsAndLimits(b);
	}

	private void calculatePositionsAndLimits(Board b) {
		// TODO not efficient
		// TODO not dynamic

		ActorLayoutConfiguration config = layout.getLayoutConfiguration();
		Map<BoardObject, Float> heightMap = CreateHeightMap.create(b);
		Map<BoardObject, Float> widthMap = layout.getLayoutStatistics()
				.getWidthMap();

		float unscaledHeight = heightMap.get(b);
		// cool formula to unroll sum function associated with height
		// calculation
		float lowestScale = (float) Math.pow(config.getVerticalScaleFactor(),
				unscaledHeight);
		float actualHeight = lowestScale
				* (unscaledHeight + 1)
				* (config.getUniformObjectHeight() + config
						.getVerticalPadding());
		// allow maximum enlargement to have the smallest object being displayed
		// with half the screen size
		maxZoom = config.getUniformObjectWidth() * 2 * lowestScale
				/ CAMERA_SIZE.x;
		// allow maximum the whole tree times 1.2 fitting on the screen
		minZoom = Math.max(actualHeight * 1.2f / CAMERA_SIZE.y, 1.f);

		Vector2 orig = config.getTreeOrigin();
		cameraPosMax.x = orig.x + widthMap.get(b);
		cameraPosMin.x = orig.x;
		cameraPosMax.y = orig.y + config.getUniformObjectHeight();
		cameraPosMin.y = orig.y - layout.getLayoutStatistics().getHeightMap().get(layout.getBoard());
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
	 * GestureListener for the BoardActor
	 * 
	 */
	private class BoardActorGestureListener extends ActorGestureListener {
		@Override
		public void pan(InputEvent event, float x, float y, float deltaX,
				float deltaY) {
			float dx = screenToWorldLen(deltaX);
			float dy = screenToWorldLen(deltaY);
			if (camera.position.x - dx >= cameraPosMin.x
					&& camera.position.x - dx <= cameraPosMax.x) {
				camera.position.x -= dx;
			}
			if (camera.position.y - dy >= cameraPosMin.y
					&& camera.position.y - dy <= cameraPosMax.y) {
				camera.position.y -= dy;
			}
			camera.update();
		}

		@Override
		public void pinch(InputEvent event, Vector2 initialPointer1,
				Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
			float initialzoom = camera.zoom;
			float initialDistance = initialPointer1.dst(initialPointer2);
			float newdistance = pointer1.dst(pointer2);
			// totally random transfer function
			float newzoom = (float) (camera.zoom * Math.pow(1.0002,
					(int) (initialDistance - newdistance))); // maybe casting to
																// int in
																// exponent is
																// faster
			if (newzoom < maxZoom || newzoom > minZoom) {
				return;
			}

			// the center of the zooming operation
			Vector2 screenpoint = initialPointer1.add(initialPointer2.sub(
					initialPointer1).div(2));
			// translate center into game world coordinates
			Vector2 zoompoint = screenToWorldCoords(screenpoint);
			float dx = camera.position.x - zoompoint.x;
			float dy = camera.position.y - zoompoint.y;
			// offsets in real world pixels with the different zoom levels
			float initialDx = dx / initialzoom;
			float initialDy = dy / initialzoom;
			float newDx = dx / newzoom;
			float newDy = dy / newzoom;

			float offsetX = (initialDx - newDx) * newzoom;
			float offsetY = (initialDy - newDy) * newzoom;

			if (camera.position.x + offsetX >= cameraPosMin.x
					&& camera.position.x + offsetX <= cameraPosMax.x) {
				camera.position.x += offsetX;
			}
			if (camera.position.y + offsetY >= cameraPosMin.y
					&& camera.position.y + offsetY <= cameraPosMax.y) {
				camera.position.y += offsetY;
			}
			camera.zoom = newzoom;
			camera.update();
		}
	}

	private float screenToWorldLen(float len) {
		// TODO code assumes aspect ratio of screen being same as aspect ratio
		// of camera
		return len / Gdx.graphics.getWidth() * CAMERA_SIZE.x * camera.zoom;
	}

	private Vector2 screenToWorldCoords(Vector2 screenCoords) {
		Vector2 result = new Vector2();
		Vector2 screenMid = new Vector2(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);
		Vector2 diffFromMid = screenCoords.cpy().sub(screenMid);
		// correct differences due to resolutions and zoom
		// TODO code assumes that aspect ratio is same between real screen and
		// our camera
		diffFromMid.scl(CAMERA_SIZE.x / Gdx.graphics.getWidth() * camera.zoom);
		result = new Vector2(camera.position.x, camera.position.y).add(
				diffFromMid.x, diffFromMid.y);

		return result;
	}

	/**
	 * Draws the actor.
	 * 
	 * @param batch
	 *            the sprite batch specifies where to draw into
	 * @param parentAlpha
	 *            the parent's alpha value
	 */
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.flush();
		Matrix4 original = batch.getProjectionMatrix();
		// set up clipping
		if(clipBegin()) {
			// set own camera to look through
			batch.setProjectionMatrix(camera.combined);
			for (BoardObjectActor child : layout) {
				child.draw(batch, parentAlpha);
			}
			batch.setProjectionMatrix(original);
			clipEnd();
		}
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

		for (InternalBoardObject eaten : eatenLst) {
			MoveToAction action = new MoveToAction();
			action.setPosition(eaterActor.getX(), eaterActor.getY());
			action.setDuration(animDuration);
			layout.getActor(eaten).addAction(action);
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
				for (InternalBoardObject eaten : eatenLst) {
					layout.onRemoveSingle(eaten);
				}
			}
		});
	}

	/**
	 * Removes
	 * 
	 * @param object
	 */
	protected void removeObjectAnimated(final InternalBoardObject object) {
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
				layout.onRemoveSingle(object);
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
	public void onBoardRebuilt(Board board) {
		layout = ActorLayoutBuilder.build(board,
				layout.getLayoutConfiguration());
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
		layout.onRemoveSingle(replacedEgg);
		layout.onAdd(bornFamily);
	}

	@Override
	protected void sizeChanged() {
		// we want to always have the camera believe it shows 1024 x 600 pixels
		// so we don't execute the next two lines of code
		// camera.viewportWidth = getWidth();
		// camera.viewportHeight = getHeight();

		final float actorX = screenToWorldLen(getX());
		final float actorY = screenToWorldLen(getY());

		final float treeMidX = layout.getLayoutConfiguration().getTreeOrigin().x
				+ layout.getLayoutStatistics().getWidthMap()
						.get(layout.getBoard()) / 2;
		final float treeMidY = layout.getLayoutConfiguration().getTreeOrigin().y
				- layout.getLayoutStatistics().getHeightMap()
						.get(layout.getBoard()) / 2;
		camera.position.set(treeMidX - actorX, treeMidY - actorY, 0);
		camera.update();
		
		this.removeListener(gestureListener);
		gestureListener = new BoardActorGestureListener();
		this.addListener(gestureListener);
	}
}
