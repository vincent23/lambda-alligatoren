package de.croggle.renderer;


/**
 *
 */
public interface BoardObjectChangedListener {

	/**
	 * If the color of a BoardObject has changed, the actor is informed so it can also change its color.   
	 */
	public void recolored();
}
