package de.croggle.game.visitor;

import de.croggle.game.event.BoardEventMessenger;

/**
 *
 * @navassoc 1 - 1 de.croggle.game.event.BoardEventMessenger
 */
public class FindEatingVisitor implements BoardObjectVisitor {
	private boolean found;
	private BoardEventMessenger boardMessenger;

	public FindEatingVisitor(BoardEventMessenger boardMessenger){
	}

	/**
	 *
	 */
	private void found() {
		found = true;
	}
}
