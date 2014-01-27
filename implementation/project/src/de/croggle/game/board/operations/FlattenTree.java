package de.croggle.game.board.operations;

import java.util.LinkedList;
import java.util.List;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;

/**
 * A visitor to flatten the syntax tree. Use the method "flatten" to receive a
 * "flat" array of BoardObjects. Useful if sequentially iterating through all
 * objects in the tree is needed to be achieved.
 */
public class FlattenTree<T extends BoardObject> implements BoardObjectVisitor {

	private List<T> flattened;

	private FlattenTree() {
		this.flattened = new LinkedList<T>();
	}

	/**
	 * Systematically travels the tree and adds each element part of it <b>one
	 * single time</b> to the array that is returned. Useful if sequentially
	 * iterating through all objects in the tree is needed to be achieved.
	 * 
	 * @param tree
	 *            The {@link BoardObject} tree to be flattened.
	 * @return A list with all elements in the given tree.
	 */
	public static BoardObject[] toArray(BoardObject tree) {
		FlattenTree<BoardObject> flattener = new FlattenTree<BoardObject>();
		tree.accept(flattener);
		return flattener.flattened.toArray(new BoardObject[flattener.flattened
				.size()]);
	}

	/**
	 * Systematically travels the tree and adds each element part of it <b>one
	 * single time</b> to the array that is returned. Useful if sequentially
	 * iterating through all objects in the tree is needed to be achieved.
	 * 
	 * @param tree
	 *            The {@link InternalBoardObject} tree to be flattened.
	 * @return A list with all elements in the given tree.
	 */
	public static InternalBoardObject[] toArray(InternalBoardObject tree) {
		FlattenTree<InternalBoardObject> flattener = new FlattenTree<InternalBoardObject>();
		tree.accept(flattener);
		return flattener.flattened
				.toArray(new InternalBoardObject[flattener.flattened.size()]);
	}

	/**
	 * Systematically travels the tree and adds each element part of it <b>one
	 * single time</b> to the array that is returned. Useful if sequentially
	 * iterating through all objects in the tree is needed to be achieved.
	 * 
	 * @param tree
	 *            The {@link BoardObject} tree to be flattened.
	 * @return A list with all elements in the given tree.
	 */
	public static List<BoardObject> toList(BoardObject tree) {
		FlattenTree<BoardObject> flattener = new FlattenTree<BoardObject>();
		tree.accept(flattener);
		return flattener.flattened;
	}

	/**
	 * Systematically travels the tree and adds each element part of it <b>one
	 * single time</b> to the array that is returned. Useful if sequentially
	 * iterating through all objects in the tree is needed to be achieved.
	 * 
	 * @param tree
	 *            The {@link InternalBoardObject} tree to be flattened.
	 * @return A list with all elements in the given tree.
	 */
	public static List<InternalBoardObject> toList(InternalBoardObject tree) {
		FlattenTree<InternalBoardObject> flattener = new FlattenTree<InternalBoardObject>();
		tree.accept(flattener);
		return flattener.flattened;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void visitEgg(Egg egg) {
		this.flattened.add((T) egg);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		this.flattened.add((T) alligator);
		alligator.acceptOnChildren(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		this.flattened.add((T) alligator);
		alligator.acceptOnChildren(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void visitBoard(Board board) {
		// Trust me, this will work :P
		this.flattened.add((T) board);
		board.acceptOnChildren(this);
	}
}
