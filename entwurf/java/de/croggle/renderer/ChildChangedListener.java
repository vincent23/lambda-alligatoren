package de.croggle.renderer;

import de.croggle.model.InternalBoardObject;

/**
 *
 */
public interface ChildChangedListener extends BoardObjectChangedListener{
	/**
	 * If there is an eating process, the parentactor of the egg which will be replaced is informed. 
	 * @param position gives the position of the affected child.
	 * @param boardObject is the BoardObject the egg should be replaced with. 
	 */
	public void replacedChildWith(int position, InternalBoardObject boardObject);

	/**
	 * Method to inform the parentactor if its child was eating during an eating process.
	 * @param position position of the eaten child.
	 */
	public void removedChild(int position);

	/**
	 * Method to inform the parentactor about an eating process among its children.
	 * @param eater position of the child which is the eater in the eating process.
	 */
	public void childGotEaten(int eater);


}
