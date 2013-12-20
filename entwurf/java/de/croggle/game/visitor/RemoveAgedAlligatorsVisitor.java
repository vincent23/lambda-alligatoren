package de.croggle.game.visitor;

/**
 * A visitor looking for aged alligators, which are not nessesary 
 * because their presence does not change the order of evaluation within the term.
 * @navassoc 1 - 1 de.croggle.game.event.BoardEventMessenger
 *
 */
public class RemoveAgedAlligatorsVisitor implements BoardObjectVisitor {
private BoardEventMessenger boardMessenger;

public RemoveAgedAlligatorsVisitor(BoardEventMessenger boardMessenger){
}
	
}
