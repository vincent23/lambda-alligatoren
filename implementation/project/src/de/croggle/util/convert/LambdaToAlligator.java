package de.croggle.util.convert;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.croggle.game.Color;
import de.croggle.game.ColorController;
import de.croggle.game.ColorOverflowException;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Alligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;
import de.croggle.game.board.operations.BoardObjectVisitor;
import de.croggle.game.board.operations.RemoveAgedAlligators;

/**
 * A helper class to allow for parsing lambda terms and build their alligator
 * constellations.
 * 
 * @author suluke
 */
public class LambdaToAlligator {

	private static char lambda = '\u03bb'; // λ
	private static String abstractionPrefix = "" + lambda;
	private static String abstractionSeparator = ".";

	private final String bindPrfx;
	private final String bindPstfx;
	private final Deque<UnparsedObject> unparsedDeque;
	private final List<UnparsedObject> unparsedList;
	private final Map<String, Color> colors;
	private final ColorController ccntrl;

	/**
	 * Creates a new LambdaToAlligator instance, initializing internal values
	 * for
	 * <ul>
	 * <li>abstraction prefix</li>
	 * <li>abstraction separator</li>
	 * <li>the linked list of not yet compiled (unparsed) BoardObjects</li>
	 * <li>the map of already assigned colors to variable names</li>
	 * <li>the color controller for retrieving new colors</li>
	 * </ul>
	 */
	private LambdaToAlligator() {
		bindPrfx = abstractionPrefix;
		bindPstfx = abstractionSeparator;
		unparsedDeque = new LinkedList<UnparsedObject>();
		unparsedList = (LinkedList<UnparsedObject>) unparsedDeque;
		ccntrl = new ColorController();
		colors = new HashMap<String, Color>(30);
	}

	/**
	 * Turns a given String of a (valid) lambda calculus term into a
	 * constellation of BoardObjects, places on the board that is returned.
	 * 
	 * @param expr
	 *            a lambda calculus term to be converted
	 * @return a board with the given term's alligator representation
	 */
	public static Board convert(String expr) {
		final LambdaToAlligator converter = new LambdaToAlligator();
		final Board result = new Board();
		converter.boardFromString(result, expr);
		return result;
	}

	/**
	 * Turns a given string into a board object.
	 * 
	 * @param b
	 *            the board to fill with the outcome of the parsed term
	 * @param expr
	 *            the lambda term to parse
	 */
	private void boardFromString(Board b, String expr) {
		expr = expr.replaceAll("\\s+", " ").trim();

		UnparsedObject unp = new UnparsedObject(expr);
		this.unparsedDeque.add(unp);
		b.addChild(unp);

		// stage one parsing
		while (unparsedDeque.element().getClass() == UnparsedObject.class) {
			parseStageOne();
		}

		java.util.Collections.sort(unparsedList);

		// stage two parsing
		while (!unparsedDeque.isEmpty()) {
			parseStageTwo();
		}

		RemoveAgedAlligators.remove(b);
	}

	/**
	 * Parses single parts of a term until there are no untyped UnparsedObjects
	 * left at the beginning of the List "unparsed"
	 */
	private void parseStageOne() {
		if (unparsedDeque.isEmpty()
				|| unparsedDeque.element().getClass() != UnparsedObject.class)
			return;

		UnparsedObject o = unparsedDeque.element();
		String e = o.getExpr();
		if (e.startsWith(bindPrfx)) {
			splitAbstraction(o);
		} else if (e.startsWith("(")) {
			splitBraces(o);
		} else {
			splitVariable(o);
		}
	}

	/**
	 * Expects the list of unparsed objects to be sorted, with first braces
	 * coming first, followed by abstractions and variables at the end. Will
	 * turn all mentioned UnparsedExpression types step-by-step into their
	 * alligator counterparts.
	 */
	private void parseStageTwo() {
		if (unparsedDeque.isEmpty())
			return;
		assert (unparsedDeque.element().getClass() != UnparsedObject.class);

		UnparsedObject o = unparsedDeque.element();
		if (o.getClass() == AbstractionBegin.class) {
			buildAbstraction((AbstractionBegin) o);
		} else if (o.getClass() == OpeningBrace.class) {
			buildBraces((OpeningBrace) o);
		} else if (o.getClass() == Variable.class) {
			buildVariable((Variable) o);
		} else {
			throw new IllegalArgumentException("Token too much: " + o.getExpr());
		}
	}

