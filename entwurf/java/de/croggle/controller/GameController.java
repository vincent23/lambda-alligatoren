package de.croggle.controller;

import de.croggle.data.persistence.Statistic;
import de.croggle.game.ColorController;
import de.croggle.game.board.Board;
import de.croggle.game.event.BoardEvent;
import de.croggle.game.event.BoardEventListener;
import de.croggle.game.level.Level;

/**
 * 
 * @navassoc 1 - 1 de.croggle.game.level.Level
 * @navassoc 1 - 2 de.croggle.game.board.Board
 * @navassoc 1 - 1 Simulator
 * @navassoc 1 - 1 ColorController
 * @navassoc 1 - 1 de.croggle.data.persistence.Statistic
 * @depend - <transmits_Statistics> - StatisticController
 */
public class GameController implements BoardEventListener {
	private Board currentBoard;
	private Simulator simulator;
	private ColorController colorController;
	private Level level;
	private Board simulationModeEntranceBoard;
	private Statistic statisticsDelta; // changes during the current Level.

	/**
	 * If 1, in PlacementMode. If 0, in SimulationMode.
	 */
	private boolean isInPlacementMode;

	/**
     *
     */
	private void switchToPlacementMode() {
	}

	/**
     *
     */
	private void switchToSimulationMode() {
	}

	/**
	 * Called when the level is completed. Writes the important results into the
	 * database and eventually tells the achievement controller which
	 * achievements were achieved.
	 * 
	 */
	public void onCompletedLevel() {
	}

	@Override
	public void callback(BoardEvent event) {
		// TODO Auto-generated method stub

	}
}
