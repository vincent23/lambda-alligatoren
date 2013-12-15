package de.croggle.controller;

/**
 * @navassoc 1 - * de.croggle.model.Level
 */
public class LevelController {
	/**
     * Called when the level is completed. Writes the important results into the database and eventually tells the achievement controller which achievements were achieved.
     *
     */
    public void onCompletedLevel() {
    }
    /**
     * Called to load a new level. With both the packageIndex and the levelIndex one can distinctively indentify the class.
     * @param packageIndex specifies the level package from which the level is supposed to be loaded
     * @param levelIndex is the id of the level within the package.
     */
    public void loadLevel( int packageIndex, int levelIndex) {
    }
}
