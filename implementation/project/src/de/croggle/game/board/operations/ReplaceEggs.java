package de.croggle.game.board.operations;

import de.croggle.game.Color;
import de.croggle.game.ColorController;
import de.croggle.game.ColorOverflowException;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.event.BoardEventMessenger;

/**
 * A visitor replacing eggs of a certain color with copies of a given family
 * (subtree). Also offers functions to replace in a semantically correct way
 * (with variable renaming).
 */
public class ReplaceEggs implements BoardObjectVisitor {
	private final BoardEventMessenger boardMessenger;
	private final InternalBoardObject bornFamilyPrototype;
	private final Color eggColor;
	private final ColorController colorController;

	private ReplaceEggs(Color eggColor, InternalBoardObject bornFamily) {
		this.eggColor = eggColor;
		this.bornFamilyPrototype = bornFamily;
		this.boardMessenger = null;
		this.colorController = null;
	}

	private ReplaceEggs(Color eggColor, InternalBoardObject bornFamily,
			BoardEventMessenger boardMessenger) {
		this.eggColor = eggColor;
		this.bornFamilyPrototype = bornFamily;
		this.boardMessenger = boardMessenger;
		this.colorController = null;
	}

	private ReplaceEggs(Color eggColor, InternalBoardObject bornFamily,
			ColorController colorController) {
		this.eggColor = eggColor;
		this.bornFamilyPrototype = bornFamily;
		this.boardMessenger = null;
		this.colorController = colorController;
	}

	private ReplaceEggs(Color eggColor, InternalBoardObject bornFamily,
			BoardEventMessenger boardMessenger, ColorController colorController) {
		this.eggColor = eggColor;
		this.bornFamilyPrototype = bornFamily;
		this.boardMessenger = boardMessenger;
		this.colorController = colorController;
	}

	/**
	 * Replaces all eggs below <code>parent</code>, which share it's color, with
	 * a copy of <code>bornFamily</code>. Correct recoloring of the newly
	 * inserted families is also performed, if necessary. When an egg is
	 * replaced, an onEat event is sent through <code>boardMessenger</code>.
	 * 
	 * @param constellation
	 *            the (sub) constellation whose child eggs are to be replaced
	 * @param eggColor
	 *            the color that the eggs to be replaced have
	 * @param bornFamily
	 *            the family with which eggs are replaced
	 * @param boardMessenger
	 *            the messenger used for sending events when eggs are replaced
	 * @param colorController
	 *            the color controller used for recoloring
	 * @throws ColorOverflowException
	 *             if recoloring occurs and there is no color available
	 */
	public static void replace(BoardObject constellation, Color eggColor,
			InternalBoardObject bornFamily, BoardEventMessenger boardMessenger,
			ColorController colorController) {
		ReplaceEggs replacer = new ReplaceEggs(eggColor, bornFamily,
				boardMessenger, colorController);
		constellation.accept(replacer);
	}

	/**
	 * Replaces all eggs below <code>parent</code>, which share it's color, with
	 * a copy of <code>bornFamily</code>. While replacing, there will be no
	 * checks if the recoloring is semantically incorrect. To perform
	 * semantically correct recoloring, use this method with the
	 * <code>colorController</code> argument. When an egg is replaced, an onEat
	 * event is sent through <code>boardMessenger</code>.
	 * 
	 * @param constellation
	 *            the (sub) constellation whose child eggs are to be replaced
	 * @param eggColor
	 *            the color that the eggs to be replaced have
	 * @param bornFamily
	 *            the family with which eggs are replaced
	 * @param boardMessenger
	 *            the messenger used for sending events when eggs are replaced
	 */
	public static void replace(BoardObject constellation, Color eggColor,
			InternalBoardObject bornFamily, BoardEventMessenger boardMessenger) {
		ReplaceEggs replacer = new ReplaceEggs(eggColor, bornFamily,
				boardMessenger);
		constellation.accept(replacer);
	}

	/**
	 * Replaces all eggs below <code>parent</code>, which share it's color, with
	 * a copy of <code>bornFamily</code>. Correct recoloring of the newly
	 * inserted families is also performed, if necessary. To receive events in
	 * case an egg is replaced, use this method with the
	 * <code>boardMessenger</code> argument.
	 * 
	 * @param constellation
	 *            the (sub) constellation whose child eggs are to be replaced
	 * @param eggColor
	 *            the color that the eggs to be replaced have
	 * @param bornFamily
	 *            the family with which eggs are replaced
	 * @param colorController
	 *            the color controller used for recoloring
	 * @throws ColorOverflowException
	 *             if recoloring occurs and there is no color available
	 */
	public static void replace(BoardObject constellation, Color eggColor,
			InternalBoardObject bornFamily, ColorController colorController) {
		ReplaceEggs replacer = new ReplaceEggs(eggColor, bornFamily,
				colorController);
		constellation.accept(replacer);
	}

	/**
	 * Replaces all eggs below <code>parent</code>, which share it's color, with
	 * a copy of <code>bornFamily</code>. While replacing, there will be no
	 * checks if the recoloring is semantically incorrect. To perform
	 * semantically correct recoloring, use this method with the
	 * <code>colorController</code> argument. To receive events in case an egg
	 * is replaced, use this method with the <code>boardMessenger</code>
	 * argument.
	 * 
	 * @param constellation
	 *            the (sub) constellation whose child eggs are to be replaced
	 * @param eggColor
	 *            the color that the eggs to be replaced have
	 * @param bornFamily
	 *            the family with which eggs are replaced
	 */
	public static void replace(BoardObject constellation, Color eggColor,
			InternalBoardObject bornFamily) {
		ReplaceEggs replacer = new ReplaceEggs(eggColor, bornFamily);
		constellation.accept(replacer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitEgg(Egg egg) {
		if (egg.getColor().equals(this.eggColor)) {
			InternalBoardObject replacement;
			if (this.colorController != null) {
				// TODO implement correct recoloring
				replacement = this.bornFamilyPrototype.copy();
			} else {
				replacement = this.bornFamilyPrototype.copy();
			}

			egg.getParent().replaceChild(egg, replacement);

			if (this.boardMessenger != null) {
				this.boardMessenger.notifyReplace(egg, replacement);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
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

	/**
	 * Returns all colors of colored alligators above <code>egg</code> but below
	 * <code>parent</code>. This is equivalent to the colors which are bound in
	 * the current subterm for the location of the egg.
	 * 
	 * @param egg
	 *            the egg for which the bound colors should be returned
	 * @return the set of bound colors
	 */
	private Color[] findLocallyBoundColors(Egg egg) {
		return null;
	}

	/**
	 * Returns all colors of colored alligators above <code>egg</code>. This is
	 * equivalent to the colors which are bound for the location of the egg.
	 * 
	 * @param egg
	 *            the egg for which the bound colors should be returned
	 * @return the set of bound colors
	 */
	private Color[] findGloballyBoundColors(Egg egg) {
		return null;
	}
}
