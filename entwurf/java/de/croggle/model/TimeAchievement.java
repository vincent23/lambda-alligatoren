package de.croggle.model;

/**
  * Achievements which are awarded for reaching certain, specified amounts of time spent playing the game.
 */
public class TimeAchievement extends Achievement{

    private int currentTime; // Irgendwie bisherige Zeit + momentane Zeit aus dem Level //TOBO: Muss man irgendwie aus der datenbank holen. hier zwischenspeichern. Bitte nochmal wer drübersehen.

    /**
     * Returns true if the achievement with the given index has been achieved, otherwise false.
     * @param index specifies which achievment out of this type of achievement should be checked.
     */
    @Override
    public boolean requirementsMet( int index){
    }


}