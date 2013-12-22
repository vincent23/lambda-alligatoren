package de.croggle.game.profile;

import java.lang.Exception;

/**
* The exception is thrown whenever there are more than the 6 possible profiles.
*/
public class ProfileOverflowException extends Exception{
	
	/**
	 * @inheritDoc
	 */
	public ProfileOverflowException() {
		super();
	}
	
	/**
	 * @inheritDoc
	 */
	public ProfileOverflowException(String message) {
		super(message);
	}
}
