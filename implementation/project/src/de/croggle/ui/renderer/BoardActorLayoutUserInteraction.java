package de.croggle.ui.renderer;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.game.board.ColoredBoardObject;

/**
 * Class containing different input listeners adding functionality to the {@link ActorLayout}'s actors on user input.
 * E.g. a listener that opens a color picker popup on clicking a recolorable {@link BoardObjectActor}.
 *
 */
class BoardActorLayoutUserInteraction {
	
	private final BoardActor b;
	
	public BoardActorLayoutUserInteraction(BoardActor b) {
		this.b = b;
	}
	
	void registerLayoutListeners() {
		for (BoardObjectActor child : b.getLayout()) {
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
}
