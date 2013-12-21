package de.croggle.game.event;

import java.util.List;

import de.croggle.game.board.Alligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;

/**
 * @has 1 - * de.croggle.game.event.BoardEventListener
 */
public class BoardEventMessenger {

	private List<BoardEventListener> listeners;

	public void register(BoardEventListener listener) {
	}

	public void unregister(BoardEventListener listener) {
	}

	/**
	 *
	 */
	@Override
	public void notifyObjectRecolored(InternalBoardObject recoloredObject) {
	}

	/**
	 *
	 */
	@Override
	public void notifyEat(ColoredAlligator eater, InternalBoardObject eatenFamily) {
	}

	/**
	 *
	 */
	@Override
	public void notifyAgedAlligatorVanishes(Alligator alligator) {
	}

	/**
	 *
	 */
	@Override
	public void notifyBoardRebuilt(Board board) {
	}

	/**
	 *
	 */
	@Override
	public void notifyReplace(Egg replacedEgg, InternalBoardObject bornFamily) {
	}
}
