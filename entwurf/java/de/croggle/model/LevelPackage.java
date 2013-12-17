package de.croggle.model;

import de.croggle.controller.LevelController;
/**
 * Compilation of several, thematically linked levels.
 * @has 1 - 1-* Level
 * @navassoc 1 - 1 de.croggle.controller.LevelController
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
