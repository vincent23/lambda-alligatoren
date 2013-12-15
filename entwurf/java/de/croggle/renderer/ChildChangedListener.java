package de.croggle.renderer;

import de.croggle.model.InternalBoardObject;

/**
 *
 */
public interface ChildChangedListener extends BoardObjectChangedListener{
	/**
	* 
	 */
	public void replacedChildWith(int position, InternalBoardObject boardObject);

	/**
	 */
	public void removedChild(int position);

	/**
	 */
	public void childGotEaten(int eater);


}
