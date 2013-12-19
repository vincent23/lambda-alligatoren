package de.croggle.game.visitor;

/**
 *
 */
public class FindEatingVisitor implements BoardObjectVisitor {
	private boolean found;

	/**
	 *
	 */
	private void found() {
		found = true;
	}
}
