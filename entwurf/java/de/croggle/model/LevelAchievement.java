package de.croggle.model;

/**
 *
 */
public class LevelAchievement extends Achievement{
    private int currentLevelsCompleted; //TOBO: Muss man irgendwie aus der datenbank holen. hier zwischenspeichern. Bitte nochmal wer drübersehen.

    /**
     * Returns true if the achievement with the given index has been achieved, otherwise false.
     * @param index specifies which achievment out of this type of achievement should be checked.
     */
    @Override
    public boolean requirementsMet( int index){
    }
    
    
}