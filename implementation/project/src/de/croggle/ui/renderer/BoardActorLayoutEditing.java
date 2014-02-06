package de.croggle.ui.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
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

	private final float fadeDuration = 0.4f;

	public BoardActorLayoutEditing(BoardActor b, BoardEventMessenger messenger,
			boolean objectBar) {
		this.b = b;
		this.dragging = new BoardObjectActorDragging(b);
		this.messenger = messenger;
		this.dnd = new DragAndDrop();
		if (objectBar) {
			this.obar = new ObjectBar(b);
		} else {
			this.obar = null;
		}
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

			boolean zoomEnabled = b.isZoomAndPanEnabled();
			b.setZoomAndPanEnabled(false);

			dnd.addSource(new ActorSource(actor, zoomEnabled));
			dnd.setDragActorPosition(-actor.getWidth() / 2 * b.getZoom(),
					actor.getHeight() / 2 * b.getZoom());
			for (BoardObjectActor layoutActor : b.getLayout()) {
				if (layoutActor.getBoardObject() instanceof Parent) {
					dnd.addTarget(new ParentTarget(layoutActor));
				}
			}

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

	private class ActorSource extends Source {

		private final boolean reenableZoom;

		public ActorSource(Actor actor, boolean reenableZoom) {
			super(actor);
			this.reenableZoom = reenableZoom;
		}

		@Override
		public Payload dragStart(InputEvent event, float x, float y, int pointer) {
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
		public void dragStop(InputEvent event, float x, float y, int pointer,
				Target target) {
			if (reenableZoom) {
				b.setZoomAndPanEnabled(true);
			}
			getActor().addAction(Actions.fadeIn(fadeDuration));
			dnd.removeSource(this);
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

		public BoardActorTarget(BoardObjectActor actor) {
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
}
