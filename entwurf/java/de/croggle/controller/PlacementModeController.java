package de.croggle.controller;

import de.croggle.model.Board;
import de.croggle.model.Level;
/**
 *
 * @navassoc 1 - 1 Level
 * @navassoc 1 - 1 Board
 */
public class PlacementModeController {
    private Level level;
    private Board currentBoard;
    /**
     * Hands control to the SimulationModeController.
     */
    private void switchToSimulation() {
    }

}