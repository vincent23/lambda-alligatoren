package de.croggle.game.visitor;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Board;

/**
 * 
 */
public interface BoardObjectVisitor {
	
	void visitEgg(Egg egg);
	void visitColoredAlligator(ColoredAlligator alligator);
	void visitAgedAlligator(AgedAlligator alligator);
	void visitBoard(Board board);
}
