package de.croggle.ui.renderer;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;
import de.croggle.game.event.BoardEventMessenger;

/**
 * Class containing different input listeners adding functionality to the
 * {@link ActorLayout}'s actors on user input. E.g. a listener that opens a
 * color picker popup on clicking a recolorable {@link BoardObjectActor}.
 * 
 */
class BoardActorLayoutEditing {

	private final BoardActor b;
	private final BoardEventMessenger messenger;
	private final DragAndDrop dnd;
	private final ObjectBar obar;
	private final BoardObjectActorDragging dragging;
	private final List<Target> temporaryTargets;
	private Source temporarySource;

	/*
	 * TODO fix libgdx' DragAndDrop so it uses the highest target instead of the
	 * first
	 */
	private BoardActorTarget boardActorTarget;

	private boolean permanentRegistered = false;

	private final float fadeDuration = 0.4f;

	public BoardActorLayoutEditing(BoardActor b, BoardEventMessenger messenger,
			boolean objectBar) {
		this.b = b;
		this.dragging = new BoardObjectActorDragging(this);
		this.messenger = messenger;
		this.temporaryTargets = new ArrayList<Target>();

		this.dnd = new DragAndDrop();

		if (objectBar) {
			this.obar = new ObjectBar(this);
		} else {
			this.obar = null;
		}

		addPermanentSourcesAndTargets();
	}

	BoardActor getBoardActor() {
		return b;
	}

	private void addPermanentSourcesAndTargets() {
		if (!permanentRegistered) {
			permanentRegistered = true;
		} else {
			throw new IllegalStateException(
					"Must not register permanent sources and targets more than once");
		}

		boardActorTarget = new BoardActorTarget(b);
		dnd.addTarget(boardActorTarget);
		if (obar != null) {
			dnd.addTarget(obar.new RemoveObjectTarget());
			dnd.addSource(obar.new BarAgedAlligatorSource());
			dnd.addSource(obar.new BarColoredAlligatorSource());
			dnd.addSource(obar.new BarEggSource());
		}
	}

	private void addTemporaryTargets(BoardObjectActor dragged) {
		for (BoardObjectActor actor : b.getLayout()) {
			if (areSiblings(actor.getBoardObject(), dragged.getBoardObject())) {
				SiblingTarget target = new SiblingTarget(actor);
				temporaryTargets.add(target);
				dnd.addTarget(target);
			} else if (actor.getBoardObject() instanceof Parent) {
				ParentTarget target = new ParentTarget(actor);
				temporaryTargets.add(target);
				dnd.addTarget(target);
			}
		}
	}

	private void removeTemporaryTargets() {
		for (Target t : temporaryTargets) {
			dnd.removeTarget(t);
		}
		temporaryTargets.clear();
	}

	ObjectBar getObjectBar() {
		return obar;
	}

	void registerLayoutListeners() {
		for (BoardObjectActor child : b.getLayout()) {
			if (child.getBoardObject() instanceof ColoredBoardObject) {
				ColoredBoardObject o = (ColoredBoardObject) child
						.getBoardObject();
				if (o.isRecolorable()) {
					child.addListener(new RecolorPopupListener(o));
				}
			}

			if (child.getBoardObject().isMovable()) {
				child.addListener(new EnableDraggingListener());
			}
		}
	}

	void unregisterLayoutListeners() {
		// TODO make this more elegant
		for (BoardObjectActor actor : b.getLayout()) {
			actor.clearListeners();
		}
	}

	ActorLayout getLayout() {
		return b.getLayout();
	}

	BoardEventMessenger getMessenger() {
		return messenger;
	}

	private class RecolorPopupListener extends ClickListener {
		private final ColoredBoardObject o;

		public RecolorPopupListener(ColoredBoardObject o) {
			this.o = o;
		}

		@Override
		public void clicked(InputEvent event, float x, float y) {
			ColorSelectorPopup popup = new ColorSelectorPopup(
					BoardActorLayoutEditing.this, o);
			popup.setX((b.getWidth() - popup.getWidth()) / 2);
			popup.setY((b.getHeight() - popup.getHeight()) / 2);

			b.addListener(popup.new AutomaticCloseListener());

			b.addToActor(popup);
		}
	}

	private static boolean areSiblings(InternalBoardObject a,
			InternalBoardObject b) {
		return a.getParent() == b.getParent();
	}

	private class EnableDraggingListener extends ActorGestureListener {
		public EnableDraggingListener() {
			getGestureDetector().setLongPressSeconds(0.8f);
		}

