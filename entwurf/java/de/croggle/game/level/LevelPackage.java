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

    public LevelController getLevelController();
}
