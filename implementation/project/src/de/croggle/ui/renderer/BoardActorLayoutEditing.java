package de.croggle.ui.renderer;

import com.badlogic.gdx.Gdx;
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

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;
import de.croggle.game.board.operations.BoardObjectVisitor;
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

	private final ColoredAlligatorActor coloredPayload;
	private final EggActor eggPayload;
	private final AgedAlligatorActor agedPayload;

	private final ColoredAlligatorActor coloredValidPayload;
	private final EggActor eggValidPayload;
	private final AgedAlligatorActor agedValidPayload;

	private final ColoredAlligatorActor coloredInvalidPayload;
	private final EggActor eggInvalidPayload;
	private final AgedAlligatorActor agedInvalidPayload;

	private final float fadeDuration = 0.4f;

	private float autoPanBorderWidth;
	private float autoPanBorderHeight;

	public BoardActorLayoutEditing(BoardActor b, BoardEventMessenger messenger,
			ObjectBar obar) {
		this.b = b;
		this.messenger = messenger;
		this.dnd = new DragAndDrop();
		this.obar = obar;

		boolean colorBlind = b.getLayout().getLayoutConfiguration()
				.isColorBlindEnabled();
		{
			coloredPayload = new ColoredAlligatorActor(new ColoredAlligator(
					false, false, Color.uncolored(), false), colorBlind);
			coloredPayload.addAction(new AutoPanAction());
			eggPayload = new EggActor(new Egg(false, false, Color.uncolored(),
					false), colorBlind);
			eggPayload.addAction(new AutoPanAction());
			agedPayload = new AgedAlligatorActor(
					new AgedAlligator(false, false));
			agedPayload.addAction(new AutoPanAction());
		}

		{
			coloredValidPayload = new ColoredAlligatorActor(
					new ColoredAlligator(false, false, Color.uncolored(), false),
					colorBlind);
			coloredValidPayload.setColor(0.f, 1.f, 0.f, 1.f);
			coloredValidPayload.addAction(new AutoPanAction());
			eggValidPayload = new EggActor(new Egg(false, false,
					Color.uncolored(), false), colorBlind);
			eggValidPayload.setColor(0.f, 1.f, 0.f, 1.f);
			eggValidPayload.addAction(new AutoPanAction());
			agedValidPayload = new AgedAlligatorActor(new AgedAlligator(false,
					false));
			agedValidPayload.setColor(0.f, 1.f, 0.f, 1.f);
			agedValidPayload.addAction(new AutoPanAction());
		}

		{
			coloredInvalidPayload = new ColoredAlligatorActor(
					new ColoredAlligator(false, false, Color.uncolored(), false),
					colorBlind);
			coloredInvalidPayload.setColor(1.f, 0.f, 0.f, 1.f);
			coloredInvalidPayload.addAction(new AutoPanAction());
			eggInvalidPayload = new EggActor(new Egg(false, false,
					Color.uncolored(), false), colorBlind);
			eggInvalidPayload.setColor(1.f, 0.f, 0.f, 1.f);
			eggInvalidPayload.addAction(new AutoPanAction());
			agedInvalidPayload = new AgedAlligatorActor(new AgedAlligator(
					false, false));
			agedInvalidPayload.setColor(1.f, 0.f, 0.f, 1.f);
			agedInvalidPayload.addAction(new AutoPanAction());
		}
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

			autoPanBorderWidth = Math.min(b.getWidth() / 2, 150);
			autoPanBorderHeight = Math.min(b.getHeight() / 2, 100);

			boolean zoomEnabled = b.isZoomAndPanEnabled();
			b.setZoomAndPanEnabled(false);

			dnd.addSource(new ActorSource(actor, zoomEnabled));
			dnd.setDragActorPosition(-actor.getWidth() / 2 * b.getZoom(),
					actor.getHeight() / 2 * b.getZoom());
			for (BoardObjectActor layoutActor : b.getLayout()) {
				if (layoutActor.getBoardObject() instanceof Parent) {
					dnd.addTarget(new ActorTarget(layoutActor));
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

	private BoardObjectActor getPayloadFor(final BoardObjectActor a) {
		// WOOOOOAAAAAHHHH sooooo hacky
		final BoardObjectActor result[] = new BoardObjectActor[1];
		BoardObjectVisitor visitor = new BoardObjectVisitor() {
			@Override
			public void visitEgg(Egg egg) {
				((Egg) eggPayload.getBoardObject()).setColor(egg.getColor());
				eggPayload.validate();
				eggPayload.setSize(a.getWidth() * b.getZoom(), a.getHeight()
						* b.getZoom());
				result[0] = eggPayload;
			}

			@Override
			public void visitColoredAlligator(ColoredAlligator alligator) {
				((ColoredAlligator) coloredPayload.getBoardObject())
						.setColor(alligator.getColor());
				coloredPayload.validate();
				coloredPayload.setSize(a.getWidth() * b.getZoom(),
						a.getHeight() * b.getZoom());
				result[0] = coloredPayload;
			}

			@Override
			public void visitBoard(Board board) {
				// Just ignore
			}

			@Override
			public void visitAgedAlligator(AgedAlligator alligator) {
				result[0] = agedPayload;
			}
		};
		a.getBoardObject().accept(visitor);
		return result[0];
	}

	private BoardObjectActor getValidPayloadFor(final BoardObjectActor a) {
		final BoardObjectActor result[] = new BoardObjectActor[1];
		BoardObjectVisitor visitor = new BoardObjectVisitor() {
			@Override
			public void visitEgg(Egg egg) {
				((Egg) eggValidPayload.getBoardObject()).setColor(egg
						.getColor());
				eggValidPayload.validate();
				eggValidPayload.setSize(a.getWidth() * b.getZoom(),
						a.getHeight() * b.getZoom());
				result[0] = eggValidPayload;
			}

			@Override
			public void visitColoredAlligator(ColoredAlligator alligator) {
				((ColoredAlligator) coloredValidPayload.getBoardObject())
						.setColor(alligator.getColor());
				coloredValidPayload.validate();
				coloredValidPayload.setSize(a.getWidth() * b.getZoom(),
						a.getHeight() * b.getZoom());
				result[0] = coloredValidPayload;
			}

			@Override
			public void visitBoard(Board board) {
				// Just ignore
			}

			@Override
			public void visitAgedAlligator(AgedAlligator alligator) {
				result[0] = agedValidPayload;
			}
		};
		a.getBoardObject().accept(visitor);
		return result[0];
	}

	private BoardObjectActor getInvalidPayloadFor(final BoardObjectActor a) {
		final BoardObjectActor result[] = new BoardObjectActor[1];
		BoardObjectVisitor visitor = new BoardObjectVisitor() {
			@Override
			public void visitEgg(Egg egg) {
				((Egg) eggInvalidPayload.getBoardObject()).setColor(egg
						.getColor());
				eggInvalidPayload.validate();
				eggInvalidPayload.setSize(a.getWidth() * b.getZoom(),
						a.getHeight() * b.getZoom());
				result[0] = eggInvalidPayload;
			}

			@Override
			public void visitColoredAlligator(ColoredAlligator alligator) {
				((ColoredAlligator) coloredInvalidPayload.getBoardObject())
						.setColor(alligator.getColor());
				coloredInvalidPayload.validate();
				coloredInvalidPayload.setSize(a.getWidth() * b.getZoom(),
						a.getHeight() * b.getZoom());
				result[0] = coloredInvalidPayload;
			}

			@Override
			public void visitBoard(Board board) {
				// Just ignore
			}

			@Override
			public void visitAgedAlligator(AgedAlligator alligator) {
				result[0] = agedInvalidPayload;
			}
		};
		a.getBoardObject().accept(visitor);
		return result[0];
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

			payload.setDragActor(getPayloadFor((BoardObjectActor) getActor()));

			payload.setValidDragActor(getValidPayloadFor((BoardObjectActor) getActor()));

			payload.setInvalidDragActor(getInvalidPayloadFor((BoardObjectActor) getActor()));

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

	private class ActorTarget extends Target {

		public ActorTarget(BoardObjectActor actor) {
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

	private class AutoPanAction extends Action {

		// only act once in %divider% milliseconds
		private final float divider = 0.01f;

		private final float xDistance = 2;
		private final float yDistance = 2;

		private float timePassed;

		@Override
		public boolean act(float delta) {
			if (timePassed >= divider) {
				timePassed = 0;
				Actor a = getActor();
				float x = a.getX();
				float y = a.getY();
				float maxx = x + a.getWidth();
				float maxy = y + a.getHeight();
				float w = b.getWidth();
				float h = b.getHeight();
				Vector2 pMin = new Vector2(x, y);
				Vector2 pMax = new Vector2(maxx, maxy);
				pMin = b.stageToLocalCoordinates(pMin);
				pMax = b.stageToLocalCoordinates(pMax);
				if (pMin.x <= autoPanBorderWidth) {
					if (pMax.x < w - autoPanBorderWidth) {
						b.panActorCoords(xDistance, 0);
					}
				} else if (pMax.x >= w - autoPanBorderWidth) {
					b.panActorCoords(-xDistance, 0);
				}

				if (pMin.y <= autoPanBorderHeight) {
					if (pMax.y < h - autoPanBorderHeight) {
						b.panActorCoords(0, yDistance);
					}
				} else if (pMax.y >= h - autoPanBorderHeight) {
					b.panActorCoords(0, -yDistance);
				}
			}
			timePassed += delta;

			// never end
			return false;
		}
	}
}
