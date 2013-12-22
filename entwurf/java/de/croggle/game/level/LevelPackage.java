package de.croggle.game.level;

/**
 * Compilation of several, thematically linked levels.
 * @has 1 - 1-* Level
 * @navassoc 1 - 1 LevelController
 */
public class LevelPackage {
	private int size;
    private int levelPackageId;
    private String name;
    private String description;
    private String emblemPath;
    private boolean hasAnimation;

    /**
     * Gets the level controller which is responsible for handling the levels within the level package.
     * @return the level controller one must use to handle the levels within the level package
     */
    public LevelController getLevelController();
}
