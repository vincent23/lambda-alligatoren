package de.croggle.game.event;

import de.croggle.game.board.InternalBoardObject;

public interface BoardEditedListener {
	public void onObjectPlaced(InternalBoardObject placed);

	public void onObjectRemoved(InternalBoardObject removed);

	public void onObjectMoved(InternalBoardObject moved);
}
