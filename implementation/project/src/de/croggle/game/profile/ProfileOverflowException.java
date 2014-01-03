package de.croggle.game.profile;

/**
 * The exception is thrown whenever there are more than the 6 possible profiles.
 */
@SuppressWarnings("serial")
public class ProfileOverflowException extends Exception {

	/**
	 * Creates a new instance of the exception with the default constructor.
	 */
	public ProfileOverflowException() {
		super();
	}

	/**
	 * Creates a new instance of the exception with the given error message.
	 * 
	 * @param message
	 *            a message describing the cause of the exception that occured
	 */
	public ProfileOverflowException(String message) {
		super(message);
	}
}
