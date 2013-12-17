package de.croggle.controller;


import de.croggle.model.Board;
import de.croggle.data.Statistic;
import de.croggle.model.Level;
import de.croggle.controller.event.BoardEventListener;

/**
 *
 * @navassoc 1 - 1 de.croggle.model.Level
 * @navassoc 1 - 2 de.croggle.model.Board
 * @navassoc 1 - 1 Simulator
 * @navassoc 1 - 1 ColorController
 * @navassoc 1 - 1 de.croggle.data.Statistic
 * @depend - <transmits_Statistics> - StatisticsController
 */
public class GameController implements BoardEventListener{
    private Board currentBoard;
    private Simulator simulator;
    private ColorController colorController;
    private Level level;
    private Board simulationModeEntranceBoard;
    private Statistic statisticsDelta; //changes during the current Level.

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
     * Called when the level is completed. Writes the important results into the database and eventually tells the achievement controller which achievements were achieved.
     *
     */
    public void onCompletedLevel() {
    }
}