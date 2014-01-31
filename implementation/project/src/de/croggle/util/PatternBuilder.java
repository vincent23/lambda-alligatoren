package de.croggle.util;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;

public class PatternBuilder {

	private final static float alpha = .8f;

	public static Pixmap generateEmpty(int width) {
		final Pixmap pattern = new Pixmap(width, width, Pixmap.Format.RGB888);
		pattern.setColor(1, 1, 1, 1);
		pattern.fill();
		return pattern;
	}

	public static Pixmap generateCircle(int width) {
		final Pixmap pattern = generateEmpty(width);
		pattern.setColor(0, 0, 0, 1);
		pattern.fillCircle(width / 2, width / 2, (int) (.35 * width));
		return pattern;
	}

	public static Pixmap generateVerticalLines(int width, int lineWidth) {
		final Pixmap pattern = generateEmpty(width);
		final int n = width / (lineWidth * 2);
		pattern.setColor(0, 0, 0, alpha);
		for (int i = 0; i < n; i++) {
			pattern.fillRectangle(2 * i * lineWidth, 0, lineWidth, width);
		}
		return pattern;
	}

	public static Pixmap generateHorizontalLines(int width, int lineWidth) {
		final Pixmap pattern = generateEmpty(width);
		final int n = width / (lineWidth * 2);
		pattern.setColor(0, 0, 0, alpha);
		for (int i = 0; i < n; i++) {
			pattern.fillRectangle(0, 2 * i * lineWidth, width, lineWidth);
		}
		return pattern;
	}

	public static Pixmap generateCheckerboard(int width, int tileWidth) {
		final Pixmap pattern = generateEmpty(width);
		final int n = width / (tileWidth * 2);
		pattern.setColor(0, 0, 0, alpha);
		for (int y = 0; y < 2 * n; y++) {
			for (int x = 0; x < n; x++) {
				pattern.fillRectangle((2 * x + (y % 2)) * tileWidth, y
						* tileWidth, tileWidth, tileWidth);
			}
		}
		return pattern;
	}

	public static Pixmap generateRhombus(int width, int rhombusWidth,
			int rhombusHeight) {
		final Pixmap pattern = generateEmpty(width);
		pattern.setColor(0, 0, 0, alpha);
		final int center = width / 2;
		final int rhombusWidth2 = rhombusWidth / 2;
		final int rhombusHeight2 = rhombusHeight / 2;
		Pixmap.setBlending(Blending.None);
		pattern.fillTriangle(center - rhombusWidth2, center, center
				+ rhombusWidth2, center, center, center - rhombusHeight2);
		pattern.fillTriangle(center - rhombusWidth2, center, center
				+ rhombusWidth2, center, center, center + rhombusHeight2);
		Pixmap.setBlending(Blending.SourceOver);
		return pattern;
	}

}
