package de.croggle.game.board;

import java.util.Map;

import de.croggle.game.Color;
import de.croggle.game.board.operations.BoardObjectVisitor;

/**
 * Colored alligators represent lambda abstractions in the Lambda Calculus. The
 * color of the alligator is the variable which is bound by the abstraction.
 **/
public class ColoredAlligator extends Alligator implements ColoredBoardObject {
	private Color color;
	private final boolean recolorable;

	/**
	 * Creates a new ColoredAlligator with the specified color and the
	 * permission value if the object is recolorable or not. The color hereby
	 * serves as the name of variables bound by this abstraction in the Lambda
	 * Calculus.
	 * 
	 * @param parent
	 *            the ColoredAlligator's parent
	 * @param movable
	 *            whether the BoardObject is movable or not
	 * @param removable
	 *            whether the BoardObject is removable or not
	 * @param c
	 *            the color this alligator has
	 * @param recolorable
	 *            whether the ColoredAlligator is recolorable (true) or not
	 *            (false)
	 */
	private ColoredAlligator(Parent parent, boolean movable, boolean removable,
			Color c, boolean recolorable) {
		super(parent, movable, removable);
		this.color = c;
		this.recolorable = recolorable;
	}

	/**
	 * Creates a new ColoredAlligator with the specified color and the
	 * permission value if the object is recolorable or not. The color hereby
	 * serves as the name of variables bound by this abstraction in the Lambda
	 * Calculus. The parent of this InternalBoardObject will be initialized with
	 * null.
	 * 
	 * @param movable
	 *            whether the BoardObject is movable or not
	 * @param removable
	 *            whether the BoardObject is removable or not
	 * @param c
	 *            the color this alligator has
	 * @param recolorable
	 *            whether the ColoredAlligator is recolorable (true) or not
	 *            (false)
	 */
	public ColoredAlligator(boolean movable, boolean removable, Color c,
			boolean recolorable) {
		super(movable, removable);
		this.color = c;
		this.recolorable = recolorable;
	}

	private ColoredAlligator(ColoredAlligator coloredAlligator) {
		super(coloredAlligator);
		this.color = coloredAlligator.color;
		this.recolorable = coloredAlligator.recolorable;
	}

	/**
	 * Accepts a visitor which is then used for traversing the subtree of the
	 * object.
	 * 
	 * @param visitor
	 *            the visitor that tries to access the tree
	 */
	@Override
	public void accept(BoardObjectVisitor visitor) {
		visitor.visitColoredAlligator(this);
	}

	/**
	 * Creates and returns a deep copy of the board object.
	 * 
	 * @return the deep copy
	 */
	@Override
	public ColoredAlligator copy() {
		return new ColoredAlligator(this);
	}

	/**
	 * Returns whether the color of the egg can be changed by the user.
	 * 
	 * @return true if the object can be recolored, otherwise false
	 */
	@Override
	public boolean isRecolorable() {
		return recolorable;
	}

	/**
	 * Returns the color that represents the name of a variable on the board.
	 * 
	 * @return the current color of the colored alligator
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of the alligator. In placement mode: Set color only if it
	 * is marked as recolorable.
	 * 
	 * @param c
	 *            the new color for the colored alligator
	 */
	@Override
	public void setColor(Color c) {
		this.color = c;
	}

	public boolean match(BoardObject o) {
		if (o == null)
			return false;
		if (o.getClass() != ColoredAlligator.class)
			return false;

		ColoredAlligator oAllig = (ColoredAlligator) o;
		return this.color.equals(oAllig.color) && super.match(oAllig); // use
																		// Parent.match
																		// for
																		// child
																		// comparison
	}

	@Override
	public boolean matchWithRecoloring(BoardObject other,
			Map<Color, Color> recoloring) {
		if (other == null) {
			return false;
		}
		if (other.getClass() != ColoredAlligator.class) {
			return false;
		}

		final ColoredAlligator otherAlligator = (ColoredAlligator) other;
		if (recoloring.containsValue(otherAlligator.color)) {
			return false;
		} else {
			if (this.color.equals(otherAlligator.color)) {
				return super.matchWithRecoloring(otherAlligator, recoloring);
			} else {
				recoloring.put(otherAlligator.color, this.color);
				final boolean equal = super.matchWithRecoloring(otherAlligator,
						recoloring);
				recoloring.remove(otherAlligator.color);
				return false;
			}
		}
	}
}