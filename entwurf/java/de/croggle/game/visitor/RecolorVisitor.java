package de.croggle.game.visitor;

import de.croggle.game.event.BoardEventMessenger;

/**
 *
 * @navassoc 1 - 1 de.croggle.game.event.BoardEventMessenger
 */
public class RecolorVisitor implements BoardObjectVisitor {
private BoardEventMessenger boardMessenger;

public RecolorVisitor(BoardEventMessenger boardMessenger){
}
	
	
}
