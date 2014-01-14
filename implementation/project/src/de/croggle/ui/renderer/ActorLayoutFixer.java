package de.croggle.ui.renderer;

import java.util.Map;

import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;

public class ActorLayoutFixer {

	/**
	 * Corrects layout issues that were caused by removing a child from the
	 * tree.
	 * 
	 * @param removed
	 *            the {@link InternalBoardObject} removed from the board
	 * @param fromParent
	 *            the parent of the removed object (in case it was unset)
	 * @param actors
	 *            the BoardObject &lt;&gt; BoardObjectActor map currently used
	 *            to display the tree
	 * @param config
	 *            the {@link ActorLayoutConfiguration} to used to maintain the
	 *            tree layout
	 */
	public static void fixOnRemove(InternalBoardObject removed,
			Parent fromParent,
			Map<InternalBoardObject, BoardObjectActor> actors,
			ActorLayoutConfiguration config) {

	}

	/**
	 * Corrects layout issues that were caused by adding a subtree to the board.
	 * This method is <strong>NOT</strong> responsible for creating new actors
	 * representing the newly added subtree, but will take care of inserting
	 * them correctly into the tree.
	 * 
	 * @param added
	 *            the added {@link InternalBoardObject}
	 * @param toParent
	 *            the parent, to which the BoardObject was or is to be added
	 * @param actors
	 *            the BoardObject &lt;&gt; BoardObjectActor map currently used
	 *            to display the tree
	 * @param config
	 *            the {@link ActorLayoutConfiguration} to used to maintain the
	 *            tree layout 
	 */
	public static void fixOnAdd(InternalBoardObject added, Parent toParent,
			Map<InternalBoardObject, BoardObjectActor> actors,
			ActorLayoutConfiguration config) {

	}
}