	/**
	 * Expects the abstraction prefix (λ) as argument and combines the following
	 * Variable, AbstractionSeparator and (Egg|Alligator) to a colored alligator
	 * 
	 * @param o
	 *            the begin of the abstraction
	 */
	private void buildAbstraction(AbstractionBegin o) {
		Parent p = o.getParent();

		InternalBoardObject nextChild;

		// get the bound variable
		Variable var;
		nextChild = p.getChildAfter(o);
		if (nextChild.getClass() != Variable.class) {
			throw new IllegalArgumentException(
					"Not a variable name bound by lambda abstraction");
		} else {
			var = (Variable) p.getChildAfter(o);
		}

		// get the abstraction separator
		AbstractionSeparator sep;
		nextChild = p.getChildAfter(var);
		if (nextChild.getClass() != AbstractionSeparator.class) {
			throw new IllegalArgumentException(
					"No binding seperator ('.') found for abstraction");
		} else {
			sep = (AbstractionSeparator) p.getChildAfter(var);
		}

		// instantiate the alligator
		Color c = strToColor(var.getExpr());
		ColoredAlligator abstraction = new ColoredAlligator(true, true, c, true);

		// get the bound terms
		int pos = p.getChildPosition(sep) + 1;
		InternalBoardObject nextBound;
		Iterator<InternalBoardObject> it = p.iterator(pos);
		if (!it.hasNext()) {
			throw new IllegalArgumentException(
					"No bound term found for abstraction");
		}
		while (it.hasNext()) {
			nextBound = it.next();
			// neither Egg (still as variable) nor parenthesis (already parsed
			// as AgedAlligators) need to be considered here
			if (!(nextBound instanceof Variable)
					&& !(nextBound instanceof Alligator)
					&& (nextBound.getClass() != AbstractionBegin.class)
					&& (nextBound.getClass() != AbstractionSeparator.class)) {
				throw new IllegalArgumentException("Illegal term bound: "
						+ nextBound.getClass().getSimpleName());
			} /*
			 * Not needed, I guess. else if (nextBound.getClass() ==
			 * AbstractionBegin.class) { buildAbstraction((AbstractionBegin)
			 * nextBound); boundTerm = (ColoredAlligator) p.getNextChild(sep);
			 * // re-get the child, which was transformed in the mean time }
			 */else {
				abstraction.addChild(nextBound);
			}
		}

		p.replaceChild(o, abstraction);
		unparsedDeque.remove(o); // the abstraction

		p.removeChild(var);
		unparsedDeque.remove(var); // has turned into a color

		p.removeChild(sep);
		unparsedDeque.remove(sep); // part of the abstraction

		for (InternalBoardObject child : abstraction) {
			p.removeChild(child);
		}
		// boundTerm is probably an alligator, or will turn into an egg later
	}

	/**
	 * Turns the given OpeningBrace and all following BoardObjects of the same
	 * parent up to the matching ClosingBrace into an aged alligator.
	 * 
	 * @param o
	 *            the opening brace of the part to be turned into an aged
	 *            alligator
	 */
	private void buildBraces(OpeningBrace o) {
		Parent p = o.getParent();
		Iterator<InternalBoardObject> i = p.iterator(p.getChildPosition(o) + 1);
		InternalBoardObject current = null;
		int open = 0;
		while (!((current = i.next()).getClass() == ClosingBrace.class && open == 0)) {
			if (current.getClass() == OpeningBrace.class)
				open++;
			else if (current.getClass() == ClosingBrace.class)
				open--;

			if (!i.hasNext()) {
				throw new IllegalArgumentException("Brace not closed");
			}
		}

		ClosingBrace close = (ClosingBrace) current;

		AgedAlligator braces = new AgedAlligator(true, true);
		i = p.iterator(p.getChildPosition(o) + 1);
		while (i.hasNext() && !(current = i.next()).equals(close)) {
			braces.addChild(current);
		}
		// dedicated loop to not confuse the iterator
		for (InternalBoardObject bo : braces) {
			p.removeChild(bo);
		}
		p.removeChild(close);
		p.replaceChild(o, braces);

		unparsedDeque.remove(o);
		unparsedDeque.remove(close);
	}

	/**
	 * Expects UNBOUND variables, to turn them into eggs.
	 * 
	 * @param o
	 *            the (unbound!) variable to be turned into an egg
	 */
	private void buildVariable(Variable o) {
		Parent p = o.getParent();
		Color c = strToColor(o.getExpr());
		Egg e = new Egg(true, true, c, true);
		p.replaceChild(o, e);
		unparsedDeque.remove(o);
	}

