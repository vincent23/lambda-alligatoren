package de.croggle.game.visitor;

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
