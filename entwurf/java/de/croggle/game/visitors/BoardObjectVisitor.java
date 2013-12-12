package de.croggle.game.visitors;

import de.croggle.game.model.Egg;
import de.croggle.game.model.ColoredAlligator;
import de.croggle.game.model.AgedAlligator;

/**
 * 
 */
public interface BoardObjectVisitor {
	
	void visitEgg(Egg egg);
	void visitColoredAlligator(ColoredAlligator alligator);
	void visitAgedAlligator(AgedAlligator alligator);
}
