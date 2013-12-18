package de.croggle.game.achievement;


/**
 * Achievements which are awarded for reaching certain, specified amounts of eaten Alligators.
 */
public class AlligatorsEatenAchievement extends Achievement{
    private int currentAlligatorsEaten; // Muss irgendwie bisherige GesamtAlligatorEaten + in dem Level gegessene Alligatoren

    /**
     * Returns true if the achievement with the given index has been achieved, otherwise false.
     * @param index specifies which achievment out of this type of achievement should be checked.
     */
    @Override
    public boolean requirementsMet( int index){
    }
    
    
}