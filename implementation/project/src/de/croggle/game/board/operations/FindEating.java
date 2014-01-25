package de.croggle.game.board.operations;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;

/**
 * A visitor for finding a colored alligator which can eat a family next to it.
 */
public class FindEating implements BoardObjectVisitor {
	private ColoredAlligator eater;

	/**
	 * Creates a new visitor with no found alligator.
	 */
	private FindEating() {
		this.eater = null;
	}

	/**
	 * Search the top-leftmost colored alligator which can eat a family next to
	 * it. In this case, "left" is preferred over "top", i.e. in a term ()The
	 * eaten family can be retrieved by calling
	 * <code>eater.getParent().getNextChild(eater)</code> where "eater" is the
	 * returned ColoredAlligator.
	 * 
	 * @param board
	 *            the board in which colored alligators should be searched
	 * @return the eating alligator if one was found, otherwise null
	 */
	public static ColoredAlligator findEater(Board board) {
		FindEating finder = new FindEating();
		board.accept(finder);
		return finder.eater;
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
		if (canEat(alligator)) {
			this.eater = alligator;
			return;
		} else {
			for (InternalBoardObject child : alligator) {
				child.accept(this);
				if (this.eater != null) {
					break;
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		for (InternalBoardObject child : alligator) {
			child.accept(this);
			if (this.eater != null) {
				break;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitBoard(Board board) {
		for (InternalBoardObject child : board) {
			child.accept(this);
			if (this.eater != null) {
				break;
			}
		}
	}

	/**
	 * Determine, whether a given {@link ColoredAlligator} can eat or not.
	 * 
	 * @param alligator
	 *            the {@link ColoredAlligator} to be tested
	 * @return true, if the given alligator can eat, false otherwise
	 */
	private boolean canEat(ColoredAlligator alligator) {
		return !alligator.getParent().isLastChild(alligator);
	}

}
