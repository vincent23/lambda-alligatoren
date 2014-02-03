package de.croggle.game.board.operations;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import de.croggle.game.Color;
import de.croggle.game.ColorController;
import de.croggle.game.ColorOverflowException;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;
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
	private BoardObject constellation;
	private Color[] boundColors;
	private Color[] freeColors;

	private ColorOverflowException colorOverflowException;

	private ReplaceEggs(BoardObject constellation, Color eggColor,
			InternalBoardObject bornFamily) {
		this(constellation, eggColor, bornFamily, null, null);
	}

	private ReplaceEggs(BoardObject constellation, Color eggColor,
			InternalBoardObject bornFamily, BoardEventMessenger boardMessenger) {
		this(constellation, eggColor, bornFamily, boardMessenger, null);
	}

	private ReplaceEggs(BoardObject constellation, Color eggColor,
			InternalBoardObject bornFamily, ColorController colorController) {
		this(constellation, eggColor, bornFamily, null, colorController);
	}

	private ReplaceEggs(BoardObject constellation, Color eggColor,
			InternalBoardObject bornFamily, BoardEventMessenger boardMessenger,
			ColorController colorController) {
		this.constellation = constellation;
		this.eggColor = eggColor;
		this.bornFamilyPrototype = bornFamily;
		this.boardMessenger = boardMessenger;
		this.colorController = colorController;

		if (colorController != null) {
			boundColors = CollectBoundColors.collect(bornFamily);
			freeColors = CollectFreeColors.collect(bornFamily);
		}
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
			ColorController colorController) throws ColorOverflowException {
		ReplaceEggs replacer = new ReplaceEggs(constellation, eggColor,
				bornFamily, boardMessenger, colorController);
		constellation.accept(replacer);
		if (replacer.colorOverflowException != null) {
			throw replacer.colorOverflowException;
		}
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
	 * @throws ColorOverflowException
	 */
	public static void replace(BoardObject constellation, Color eggColor,
			InternalBoardObject bornFamily, BoardEventMessenger boardMessenger)
			throws ColorOverflowException {
		ReplaceEggs replacer = new ReplaceEggs(constellation, eggColor,
				bornFamily, boardMessenger);
		constellation.accept(replacer);
		if (replacer.colorOverflowException != null) {
			throw replacer.colorOverflowException;
		}
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
			InternalBoardObject bornFamily, ColorController colorController)
			throws ColorOverflowException {
		ReplaceEggs replacer = new ReplaceEggs(constellation, eggColor,
				bornFamily, colorController);
		constellation.accept(replacer);
		if (replacer.colorOverflowException != null) {
			throw replacer.colorOverflowException;
		}
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
	 * @throws ColorOverflowException
	 */
	public static void replace(BoardObject constellation, Color eggColor,
			InternalBoardObject bornFamily) throws ColorOverflowException {
		ReplaceEggs replacer = new ReplaceEggs(constellation, eggColor,
				bornFamily);
		constellation.accept(replacer);
		if (replacer.colorOverflowException != null) {
			throw replacer.colorOverflowException;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitEgg(Egg egg) {
		if (colorOverflowException != null) {
			return;
		}
		if (egg.getColor().equals(this.eggColor)) {
			InternalBoardObject replacement;
			if (this.colorController != null) {
				replacement = this.bornFamilyPrototype.copy();
				final Color[] locallyBoundColors = findLocallyBoundColors(egg);
				final Color[] globallyBoundColors = findGloballyBoundColors(egg);

				final Set<Color> unusableColors = new HashSet<Color>();
				Collections.addAll(unusableColors, globallyBoundColors);
				Collections.addAll(unusableColors, freeColors);
				Collections.addAll(unusableColors, boundColors);

				final Set<Color> locallyBoundAndFreeColors = new HashSet<Color>();
				Collections.addAll(locallyBoundAndFreeColors,
						locallyBoundColors);
				locallyBoundAndFreeColors.retainAll(Arrays.asList(freeColors));
				for (Color color : locallyBoundAndFreeColors) {
					try {
						final Color newColor = colorController
								.requestColor(unusableColors
										.toArray(new Color[unusableColors
												.size()]));
						ExchangeColor.recolor(constellation, color, newColor,
								boardMessenger);
					} catch (ColorOverflowException e) {
						colorOverflowException = e;
						return;
					}
				}
				final Set<Color> locallyBoundAndBoundColors = new HashSet<Color>();
				Collections.addAll(locallyBoundAndBoundColors,
						locallyBoundColors);
				locallyBoundAndBoundColors
						.retainAll(Arrays.asList(boundColors));
				for (Color color : locallyBoundAndBoundColors) {
					try {
						final Color newColor = colorController
								.requestColor(unusableColors
										.toArray(new Color[unusableColors
												.size()]));
						unusableColors.add(newColor);
						ExchangeColor.recolor(replacement, color, newColor,
								boardMessenger);
					} catch (ColorOverflowException e) {
						colorOverflowException = e;
						return;
					}

				}
			} else {
				replacement = this.bornFamilyPrototype.copy();
			}

			egg.getParent().replaceChild(egg, replacement);

			if (this.boardMessenger != null) {
				this.boardMessenger.notifyHatched(egg, replacement);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		if (colorOverflowException == null) {
			alligator.acceptOnChildren(this);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		if (colorOverflowException == null) {
			alligator.acceptOnChildren(this);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitBoard(Board board) {
		if (colorOverflowException == null) {
			board.acceptOnChildren(this);
		}
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
		return findBoundColorsBelow(egg, constellation);
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
		return findBoundColorsBelow(egg, null);
	}

	private Color[] findBoundColorsBelow(Egg egg, BoardObject topmost) {
		final Set<Color> boundColors = new HashSet<Color>();
		for (Parent parent : GetParentHierarchy.get(egg)) {
			// TODO remove instanceof
			if (parent instanceof ColoredAlligator) {
				final ColoredAlligator coloredAlligator = (ColoredAlligator) parent;
				boundColors.add(coloredAlligator.getColor());
			}
			if (parent == topmost) {
				break;
			}
		}
		return boundColors.toArray(new Color[boundColors.size()]);
	}
}
