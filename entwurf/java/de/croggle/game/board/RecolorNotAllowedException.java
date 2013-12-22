package de.croggle.game.board;

/**
 * A RecolorNotAllowedException is thrown when someone tries to alter the color of a ColoredBoardObject which is not recolorable. 
 */
public class RecolorNotAllowedException extends Exception {
	
	/**
	 * Creates a new instance of the exception with the default constructor.
	 */
	public RecolorNotAllowedException() {
		super();
	}
	
	/**
	 * Creates a new instance of the exception with the given error message.
	 * @param message a message describing the cause of the exception that occured.
	 */
	public RecolorNotAllowedException(String message) {
		super(message);
	}
}
