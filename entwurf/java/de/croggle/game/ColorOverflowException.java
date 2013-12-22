package de.croggle.game;

import java.lang.Exception;

/**
* The exception is thrown whenever a lambda term contains more than 30 different colors. This mostly happens during alpha conversion.
*/
public class ColorOverflowException extends Exception{
	
	/**
	 * @inheritDoc
	 */
	public ColorOverflowException() {
		super();
	}
	
	/**
	 * @inheritDoc
	 */
	public ColorOverflowException(String message) {
		super(message);
	}
}
