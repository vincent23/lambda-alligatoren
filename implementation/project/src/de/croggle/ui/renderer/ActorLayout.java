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
			Board b, ActorLayoutConfiguration config) {
		this.layout = layout;
		this.b = b;
		this.config = config;
		statistics = new ActorLayoutStatistics(this);
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
	
	public boolean removeActor(BoardObjectActor actor) {
		if (actor == null) {
			return false;
		}
		return layout.remove(actor.getBoardObject()) != null;
	}
	
	public void addActor(BoardObjectActor actor) {
		layout.put(actor.getBoardObject(), actor);
	}

	@Override
	public Iterator<BoardObjectActor> iterator() {
		return layout.values().iterator();
	}

	/**
	 * Calculates which actors need to be altered and in which way to restore
	 * the layout after this layout's board has changed. Afterwards, it returns the results of the
	 * calculation but leaves it to the caller to apply them. This allows the
	 * code rendering this layout to apply animations on the changes to be made.
	 * 
	 * @return
	 */
	public List<ActorDelta> getDeltasToFix() {
		return ActorLayoutFixer.getDeltas(this, b);
	}

	/**
	 * Returns the {@link Board} instance this layout is representing.
	 * 
	 * @return the {@link Board} that is represented by this layout
	 */
	public Board getBoard() {
		return b;
	}
	
	public int size() {
		return layout.size();
	}
}
