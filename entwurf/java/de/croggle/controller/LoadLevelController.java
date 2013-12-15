package de.croggle.controller;

import de.croggle.model.Level;
/**
 * @navassoc 1 - 1 Level
 */
public class LoadLevelController {
    /**
     * Called to load a new level. With both the packageIndex and the levelIndex one can distinctively indentify the class.
     * @param packageIndex specifies the level package from which the level is supposed to be loaded
     * @param levelIndex is the id of the level within the package.
     */
    public LoadLevelController(int packageIndex, int levelIndex) {
    }

    /**
     * Starts the level and then enters the placement mode.
     */
    private void startLevel(Level level) {
    }

}
