package de.croggle.game;

import de.croggle.data.persistence.Statistic;
import de.croggle.game.ColorController;
import de.croggle.game.board.Alligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.event.BoardEventListener;
import de.croggle.game.event.BoardEventMessenger;
import de.croggle.game.level.Level;

/**
 * Central controller, within which the actual playing of the a level is controlled. Additionally it handles the consequences of finishing a level and distributes the changes.
 *
 * @navassoc 1 - 1 de.croggle.game.level.Level
 * @navassoc 1 - 2 de.croggle.game.board.Board
 * @navassoc 1 - 1 Simulator
 * @navassoc 1 - 1 ColorController
 * @navassoc 1 - 1 de.croggle.data.persistence.Statistic
 * @navassoc 1 - 1 de.croggle.game.BoardEventMessenger
 * @depend - <transmits_Statistics> - de.croggle.data.persistence.StatisticController
 */
public class GameController implements BoardEventListener {
	/**
	 * The board that is used by the BoardActor to display the current state,
	 * both in simulation mode and placement mode.
	 */
	private Board shownBoard;
	/**
	 * The state of the board edited by the user before any simulations are applied to it.
	 * Used to return from simulation to placement mode.
	 */
	private Board userBoard;
	private Simulator simulator;
	private ColorController colorController;
	private Level level;
	private Statistic statisticsDelta; // changes during the current Level.
	private BoardEventMessenger boardEventMessenger;

	/**
	 * prepare to switch game mode to placement, in which the player is able to manipulate the board.
	 */
	private void enterPlacement() {
	}

	/**
	 * prepare to switch game mode to simulation, in which the given board can be evaluated.
	 */
	private void enterSimulation() {
	}

	/**
	 * Called when the level is completed. Writes the important results into the
	 * database and eventually tells the achievement controller which
	 * achievements were achieved.
	 * 
	 */
	public void onCompletedLevel() {
	}

	/**
	 *
	 */
	public void registerBoardEventListener(BoardEventListener listener) {
	}

	/**
	 *
	 */
	public void unregisterBoardEventListener(BoardEventListener listener) {
	}

	/**
	 *
	 */
	@Override
	public void onObjectRecolored(InternalBoardObject recoloredObject) {
	}

	/**
	 *
	 */
	@Override
	public void onEat(ColoredAlligator eater, InternalBoardObject eatenFamily) {
	}

	/**
	 *
	 */
	@Override
	public void onAlligatorVanishes(Alligator alligator) {
	}

	/**
	 *
	 */
	@Override
	public void onBoardRebuilt(Board board) {
	}

	/**
	 *
	 */
	@Override
	public void onReplace(Egg replacedEgg, InternalBoardObject bornFamily) {
	}
}
