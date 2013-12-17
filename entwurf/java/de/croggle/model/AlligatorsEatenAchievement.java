package de.croggle.model;

/**
 * Achievements which are awarded for reaching certain, specified amounts of eaten Alligators.
 */
public class AlligatorsEatenAchievement extends Achievement{
    private String Description;
    private String emblemPath;
    private int id;
    private int currentAlligatorsEaten; // Muss irgendwie bisherige GesamtAlligatorEaten + in dem Level gegessene Alligatoren
    /**
     * Array, which describes the different stages of the achievement and which time requirements have to be met in order to fulfill this achievement.
     */
    private int[] stages;

    /**
     * Index, which specifies on which stage this achievement is;
     */
    private int index;

    /**
     *
     */
    public String getDescription() {
    }
    /**
     *
     */
    public String getEmblemPath() {
    }
    /**
     *
     */
    public int getId(){
    }
    /**
     * Returns true if the achievement with the given index has been achieved, otherwise false.
     * @param index specifies which achievment out of this type of achievement should be checked.
     */
    @Override
    public boolean requirementsMet( int index){
    }
    
    
}