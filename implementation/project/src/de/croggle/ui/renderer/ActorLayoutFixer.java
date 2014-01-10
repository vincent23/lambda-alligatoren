package de.croggle.ui.renderer;

import java.util.Map;

import de.croggle.game.board.BoardObject;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;

public class ActorLayoutFixer {

	/**
	 * Corrects layout issues that were caused by removing a child from the
	 * tree. Therefore it requires information of the removed subtree's parent,
	 * its original position in the parent's child list and the width of the
	 * removed subtree. It also requires to know whether the child is still
	 * present in the tree and with which growth direction the board was originally
	 * layouted. Of course, a map to access the remaining object actors using
	 * their respective {@link BoardObject}s as keys, is also mandatory. The process of
	 * fixing the tree can be animated by giving a positive, non-zero
	 * <code>animationlength</code> value.
	 * 
	 * @param fromParent the parent from which a child subtree was removed
	 * @param childIndex the index of the removed child in the parent's child list to the time it was removed
	 * @param childWidth the width of the removed child's subtree
	 * @param stillPresent 
	 * @param actors
	 * @param growth
	 * @param animationlength
	 */
	public static void fixOnRemove(Parent fromParent, int childIndex,
			float childWidth, boolean stillPresent,
			Map<InternalBoardObject, BoardObjectActor> actors, TreeGrowth growth, float animationlength) {

	}

	/**
	 * Corrects layout issues that were caused by adding a subtree to the
	 * board. Therefore it requires information of the added subtree's parent,
	 * its new position in the parent's child list and the width of the
	 * added subtree. It also requires to know whether the child is already
	 * present in the tree and with which growth direction the board was originally
	 * layouted. Of course, a map to access the other object actors using
	 * their respective {@link BoardObject}s as keys, is also mandatory. The process of
	 * fixing the tree can be animated by giving a positive, non-zero
	 * <code>animationlength</code> value.
	 * 
	 * @param toParent
	 * @param childIndex
	 * @param childWidth
	 * @param yetPresent
	 * @param actors
	 * @param growth
	 * @param animationlength
	 */
	public static void fixOnAdd(Parent toParent, int childIndex,
			float childWidth, boolean yetPresent,
			Map<InternalBoardObject, BoardObjectActor> actors, TreeGrowth growth, float animationlength) {

	}
}
