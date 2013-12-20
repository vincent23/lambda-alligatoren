package de.croggle.game.visitor;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Board;

import de.croggle.game.event.BoardEventMessenger;

/**
 * A visitor looking for aged alligators, which are not nessesary 
 * because their presence does not change the order of evaluation within the term.
 * @navassoc 1 - 1 de.croggle.game.event.BoardEventMessenger
 *
 */
public class RemoveAgedAlligatorsVisitor implements BoardObjectVisitor {
private BoardEventMessenger boardMessenger;

public RemoveAgedAlligatorsVisitor(BoardEventMessenger boardMessenger){
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
