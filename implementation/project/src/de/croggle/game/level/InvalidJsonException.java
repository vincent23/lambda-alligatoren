package de.croggle.game.level;

/**
 * Exception thrown if there is an invalid Json.
 * 
 */
@SuppressWarnings("serial")
public class InvalidJsonException extends Exception {

	/**
	 * Default constructor for the exception.
	 */
	public InvalidJsonException() {
		super();
	}

	/**
	 * @param message
	 *            message to indicate why the esception was thrown.
	 */
	public InvalidJsonException(String message) {
		super(message);
	}

}
