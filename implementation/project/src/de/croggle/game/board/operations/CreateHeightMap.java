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

public class CreateHeightMap implements BoardObjectVisitor {

	private Map<BoardObject, Integer> heightMap;

	private CreateHeightMap() {
		this.heightMap = new HashMap<BoardObject, Integer>();
	}

	/**
	 * Creates a new map containing pairs of {@link BoardObject}s and the height
	 * of their child hierarchy as keys and values.
	 * 
	 * @param b
	 *            the {@link BoardObject} to create a height map for
	 * @return a height map corresponding to b
	 */
	public static Map<BoardObject, Integer> create(BoardObject b) {
		CreateHeightMap creator = new CreateHeightMap();
		b.accept(creator);
		return creator.heightMap;
	}

	@Override
	public void visitEgg(Egg egg) {
		heightMap.put(egg, 0);
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
		int height = 0;
		int childHeight;
		for (InternalBoardObject child : p) {
			child.accept(this);
			childHeight = heightMap.get(child);
			if (childHeight > height) {
				height = childHeight;
			}
		}
		heightMap.put(p, height + 1);
	}

}
