package de.croggle.game.board;

/**
 * The exception is thrown whenever a lambda term contains more than 300
 * InternalBoardObjects. This happens while adding alligators or eggs to the
 * working set, e.g. in the sandbox mode.
 */
@SuppressWarnings("serial")
public class AlligatorOverflowException extends Exception {

	/**
	 * Creates a new instance of the exception with the default constructor.
	 */
	public AlligatorOverflowException() {
		super();
	}

	/**
	 * Creates a new instance of the exception with the given error message.
	 * 
	 * @param message
	 *            a message describing the cause of the exception that occured
	 */
	public AlligatorOverflowException(String message) {
		super(message);
	}
}
