package de.croggle.game.board;

@SuppressWarnings("serial")
public class NoSuchChildException extends IndexOutOfBoundsException {
	public NoSuchChildException() {
		super();
	}

	public NoSuchChildException(String message) {
		super(message);
	}
}