		@Override
		public boolean longPress(Actor actor, float x, float y) {
			if (!(actor instanceof BoardObjectActor)) {
				throw new IllegalArgumentException(
						"Cannot drag actors other than BoardObjectActors");
			}

			Gdx.input.vibrate(100);

			dnd.addSource(new ExistingActorSource((BoardObjectActor) actor));
			final float zoomAmount = 8f;
			final float zoomDelay = .2f;
			b.zoomOut(zoomAmount);
			actor.addAction(Actions.delay(zoomDelay, new Action() {
				private boolean done = false;

				@Override
				public boolean act(float delta) {
					if (!done) {
						done = true;
						b.zoomIn(100 - 100 / (1 + zoomAmount / 100));
					}
					return true;
				}

			}));
			actor.addAction(Actions.alpha(0.5f, fadeDuration));

			/*
			 * fake some events to immediately start dragging
			 */
			{
				InputEvent ev = new InputEvent();
				ev.setListenerActor(actor);
				ev.setStage(actor.getStage());
				Vector2 point = new Vector2();
				actor.localToStageCoordinates(point);
				ev.setStageX(point.x);
				ev.setStageY(point.y);
				ev.setTarget(actor);
				ev.setButton(0);

				ev.setType(Type.touchDown);
				actor.notify(ev, true);

				ev.setType(Type.touchDragged);
				actor.notify(ev, true);
			}

			return false;
		}
	}

	public abstract static class LayoutEditingSource extends Source {
		private final BoardActorLayoutEditing layoutEditing;
		private boolean reenableZoom;

		public LayoutEditingSource(BoardObjectActor actor,
				BoardActorLayoutEditing layoutEditing) {
			super(actor);
			this.layoutEditing = layoutEditing;
		}

		@Override
		final public Payload dragStart(InputEvent event, float x, float y,
				int pointer) {
			reenableZoom = layoutEditing.b.isZoomAndPanEnabled();
			layoutEditing.b.setZoomAndPanEnabled(false);
			layoutEditing.addTemporaryTargets((BoardObjectActor) getActor());

			/*
			 * TODO same problem with BoardActorTarget as above
			 */
			layoutEditing.dnd.removeTarget(layoutEditing.boardActorTarget);
			layoutEditing.dnd.addTarget(layoutEditing.boardActorTarget);

			Payload p = onDragStart(event, x, y, pointer);

			layoutEditing.dnd.setDragActorPosition(
					-p.getDragActor().getWidth() / 2, p.getDragActor()
							.getHeight() / 2);

			return p;
		}

		@Override
		public final void dragStop(InputEvent event, float x, float y,
				int pointer, Target target) {
			if (reenableZoom) {
				layoutEditing.b.setZoomAndPanEnabled(true);
			}
			layoutEditing.removeTemporaryTargets();
			onDragStop(event, x, y, pointer, target);
		}

		public void onDragStop(InputEvent event, float x, float y, int pointer,
				Target target) {

		}

		abstract Payload onDragStart(InputEvent event, float x, float y,
				int pointer);
	}

	private class ExistingActorSource extends LayoutEditingSource {

		public ExistingActorSource(BoardObjectActor actor) {
			super(actor, BoardActorLayoutEditing.this);
			if (temporarySource != null) {
				throw new IllegalStateException(
						"There can only be one temporary source at a time");
			}
			temporarySource = this;
		}

		@Override
		public Payload onDragStart(InputEvent event, float x, float y,
				int pointer) {
			Payload payload = new Payload();
			payload.setObject(this.getActor());

			payload.setDragActor(dragging
					.getDragActor((BoardObjectActor) getActor()));

			payload.setValidDragActor(dragging
					.getValidDragActor((BoardObjectActor) getActor()));

			payload.setInvalidDragActor(dragging
					.getInvalidDragActor((BoardObjectActor) getActor()));

			return payload;
		}

		@Override
		public void onDragStop(InputEvent event, float x, float y, int pointer,
				Target target) {

			getActor().addAction(Actions.fadeIn(fadeDuration));
			dnd.removeSource(temporarySource); // == this
			temporarySource = null;
		}
	}

	private class ParentTarget extends Target {

		public ParentTarget(BoardObjectActor actor) {
			super(actor);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean drag(Source source, Payload payload, float x, float y,
				int pointer) {
			// TODO Auto-generated method stub
			System.out.println("I am not a board, though dragged over");
			return true;
		}

		@Override
		public void drop(Source source, Payload payload, float x, float y,
				int pointer) {
			// TODO Auto-generated method stub

		}
	}

	private class SiblingTarget extends Target {

		public SiblingTarget(BoardObjectActor actor) {
			super(actor);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean drag(Source source, Payload payload, float x, float y,
				int pointer) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void drop(Source source, Payload payload, float x, float y,
				int pointer) {
			// TODO Auto-generated method stub

		}
	}

	private class BoardActorTarget extends Target {

		public BoardActorTarget(BoardActor actor) {
			super(actor);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean drag(Source source, Payload payload, float x, float y,
				int pointer) {
			b.setBackgroundColor(new Color(0.f, 1.f, 0.f, .5f));
			return true;
		}

		@Override
		public void reset(Source source, Payload payload) {
			b.setBackgroundColor(new Color(1.f, 1.f, 1.f, 0.f));
		}

		@Override
		public void drop(Source source, Payload payload, float x, float y,
				int pointer) {
			// TODO Auto-generated method stub

		}
	}
}
