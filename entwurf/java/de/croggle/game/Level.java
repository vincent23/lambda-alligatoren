package de.croggle.game;

import de.croggle.data.Animation;
/**
 * 
 */
public interface Level {
	int getPackageIndex();
	int getLevelIndex();
	
	//LevelState state; //?
	Scene getInitialTerm();
	Scene getGoalTerm();
	Animation getAnimation();
}
