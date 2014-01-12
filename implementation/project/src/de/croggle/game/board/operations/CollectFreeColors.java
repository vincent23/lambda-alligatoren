package de.croggle.game.board.operations;

import java.util.HashSet;
import java.util.Set;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Parent;

/**
 * A visitor for collecting all the colors of eggs with no matching alligator
 * above them. This is equivalent to the set of variables which occur free in a
 * given subterm.
 * 
 */
public class CollectFreeColors implements BoardObjectVisitor {
	private final Set<Color> freeColors;
	private final BoardObject family;

	private CollectFreeColors(BoardObject family) {
		freeColors = new HashSet<Color>();
		this.family = family;
	}

	/**
	 * Returns the set of colors of eggs with no matching alligator above them
	 * in the given family.
	 * 
	 * @param family
	 *            the family to examine
	 * @return the set of free colors
	 */
	public static Color[] collect(BoardObject family) {
		final CollectFreeColors visitor = new CollectFreeColors(family);
		family.accept(visitor);
		return visitor.freeColors.toArray(new Color[visitor.freeColors.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitEgg(Egg egg) {
		final Color color = egg.getColor();
		Parent parent;
		do {
			parent = egg.getParent();
			// TODO find a way to remove instanceof
			if (parent instanceof ColoredAlligator) {
				final ColoredAlligator colored = (ColoredAlligator) parent;
				if (color.equals(colored.getColor())) {
					return;
				}
			}
		} while (parent != family);
		freeColors.add(color);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		alligator.acceptOnChildren(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		alligator.acceptOnChildren(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitBoard(Board board) {
		board.acceptOnChildren(this);
	}
}
