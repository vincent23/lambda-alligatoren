package de.croggle.ui.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.SettingChangeListener;
import de.croggle.game.ColorController;
import de.croggle.game.board.Board;
import de.croggle.game.event.BoardEventListener;
import de.croggle.game.event.BoardEventMessenger;

/**
 * An actor used for representing a whole board, i.e. an alligator
 * constellation.
 */
public class BoardActor extends Group implements SettingChangeListener {

	/*
	 * the layout to be displayed
	 */
	private ActorLayout layout;

	/*
	 * the configuartion applied on the layout
	 */
	private final ActorLayoutConfiguration config;
	/*
	 * listener for board events. Responsible for updating the layout and
	 * applying animations
	 */
	private final BoardActorBoardChangeAnimator boardEventListener;
	/*
	 * provides functionality to zoom and pan this actor. Comes with standard
	 * gesture listener implementation
	 */
	private final BoardActorZoomAndPan zoomAndPan;

	/*
	 * provides functionality to add and manage user input listeners for the
	 * BoardObjectActors in the ActorLayout representing the board
	 */
	private BoardActorLayoutEditing layoutEditing;

	/*
	 * dedicated actor to display the game world in. Makes it easy to transform
	 * coordinates with parentToLocal and localToParent
	 */
	private final WorldPane world;

	/*
	 * the x position of the game world's origin relative to this BoardActor's
	 * origin and in this' coordinates/length.
	 */
	private float posX;
	/*
	 * the y position of the game world's origin relative to this BoardActor's
	 * origin and in this' coordinates/length.
	 */
	private float posY;

	/*
	 * whether this actor displays the board in color blind mode or not.
	 * Initially set to the value of isColorBlindEnabled of the
	 * ActorLayoutConfiguration given in the constructor
	 */
	private boolean colorBlind;

	private boolean zoomAndPanEnabled = false;

	private boolean layoutEditingEnabled = false;

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
		colorBlind = config.isColorBlindEnabled();

		// initialize world pane
		world = new WorldPane(this);
		super.addActor(world);

		// initialize layout by calling onBoardRebuilt. Needs world to add
		// actors to.
		boardEventListener = new BoardActorBoardChangeAnimator(this);
		boardEventListener.onBoardRebuilt(b);

