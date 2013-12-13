package de.croggle.model.visitor;

import de.croggle.model.AgedAlligator;
import de.croggle.model.ColoredAlligator;
import de.croggle.model.Egg;

/**
 * 
 */
public interface BoardObjectVisitor {
	
	void visitEgg(Egg egg);
	void visitColoredAlligator(ColoredAlligator alligator);
	void visitAgedAlligator(AgedAlligator alligator);
}
