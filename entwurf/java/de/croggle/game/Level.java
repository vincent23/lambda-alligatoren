package de.croggle.game;

import de.croggle.data.Animation;
/**
 * @opt all
 * @node class
 * @composed 1 - 3 Board
 */
public abstract class Level {
    private int levelIndex;
    private int packageIndex;
    
    private Board inputBoard;
    private Board goalBoard;
    private Board currentBoard;
    private boolean animation; //TODO: ein boolean ob da eine Animation ist, und wenn ja, dann das handeln OR einfach eine Animation und wenn keine da ist NULL oder so
    
	public int getPackageIndex();
	public int getLevelIndex();
	
	//LevelState state; //?
	public Board getInputBoard();
	public Board getGoalBoard();
	public Animation getAnimation();
}
