package de.croggle.game;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.AlligatorOverflowException;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.IllegalBoardException;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;
import de.croggle.game.board.operations.CountBoardObjects;
import de.croggle.game.board.operations.FindEating;
import de.croggle.game.board.operations.RemoveAgedAlligators;
import de.croggle.game.board.operations.ReplaceEggs;
import de.croggle.game.event.BoardEventMessenger;
import de.croggle.util.RingBuffer;

/**
 * The Simulator is the instance which evaluates the Board given to it. It can
 * also undo steps done in the evaluation process.
 */
public class Simulator {
	private Board entranceBoard;
	private Board currentBoard;
	private RingBuffer<Board> history; // 30 elements needed.
	private ColorController colorController;
	private BoardEventMessenger boardMessenger;
	private int steps;

	private static final int MAX_ALLIGATORS = 300;

	/**
	 * Creates a new Simulator.
	 * 
	 * @param entranceBoard
	 *            the board that is evaluated by this simulator
	 * @param colorController
	 *            the color controller used for recoloring during evaluation
	 * @param boardMessenger
	 *            the board messenger used for sending events during evaluation
	 * 
	 * @throws IllegalBoardException
	 *             if the <code>entranceBoard</code> is not a valid board
	 */
	public Simulator(Board entranceBoard, ColorController colorController,
			BoardEventMessenger boardMessenger) throws IllegalBoardException {
		this.history = new RingBuffer<Board>(30);
		this.entranceBoard = entranceBoard;
		this.currentBoard = entranceBoard;
		this.colorController = colorController;
		this.boardMessenger = boardMessenger;
		this.steps = 0;
	}

	/**
	 * Evaluates one step in the Lambda Calculus.
	 * 
	 * @return the board after said step
	 * @throws ColorOverflowException
	 *             if recoloring occurs and there is no color available
	 * @throws AlligatorOverflowException
	 *             if there are more than the max. allowed amount of
	 *             BoardObjects on the board after the evaluation step
	 */
	public Board evaluate() throws ColorOverflowException,
			AlligatorOverflowException {
		final ColoredAlligator eater = FindEating.findEater(currentBoard);
		if (eater == null) {
			return currentBoard;
		}
		history.push(currentBoard);
		currentBoard = currentBoard.copy();

		final Parent parent = eater.getParent();
		final InternalBoardObject eaten = parent.getChildAfter(eater);
		boardMessenger.notifyEat(eater, eaten, parent.getChildPosition(eaten));
		parent.removeChild(eaten);
		final AgedAlligator constellation = replaceColoredWithAgedAlligator(eater);
		ReplaceEggs.replace(constellation, eater.getColor(), eaten,
				boardMessenger, colorController);

		RemoveAgedAlligators.remove(constellation, boardMessenger);
		if (MAX_ALLIGATORS < CountBoardObjects.count(currentBoard)) {
			throw new AlligatorOverflowException();
		}
		steps++;
		return currentBoard;
	}

	/**
	 * Reverses the last evaluation step.
	 * 
	 * @return the board, in its status before the last evaluation step
	 */
	public Board undo() {
		try {
			currentBoard = history.pop();
			steps--;
		} catch (Exception e) {
			// TODO
		}
		return currentBoard;
	}

	/**
	 * Reverses the board into the position it had upon entering simulation
	 * mode.
	 * 
	 * @return the board in said state
	 */
	public Board reset() {
		currentBoard = entranceBoard;
		steps = 0;
		return currentBoard;
	}

	private AgedAlligator replaceColoredWithAgedAlligator(
			ColoredAlligator coloredAlligator) {
		final AgedAlligator agedAlligator = new AgedAlligator(
				coloredAlligator.isMovable(), coloredAlligator.isRemovable());
		final Parent parent = coloredAlligator.getParent();
		parent.replaceChild(coloredAlligator, agedAlligator);
		for (InternalBoardObject child : coloredAlligator) {
			agedAlligator.addChild(child);
		}
		return agedAlligator;
	}

	public int getSteps() {
		return steps;
	}

}
