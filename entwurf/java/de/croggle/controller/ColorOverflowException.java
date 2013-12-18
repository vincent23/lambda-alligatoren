package de.croggle.controller;

import java.lang.Exception;

/**
* The exception is thrown, when a lambda term contains more than 30 different colors. This mostly happens during alpha reduction.
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
