package de.croggle.ui.renderer;

import java.util.ArrayList;
import java.util.List;

import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.operations.FlattenTree;

/**
 * A helper class providing functionality to fix {@link ActorLayout}s when the
 * represented {@link de.croggle.game.board.BoardObject} structure has changed.
 * For example, if an {@link de.croggle.game.board.AgedAlligator} vanishes, its
 * children need to be sifted up
 * 
 * Note: does not have an explicit access modifier since only the
 * {@link ActorLayout} class itself is supposed to make use of the functions
 * inside this class
 */
class ActorLayoutFixer {

	/**
	 * Calculates and returns all property-deltas that would need to be applied
	 * after a single InternalBoardActor was removed from the board, leaving all
	 * children untouched (i.e. moving them up into the next higher parent)
	 * 
	 * @param l
	 *            the layout the change is to be applied on
	 * @param o
	 *            the {@link InternalBoardObject} that is simulated to be
	 *            removed from the tree
	 * @return a list of {@link ActorDelta deltas} that would need to be applied
	 *         if <code>o</code> was actually removed
	 */
	public static List<ActorDelta> fixRemoveSingle(ActorLayout l,
			InternalBoardObject o) {
		List<InternalBoardObject> children = FlattenTree.toList(o);
		// should run in O(1) as long as o is added first by toList
		children.remove(o);
		BoardObjectActor removedActor = l.getActor(o);
		List<ActorDelta> result = new ArrayList<ActorDelta>(children.size());
		if (children.size() != 0) {
			float offset;
			float scale;
			if (o.getClass() == ColoredAlligator.class) {
				scale = removedActor.getHeight()
						/ l.getLayoutConfiguration()
								.getColoredAlligatorHeight();
				offset = l.getLayoutConfiguration().getUniformObjectHeight()
						* scale;
			} else {
				// aged alligator
				scale = removedActor.getHeight()
						/ l.getLayoutConfiguration().getAgedAlligatorHeight();
				offset = l.getLayoutConfiguration().getUniformObjectHeight()
						* scale;
			}
			if (l.getLayoutConfiguration().getVerticalGrowth() == TreeGrowth.POS_NEG) {
				offset *= -1;
			}

			BoardObjectActor b;
			for (InternalBoardObject child : children) {
				b = l.getActor(child);
				result.add(new ActorDelta(b, b.getX(), b.getY() - offset, null));
			}
		}
		return result;
	}

	public static List<ActorDelta> fixRemoveSubtree(ActorLayout l,
			InternalBoardObject o) {
		return null;
	}
}
