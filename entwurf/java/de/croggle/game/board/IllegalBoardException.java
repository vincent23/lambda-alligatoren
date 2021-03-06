package de.croggle.game.board;

import java.lang.Exception;

/**
* The exception is thrown when the alligator constellation does not represent a correct lambda term after the simulation has been started.
*/
public class IllegalBoardException extends Exception{
	
	/**
	 * Creates a new instance of the exception with the default constructor.
	 */
	public IllegalBoardException() {
		super();
	}
	
	/**
	 * Creates a new instance of the exception with the given error message.
	 * @param message a message describing the cause of the exception that occured
	 */
	public IllegalBoardException(String message) {
		super(message);
	}
}
