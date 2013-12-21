package de.croggle.game.visitor;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Board;

import de.croggle.game.event.BoardEventMessenger;

/**
 *
 * @navassoc 1 - 1 de.croggle.game.event.BoardEventMessenger
 */
public class RecolorVisitor implements BoardObjectVisitor {
private BoardEventMessenger boardMessenger;

public RecolorVisitor(BoardEventMessenger boardMessenger){
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
