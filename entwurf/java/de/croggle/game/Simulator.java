package de.croggle.game;

import java.util.List;

import de.croggle.game.ColorController;
import de.croggle.game.board.Board;
import de.croggle.game.event.EatEventListener;
import de.croggle.util.RingBuffer;

/**
 * @has 1 - 2-32 de.croggle.game.board.Board
 * @navassoc 1 - 1 de.croggle.util.RingBuffer
 */
public class Simulator {
	private Board entranceBoard;
    private Board currentBoard;
	private RingBuffer<Board> history; // 30 elements needed.
    private ColorController colorController;
	private List<EatEventListener> eatListeners;

	public Simulator(Board entranceBoard, ColorController colorController) {
		this.history = new RingBuffer(30);
        this.entranceBoard = entranceBoard;
        this.currentBoard = entranceBoard;
        this.colorController = colorController;
	}

	/**
	 * Method, that evaluates one step in the Lambda Calculus.
     * @return the board after said step.
	 */
	public Board evaluate(Board currentBoard) {
		return null;
	}

	/**
	 * Method, that reverses the last evaluation step.
	 * 
	 * @return the board, in its status before the last evaluation step.
	 */
	public Board undo() {
		return null;
	}

    /**
     * Method, that reverses the board into the position it had upon entering simulation mode.
     * @return the board in said state.
     */
    public Board reset() {

    }

}
