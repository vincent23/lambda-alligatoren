package de.croggle.game.board;

import java.lang.Exception;

/**
* The exception is thrown, when a lambda term contains more than 300 InternalBoardObjects. This happens while adding alligators or eggs to the working set, e.g. in the sandbox mode.
*/
public class AlligatorOverflowException extends Exception{
	
	/**
	 * @inheritDoc
	 */
	public AlligatorOverflowException() {
		super();
	}
	
	/**
	 * @inheritDoc
	 */
	public AlligatorOverflowException(String message) {
		super(message);
	}
}
