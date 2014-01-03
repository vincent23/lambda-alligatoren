package de.croggle.game;

/**
 * The exception is thrown whenever a lambda term contains more than 30
 * different colors. This mostly happens during alpha conversion.
 */
@SuppressWarnings("serial")
public class ColorOverflowException extends Exception {

	/**
	 * Creates a new instance of the exception with the default constructor.
	 */
	public ColorOverflowException() {
		super();
	}

	/**
	 * Creates a new instance of the exception with the given error message.
	 * 
	 * @param message
	 *            a message describing the cause of the exception that occured
	 */
	public ColorOverflowException(String message) {
		super(message);
	}
}
