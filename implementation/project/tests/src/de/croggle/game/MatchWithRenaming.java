package de.croggle.game;

import java.util.Iterator;

import android.util.SparseArray;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;
import de.croggle.game.board.operations.BoardObjectVisitor;

/**
 * A board operation that offers functionality to compare alligator expressions
 * regardless of the concrete color/variable name mapping. That means, that for
 * two alligator expressions to match it is only necessary that a bijective
 * transfer function between the color names of expression a and the color names
 * of expression b exists (alpha equivalence).
 * 
 */
public class MatchWithRenaming implements BoardObjectVisitor {

	private boolean match = true;
	private BoardObject toCompare;
	private SparseArray<Color> mapping;

	private MatchWithRenaming(BoardObject toCompare) {
		this.toCompare = toCompare;
		mapping = new SparseArray<Color>();
	}

	/**
	 * Performs a test for alpha equivalence (see class description) between two
	 * alligator expressions.
	 * 
	 * @param a
	 *            the first expression to be compared
	 * @param b
	 *            the second expression to be compared
	 * @return true, if the two given expressions are alpha-equivalent, false
	 *         otherwise
	 */
	public static boolean match(BoardObject a, BoardObject b) {
		MatchWithRenaming matcher = new MatchWithRenaming(b);
		a.accept(matcher);
		return matcher.match;
	}

	private boolean colorsMatch(Color one, Color two) {
		if (Color.uncolored().equals(one)) {
			return Color.uncolored().equals(two);
		}

		if (mapping.get(one.getId()) == null) {
			mapping.append(one.getId(), two);
			return true;
		} else {
			return mapping.get(one.getId()).equals(two);
		}
	}

	@Override
	public void visitEgg(Egg egg) {
		if (toCompare.getClass() == Egg.class) {
			if (colorsMatch(egg.getColor(), ((Egg) toCompare).getColor())) {
				return;
			}
		}
		match = false;
	}

	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		if (toCompare.getClass() == ColoredAlligator.class) {
			if (colorsMatch(alligator.getColor(),
					((ColoredAlligator) toCompare).getColor())) {
				visitParent(alligator, (ColoredAlligator) toCompare);
				return;
			}
		}
		match = false;
	}

	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		if (toCompare.getClass() == AgedAlligator.class) {
			visitParent(alligator, (AgedAlligator) toCompare);
		} else {
			match = false;
		}
	}

	@Override
	public void visitBoard(Board board) {
		if (toCompare.getClass() == Board.class) {
			visitParent(board, (Board) toCompare);
		} else {
			match = false;
		}
	}

	private void visitParent(Parent thisParent, Parent compareParent) {
		if (thisParent.getChildCount() == compareParent.getChildCount()) {
			// just to make sure there is no inconsistency if someone executes
			// code
			// after calling visitParent
			BoardObject toCmp = toCompare;
			Iterator<InternalBoardObject> thisIt = thisParent.iterator();
			Iterator<InternalBoardObject> compareIt = compareParent.iterator();
			while (match && thisIt.hasNext()) {
				toCompare = compareIt.next();
				thisIt.next().accept(this);
			}
			toCompare = toCmp;
		} else {
			match = false;
		}
	}

}
