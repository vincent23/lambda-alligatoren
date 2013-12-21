package de.croggle.game.visitor;

import de.croggle.game.event.BoardEventMessenger;
import de.croggle.game.Color;
import de.croggle.game.board.InternalBoardObject;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;

/**
 * A visitor replacing eggs of a certain color with copies of a given
 * family (subtree).
 * @navassoc 1 - 1 de.croggle.game.event.BoardEventMessenger
 *
 */
public class ReplaceEggsVisitor implements BoardObjectVisitor {
	private BoardEventMessenger boardMessenger;

	/**
	 * Creates a new visitor, which replaces eggs of the color <code>eggColor</code> with copies of <code>bornFamily</code>.
	 *
	 * @param eggColor the color of the eggs to replace
	 * @param bornFamily the family with which eggs are replaced
	 * @param boardMessenger the messenger used for sending events when eggs are replaced
	 */
	private ReplaceEggsVisitor(Color eggColor, InternalBoardObject bornFamily, BoardEventMessenger boardMessenger){
	}

	/**
	 * Replaces all egs of the color <code>eggColor</code> in the <code>family</code> with copies of <code>bornFamily</code>.
	 * When an egg is replaced, an onEat event is sent through <code>boardMessenger</code>.
	 *
	 * @param family the family in which eggs should be replaced
	 * @param eggColor the color of the eggs to replace
	 * @param bornFamily the family with which eggs are replaced
	 * @param boardMessenger the messenger used for sending events when eggs are replaced
	 */
	public static void replace(BoardObject family, Color eggColor, InternalBoardObject bornFamily, BoardEventMessenger boardMessenger) {
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
