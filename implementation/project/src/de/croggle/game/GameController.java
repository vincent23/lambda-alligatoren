package de.croggle.game;

import java.util.ArrayList;
import java.util.List;

import de.croggle.data.persistence.Statistic;
import de.croggle.data.persistence.StatisticsDeltaProcessor;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.AlligatorOverflowException;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.board.Egg;
import de.croggle.game.board.IllegalBoardException;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.operations.CountBoardObjects;
import de.croggle.game.event.BoardEventListener;
import de.croggle.game.event.BoardEventMessenger;
import de.croggle.game.level.Level;
import de.croggle.game.level.MultipleChoiceLevel;

/**
 * Central controller within which the actual playing of the level is
 * controlled. Additionally, it handles the consequences of finishing a level
 * and distributes the changes.
 */
public class GameController implements BoardEventListener {
	/**
	 * The board that is used by the BoardActor to display the current state,
	 * both in simulation mode and placement mode.
	 */
	private Board shownBoard;
	/**
	 * The state of the board edited by the user before any simulations are
	 * applied to it. Used to return from simulation to placement mode.
	 */
	private Board userBoard;
	private Simulator simulator;
	private ColorController colorController;
	private final Level level;
	private Statistic statisticsDelta; // changes during the current Level.
	private final BoardEventMessenger simulationMessenger;
	private final BoardEventMessenger placementMessenger;
	// listeners of the statisticsDelta
	private final List<StatisticsDeltaProcessor> statisticsDeltaProcessors;
	// TODO find better solution to determine whether level is an Multiple
	// Choice level
	private boolean isMC;
	private boolean answerMcIsValid;
	private int answerMC;

	/**
	 * Creates a new game controller for the given level.
	 * 
	 * @param level
	 *            the level the GameController should work with
	 */
	public GameController(Level level) {
		this.level = level;
		setupColorController();
		this.shownBoard = level.getInitialBoard();
		this.userBoard = shownBoard;
		this.statisticsDelta = new Statistic();
		this.simulationMessenger = new BoardEventMessenger();
		this.placementMessenger = new BoardEventMessenger();
		this.statisticsDeltaProcessors = new ArrayList<StatisticsDeltaProcessor>();
	}

	private void setupColorController() {
		this.colorController = new ColorController();
		// TODO fill with level's user colors
	}

	/**
	 * Returns the gam's ColorController.
	 * 
	 * @return the game's current ColorController
	 */
	public ColorController getColorController() {
		return this.colorController;
	}

	/**
	 * prepare to switch game mode to placement, in which the player is able to
	 * manipulate the board.
	 */
	public void enterPlacement() {
		shownBoard = userBoard;
		simulator = null;
		// TODO not sure if both messengers should be notified
		placementMessenger.notifyBoardRebuilt(shownBoard);
		simulationMessenger.notifyBoardRebuilt(shownBoard);
	}

	/**
	 * prepare to switch game mode to simulation, in which the given board can
	 * be evaluated.
	 * 
	 * @throws IllegalBoardException
	 */
	public void enterSimulation() throws IllegalBoardException {
		simulator = new Simulator(shownBoard.copy(), colorController,
				simulationMessenger);
		shownBoard = simulator.getCurrentBoard();
		// TODO not sure if both messengers should be notified
		placementMessenger.notifyBoardRebuilt(shownBoard);
		simulationMessenger.notifyBoardRebuilt(shownBoard);
	}

	/**
	 * Called when the level is completed. Writes the important results into the
	 * database and eventually tells the achievement controller which
	 * achievements were achieved. Passes the statisticsDelta to all of its
	 * listeners.
	 * 
	 */
	private void onCompletedLevel() {
		for (StatisticsDeltaProcessor processor : statisticsDeltaProcessors) {
			processor.processDelta(statisticsDelta);
		}
		statisticsDelta = new Statistic();
	}

	/**
	 * Registers a listener to whom the statisticsDelta should be passed after
	 * the completion of the level.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void register(StatisticsDeltaProcessor listener) {
		statisticsDeltaProcessors.add(listener);
	}

	/**
	 * Unregisters the statistic listener.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void unregister(StatisticsDeltaProcessor listener) {
		statisticsDeltaProcessors.remove(listener);
	}

	/**
	 * Registers a listener to which board events should be sent.
	 * 
	 * @param listener
	 *            the listener which should receive the events
	 */
	public void registerSimulationBoardEventListener(BoardEventListener listener) {
		simulationMessenger.register(listener);
	}

