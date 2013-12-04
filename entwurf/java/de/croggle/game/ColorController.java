package de.croggle.game;


import java.util.ArrayList;
import de.croggle.game.model.Color;

/**
 * @navassoc 1 - * Color
 */
public class ColorController {
	private ArrayList<Color> usableColors = new ArrayList<Color>();
    private ArrayList<Color> bannedColors = new ArrayList<Color>();

	/**
	* Add color to usableColors[].
	*
	**/
    public void addUsableColor(Color color) {
        //Check first if color is already in usableColors[].
    }

    /**
	* Add color to bannedColors[].
	*
	**/
    public void addBannedColor(Color color) {
        //Check first if color is already in bannedColors[].
    }

	/**
	* Remove color from usableColors[].
	*
	**/
    public void removeUsableColor(Color color) {
        //Check first if color is not in UsableColors[].
    }

	/**
	* Remove color from bannedColors[].
	*
	**/
    public void removeBannedColor(Color color) {
         //Check first if color is not in BannedColors[].
    }
    
    
}
