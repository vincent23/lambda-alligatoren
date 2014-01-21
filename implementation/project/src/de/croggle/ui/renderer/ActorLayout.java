package de.croggle.ui.renderer;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.croggle.game.board.Board;
import de.croggle.game.board.InternalBoardObject;

/**
 * A class encapsulating all information that is involved in layouting actors to
 * represent a board. That is:
 * <ul>
 * <li>the layout itself (a collection of {@link BoardActor}s)</li>
 * <li>the {@link Board} on which the layout is based</li>
 * <li>a map to lookup the actor representing a certain
 * {@link InternalBoardObject} in estimated O(1)</li>
 * <li>the {@link ActorLayoutConfiguration configuration} the layout builder
 * used to layout the actors</li>
 * <li>useful {@link ActorLayoutStatistics statistics} generated during the
 * layout process, like e.g. the total width</li>
 * </ul>
 */
public class ActorLayout implements Iterable<BoardObjectActor> {
	private final Map<InternalBoardObject, BoardObjectActor> layout;
	private final Board b;
	private final ActorLayoutStatistics statistics;
	private final ActorLayoutConfiguration config;

	/**
	 * 
	 * @param layout
	 * @param config
	 * @param statistics
	 */
	public ActorLayout(Map<InternalBoardObject, BoardObjectActor> layout,
			Board b, ActorLayoutConfiguration config,
			ActorLayoutStatistics statistics) {
		this.layout = layout;
		this.b = b;
		this.config = config;
		this.statistics = statistics;
	}

	/**
	 * 
	 * @return
	 */
	public Collection<BoardObjectActor> getActors() {
		return layout.values();
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	public BoardObjectActor getActor(InternalBoardObject object) {
		return layout.get(object);
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public InternalBoardObject getBoardObjectByActor(BoardObjectActor b) {
		return b.getBoardObject();
	}

	/**
	 * 
	 * @return
	 */
	public ActorLayoutStatistics getLayoutStatistics() {
		return statistics;
	}

	/**
	 * 
	 * @return
	 */
	public ActorLayoutConfiguration getLayoutConfiguration() {
		return config;
	}

	@Override
	public Iterator<BoardObjectActor> iterator() {
		return layout.values().iterator();
	}

	/**
	 * Calculates which actors need to be altered and in which way to restore
	 * the layout after the given {@link BoardObjectActor} (and its
	 * corresponding {@link InternalBoardObject}) was removed from the
	 * {@link Board}, including its children. The method assumes that the parent
	 * link is still set correctly. Afterwards, it returns the results of the
	 * calculation but leaves it to the caller to apply them. This allows the
	 * code rendering this layout to apply animations on the changes to be made.
	 * 
	 * @param b
	 * @return
	 */
	public boolean onRemoveTree(BoardObjectActor b) {
		return onRemoveTree(b.getBoardObject());
	}

	/**
	 * Calculates which actors need to be altered and in which way to restore
	 * the layout after the given {@link InternalBoardObject} was removed from
	 * the {@link Board}, including all of its children. The method assumes that
	 * the parent link is still set correctly. Afterwards, it returns the
	 * results of the calculation but leaves it to the caller to apply them.
	 * This allows the code rendering this layout to apply animations on the
	 * changes to be made.
	 * 
	 * @param b
	 * @return true, if the actor representing the given
	 *         {@link InternalBoardObject} was successfully removed
	 */
	public boolean onRemoveTree(InternalBoardObject b) {
		return layout.remove(b) != null;
	}

	/**
	 * Calculates which actors need to be altered and in which way to restore
	 * the layout after the given {@link InternalBoardObject} was removed from
	 * the {@link Board}, without its children. The method assumes that the
	 * parent link is still set correctly. Afterwards, it returns the results of
	 * the calculation but leaves it to the caller to apply them. This allows
	 * the code rendering this layout to apply animations on the changes to be
	 * made.
	 * 
	 * @param b
	 * @return true, if the actor representing the given
	 *         {@link InternalBoardObject} was successfully removed
	 */
	public List<ActorDelta> onRemoveSingle(InternalBoardObject b) {
		// TODO
		return null;
	}

	/**
	 * Calculates which actors need to be altered and in which way to restore
	 * the layout after the given {@link BoardObjectActor} (and its
	 * corresponding {@link InternalBoardObject}) was removed from the
	 * {@link Board}, without its children. The method assumes that the parent
	 * link is still set correctly. Afterwards, it returns the results of the
	 * calculation but leaves it to the caller to apply them. This allows the
	 * code rendering this layout to apply animations on the changes to be made.
	 * 
	 * @param b
	 * @return
	 */
	public List<ActorDelta> onRemoveSingle(BoardObjectActor b) {
		// TODO
		return null;
	}

	/**
	 * Calculates which actors need to be altered and in which way to restore
	 * the layout after the given tree of {@link InternalBoardObject}s was added
	 * to the {@link Board} it represents. The method assumes that the parent
	 * link is already set. Afterwards, it returns the results of the
	 * calculation but leaves it to the caller to apply them. This allows the
	 * code rendering this layout to apply animations on the changes to be made.
	 * 
	 * @param added
	 * @return
	 */
	public List<ActorDelta> onAdd(InternalBoardObject added) {
		// TODO
		return null;
	}

	/**
	 * Returns the {@link Board} instance this layout is representing.
	 * 
	 * @return the {@link Board} that is represented by this layout
	 */
	public Board getBoard() {
		return b;
	}
}
