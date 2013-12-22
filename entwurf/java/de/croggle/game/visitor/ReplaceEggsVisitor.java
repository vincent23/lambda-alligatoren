package de.croggle.game.visitor;

import de.croggle.game.event.BoardEventMessenger;
import de.croggle.game.Color;
import de.croggle.game.board.InternalBoardObject;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Board;

import de.croggle.game.ColorController;

/**
 * A visitor replacing eggs of a certain color with copies of a given
 * family (subtree).
 * @navassoc 1 - 1 de.croggle.game.event.BoardEventMessenger
 * @depend - <uses> - de.croggle.game.visitor.RecolorVisitor
 * @depend - <uses> - de.croggle.game.visitor.CollectFreeColorsVisitor
 * @depend - <uses> - de.croggle.game.visitor.CollectBoundColorsVisitor
 *
 */
public class ReplaceEggsVisitor implements BoardObjectVisitor {
	private BoardEventMessenger boardMessenger;

	private ReplaceEggsVisitor(ColoredAlligator parent, InternalBoardObject bornFamily, BoardEventMessenger boardMessenger, ColorController colorController) {
	}

	/**
	 * Replaces all eggs below <code>parent</code>, which share it's color, with a copy of <code>bornFamily</code>.
	 * Correct recoloring of the newly inserted families is also performed, if necessary.
	 * When an egg is replaced, an onEat event is sent through <code>boardMessenger</code>.
	 *
	 * @param parent the colored alligator whose child eggs should be replaced
	 * @param bornFamily the family with which eggs are replaced
	 * @param boardMessenger the messenger used for sending events when eggs are replaced
	 * @param colorController the color controller used for recoloring
	 */
	public static void replace(ColoredAlligator parent, InternalBoardObject bornFamily, BoardEventMessenger boardMessenger, ColorController colorController) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitEgg(Egg egg);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitColoredAlligator(ColoredAlligator alligator);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitAgedAlligator(AgedAlligator alligator);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitBoard(Board board);

	/**
	 * Returns all colors of colored alligators above <code>egg</code> but below <code>parent</code>.
	 * This is equivalent to the colors which are bound in the current subterm for the location of the egg.
	 *
	 * @param egg the egg for which the bound colors should be returned
	 * @return the set of bound colors
	 */
	private Color[] findLocallyBoundColors(Egg egg) {
	}
}
