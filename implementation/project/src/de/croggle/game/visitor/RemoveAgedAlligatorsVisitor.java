package de.croggle.game.visitor;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.event.BoardEventMessenger;

/**
 * A visitor looking for aged alligators, which are not nessesary because they
 * have only one children.
 * 
 */
public class RemoveAgedAlligatorsVisitor implements BoardObjectVisitor {
	private BoardEventMessenger boardMessenger;

	/**
	 *
	 * @param boardMessenger
	 */
	private RemoveAgedAlligatorsVisitor(BoardEventMessenger boardMessenger) {
		this.boardMessenger = boardMessenger;
	}
	
	/**
	 * 
	 */
	private RemoveAgedAlligatorsVisitor() {
		this.boardMessenger = null;
	}

	/**
	 * Removes all old alligators which are not necessary.
	 * 
	 * @param family
	 *            the family in which old alligators should be removed
	 * @param boardMessenger
	 *            the messenger used for notifying listeners about removed
	 *            alligators
	 */
	public static void remove(BoardObject family,
			BoardEventMessenger boardMessenger) {
		RemoveAgedAlligatorsVisitor visitor = new RemoveAgedAlligatorsVisitor(boardMessenger);
		family.accept(visitor);
	}
	
	/**
	 * Removes all old alligators which are not necessary.
	 * 
	 * @param family
	 *            the family in which old alligators should be removed
	 */
	public static void remove(BoardObject family) {
		RemoveAgedAlligatorsVisitor visitor = new RemoveAgedAlligatorsVisitor();
		family.accept(visitor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitEgg(Egg egg) {
		return;
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
		int children = alligator.getChildCount();
		if (children <= 1) {
			if (children == 0) {
				alligator.getParent().removeChild(alligator);
			} else if (children == 1) {
				alligator.getParent().replaceChild(alligator, alligator.getFirstChild());
			}
			if (this.boardMessenger != null)
				this.boardMessenger.notifyAgedAlligatorVanishes(alligator);
		} else {
			alligator.acceptOnChildren(this);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitBoard(Board board) {
		board.acceptOnChildren(this);
	}

}
