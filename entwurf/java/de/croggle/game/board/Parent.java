package de.croggle.game.board;

import java.util.List;
import java.util.Iterator;


/**
 *
 * @has 1 - * InternalBoardObject
**/

public abstract class Parent {
	/**
	 * 
	 */
	private List<InternalBoardObject> children;

	/**
	 * 
	 */
	public void addChild(InternalBoardObject child) {
	}

	/**
	 * 
	 */
	public boolean removeChild(InternalBoardObject child) {
		return false;
	}

	/**
	 * 
	 */
	public Iterator<InternalBoardObject> getIterator() {
		return null;
	}
}
