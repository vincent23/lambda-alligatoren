package de.croggle.game;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.LevelProgress;
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
import de.croggle.ui.screens.PlacementModeScreen;

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
	private int elapsedTime;
	private long timeStamp;
	private Simulator simulator;
	private ColorController colorController;
	private final Level level;
	private Statistic statisticsDelta; // changes during the current Level.
	private final BoardEventMessenger simulationMessenger;
	private final BoardEventMessenger placementMessenger;
	// listeners of the statisticsDelta
	private final List<StatisticsDeltaProcessor> statisticsDeltaProcessors;
	private final AlligatorApp app;
	private LevelProgress progress;
	private boolean simulationPaused;

	/**
	 * Creates a new game controller for the given level.
	 * 
	 * @param level
	 *            the level the GameController should work with
	 */
	public GameController(AlligatorApp app, Level level) {
		this.app = app;
		this.level = level;
		this.elapsedTime = 0;
		this.timeStamp = TimeUtils.millis();
		setupColorController();
		this.shownBoard = level.getInitialBoard().copy();
		this.userBoard = shownBoard;
		this.statisticsDelta = new Statistic();
		this.simulationMessenger = new BoardEventMessenger();
		this.placementMessenger = new BoardEventMessenger();
		this.statisticsDeltaProcessors = new ArrayList<StatisticsDeltaProcessor>();
		this.simulationPaused = false;

		simulationMessenger.register(this);
		placementMessenger.register(this);
		loadProgress();
	}

	protected void setupColorController() {
		colorController = createColorController();
	}

	protected ColorController createColorController() {
		return new ColorController();
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
		simulationPaused = false;
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
		simulationPaused = false;
		simulator = new Simulator(userBoard, colorController,
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
	private void onCompletedLevel(boolean won) {
		boolean alreadyWon = progress.isSolved(); 
		onFinishedSimulation();
		saveProgress();
		if (progress.isSolved()) {
			statisticsDelta.setPlaytime(elapsedTime / 1000); // time in
																// statisticDelta
																// is
																// in sec,
																// elapsedTime
																// in
																// millisec.
			Log.d("statistic trace", "Statistic: Playtime:" + statisticsDelta.getPlaytime()); // TODO remove debug code
			Log.d("statistic trace", "Statistic: Alligators Eaten " + statisticsDelta.getAlligatorsEaten() );
			Log.d("statistic trace", "Statistic: Alligators placed " + statisticsDelta.getAlligatorsPlaced() );
			Log.d("statistic trace", "Statistic: LevelsCompleted " + statisticsDelta.getLevelsComplete() );
			for (StatisticsDeltaProcessor processor : statisticsDeltaProcessors) {
				processor.processDelta(statisticsDelta);
			}
		}
		statisticsDelta = new Statistic();
		app.showLevelTerminatedScreen(this);
		simulationPaused = false;
		if(!progress.isSolved() && alreadyWon){
			progress.setSolved(true);
		}
		saveProgress();
		app.clearScreenStackAfterSimulation();
		
	}

	protected void onFinishedSimulation() {
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
		saveProgress();
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

	@Override
	public void onObjectPlaced(InternalBoardObject placed) {
		saveProgress();
	}

	@Override
	public void onObjectRemoved(InternalBoardObject removed) {
		saveProgress();
	}

	@Override
	public void onObjectMoved(InternalBoardObject moved) {
		saveProgress();
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
		if (simulationPaused) {
			return;
		}
		final boolean evaluated = simulator.evaluate();
		if (level.isLevelSolved(simulator.getCurrentBoard(),
				simulator.getSteps())) {
			Timer timer = new Timer();
			simulationPaused = true;
			timer.scheduleTask(new Task() {
				public void run() {
					if (isInSimulationMode()) {
						onCompletedLevel(true);
					}
				}
			}, 2.0f);

		} else if (!evaluated || !level.isSolveable(simulator.getSteps())) {
			Timer timer = new Timer();
			simulationPaused = true;
			timer.scheduleTask(new Task() {
				public void run() {
					if (isInSimulationMode()) {
						onCompletedLevel(false);
					}
				}
			}, 2.0f);
		}
	}

	public boolean isInSimulationMode() {
		return (simulator != null);
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
		setupColorController();
		userBoard = level.getInitialBoard().copy();
		placementMessenger.notifyBoardRebuilt(userBoard);
		statisticsDelta.setResetsUsed(statisticsDelta.getResetsUsed() + 1);
		saveProgress();
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

	public int getElapsedTime() {
		return elapsedTime;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
		progress.setUsedTime(elapsedTime);
		saveProgress();
	}

	public void updateTime() {
		long timeNow = TimeUtils.millis();
		int timeAddition = (int) (timeNow - timeStamp);
		elapsedTime += timeAddition;
		// TODO is this a good place?
		progress.setUsedTime(elapsedTime);
		saveProgress();
	}

	public void setTimeStamp() {
		this.timeStamp = TimeUtils.millis();
	}

	public Level getLevel() {
		return level;
	}

	public Screen createPlacementScreen(AlligatorApp app) {
		return new PlacementModeScreen(app, this);
	}

	public boolean isSolved() {
		return getProgress().isSolved();
	}

	protected LevelProgress getProgress() {
		return progress;
	}

	protected void setUserBoard(Board userBoard) {
		this.userBoard = userBoard;
		placementMessenger.notifyBoardRebuilt(userBoard);
	}

	protected void onBeforeSaveProgress(LevelProgress progress) {
	}

	protected void onAfterLoadProgress(LevelProgress progress) {
	}

	private void saveProgress() {
		final String profileName = app.getProfileController()
				.getCurrentProfileName();
		onBeforeSaveProgress(progress);
		app.getPersistenceManager().saveLevelProgress(profileName, progress);
	}

	private void loadProgress() {
		final String profileName = app.getProfileController()
				.getCurrentProfileName();
		final LevelProgress previousProgress = app.getPersistenceManager()
				.getLevelProgress(profileName, level.getLevelId());
		if (previousProgress == null) {
			Log.d("GameController", "No previous progress");
			progress = new LevelProgress(level.getLevelId(), false, "", 0);
			return;
		}
		onAfterLoadProgress(previousProgress);
		progress = previousProgress;
	}

	protected Board getUserBoard() {
		return userBoard;
	}

	// TODO call this somewhere
	public void onUsedHint() {
		statisticsDelta.setUsedHints(statisticsDelta.getUsedHints() + 1);
		saveProgress();
	}

	protected Simulator getSimulator() {
		return simulator;
	}
}
