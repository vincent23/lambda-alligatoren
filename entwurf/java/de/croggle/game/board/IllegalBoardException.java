package de.croggle.game.board;

import java.lang.Exception;

/**
* The exception is thrown, when the alligator constellation does not represent a correct lambda term after the simulation has been started.
*/
public class IllegalBoardException extends Exception{
	
	/**
	 * @inheritDoc
	 */
	public IllegalBoardException() {
		super();
	}
	
	/**
	 * @inheritDoc
	 */
	public IllegalBoardException(String message) {
		super(message);
	}
}
