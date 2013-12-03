package de.croggle.game;

/**
 * @opt all
 * @node class
 */
public class Color {
	private int red; //between 0 and 255.
    private int green; //between 0 and 255.
    private int blue; //between 0 and 255.
    private String name; 
    
    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = "R: " + this.red + " G: " + this.green + " B: " + this.blue;
    }
    
    public Color(int red, int green, int blue, String name) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }
}
