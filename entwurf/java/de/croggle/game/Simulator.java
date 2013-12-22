package de.croggle.game;

import de.croggle.game.board.Board;
import de.croggle.game.event.BoardEventMessenger;
import de.croggle.util.RingBuffer;

import de.croggle.game.ColorOverflowException;

/**
 * The Simulator is the instance, which evaluates the Board given to it. It can also undo steps done in the evaluation process.
 * 
 * @has 1 - 2-32 de.croggle.game.board.Board
 * @navassoc 1 - 1 de.croggle.util.RingBuffer
 * @navassoc 1 - 1 de.croggle.game.event.BoardEventMessenger
 * @depend - <uses> - de.croggle.game.visitor.FindEatingVisitor
 * @depend - <uses> - de.croggle.game.visitor.ReplaceEggsVisitor
 * @depend - <uses> - de.croggle.game.visitor.RemoveAgedAlligatorsVisitor
 */
public class Simulator {
	private Board entranceBoard;
	private Board currentBoard;
	private RingBuffer<Board> history; // 30 elements needed.
	private ColorController colorController;
	private BoardEventMessenger boardMessenger;

	/**
	 * Creates a new Simulator with the given entranceBoard, which it evaluates, colorController, which detremine the usable colors and boardMessenger. 
	 * 
	 */	
	public Simulator(Board entranceBoard, ColorController colorController,
			BoardEventMessenger boardMessenger) {
		this.history = new RingBuffer(30);
		this.entranceBoard = entranceBoard;
		this.currentBoard = entranceBoard;
		this.colorController = colorController;
		this.boardMessenger = boardMessenger;
	}

	/**
	 * Method, that evaluates one step in the Lambda Calculus.
	 * 
	 * @return the board after said step
	 * @throws ColorOverflowException if recoloring occurs and there is no color available
	 */
	public Board evaluate(Board currentBoard) {
		return null;
	}

	/**
	 * Method, that reverses the last evaluation step.
	 * 
	 * @return the board, in its status before the last evaluation step
	 */
	public Board undo() {
		return null;
	}

	/**
	 * Method, that reverses the board into the position it had upon entering
	 * simulation mode.
	 * 
	 * @return the board in said state
	 */
	public Board reset() {
		return null;
	}

}
