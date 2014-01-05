package de.croggle.game.board.operations;

import java.util.HashMap;
import java.util.Map;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Parent;

public class CreateDepthMap implements BoardObjectVisitor {

	private Map<BoardObject, Integer> depthMap;
	private int depth = 0;

	private CreateDepthMap() {
		this.depthMap = new HashMap<BoardObject, Integer>();
	}

	/**
	 * Creates a map of pairs of {@link BoardObject}s and the height of their
	 * parent hierarchy as keys and values.
	 * 
	 * @param b
	 *            the BoardObject to build the depth map for.
	 * @return a map of BoardObjects, used as keys for their depth inside the
	 *         syntax tree
	 */
	public static Map<BoardObject, Integer> create(BoardObject b) {
		CreateDepthMap creator = new CreateDepthMap();
		b.accept(creator);
		return creator.depthMap;
	}

	@Override
	public void visitEgg(Egg egg) {
		this.depthMap.put(egg, depth);
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
		this.depthMap.put(p, depth);
		depth++;
		p.acceptOnChildren(this);
		depth--;
	}
}