	/**
	 * Translate a string into a color using a color controller.
	 * 
	 * @param s
	 *            the string to be translated into a color
	 * @return a color that will from now on be associated with the given string
	 */
	private Color strToColor(String s) {
		Color c;
		if (colors.containsKey(s)) {
			c = colors.get(s);
		} else {
			try {
				c = ccntrl.requestColor(colors.values().toArray(
						new Color[colors.size()]));
				colors.put(s, c);
			} catch (ColorOverflowException ex) {
				throw new IllegalArgumentException(
						"Too many variable names in given term");
			}
		}
		return c;
	}

	/**
	 * Extracts the first occurrence of a lambda abstraction (starting at the
	 * beginning of the unparsed term) and replaces it with the abstraction
	 * begin, the bound variable string, the abstraction separator and the rest.
	 * 
	 * @param o
	 *            the UnparsedExpression with a lambda abstraction at the
	 *            beginning
	 */
	private void splitAbstraction(UnparsedObject o) {
		String var;
		String rest;
		String e = o.getExpr();
		assert (e.startsWith(bindPrfx));

		int separatorIndex = e.indexOf(bindPstfx, bindPrfx.length());

		AbstractionBegin ab = new AbstractionBegin();
		AbstractionSeparator as = new AbstractionSeparator();
		var = e.substring(bindPrfx.length(), separatorIndex);
		rest = e.substring(separatorIndex + bindPstfx.length());

		UnparsedObject varObj = new UnparsedObject(var);

		Parent p = o.getParent();
		int pos = p.getChildPosition(o);
		if (!rest.trim().equals("")) {
			UnparsedObject restObj = new UnparsedObject(rest);
			p.insertChild(restObj, pos + 1);
			unparsedDeque.addFirst(restObj);
		}

		p.replaceChild(o, as);
		unparsedDeque.addLast(as);
		unparsedDeque.remove(o);

		p.insertChild(varObj, pos);
		unparsedDeque.addFirst(varObj);

		p.insertChild(ab, pos);
		unparsedDeque.addLast(ab);

	}

	/**
	 * Extracts the first occurrence of a set of braces (starting at the
	 * beginning of the uparsed term) from the given UnparsedObject and replaces
	 * it with starting braces, the brace body, a closing brace and the
	 * remaining unparsed term.
	 * 
	 * @param o
	 *            the UnparsedObject to be replaced
	 */
	private void splitBraces(UnparsedObject o) {
		String body;
		String rest;
		String e = o.getExpr();
		assert (e.charAt(0) == '(');
		int open = 0;
		int i;
		for (i = 1; !(e.charAt(i) == ')' && open == 0); i++) {
			if (e.charAt(i) == '(')
				open++;
			else if (e.charAt(i) == ')')
				open--;
			if (i == e.length() - 1) {
				throw new IllegalArgumentException("Brace not closed");
			}
		}

		body = e.substring(1, i);
		rest = e.substring(i + 1);
		Parent p = o.getParent();
		int pos = p.getChildPosition(o);

		OpeningBrace ob = new OpeningBrace();
		ClosingBrace cb = new ClosingBrace();

		if (!rest.trim().equals("")) {
			UnparsedObject restObj = new UnparsedObject(rest);
			p.insertChild(restObj, pos + 1);
			unparsedDeque.addFirst(restObj);
		}

		p.replaceChild(o, cb);
		unparsedDeque.addLast(cb);
		unparsedDeque.remove(o);

		if (!body.trim().equals("")) {
			UnparsedObject bodyObj = new UnparsedObject(body);
			p.insertChild(bodyObj, pos);
			unparsedDeque.addFirst(bodyObj);
		}

		p.insertChild(ob, pos);
		unparsedDeque.addLast(ob);

	}

	/**
	 * Extracts the first occuring variable (starting at the beginning of the
	 * unparsed term) from the given UnparsedObject and replaces it with it and
	 * the remaining unparsed term.
	 * 
	 * @param o
	 *            the UnparsedObject to be replaced with a variable and the
	 *            remaining unparsed term
	 */
	private void splitVariable(UnparsedObject o) {
		Parent p = o.getParent();
		String e = o.getExpr();
		int end = e.length();
		int elen = e.length();
		int i = e.indexOf('(');
		end = Math.min(end, i < 0 ? elen : i);
		i = e.indexOf(')');
		end = Math.min(end, i < 0 ? elen : i);
		i = e.indexOf(bindPrfx);
		end = Math.min(end, i < 0 ? elen : i);
		i = e.indexOf(bindPstfx);
		end = Math.min(end, i < 0 ? elen : i);
		i = e.indexOf(' ');
		end = Math.min(end, i < 0 ? elen : i);

		String var = e.substring(0, end);
		Variable varObj = new Variable(var);

		String rest = e.substring(end);
		if (!rest.trim().equals("")) {
			UnparsedObject restObj = new UnparsedObject(rest);
			this.unparsedDeque.addFirst(restObj);
			p.insertChild(restObj, p.getChildPosition(o) + 1);
		}

		p.replaceChild(o, varObj);
		this.unparsedDeque.remove(o);
		this.unparsedDeque.addLast(varObj);
	}

