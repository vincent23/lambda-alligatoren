package de.croggle.game;

/**
 * A color represents a variable name.
 */
public class Color implements Comparable<Color> {
	static {
		uncolored = new UncoloredColor();
	}

	private final int id;

	private static final Color uncolored;

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
			throw new IllegalArgumentException(
					"Cannot inintialize Color with negative id");
		}
		this.id = id;
	}

	/**
	 * Returns the pointer-identical reference to a unique color that represents
	 * the "uncolored" color. I.e. a color representing all colors of objects,
	 * whose color is not yet defined.
	 * 
	 * @return the global singleton "uncolored" color.
	 */
	public static Color uncolored() {
		return Color.uncolored;
	}

	/**
	 * Gets the globally unique color id between 0 and 29.
	 * 
	 * @return the color id that this object is an instance of.
	 */
	public int getId() {
		return this.id;
	}

	@Override
	public boolean equals(Object o) {
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

	private static class UncoloredColor extends Color {
		public UncoloredColor() {
			super(0);
			if (Color.uncolored != null) {
				throw new IllegalStateException(
						"The uncolored color must be globally unique");
			}
		}

		@Override
		public int getId() {
			return -1;
		}

		@Override
		public boolean equals(Object o) {
			return o == this;
		}

		@Override
		public int hashCode() {
			int hash = 5;
			hash = 17 * hash - 1;
			return hash;
		}
	}
}
