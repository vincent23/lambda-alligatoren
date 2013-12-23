package de.croggle.game.visitor;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;

import de.croggle.game.event.BoardEventMessenger;

/**
 * A visitor looking for aged alligators, which are not nessesary
 * because they have only one children.
 * @navassoc 1 - 1 de.croggle.game.event.BoardEventMessenger
 *
 */
public class RemoveAgedAlligatorsVisitor implements BoardObjectVisitor {
	private BoardEventMessenger boardMessenger;

	/**
	 *
	 */
	private RemoveAgedAlligatorsVisitor(BoardObject family, BoardEventMessenger boardMessenger){
	}

	/**
	 * Removes all old alligators which are not necessary.
	 *
	 * @param family the family in which old alligators should be removed
	 * @param boardMessenger the messenger used for notifying listeners about removed alligators
	 */
	public static void remove(BoardObject family, BoardEventMessenger boardMessenger) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitEgg(Egg egg) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitBoard(Board board) {
		
	}

}
