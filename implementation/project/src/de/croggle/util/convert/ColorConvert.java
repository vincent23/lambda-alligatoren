package de.croggle.util.convert;

public class ColorConvert {
	private ColorConvert() {

	}

	/*
	 * Expects a hex value as integer and returns the appropriate Color object.
	 * 
	 * @param hex must be of the form 0xAARRGGBB
	 * 
	 * @return the generated Color object
	 * 
	 * @author Michael.Lowfyr@gmail.com -
	 * https://code.google.com/p/libgdx-users/wiki/ColorHexConverter
	 */
	public static com.badlogic.gdx.graphics.Color fromHex(long hex) {
		float a = (hex & 0xFF000000L) >> 24;
		float r = (hex & 0xFF0000L) >> 16;
		float g = (hex & 0xFF00L) >> 8;
		float b = (hex & 0xFFL);

		return new com.badlogic.gdx.graphics.Color(r / 255f, g / 255f,
				b / 255f, a / 255f);
	}

	/*
	 * Expects a hex value as String and returns the appropriate Color object
	 * 
	 * @param s the hex string to create the Color object from
	 * 
	 * @return the generated Color object
	 * 
	 * @author Michael.Lowfyr@gmail.com -
	 * https://code.google.com/p/libgdx-users/wiki/ColorHexConverter
	 */
	public static com.badlogic.gdx.graphics.Color fromHexString(String s) {
		if (s.startsWith("0x"))
			s = s.substring(2);
		else if (s.startsWith("#"))
			s = s.substring(1);

		if (s.length() == 6)
			s = "FF" + s;

		if (s.length() != 8) // AARRGGBB
			throw new IllegalArgumentException(
					"String must have the form AARRGGBB");

		return fromHex(Long.parseLong(s, 16));
	}
}
