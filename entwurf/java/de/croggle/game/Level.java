package de.croggle.game;

import de.croggle.data.Animation;
/**
 * @opt all
 * @node class
 */
public class Level {
    int levelIndex;
    int packageIndex;
    
    LambdaConstellation inputConstellation;
    LambdaConstellation goalConstellation;
    LambdaConstellation currentConstellation;
    boolean animation; //TODO: ein boolean ob da eine Animation ist, und wenn ja, dann das handeln OR einfach eine Animation und wenn keine da ist NULL oder so
    
	int getPackageIndex();
	int getLevelIndex();
	
	//LevelState state; //?
	Scene getInputConstellation();
	Scene getGoalConstellation();
	Animation getAnimation();
}
