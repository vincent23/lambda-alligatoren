package de.croggle.controller;

import de.croggle.model.Board;
/**
 *
 * @navassoc 1 - 1 Simulator
 * @navassoc 1 - 1 Colorcontroller
 */
public class SimulationModeController {
    private Simulator simulator;
    private ColorController colorController;
    /**
     *
     */
    private void switchToPlacement() {
    }
    /**
     * Called when the level is completed. Writes the important results into the database and eventually tells the achievement controller which achievements were achieved.
     *
     */
    public void onCompletedLevel() {
    }

}