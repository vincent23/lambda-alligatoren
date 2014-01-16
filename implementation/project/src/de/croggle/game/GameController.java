package de.croggle.game;

import java.util.List;

import de.croggle.data.persistence.Statistic;
import de.croggle.data.persistence.StatisticsDeltaProcessor;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.event.BoardEventListener;
import de.croggle.game.event.BoardEventMessenger;
import de.croggle.game.level.Level;

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
	private Level level;
	private Statistic statisticsDelta; // changes during the current Level.
	private BoardEventMessenger boardEventMessenger;
	// listeners of the statisticsDelta
	private List<StatisticsDeltaProcessor> statisticsDeltaProcessors;

	/**
	 * Creates a new game controller for the given level.
	 * 
	 * @param level
	 *            the level the GameController should work with
	 */
	public GameController(Level level) {
		this.colorController = new ColorController();
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
	private void enterPlacement() {
	}

	/**
	 * prepare to switch game mode to simulation, in which the given board can
	 * be evaluated.
	 */
	private void enterSimulation() {
	}

	/**
	 * Called when the level is completed. Writes the important results into the
	 * database and eventually tells the achievement controller which
	 * achievements were achieved. Passes the statisticsDelta to all of its
	 * listeners.
	 * 
	 */
	public void onCompletedLevel() {
	}

	/**
	 * Registers a listener to whom the statisticsDelta should be passed after
	 * the completion of the level.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void register(StatisticsDeltaProcessor listener) {
	}

	/**
	 * Unregisters the statistic listener.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void unregister(StatisticsDeltaProcessor listener) {

	}

	/**
	 * Registers a listener to which board events should be sent.
	 * 
	 * @param listener
	 *            the listener which should receive the events
	 */
	public void registerBoardEventListener(BoardEventListener listener) {
	}

	/**
	 * Unregisters a board event listener so that it won't receive future
	 * events.
	 * 
	 * @param listener
	 *            the listener to unregister
	 */
	public void unregisterBoardEventListener(BoardEventListener listener) {
	}

	/**
	 * Registers the recoloring of an object in the statisticsDelta.
	 */
	@Override
	public void onObjectRecolored(ColoredBoardObject recoloredObject) {
	}

	/**
	 * Registers the amount of alligators and eggs eaten in the statisticsDelta.
	 */
	@Override
	public void onEat(ColoredAlligator eater, InternalBoardObject eatenFamily, int eatenPositionInParent) {
	}

	/**
	 * Would register this in the statisticsDelta, but currently there is no
	 * value like this in the statistic.
	 */
	@Override
	public void onAgedAlligatorVanishes(AgedAlligator alligator, int positionInParent) {
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
	public void onReplace(Egg replacedEgg, InternalBoardObject bornFamily) {
	}
}
