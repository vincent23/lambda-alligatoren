package de.croggle.model;

import de.croggle.renderer.BoardObjectChangedListener;

/**
 * An egg represents a variable within the Lambda Calculus. If the guarding alligator eats something, the eaten thing will hatch from the egg.
 * @composed 1 - 1-0 Color
 * @has 1 - 1 BoardObjectChangedListener
 */
public class Egg implements InternalBoardObject {
	private Color color;
	private BoardObjectChangedListener actor;
}
