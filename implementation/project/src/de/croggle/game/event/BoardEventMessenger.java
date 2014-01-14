package de.croggle.game.event;

import java.util.ArrayList;
import java.util.List;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;

/**
 * The location in which listeners are able to register and unregister
 * themselves so they would recieve further notifications, e.g. when an object
 * has been recolored. Objects of this class can easily be passed to methods, so
 * that these can trigger events.
 */
public class BoardEventMessenger {

	private List<BoardEventListener> listeners;

	public BoardEventMessenger() {
		listeners = new ArrayList<BoardEventListener>();
	}

	/**
	 * Registers a new listener to listen for board events sent via this
	 * messenger. The listener will receive all future events, until it is
	 * unregistered.
	 * 
	 * @param listener
	 *            the listener to register
	 */
	public void register(BoardEventListener listener) {
		listeners.add(listener);
	}

	/**
	 * Unregisters a listener so that it won't receive any events sent via this
	 * messenger. If the listener isn't registered, this method has no effect.
	 * 
	 * @param listener
	 *            the listener to unregister
	 */
	public void unregister(BoardEventListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Sends an <code>onObjectRecolored</code> event to all registered
	 * listeners.
	 * 
	 * @param recoloredObject
	 *            the object which was recolored
	 */
	public void notifyObjectRecolored(ColoredBoardObject recoloredObject) {
		for (BoardEventListener listener : listeners) {
			listener.onObjectRecolored(recoloredObject);
		}
	}

	/**
	 * Sends an <code>onEat</code> event to all registered listeners.
	 * 
	 * @param eater
	 *            the colored alligator that has eaten
	 * @param eatenFamily
	 *            the family which was eaten
	 */
	public void notifyEat(ColoredAlligator eater,
			InternalBoardObject eatenFamily) {
		for (BoardEventListener listener : listeners) {
			listener.onEat(eater, eatenFamily);
			;
		}
	}

	/**
	 * Sends an <code>onAgedAlligatorVanishes</code> event to all registered
	 * listeners.
	 * 
	 * @param alligator
	 *            the alligator that has vanished
	 */
	public void notifyAgedAlligatorVanishes(AgedAlligator alligator) {
		for (BoardEventListener listener : listeners) {
			listener.onAgedAlligatorVanishes(alligator);
		}
	}

	/**
	 * Sends an <code>onBoardRebuilt</code> event to all registered listeners.
	 * 
	 * @param board
	 *            the board which was rebuilt
	 */
	public void notifyBoardRebuilt(Board board) {
		for (BoardEventListener listener : listeners) {
			listener.onBoardRebuilt(board);
			;
		}
	}

	/**
	 * Sends an <code>onReplace</code> event to all registered listeners.
	 * 
	 * @param replacedEgg
	 *            the egg that was replaced
	 * @param bornFamily
	 *            the family that hatched out of the egg
	 */
	public void notifyReplace(Egg replacedEgg, InternalBoardObject bornFamily) {
		for (BoardEventListener listener : listeners) {
			listener.onReplace(replacedEgg, bornFamily);
		}
	}
}
