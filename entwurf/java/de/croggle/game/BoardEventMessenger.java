package de.croggle.game;

import java.util.List;

import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Alligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.Egg;
import de.croggle.game.event.BoardEventListener;

/**
 * @has 1 - * de.croggle.game.event.BoardEventListener
 */
public class BoardEventMessenger {

	private List<BoardEventListener> listeners;

	public void register() {
	}

	public void unregister() {
	}

	/**
	 *
	 */
	@Override
	public void objectRecolored(InternalBoardObject recoloredObject) {
	}

	/**
	 *
	 */
	@Override
	public void eat(ColoredAlligator eater, InternalBoardObject eatenFamily) {
	}

	/**
	 *
	 */
	@Override
	public void alligatorVanishes(Alligator alligator) {
	}

	/**
	 *
	 */
	@Override
	public void boardRebuilt(Board board) {
	}

	/**
	 *
	 */
	@Override
	public void replace(Egg replacedEgg, InternalBoardObject bornFamily) {
	}
}
