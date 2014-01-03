package de.croggle.game.board.operations;

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
 */
public class ExchangeColor implements BoardObjectVisitor {
	private BoardEventMessenger boardMessenger;

	private final Color oldColor;
	private final Color newColor;

	private ExchangeColor(Color oldColor, Color newColor,
			BoardEventMessenger boardMessenger) {
		this.boardMessenger = boardMessenger;
		this.newColor = newColor;
		this.oldColor = oldColor;
	}

	private ExchangeColor(Color oldColor, Color newColor) {
		this.boardMessenger = null;
		this.newColor = newColor;
		this.oldColor = oldColor;
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
	 *            the messenger used for notifying listeners about the recolored
	 *            BoardObjects
	 */
	public static void recolor(BoardObject family, Color oldColor,
			Color newColor, BoardEventMessenger boardMessenger) {
		ExchangeColor colorExchanger = new ExchangeColor(oldColor, newColor,
				boardMessenger);
		family.accept(colorExchanger);
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
	 */
	public static void recolor(BoardObject family, Color oldColor,
			Color newColor) {
		ExchangeColor colorExchanger = new ExchangeColor(oldColor, newColor);
		family.accept(colorExchanger);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitEgg(Egg egg) {
		if (egg.getColor().equals(this.oldColor)) {
			egg.setColor(this.newColor);
			if (this.boardMessenger != null) {
				this.boardMessenger.notifyObjectRecolored(egg);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		if (alligator.getColor().equals(this.oldColor)) {
			alligator.setColor(this.newColor);
			if (this.boardMessenger != null) {
				this.boardMessenger.notifyObjectRecolored(alligator);
			}
		}
		alligator.acceptOnChildren(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		alligator.acceptOnChildren(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitBoard(Board board) {
		board.acceptOnChildren(this);
	}
}
