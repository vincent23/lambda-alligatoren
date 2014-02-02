package de.croggle.ui.renderer;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.game.board.ColoredBoardObject;
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

	public BoardActorLayoutEditing(BoardActor b, BoardEventMessenger messenger) {
		this.b = b;
		this.messenger = messenger;
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
}
