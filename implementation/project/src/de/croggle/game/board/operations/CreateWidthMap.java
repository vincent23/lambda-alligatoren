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

	private Map<BoardObject, Float> widthMap;

	private final float objectWidth;
	private final float scaleFactor;
	private final float padding;

	private float scaling = 1;

	private CreateWidthMap(float objectWidth, float depthScaleFactor,
			float padding) {
		this.widthMap = new HashMap<BoardObject, Float>();
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
	public static Map<BoardObject, Float> create(BoardObject b,
			float objectWidth, float depthScaleFactor, float padding) {
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
	public static Map<BoardObject, Float> create(BoardObject b) {
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
		// prevent board from adding up to level depth
		goHigher();
		visitParent(board);
		goDeeper();
	}

	private void visitParent(Parent p) {
		float width = getObectWidth();
		float childWidth = 0;
		goDeeper();
		Iterator<InternalBoardObject> it = p.iterator();
		while (it.hasNext()) {
			InternalBoardObject child = it.next();
			child.accept(this);
			childWidth += widthMap.get(child);
			if (it.hasNext()) {
				// TODO apply scaling on padding or not?
				childWidth += padding * getScaling();
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

	/**
	 * Leave the current level inside the syntax tree
	 */
	private void goHigher() {
		this.scaling /= scaleFactor;
	}

	private float getScaling() {
		return scaling;
	}

	private float getObectWidth() {
		return scaling * objectWidth;
	}
}
