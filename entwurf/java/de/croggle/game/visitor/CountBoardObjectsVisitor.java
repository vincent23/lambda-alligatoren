package de.croggle.game.visitor;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Board;

public class CountBoardObjectsVisitor implements BoardObjectVisitor {
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
