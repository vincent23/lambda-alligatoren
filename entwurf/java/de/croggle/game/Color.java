package de.croggle.game;

/**
 * @opt all
 * @node class
 */
public class Color {
	int red; //zwischen 0 und 255.
    int green; //zwischen 0 und 255.
    int blue; //zwischen 0 und 255.
    String name; // sollte was mit dem RGB wert zutun haben.
    
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
