package de.croggle.ui.renderer;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;
import de.croggle.ui.StyleHelper;

/**
 * The bar to drag alligators and eggs from onto the screen.
 **/
public class ObjectBar extends Table {
	private final static com.badlogic.gdx.graphics.Color trashColor = new com.badlogic.gdx.graphics.Color(
			1.f, 0.f, 0.f, .5f);

	private final BoardActorLayoutEditing editing;

	private final boolean colorBlind;

	private final AgedAlligatorActor agedAlligator;
	private final ColoredAlligatorActor coloredAlligator;
	private final EggActor eggActor;

	private final BoardObjectActorDragging dragging;

	private AgedAlligatorActor bufferedAged;
	private ColoredAlligatorActor bufferedColored;
	private EggActor bufferedEgg;

	/**
	 * Creates an object bar with the ui elements the user can drag to the
	 * screen per default. Only BoardActorLayoutEditing should be allowed to
	 * instantiate ObjectBars
	 * 
	 * @param b
	 */
	ObjectBar(BoardActorLayoutEditing editing) {
		this.editing = editing;
		this.colorBlind = editing.getBoardActor().getLayoutConfiguration()
				.isColorBlindEnabled();

		this.dragging = new BoardObjectActorDragging(editing);

		agedAlligator = new AgedAlligatorActor(new AgedAlligator(false, false));
		coloredAlligator = new ColoredAlligatorActor(new ColoredAlligator(
				false, false, Color.uncolored(), false), colorBlind);
		eggActor = new EggActor(
				new Egg(false, false, Color.uncolored(), false), colorBlind);

		setBackground(StyleHelper.getInstance().getDrawable("widgets/button"));

		// TODO size guessed and hardcoded, add way to get preferred aspect
		// ratio
		add(coloredAlligator).size(200, 110).row();
		add(agedAlligator).size(200, 95).row();
		add(eggActor).size(140, 100).row();
	}

	private BoardObjectActor getPlacedActor(BoardObjectActor prototype) {
		if (prototype == agedAlligator) {
			if (bufferedAged != null) {
				AgedAlligatorActor aged = bufferedAged;
				bufferedAged = null;
				return aged;
			} else {
				return new AgedAlligatorActor(new AgedAlligator(true, true));
			}
		} else if (prototype == coloredAlligator) {
			if (bufferedColored != null) {
				ColoredAlligatorActor colored = bufferedColored;
				bufferedColored = null;
				return colored;
			} else {
				return new ColoredAlligatorActor(new ColoredAlligator(true,
						true, Color.uncolored(), true), colorBlind);
			}
		} else if (prototype == eggActor) {
			if (bufferedEgg != null) {
				EggActor egg = bufferedEgg;
				bufferedEgg = null;
				return egg;
			} else {
				return new EggActor(
						new Egg(true, true, Color.uncolored(), true),
						colorBlind);
			}
		} else {
			throw new IllegalStateException();
		}
	}

	private class ObjectBarSource extends
			BoardActorLayoutEditing.LayoutEditingSource {

		public ObjectBarSource(BoardObjectActor actor) {
			super(actor, editing);
		}

		@Override
		public Payload onDragStart(InputEvent event, float x, float y,
				int pointer) {
			Payload payload = new Payload();
			payload.setObject(getPlacedActor((BoardObjectActor) this.getActor()));

			payload.setDragActor(dragging
					.getDragActor((BoardObjectActor) getActor()));
			payload.getDragActor().setScale(1.f);

			payload.setValidDragActor(dragging
					.getValidDragActor((BoardObjectActor) getActor()));
			payload.getValidDragActor().setScale(1.f);

			payload.setInvalidDragActor(dragging
					.getInvalidDragActor((BoardObjectActor) getActor()));
			payload.getInvalidDragActor().setScale(1.f);

			return payload;
		}

		@Override
		public void onDragStop(InputEvent event, float x, float y, int pointer,
				Target target) {
			// TODO buffer getObject if stopped on illegal target
		}

	}

	class BarColoredAlligatorSource extends ObjectBarSource {
		public BarColoredAlligatorSource() {
			super(coloredAlligator);
		}
	}

	class BarAgedAlligatorSource extends ObjectBarSource {
		public BarAgedAlligatorSource() {
			super(agedAlligator);
		}
	}

	class BarEggSource extends ObjectBarSource {
		public BarEggSource() {
			super(eggActor);
		}
	}

	class RemoveObjectTarget extends Target {

		private com.badlogic.gdx.graphics.Color tempColor;

		public RemoveObjectTarget() {
			super(ObjectBar.this);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean drag(Source source, Payload payload, float x, float y,
				int pointer) {
			if (tempColor == null) {
				tempColor = payload.getValidDragActor().getColor().cpy();
				payload.getValidDragActor().setColor(trashColor);
			}

			return true;
		}

		@Override
		public void reset(Source source, Payload payload) {
			if (tempColor != null) {
				payload.getValidDragActor().setColor(tempColor);
				tempColor = null;
			}
		}

		@Override
		public void drop(Source source, Payload payload, float x, float y,
				int pointer) {
			BoardObjectActor payloadActor = (BoardObjectActor) payload
					.getObject();
			InternalBoardObject payloadObject = payloadActor.getBoardObject();
			if (payloadObject.getParent() != null) {
				Parent objParent = payloadObject.getParent();
				int objectPos = objParent.getChildPosition(payloadObject);
				objParent.removeChild(payloadObject);

				if (payloadObject instanceof Parent) {
					// sift up children
					Parent objAsParent = (Parent) payloadObject;
					// careful not to reverse the children order
					for (int i = 0; i < objAsParent.getChildCount(); i++) {
						InternalBoardObject child = objAsParent
								.getChildAtPosition(i);
						objParent.insertChild(child, objectPos + i);
					}
					objAsParent.clearChildren();
				}
			}
			editing.getMessenger().notifyObjectRemoved(payloadObject);
			editing.getBoardActor().boardSizeChanged();
		}
	}
}
