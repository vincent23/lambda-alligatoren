package de.croggle.game;

/**
 * @opt all
 * @node class
 */
public class Color {
	private int red; //between 0 and 255.
    private int green; //between 0 and 255.
    private int blue; //between 0 and 255.
    String name; 
    
    public Color(int pRed, int pGreen, int pBlue) {
        this.red = pRed;
        this.green = pGreen;
        this.blue = pBlue;
        this.name = "R: " + this.red + " G: " + this.green + " B: " + this.blue;
    }
    
    public Color(int pRed, int pGreen, int pBlue, String pName) {
        this.red = pRed;
        this.green = pGreen;
        this.blue = pBlue;
        this.name = pName;
    }
}
