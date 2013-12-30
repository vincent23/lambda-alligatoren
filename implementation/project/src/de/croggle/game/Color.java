package de.croggle.game;

/**
 * A color represents a variable name.
 */
public class Color implements Comparable<Color> {
	private final int id;

	/**
	 * Creates a color with the given id. The id needs to be between 0 and 29
	 * and represents a certain "real" color according to the ColorController.
	 * 
	 * @param id
	 *            the identifying color id
	 * @throws IllegalArgumentException
	 *             when the id is not a number between 0 and 29
	 */
	public Color(int id) {
		if (id < 0) {
			throw new IllegalArgumentException("Cannot inintialize Color with negative id");
		}
		this.id = id;
	}
	
	private Color() {
		this.id = -1;
	}
	
	protected static Color uncolored() {
		return new Color();
	}

	/**
	 * Gets the globally unique color id between 0 and 29.
	 * @return the color id that this object is an instance of. 
	 */
	public int getId() {
		return this.id;
	}
	
	@Override
	public boolean equals (Object o) {
		if (o == null) 
			return false;
		if (o.getClass() != Color.class)
			return false;
		
		Color oColor = (Color) o;
		return oColor.id == this.id;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 17 * hash + this.id;
		return hash;
	}

	public int compareTo(Color c) {
		return this.id - c.id;
	}
}
