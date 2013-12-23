package de.croggle.game.visitor;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.event.BoardEventMessenger;

/**
 * A visitor for replacing occurences of one color in a family with another
 * color.
 * 
 * @navassoc 1 - 1 de.croggle.game.event.BoardEventMessenger
 */
public class RecolorVisitor implements BoardObjectVisitor {
	private BoardEventMessenger boardMessenger;

	private RecolorVisitor(BoardObject family, Color oldColor, Color newColor,
			BoardEventMessenger boardMessenger) {
	}

	/**
	 * Recolor all alligators and eggs in <code>family</code> which have the
	 * color <code>oldColor</code> with <code>newColor</code>.
	 * 
	 * @param family
	 *            the family to recolor
	 * @param oldColor
	 *            the color to replaced
	 * @param newColor
	 *            the color used for replacing the old color
	 * @param boardMessenger
	 *            the messenger used for notifying listeners about the
	 *            recoloring
	 */
	public static void recolor(BoardObject family, Color oldColor,
			Color newColor, BoardEventMessenger boardMessenger) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitEgg(Egg egg) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitBoard(Board board) {

	}
}