	/**
	 * Unregisters a board event listener so that it won't receive future
	 * events.
	 * 
	 * @param listener
	 *            the listener to unregister
	 */
	public void unregisterSimulationBoardEventListener(
			BoardEventListener listener) {
		simulationMessenger.unregister(listener);
	}

	/**
	 * Registers a listener to which board events should be sent on board
	 * changes during placement.
	 * 
	 * @param listener
	 *            the listener which should receive the events
	 */
	public void registerPlacementBoardEventListener(BoardEventListener listener) {
		placementMessenger.register(listener);
	}

	/**
	 * Unregisters a board event listener so that it won't receive future events
	 * fired during placement.
	 * 
	 * @param listener
	 *            the listener to unregister
	 */
	public void unregisterPlacementBoardEventListener(
			BoardEventListener listener) {
		placementMessenger.unregister(listener);
	}

	public BoardEventMessenger getPlacmentBoardEventListener() {
		return placementMessenger;
	}

	/**
	 * Registers the recoloring of an object in the statisticsDelta.
	 */
	@Override
	public void onObjectRecolored(ColoredBoardObject recoloredObject) {
		statisticsDelta.setRecolorings(statisticsDelta.getRecolorings() + 1);
	}

	/**
	 * Registers the amount of alligators and eggs eaten in the statisticsDelta.
	 */
	@Override
	public void onEat(ColoredAlligator eater, InternalBoardObject eatenFamily,
			int eatenPositionInParent) {
		int alligatorsEaten = statisticsDelta.getAlligatorsEaten();
		alligatorsEaten += CountBoardObjects.count(eatenFamily, false, false,
				true, true);
		statisticsDelta.setAlligatorsEaten(alligatorsEaten);
	}

	/**
	 * Would register this in the statisticsDelta, but currently there is no
	 * value like this in the statistic.
	 */
	@Override
	public void onAgedAlligatorVanishes(AgedAlligator alligator,
			int positionInParent) {
	}

	/**
	 * Resets all necessary values of the statisticsDelta.
	 */
	@Override
	public void onBoardRebuilt(Board board) {
	}

	/**
	 * Registers the hatched egg and the born family in the statisticsDelta.
	 */
	@Override
	public void onHatched(Egg replacedEgg, InternalBoardObject bornFamily) {
		statisticsDelta.setEggsHatched(statisticsDelta.getEggsHatched() + 1);
	}

	@Override
	public void onAge(ColoredAlligator colored, AgedAlligator aged) {
	}

	public void evaluateStep() throws ColorOverflowException,
			AlligatorOverflowException {
		final Board newBoard = simulator.evaluate();
		if (level.isLevelSolved(newBoard, simulator.getSteps())) {
			onCompletedLevel();
		}
	}

	public boolean canUndo() {
		return simulator.canUndo();
	}

	public void undo() {
		simulator.undo();
	}

	/**
	 * Resets the game. That means, while in placement mode, the board is reset
	 * to the level's initial board. In simulation mode, the simulator will
	 * reset the currently shown board with the state the board had when the
	 * user entered simulation.
	 */
	public void reset() {
		// TODO maybe keep track of state (placement/simulation) in dedicated
		// variable?
		if (simulator != null) {
			simulator.reset();
		} else {
			setupColorController();
			userBoard = level.getInitialBoard();
			placementMessenger.notifyBoardRebuilt(userBoard);
		}

	}

	public Board getShownBoard() {
		// TODO maybe keep track of state (placement/simulation) in dedicated
		// variable?
		if (simulator == null) {
			return shownBoard;
		} else {
			return simulator.getCurrentBoard();
		}
	}

	public Level getLevel() {
		return level;
	}

	// TODO
	public void setMCSelection(boolean answers[]) {
		boolean set = false;
		for (int i = 0; i < answers.length; i++) {
			if (answers[i]) {
				if (set) {
					this.answerMcIsValid = false;
					break;
				} else {
					this.answerMC = i;
					this.answerMcIsValid = true;
					set = true;
				}
			}
		}

	}

	public void setMCtrue() {
		this.isMC = true;
	}

	public boolean getIsMC() {
		return this.isMC;
	}

	public boolean getAnswerMcIsValid() {
		return this.answerMcIsValid;
	}

	public int getAnswerMC() {
		return this.answerMC;
	}

	public boolean isLevelWon() {
		if (isMC) {
			return ((MultipleChoiceLevel) level).validateAnswer(answerMC);
		} else {
			// TODO
			return false;
		}
	}
}