		// initialize zoom and pan. Needs layout to derive limits from.
		zoomAndPan = new BoardActorZoomAndPan(this);
		setZoomAndPanEnabled(true);

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

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (clipBegin()) {
			super.draw(batch, parentAlpha);
			clipEnd();
		}
	}

	private void initializePosition() {
		final float offsetLeft = 0;
		final float offsetTop = 30;

		// have the tree displayed horizontally centered and with its top at the
		// upper edge
		ActorLayoutStatistics stats = layout.getLayoutStatistics();
		Vector2 orig = config.getTreeOrigin();
		float zoom = getZoom();
		// TODO don't think, this is generally correct
		float treeMidX = orig.x + stats.getWidthMap().get(layout.getBoard())
				/ 2;
		float treeTop = orig.y;
		posX = -(treeMidX - getWidth() * zoom / 2) + offsetLeft;
		posY = getHeight() * zoom - treeTop - offsetTop;
	}

	float getZoom() {
		return world.getScaleX();
	}

	public boolean zoomIn(float percent, float pointX, float pointY) {
		if (zoomAndPan != null) {
			return zoomAndPan.zoomIn(percent, pointX, pointY);
		}
		return false;
	}

	public boolean zoomIn(float percent) {
		return zoomIn(percent, getWidth() / 2, getHeight() / 2);
	}

	public boolean zoomOut(float percent, float pointX, float pointY) {
		if (zoomAndPan != null) {
			return zoomAndPan.zoomOut(percent, pointX, pointY);
		}
		return false;
	}

	public boolean zoomOut(float percent) {
		return zoomOut(percent, getWidth() / 2, getHeight() / 2);
	}

	@Override
	protected void sizeChanged() {
		world.syncBounds();
		if (zoomAndPan != null) {
			zoomAndPan.validate();
		}
		initializePosition();
	}

	ActorLayout getLayout() {
		return layout;
	}

	void setLayout(ActorLayout layout) {
		this.layout = layout;
	}

	public Vector2 boardActorToWorldCoordinates(Vector2 coords, float scale) {
		return world.parentToLocalCoordinates(coords, scale);
	}

	public Vector2 worldToBoardActorCoordinates(Vector2 coords, float scale) {
		return world.localToParentCoordinates(coords, scale);
	}

	/**
	 * Unsafe zoom method. Used by {@link BoardActorZoomAndPan}. Use
	 * {@link BoardActorZoomAndPan} methods for better safety.
	 * 
	 * @param zoom
	 */
	void setZoom(float zoom) {
		world.setScale(zoom);
	}

	void clearWorld() {
		world.clearChildren();
	}

	void addToWorld(Actor actor) {
		world.addActor(actor);
	}

	boolean removeFromWorld(Actor actor) {
		return world.removeActor(actor);
	}

	void clearActor() {
		super.clearChildren();
		super.addActor(world);
	}

	void addToActor(Actor actor) {
		super.addActor(actor);
	}

	boolean removeFromActor(Actor actor) {
		return super.removeActor(actor);
	}

	void updateListeners() {
		if (layoutEditingEnabled) {
			layoutEditing.unregisterLayoutListeners();
			layoutEditing.registerLayoutListeners();
		}
	}

	ActorLayoutConfiguration getLayoutConfiguration() {
		return config;
	}

	float getWorldX() {
		return posX;
	}

	void setWorldX(float x) {
		posX = x;
	}

	float getWorldY() {
		return posY;
	}

	void setWorldY(float y) {
		posY = y;
	}

	/**
	 * 
	 * @return a {@link BoardEventListener} to provide a means for this
	 *         {@link BoardActor} to be updated if the represented {@link Board}
	 *         changes.
	 */
	public BoardEventListener getBoardEventListener() {
		return boardEventListener;
	}

	public void setColorBlindEnabled(boolean enabled) {
		if (enabled == colorBlind) {
			return;
		} else {
			config.setColorBlindEnabled(enabled);
			colorBlind = enabled;
			for (Actor actor : world.getChildren()) {
				if (actor instanceof ColoredBoardObjectActor) {
					((ColoredBoardObjectActor) actor)
							.setColorBlindEnabled(enabled);
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

	/**
	 * Removes all listeners added by the user but will maintain actor managed
	 * listeners like e.g. zoomAndPan
	 */
	@Override
	public void clearListeners() {
		super.clearListeners();
		if (zoomAndPanEnabled) {
			super.addListener(zoomAndPan);
		}
	}

	public boolean isZoomAndPanEnabled() {
		return zoomAndPanEnabled;
	}

	public void setZoomAndPanEnabled(boolean zoomAndPanEnabled) {
		if (zoomAndPanEnabled != this.zoomAndPanEnabled) {
			this.zoomAndPanEnabled = zoomAndPanEnabled;
			if (zoomAndPanEnabled) {
				// zoomAndPan = new BoardActorZoomAndPan(this);
				super.addListener(zoomAndPan);
			} else {
				super.removeListener(zoomAndPan);
			}
		}
	}

	public boolean isUserLayoutInteractionEnabled() {
		return layoutEditingEnabled;
	}

	public void enableLayoutEditing(BoardEventMessenger m) {
		if (layoutEditingEnabled == false) {
			// initialize user interaction listeners on layout
			layoutEditing = new BoardActorLayoutEditing(this, m);
			layoutEditing.registerLayoutListeners();
			layoutEditingEnabled = true;
		}
	}

	public void disableLayoutEditing() {
		layoutEditing.unregisterLayoutListeners();
		layoutEditing = null;
		layoutEditingEnabled = false;
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (touchable && getTouchable() == Touchable.disabled) {
			return null;
		}
		Vector2 point = new Vector2();
		Array<Actor> children = super.getChildren();
		Actor hit;
		// look first for hits NOT being world
		for (int i = children.size - 1; i >= 0; i--) {
			Actor child = children.get(i);
			if (!child.isVisible()) {
				continue;
			}
			child.parentToLocalCoordinates(point.set(x, y));
			hit = child.hit(point.x, point.y, touchable);
			if (hit != null) {
				if (hit == world) {
					continue;
				}
				return hit;
			}
		}

		// consider elements in world as hit targets
		world.parentToLocalCoordinates(point.set(x, y));
		hit = world.hit(point.x, point.y, touchable);
		if (hit != world) {
			return hit;
		}

		// if the actor was hit, but none of the children, return this
		if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
			return this;
		}

		return null;
	}

	// stuff inherited from Group that should not be used as originally intended
	// still performing their tasks as there are some back and forth
	// dependencies in actors
	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void addActor(Actor actor) {
		super.addActor(actor);
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void addActorAfter(Actor a, Actor b) {
		super.addActorAfter(a, b);
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void addActorAt(int index, Actor actor) {
		super.addActorAt(index, actor);
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void addActorBefore(Actor a, Actor b) {
		super.addActorBefore(a, b);
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void clearChildren() {

	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public boolean removeActor(Actor a) {
		return super.removeActor(a);
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public boolean swapActor(Actor a, Actor b) {
		return super.swapActor(a, b);
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public boolean swapActor(int x, int y) {
		return super.swapActor(x, y);
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public SnapshotArray<Actor> getChildren() {
		return super.getChildren();
	}
}