	/**
	 * Base class of all unparsed BoardObjects. Represents a typeless unparsed
	 * part of a lambda term.
	 * 
	 */
	private class UnparsedObject implements InternalBoardObject,
			Comparable<UnparsedObject> {
		private Parent parent;
		private String expr;

		public UnparsedObject() {
		}

		public UnparsedObject(String expr) {
			this.expr = expr.trim();
			assert (!this.expr.equals(""));
		}

		public UnparsedObject(UnparsedObject o) {
			this.parent = o.parent;
			this.expr = o.expr;
		}

		/**
		 * Get the unparsed part of the term as string this object represents.
		 * 
		 * @return the unparsed expression string
		 */
		public String getExpr() {
			return expr;
		}

		public void setExpr(String expr) {
			this.expr = expr.trim();
			assert (!this.expr.equals(""));
		}

		@Override
		public Parent getParent() {
			return this.parent;
		}

		@Override
		public void setParent(Parent parent) {
			this.parent = parent;
		}

		@Override
		public boolean isMovable() {
			return true;
		}

		@Override
		public boolean isRemovable() {
			return true;
		}

		@Override
		public InternalBoardObject copy() {
			return new UnparsedObject(this);
		}

		@Override
		public void accept(BoardObjectVisitor visitor) {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean match(BoardObject b) {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean matchWithRecoloring(BoardObject other,
				Map<Color, Color> recoloring) {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public int compareTo(UnparsedObject arg0) {
			if (arg0.getClass() == UnparsedObject.class) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	/**
	 * A class representing the begin of a lambda abstraction.
	 * 
	 */
	private class AbstractionBegin extends UnparsedObject {
		public AbstractionBegin() {
			super(bindPrfx);
		}

		@Override
		public void setExpr(String expr) {
			assert (false);
		}

		@Override
		public int compareTo(UnparsedObject arg0) {
			if (arg0.getClass() == UnparsedObject.class
					|| arg0.getClass() == OpeningBrace.class
					|| arg0.getClass() == ClosingBrace.class) {
				return 1;
			} else if (arg0.getClass() == AbstractionBegin.class) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	/**
	 * Class representing the separation of the bound variable from the bound
	 * term in a lambda abstraction
	 * 
	 */
	private class AbstractionSeparator extends UnparsedObject {
		public AbstractionSeparator() {
			super(bindPstfx);
		}

		@Override
		public void setExpr(String expr) {
			assert (false);
		}

		@Override
		public int compareTo(UnparsedObject arg0) {
			if (arg0.getClass() == AbstractionSeparator.class) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	/**
	 * A class representing an opening brace in a lambda term.
	 * 
	 */
	private class OpeningBrace extends UnparsedObject {
		public OpeningBrace() {
			super("(");
		}

		@Override
		public void setExpr(String expr) {
			assert (false);
		}

		@Override
		public int compareTo(UnparsedObject arg0) {
			if (arg0.getClass() == UnparsedObject.class) {
				return 1;
			} else if (arg0.getClass() == OpeningBrace.class) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	/**
	 * Class representing a closing brace in a lambda term.
	 * 
	 */
	private class ClosingBrace extends UnparsedObject {
		public ClosingBrace() {
			super(")");
		}

		@Override
		public void setExpr(String expr) {
			assert (false);
		}

		@Override
		public int compareTo(UnparsedObject arg0) {
			if (arg0.getClass() == UnparsedObject.class
					|| arg0.getClass() == OpeningBrace.class) {
				return 1;
			} else if (arg0.getClass() == ClosingBrace.class) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	/**
	 * Class representing all variable(-names) that can occur in a lambda term,
	 * i.e. bound and unbound variables.
	 * 
	 */
	private class Variable extends UnparsedObject {
		public Variable(String name) {
			this.setExpr(name);
		}

		@Override
		public int compareTo(UnparsedObject arg0) {
			if (arg0.getClass() == Variable.class) {
				return 0;
			} else {
				return 1;
			}
		}
	}
}
