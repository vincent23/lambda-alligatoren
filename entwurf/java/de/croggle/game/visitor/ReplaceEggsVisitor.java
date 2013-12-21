package de.croggle.game.visitor;

import de.croggle.game.event.BoardEventMessenger;
import de.croggle.game.Color;
import de.croggle.game.board.InternalBoardObject;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Board;

/**
 * A visitor replacing eggs of a certain color with copies of a given
 * family (subtree).
 * @navassoc 1 - 1 de.croggle.game.event.BoardEventMessenger
 *
 */
public class ReplaceEggsVisitor implements BoardObjectVisitor {
	private BoardEventMessenger boardMessenger;

	private ReplaceEggsVisitor(ColoredAlligator parent, InternalBoardObject bornFamily, BoardEventMessenger boardMessenger){
	}

	/**
	 * Replaces all eggs below <code>parent</code>, which share it's color, with a copy of <code>bornFamily</code>.
	 * Correct recoloring of the newly inserted families is also performed, if necessary.
	 * When an egg is replaced, an onEat event is sent through <code>boardMessenger</code>.
	 *
	 * @param parent the colored alligator whose child eggs should be replaced
	 * @param bornFamily the family with which eggs are replaced
	 * @param boardMessenger the messenger used for sending events when eggs are replaced
	 */
	public static void replace(ColoredAlligator parent, InternalBoardObject bornFamily, BoardEventMessenger boardMessenger) {
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

}
