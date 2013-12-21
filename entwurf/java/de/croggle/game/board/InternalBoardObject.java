package de.croggle.game.board;


/**
 * Special type of BoardObject, whose specific attribute is, that its not the uppermost BoardObject.
 */
public interface InternalBoardObject extends BoardObject {
	
	/**
	 * Having internal board objects returning families instead of other 
	 * (internal) board objects has multiple advantages:
	 * <ol>
	 * <li>Less casting: Usually, visitors will only need access to the direct parent, e.g. to substitute the child
	 * they are in with something else. Therefore they would need the Family's features either way, so why not
	 * give them the family directly?</li>
	 * <li>Limit upward traversal: By making it hard (not impossible) to iterate the syntax tree
	 * in upwards direction, unnormal usage of the tree structure is discouraged from the beginning.</li>
	 * <li>Root can be a parent without having to offer itsef a parent: This is pretty self-explaining I think.
	 * I cannot think of a better way to accomplish this.</li>
	 * </ol>
	 * 
	 */
	public Parent getParent();

    /**
     * Gets whether the object is protected from user interactions involving colors or not.
     * @return true if the object can be recolored, otherwise false
     */
    public boolean isRecolorable();

    /**
     * Gets whether the object is protected from the user moving it or not.
     * @return true if the object can be moved, otherwise false
     */
    public boolean isMovable();

    /**
     * Gets whether the object is protected being removed from the board or not.
     * @return true if the object can be removed, otherwise false
     */
    public boolean isRemovable();
}
