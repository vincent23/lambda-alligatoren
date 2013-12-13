package de.croggle.model;

import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * @composed 1 - 3 Board
 */
public abstract class Level {
	private int levelIndex;
	private int packageIndex;
    
	private Board inputBoard;
	private Board goalBoard;
	private Animation animation;
    
	public int getPackageIndex();
	public int getLevelIndex();
	
	//LevelState state; //?
	public Board getInputBoard();
	public Board getGoalBoard();
	public boolean hasAnimation();
	public Animation getAnimation();
}
