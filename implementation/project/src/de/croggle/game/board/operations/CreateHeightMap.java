package de.croggle.game.board.operations;

import java.util.HashMap;
import java.util.Map;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;

/**
 * An alligator syntax tree operation to create a map, assigning each
 * {@link BoardObject} inside the tree a height. Each level inside the tree of
 * ancestors adds up to this height, also including the subtree's root itself
 * (except if it is a {@link Board}). That means, a tree consisting of just an
 * {@link Egg} has the standard height. Boards, however, do not add up to a
 * tree's height, meaning that an empty board will have a height of 0.
 * 
 * Using the more advanced
 * {@link CreateHeightMap#create(BoardObject, float, float) parameters of the
 * create method}, it is possible to assign a different height than the standard
 * height (=1) to BoardObjects during calculation. It is also possible to have
 * the height of different levels in the syntax tree shrink or expand, by
 * specifying a depthScaleFactor, that is multiplied by the objectHeight each
 * time the function goes deeper in the tree.
 */
public class CreateHeightMap implements BoardObjectVisitor {

	private Map<BoardObject, Float> heightMap;

	private final float depthScaleFactor;
	private float scale = 1;
	private final float objectHeight;

	private CreateHeightMap() {
		objectHeight = 1;
		depthScaleFactor = 1;
		this.heightMap = new HashMap<BoardObject, Float>();
	}

	private CreateHeightMap(float objectHeight, float depthScaleFactor) {
		this.objectHeight = objectHeight;
		this.depthScaleFactor = depthScaleFactor;
		this.heightMap = new HashMap<BoardObject, Float>();
	}

	/**
	 * Creates a new map containing pairs of {@link BoardObject}s and their
	 * respective child hierarchy heights - including themselves, if they are
	 * not {@link Board}s - as keys and values.
	 * <br/>
	 * <strong>Note:</strong> {@link Board}s are not adding any height to an
	 *                         alligator tree, as empty Boards are defined to
	 *                         have zero height.
	 * 
	 * @param b
	 *            the {@link BoardObject} to create a height map for
	 * @return a height map corresponding to b
	 */
	public static Map<BoardObject, Float> create(BoardObject b) {
		CreateHeightMap creator = new CreateHeightMap();
		b.accept(creator);
		return creator.heightMap;
	}

	/**
	 * Creates a new map containing pairs of {@link BoardObject}s and their
	 * respective child hierarchy heights - including themselves, if they are
	 * not {@link Board}s - as keys and values. The height is modulated using
	 * the specified parameters objectHeight and depthScaleFactor.
	 * 
	 * @<strong>Note:</strong> {@link Board}s are not adding any height to an
	 *                         alligator tree, as empty Boards are defined to
	 *                         have zero height.
	 * 
	 * @param b
	 *            the {@link BoardObject} to create a height map for
	 * @param objectHeight
	 *            the standard height that (unscaled)
	 *            {@link InternalBoardObject}s add to the child hierarchy's
	 *            height
	 * @param depthScaleFactor
	 *            a factor multiplied by objectHeight each time the hierarchy is
	 *            traversed down one level. Can be used to account for
	 *            growing/shrinking of levels the farer they are away from the
	 *            root
	 * @return a height map corresponding to b
	 */
	public static Map<BoardObject, Float> create(BoardObject b,
			float objectHeight, float depthScaleFactor) {
		CreateHeightMap creator = new CreateHeightMap(objectHeight,
				depthScaleFactor);
		b.accept(creator);
		return creator.heightMap;
	}

	@Override
	public void visitEgg(Egg egg) {
		heightMap.put(egg, objectHeight * getScale());
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
		// prevent Board from adding up to the depth
		goHigher();
		visitParent(board);
		// remove some height from Board, as it doesn't have a height on its own
		// (=> empty boards have height 0)
		heightMap.put(board, heightMap.get(board) - objectHeight * getScale());
		goDeeper();
	}

	private void visitParent(Parent p) {
		float height = 0;
		float childHeight;
		goDeeper();
		for (InternalBoardObject child : p) {
			child.accept(this);
			childHeight = heightMap.get(child);
			if (childHeight > height) {
				height = childHeight;
			}
		}
		goHigher();
		heightMap.put(p, height + objectHeight * getScale());
	}

	private float getScale() {
		return scale;
	}

	private void goHigher() {
		scale /= depthScaleFactor;
	}

	private void goDeeper() {
		scale *= depthScaleFactor;
	}

}
