package de.croggle.ui.renderer;

import java.util.Collection;
import java.util.Iterator;
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
	public ActorLayout(Map<InternalBoardObject, BoardObjectActor> layout, Board b,
			ActorLayoutConfiguration config, ActorLayoutStatistics statistics) {
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

	public boolean onRemoveTree(BoardObjectActor b) {
		return onRemoveTree(b.getBoardObject());
	}

	/**
	 * 
	 * @param b
	 * @return true, if the actor representing the given
	 *         {@link InternalBoardObject} was successfully removed
	 */
	public boolean onRemoveSingle(InternalBoardObject b) {
		return layout.remove(b) != null;
	}
	
	public boolean onRemoveSingle(BoardObjectActor b) {
		return onRemoveTree(b.getBoardObject());
	}

	/**
	 * 
	 * @param b
	 * @return true, if the actor representing the given
	 *         {@link InternalBoardObject} was successfully removed
	 */
	public boolean onRemoveTree(InternalBoardObject b) {
		return layout.remove(b) != null;
	}
	
	public void onAdd(InternalBoardObject added) {
		
	}

	public Board getBoard() {
		return b;
	}
}
