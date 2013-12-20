package de.croggle.game.visitor;

import de.croggle.game.event.BoardEventMessenger;

/**
 * A visitor replacing eggs of a certain color with copies of a given 
 * family (subtree).
 * @navassoc 1 - 1 de.croggle.game.event.BoardEventMessenger
 *
 */
public class ReplaceEggVisitor implements BoardObjectVisitor {
private BoardEventMessenger boardMessenger;

public ReplaceEggVisitor(BoardEventMessenger boardMessenger){
}
	
}
