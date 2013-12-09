package de.croggle.game.model;
/**
 * @composed 1 - 1-0 Color
 * @has 1 - * BoardObject
 **/
public class ColoredAlligator extends Family implements InternalBoardObject {
	private Color color;
	private List<BoardObject> children;
}
