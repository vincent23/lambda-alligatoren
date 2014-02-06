package de.croggle.ui.renderer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.ui.StyleHelper;

/**
 * The bar to drag alligators and eggs from onto the screen.
 **/
public class ObjectBar extends Table {

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
	ObjectBar(BoardActor b) {
		this.colorBlind = b.getLayoutConfiguration().isColorBlindEnabled();

		this.dragging = new BoardObjectActorDragging(b);

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
				return new ColoredAlligatorActor(new ColoredAlligator(true,
						true, Color.uncolored(), true), colorBlind);
			}
		} else {
			throw new IllegalStateException();
		}
	}

	class ObjectBarSource extends Source {

		public ObjectBarSource(Actor actor, boolean reenableZoom) {
			super(actor);
		}

		@Override
		public Payload dragStart(InputEvent event, float x, float y, int pointer) {
			Payload payload = new Payload();
			payload.setObject(getPlacedActor((BoardObjectActor) this.getActor()));

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
			// TODO buffer getObject if stopped on illegal target
		}

	}

	private class RemoveObjectTarget extends Target {

		public RemoveObjectTarget(BoardObjectActor actor) {
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
