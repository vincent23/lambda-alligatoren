package de.croggle.game.visitor;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Board;

/**
 *
 */
public class FindEatingVisitor implements BoardObjectVisitor {
	private ColoredAlligator eater;

	public FindEatingVisitor() {
		this.eater = null;
	}

	/**
	 *
	 */
	private void foundEater(ColoredAlligator eater) {
		this.eater = eater;
	}

	/**
	 *
	 */
	private boolean hasFoundEater() {
		return eater != null;
	}

	/**
	 *
	 */
	public ColoredAlligator getEater() {
		return eater;
	}

	/**
	 *
	 */
	@Override
	public void visitEgg(Egg egg) {
		
	}

	/**
	 *
	 */
	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		
	}

	/**
	 *
	 */
	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		
	}

	/**
	 *
	 */
	@Override
	public void visitBoard(Board board) {
		
	}

}
