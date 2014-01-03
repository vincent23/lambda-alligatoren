package de.croggle.game.event;

import de.croggle.game.board.Board;

/**
 * 
 * Interface for listeners specifically listening to the onBoardRebuilt event.
 * This event is produced when a simulator does something that requires the
 * complete renewal of the elements shown on the board, e.g. undoing the last
 * step or reseting to the initial board upon entering the simulation mode.
 */
public interface BoardRebuiltEventListener {

	/**
	 * Receive a board rebuilt event for further processing. E.g. the renderer
	 * can determine by accepting a board rebuilt event that the elements shown
	 * on the board have to be renewed.
	 * 
	 * @param board
	 *            the board that has to be rebuild
	 */
	public void onBoardRebuilt(Board board);
}
