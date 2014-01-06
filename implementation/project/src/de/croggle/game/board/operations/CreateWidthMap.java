package de.croggle.game.board.operations;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;

public class CreateWidthMap implements BoardObjectVisitor {

	private Map<BoardObject, Double> widthMap;

	private final int objectWidth;
	private final double scaleFactor;
	private final int padding;

	private double scaling = 1;

	private CreateWidthMap(int objectWidth, double depthScaleFactor, int padding) {
		this.widthMap = new HashMap<BoardObject, Double>();
		this.objectWidth = objectWidth;
		this.scaleFactor = depthScaleFactor;
		this.padding = padding;
	}

	/**
	 * Creates a map of pairs of BoardObjects with their respective widths, i.e.
	 * the maximum amount of space the subtree beneath of and including the
	 * BoardObject itself will need to display all children on a level next to
	 * each other.
	 * 
	 * @param b
	 *            the BoardObject to create a width map for
	 * @param objectWidth
	 *            the width of a single child
	 * @param depthScaleFactor
	 *            the relative size of a child compared to its parent. E.g. 0.5
	 *            means, that the width and height of a child will be half of
	 *            each of the parent's, and the area covered by the child a
	 *            quarter of the parent's.
	 * @param padding
	 *            the number of units between two children to separate them
	 *            visually
	 * @return a map containing {@link BoardObject}s and their respective width,
	 *         in regard of the given parameters
	 */
	public static Map<BoardObject, Double> create(BoardObject b,
			int objectWidth, double depthScaleFactor, int padding) {
		CreateWidthMap creator = new CreateWidthMap(objectWidth,
				depthScaleFactor, padding);
		b.accept(creator);
		return creator.widthMap;
	}

	/**
	 * Creates a map of pairs of BoardObjects with the maximum number of
	 * children next to each other (i.e. on one level in the tree hierarchy)
	 * 
	 * @param b
	 *            the BoardObject to create a width map for
	 * @return a map containing {@link BoardObject}s and their respective width
	 */
	public static Map<BoardObject, Double> create(BoardObject b) {
		return create(b, 1, 1, 0);
	}

	@Override
	public void visitEgg(Egg egg) {
		widthMap.put(egg, getObectWidth());
	}

	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		visitParent(alligator);
	}

	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		visitParent(alligator);
	}

	@Override
	public void visitBoard(Board board) {
		visitParent(board);
	}

	private void visitParent(Parent p) {
		double width = getObectWidth();
		double childWidth = 0;
		goDeeper();
		Iterator<InternalBoardObject> it = p.iterator();
		while (it.hasNext()) {
			InternalBoardObject child = it.next();
			child.accept(this);
			childWidth += widthMap.get(child);
			if (it.hasNext()) {
				childWidth += padding;
			}
		}
		goHigher();
		widthMap.put(p, Math.max(width, childWidth));
	}

	/**
	 * Enter the next level inside the syntax tree
	 */
	private void goDeeper() {
		this.scaling *= scaleFactor;
	}

	private void goHigher() {
		this.scaling /= scaleFactor;
	}

	private double getObectWidth() {
		return scaling * objectWidth;
	}
}
