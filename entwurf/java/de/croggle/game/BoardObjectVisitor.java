package de.croggle.game;

/*
 * 
 */
public interface BoardObjectVisitor {
	
	void visitEgg(Egg egg);
	void visitColoredAlligator(ColoredAlligator alligator);
	void visitAgedAlligator(AgedAlligator alligator);
	//void visitInvisibleAlligator(InvisibleAlligator alligator);
}
