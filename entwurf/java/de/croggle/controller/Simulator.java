package de.croggle.controller;

import java.util.List;

import de.croggle.controller.event.EatEventListener;
import de.croggle.model.Board;
import de.croggle.util.RingBuffer;

/**
 * @has 1 - 0-30 de.croggle.model.Board
 * @navassoc 1 - 1 de.croggle.util.RingBuffer
 */
public class Simulator {
	private Board entranceBoard;
	private RingBuffer<Board> history; // 30 elements needed.

	private List<EatEventListener> eatListeners;

	public Simulator() {
		this.history = new RingBuffer(30);
	}

	/**
	 * Method that evaluates one step in the Lambda Calculus
	 */
	public Board evaluate(Board entranceBoard) {
		// gotta save the old board to history[*rightplace*]
		return null;
	}

	/**
	 * Method that reverses the last evaluation step.
	 * 
	 * @return The last
	 */
	public Board undo() {
		return null;
	}

}
