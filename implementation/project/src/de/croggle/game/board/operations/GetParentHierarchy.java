package de.croggle.game.board.operations;

import java.util.ArrayList;
import java.util.List;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;

/**
 * A visitor-based operation to determine the list of a BoardObject's parents.
 * Use the static <code>get</code> method(s) to receive the respective lists.
 * 
 */
public class GetParentHierarchy implements BoardObjectVisitor {

	private List<Parent> parents;

	private GetParentHierarchy() {
		this.parents = new ArrayList<Parent>();
	}

	/**
	 * Iterates the tree upwards and adds all parents of the given BoardObject
	 * to the end of a list, that will be returned. That implicates that the
	 * resulting list will have the topmost ancestor at its end. The given
	 * BoardObject itself will NOT be part of the list, so the list's first
	 * element will be the given BoardObject's parent.
	 * 
	 * The implementation currently uses a LinkedList for its better pushFront
	 * behavior without knowing the final list's length.
	 * 
	 * @param b
	 *            the BoardObject whose parent hierarchy is to be determined
	 * @return the given BoardObject's parent hierarchy beginning with the b's
	 *         parent and ending with tree's root
	 */
	public static List<Parent> get(BoardObject b) {
		GetParentHierarchy getter = new GetParentHierarchy();
		b.accept(getter);
		return getter.parents;
	}

	/**
	 * Common behavior of all internal board objects.
	 * 
	 * @param ibo
	 *            the InternalBoardObject to be visited
	 */
	private void visitInternalBoardObject(InternalBoardObject ibo) {
		Parent p = ibo.getParent();
		if (p == null) {
			return;
		}
		parents.add(p);
		p.accept(this);
	}

	@Override
	public void visitEgg(Egg egg) {
		visitInternalBoardObject(egg);
	}

	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		visitInternalBoardObject(alligator);

	}

	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		visitInternalBoardObject(alligator);
	}

	@Override
	public void visitBoard(Board board) {
		// has no parents
		return;
	}

}
