package de.croggle.model;

/**
 *
 */
public class LevelAchievement implements Achievement{
    private String Description;
    private String emblemPath;
    private int id;
    private int currentLevelsCompleted; //TOBO: Muss man irgendwie aus der datenbank holen. hier zwischenspeichern. Bitte nochmal wer drübersehen.
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