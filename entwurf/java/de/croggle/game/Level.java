package de.croggle.game;

import de.croggle.data.Animation;
/**
 * @opt all
 * @node class
 */
public abstract class Level {
    private int levelIndex;
    private int packageIndex;
    
    private LambdaConstellation inputConstellation;
    private LambdaConstellation goalConstellation;
    private LambdaConstellation currentConstellation;
    private boolean animation; //TODO: ein boolean ob da eine Animation ist, und wenn ja, dann das handeln OR einfach eine Animation und wenn keine da ist NULL oder so
    
	public int getPackageIndex();
	public int getLevelIndex();
	
	//LevelState state; //?
	public Scene getInputConstellation();
	public Scene getGoalConstellation();
	public Animation getAnimation();
}
