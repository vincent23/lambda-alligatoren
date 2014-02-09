package de.croggle.game;

import java.util.List;

import de.croggle.AlligatorApp;
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
import de.croggle.game.board.operations.RemoveUselessAgedAlligators;
import de.croggle.game.board.operations.ReplaceEggs;
import de.croggle.game.board.operations.validation.BoardError;
import de.croggle.game.board.operations.validation.FindBoardErrors;
import de.croggle.game.event.BoardEventMessenger;
import de.croggle.util.RingBuffer;

/**
 * The Simulator is the instance which evaluates the Board given to it. It can
 * also undo steps done in the evaluation process.
 */
public class Simulator {
	private final Board entranceBoard;
	private Board currentBoard;
	private final RingBuffer<Board> history; // 30 elements needed.
	private final ColorController colorController;
	private final BoardEventMessenger boardMessenger;
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
		this.currentBoard = entranceBoard.copy();
		this.colorController = colorController;
		this.boardMessenger = boardMessenger;
		this.steps = 0;

		final List<BoardError> errors = FindBoardErrors.find(entranceBoard);
		if (!errors.isEmpty() && !AlligatorApp.DEBUG) {
			throw new IllegalBoardException();
		}
	}

	/**
	 * Evaluates one step in the Lambda Calculus.
	 * 
	 * @return true if an evaluation step has happened, false otherwise
	 * @throws ColorOverflowException
	 *             if recoloring occurs and there is no color available
	 * @throws AlligatorOverflowException
	 *             if there are more than the max. allowed amount of
	 *             BoardObjects on the board after the evaluation step
	 */
	public boolean evaluate() throws ColorOverflowException,
			AlligatorOverflowException {
		final ColoredAlligator eater = FindEating.findEater(currentBoard);
		if (eater == null) {
			return false;
		}
		final Board oldBoard = currentBoard.copy();
		history.push(oldBoard);

		final Parent parent = eater.getParent();
		final InternalBoardObject eaten = parent.getChildAfter(eater);
		parent.removeChild(eaten);
		boardMessenger.notifyEat(eater, eaten, parent.getChildPosition(eaten));

		final AgedAlligator constellation = replaceColoredWithAgedAlligator(eater);
		ReplaceEggs.replace(constellation, eater.getColor(), eaten,
				boardMessenger, colorController);

		RemoveAgedAlligators.remove(currentBoard, boardMessenger);
		RemoveUselessAgedAlligators.remove(currentBoard, boardMessenger);
		if (MAX_ALLIGATORS < CountBoardObjects.count(currentBoard)) {
			throw new AlligatorOverflowException();
		}
		steps++;
		return true;
	}

	public boolean canUndo() {
		return history.size() != 0;
	}

	/**
	 * Reverses the last evaluation step.
	 * 
	 * @return the board, in its status before the last evaluation step
	 */
	public Board undo() {
		try {
			currentBoard = history.pop();
			boardMessenger.notifyBoardRebuilt(currentBoard);
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
		currentBoard = entranceBoard.copy();
		steps = 0;
		boardMessenger.notifyBoardRebuilt(currentBoard);
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
		boardMessenger.notifyAlligatorAged(coloredAlligator, agedAlligator);
		return agedAlligator;
	}

	public int getSteps() {
		return steps;
	}

	public Board getCurrentBoard() {
		return currentBoard;
	}

}
