package de.croggle.game;

/**
 * A color represents a variable name.
 */
public class Color implements Comparable<Color> {
	public static final int MAX_COLORS = 30;

	static {
		uncolored = new UncoloredColor();
	}
	private static final Color uncolored;
	/*
	 * The predefined colors mapped onto variable names source:
	 * http://tools.medialab.sciences-po.fr/iwanthue/index.php
	 */
	private static final String[] colorStrings = { "#9cffff", "#8D2C95",
			"#5FDB39", "#F48524", "#2ACBE7", "#32362D", "#BADB21", "#D93166",
			"#CB52D7", "#FD96EA", "#6F6BD2", "#733320", "#4D971B", "#F9F20F",
			"#EE191E", "#EF5C38", "#52043D", "#6CD882", "#F7F186", "#429DF9",
			"#2FE2BE", "#033665", "#084F0E", "#A4A4F8", "#E2339D", "#F4AB13",
			"#0F90C0", "#D37EFC", "#DDC84E", "#940B66" };
	private static final com.badlogic.gdx.graphics.Color[] representations;
	static {
		// assert (MAX_COLORS == colorStrings.length); // done by ColorTest unit
		// test
		representations = new com.badlogic.gdx.graphics.Color[MAX_COLORS];
		for (int i = 0; i < colorStrings.length; i++) {
			representations[i] = de.croggle.util.convert.ColorConvert
					.fromHexString(colorStrings[i]);
		}
	}

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

	public static com.badlogic.gdx.graphics.Color[] getRepresentations() {
		return representations;
	}

	public static com.badlogic.gdx.graphics.Color getRepresentation(Color c) {
		return representations[c.id];
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
