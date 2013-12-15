package de.croggle.model;

import java.util.List;
import java.util.Iterator;

import de.croggle.renderer.ChildChangedListener;

/**
 *
 * @has 1 - * InternalBoardObject
 * @has 1 - 1 ChildChangedListener
**/

public abstract class Parent {
	/**
	 * 
	 */
	private List<InternalBoardObject> children;
	private ChildChangedListener actor;

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