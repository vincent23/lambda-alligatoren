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
public class ReplaceEggVisitor implements BoardObjectVisitor {
	private BoardEventMessenger boardMessenger;

	public ReplaceEggVisitor(Color eggColor, InternalBoardObject bornFamily, BoardEventMessenger boardMessenger){
	}

	/**
	 *
	 */
	@Override
	void visitEgg(Egg egg);

	/**
	 *
	 */
	@Override
	void visitColoredAlligator(ColoredAlligator alligator);

	/**
	 *
	 */
	@Override
	void visitAgedAlligator(AgedAlligator alligator);

	/**
	 *
	 */
	@Override
	void visitBoard(Board board);

}
