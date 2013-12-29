package de.croggle.util.convert;

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
import de.croggle.game.visitor.BoardObjectVisitor;
import de.croggle.game.visitor.RemoveAgedAlligatorsVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A helper class to allow for parsing lambda terms and build their alligator constellations.
 * 
 * @author suluke
 */
public class LambdaToAlligator {
	
	private static char lambda = '\u03bb'; //λ
	private static String bindPrefix = "" + lambda;
	private static String bindPostfix = ".";
	
	private String bindPrfx;
	private String bindPstfx;
	private List<UnparsedObject> unparsed;
	private Map<String, Color> colors;
	//private Board b;
	private ColorController ccntrl;
	
	private LambdaToAlligator() {
		bindPrfx = bindPrefix;
		bindPstfx = bindPostfix;
		unparsed = new ArrayList<UnparsedObject>();
		ccntrl = new ColorController();
		colors = new HashMap<String, Color>(30);
	}
	
	/**
	 * 
	 * @param expr
	 * @return 
	 */
	public static Board convert(String expr) {
		LambdaToAlligator converter = new LambdaToAlligator();
		Board result = new Board();
		converter.boardFromString(result, expr);
		return result;
	}
	
	/**
	 * Turns a given string into a board object.
	 * 
	 * @param b the board to fill with the outcome of the parsed term
	 * @param expr the lambda term to parse
	 */
	private void boardFromString(Board b, String expr) {
		expr = expr.replaceAll("\\s+", " ").trim();
		
		//this.b = b;
		UnparsedObject unp = new UnparsedObject(expr);
		this.unparsed.add(unp);
		b.addChild(unp);
		
		// stage one parsing
		while (unparsed.get(0).getClass() == UnparsedObject.class) {
			parseStageOne();
		}
		
		java.util.Collections.sort(unparsed);
		
		// stage two parsing
		while (!unparsed.isEmpty()) {
			parseStageTwo();
		}
		
		RemoveAgedAlligatorsVisitor.remove(b);
	}
	
	/**
	 * Parses single parts of a term until there are no untyped UnparsedObjects left
	 * at the beginning of the List "unparsed"
	 */
	private void parseStageOne() {
		if (unparsed.isEmpty() || unparsed.get(0).getClass() != UnparsedObject.class)
			return;
		
		UnparsedObject o = unparsed.get(0);
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
	 * Expects "unparsed" to be sorted, with first braces coming first, followed by abstractions and variables at the end.
	 * Will turn all mentioned UnparsedExpression types step-by-step into their alligator counterparts.
	 */
	private void parseStageTwo() {
		if (unparsed.isEmpty())
			return;
		assert(unparsed.get(0).getClass() != UnparsedObject.class);
		
		UnparsedObject o = unparsed.get(0);
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
	 * Expects the abstraction prefix (λ) as argument and combines
	 * the following Variable, AbstractionSeparator and (Egg|Alligator) to a colored alligator
	 * 
	 * @param o 
	 */
	private void buildAbstraction(AbstractionBegin o) {
		Parent p = o.getParent();
		
		InternalBoardObject nextChild;
		
		Variable var;
		nextChild = p.getNextChild(o);
		if (nextChild.getClass() != Variable.class) {
			throw new IllegalArgumentException("Not a variable name bound by lambda abstraction");
		} else {
			var = (Variable) p.getNextChild(o);
		}
		
		AbstractionSeparator sep;
		nextChild = p.getNextChild(var);
		if (nextChild.getClass() != AbstractionSeparator.class) {
			throw new IllegalArgumentException("No binding seperator ('.') found for abstraction");
		} else {
			sep = (AbstractionSeparator) p.getNextChild(var);
		}
		
		InternalBoardObject boundTerm;
		nextChild = p.getNextChild(sep);
		// neither Egg (still as variable) nor parenthesis (already parsed as AgedAlligators) nor 
		
		if (!(nextChild instanceof Variable) && !(nextChild instanceof Alligator) && (nextChild.getClass() != AbstractionBegin.class)) {
			throw new IllegalArgumentException("No binding seperator ('.') found for abstraction");
		} else if (nextChild.getClass() == AbstractionBegin.class) {
			buildAbstraction((AbstractionBegin) nextChild);
			boundTerm = (ColoredAlligator) p.getNextChild(sep); // re-get the child, which was transformed in the mean time
		} else {
			boundTerm = (InternalBoardObject) nextChild;
		}
		
		Color c = strToColor(var.getExpr());
		ColoredAlligator abstraction = new ColoredAlligator(p, true, true, c, true);
		abstraction.addChild(boundTerm);
		p.replaceChild(o, abstraction);
		p.removeChild(var);
		p.removeChild(sep);
		p.removeChild(boundTerm);
		
		unparsed.remove(o); // the abstraction
		unparsed.remove(var); // has turned into a color
		unparsed.remove(sep); // part of the abstraction
		// boundTerm is probably an alligator, or will turn into an egg later
	}
	
	/**
	 * 
	 * @param o the opening brace to be turned into an aged alligator
	 */
	private void buildBraces(OpeningBrace o) {
		Parent p = o.getParent();
		Iterator<InternalBoardObject> i = p.iterator(p.getChildPosition(o) + 1);
		InternalBoardObject current = null;
		int open = 0;
		while(!((current = i.next()).getClass() == ClosingBrace.class && open == 0)) {
			if (current.getClass() == OpeningBrace.class)
				open++;
			else if (current.getClass() == ClosingBrace.class)
				open--;
			
			if (!i.hasNext()) {
				throw new IllegalArgumentException("Brace not closed");
			}
		}
		
		ClosingBrace close = (ClosingBrace) current;
		
		AgedAlligator braces = new AgedAlligator(p, true, true);
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
		
		unparsed.remove(o);
		unparsed.remove(close);
	}
	
	/**
	 * Expects UNBOUND variables, to turn them into eggs.
	 * 
	 * @param o the (unbound!) variable to be turned into an egg
	 */
	private void buildVariable(Variable o) {
		Parent p = o.getParent();
		Color c = strToColor(o.getExpr());
		Egg e = new Egg(p, true, true, c, true);
		p.replaceChild(o, e);
		unparsed.remove(o);
	}
	
	/**
	 * Translate a string into a color using a color controller.
	 * @param s the string to be translated into a color
	 * @return a color that will from now on be associated with the given string
	 */
	private Color strToColor(String s) {
		Color c;
		if (colors.containsKey(s)) {
			c = colors.get(s);
		} else {
			try {
				c = ccntrl.requestColor(colors.values().toArray(new Color[colors.size()]));
				colors.put(s, c);
			} catch (ColorOverflowException ex) {
				throw new IllegalArgumentException("Too many variable names in given term");
			}
		}
		return c;
	}
	
	/**
	 * Extracts the first occuring lambda abstarction (starting at the beginning of the uparsed term)
	 * and replaces it with the abstraction begin, the bound variable string, the 
	 * 
	 * @param o 
	 */
	private void splitAbstraction(UnparsedObject o) {
		String var;
		String rest;
		String e = o.getExpr();
		assert(e.startsWith(bindPrfx));
		
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
			unparsed.add(0, restObj);
		}
		
		p.replaceChild(o, as);
		unparsed.add(unparsed.size(), as);
		unparsed.remove(o);
		
		p.insertChild(varObj, pos);
		unparsed.add(0, varObj);
		
		p.insertChild(ab, pos);
		unparsed.add(unparsed.size(), ab);
		
	}
	
	/**
	 * Extracts the first occuring set of braces (starting at the beginning of the uparsed term) from the given UnparsedObject
	 * and replaces it with starting braces, the brace body, a closing brace and the remaining unparsed term.
	 * 
	 * @param o the UnparsedObject to be replaced
	 */
	private void splitBraces(UnparsedObject o) {
		String body;
		String rest;
		String e = o.getExpr();
		assert(e.charAt(0) == '(');
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
			unparsed.add(0, restObj);
		}
		
		p.replaceChild(o, cb);
		unparsed.add(unparsed.size(), cb);
		unparsed.remove(o);
		
		if (!body.trim().equals("")) {
			UnparsedObject bodyObj = new UnparsedObject(body);
			p.insertChild(bodyObj, pos);
			unparsed.add(0, bodyObj);
		}
		
		p.insertChild(ob, pos);
		unparsed.add(unparsed.size(), ob);
		
	}
	
	/**
	 * Extracts the first occuring variable (starting at the beginning of the unparsed term) from the given UnparsedObject and replaces it with it and the remaining unparsed term.
	 * 
	 * @param o the UnparsedObject to be replaced with a variable and the remaining unparsed term
	 */
	private void splitVariable(UnparsedObject o) {
		Parent p = o.getParent();
		String e = o.getExpr();
		int end = e.length();
		int elen = e.length();
		int i = e.indexOf('(');
		end = Math.min(end, i < 0? elen : i);
		i = e.indexOf(')');
		end = Math.min(end, i < 0? elen : i);
		i = e.indexOf(bindPrfx);
		end = Math.min(end, i < 0? elen : i);
		i = e.indexOf(bindPstfx);
		end = Math.min(end, i < 0? elen : i);
		i = e.indexOf(' ');
		end = Math.min(end, i < 0? elen : i);
		
		String var = e.substring(0, end);
		Variable varObj = new Variable(var);
		
		String rest = e.substring(end);
		if (!rest.trim().equals("")) {
			UnparsedObject restObj = new UnparsedObject(rest);
			this.unparsed.add(0, restObj);
			p.insertChild(restObj, p.getChildPosition(o) + 1);
		}
		
		p.replaceChild(o, varObj);
		this.unparsed.remove(o);
		this.unparsed.add(unparsed.size(), varObj);
	}
	
	private class UnparsedObject implements InternalBoardObject, Comparable<UnparsedObject> {
		private Parent parent;
		private String expr;
		
		public UnparsedObject() {
			
		}
		
		public UnparsedObject(String expr) {
			this.expr = expr.trim();
			assert(!this.expr.equals(""));
		}

		public String getExpr() {
			return expr;
		}

		public void setExpr(String expr) {
			this.expr = expr.trim();
			assert(!this.expr.equals(""));
		}
		
		
		public UnparsedObject(UnparsedObject o) {
			this.parent = o.parent;
			this.expr = o.expr;
		}
		
		public Parent getParent() {
			return this.parent;
		}

		public void setParent(Parent parent) {
			this.parent = parent;
		}

		public boolean isMovable() {
			return true;
		}

		public boolean isRemovable() {
			return true;
		}

		public InternalBoardObject copy() {
			return new UnparsedObject(this);
		}

		public void accept(BoardObjectVisitor visitor) {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		public boolean match(BoardObject b) {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		public int compareTo(UnparsedObject arg0) {
			if (arg0.getClass() == UnparsedObject.class) {
				return 0;
			} else {
				return -1;
			}
		}
	}
	
	private class AbstractionBegin extends UnparsedObject {
		public AbstractionBegin() {
			super(bindPrfx);
		}
		@Override
		public void setExpr(String expr) {
			assert(false);
		}
		@Override
		public int compareTo(UnparsedObject arg0) {
			if (arg0.getClass() == UnparsedObject.class || arg0.getClass() == OpeningBrace.class || arg0.getClass() == ClosingBrace.class) {
				return 1;
			} else if (arg0.getClass() == AbstractionBegin.class) {
				return 0;
			} else {
				return -1;
			}
		}
	}
	private class AbstractionSeparator extends UnparsedObject {
		public AbstractionSeparator() {
			super(bindPstfx);
		}
		@Override
		public void setExpr(String expr) {
			assert(false);
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
	private class OpeningBrace extends UnparsedObject {
		public OpeningBrace() {
			super("(");
		}
		@Override
		public void setExpr(String expr) {
			assert(false);
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
	private class ClosingBrace extends UnparsedObject {
		public ClosingBrace() {
			super(")");
		}
		@Override
		public void setExpr(String expr) {
			assert(false);
		}
		@Override
		public int compareTo(UnparsedObject arg0) {
			if (arg0.getClass() == UnparsedObject.class || arg0.getClass() == OpeningBrace.class) {
				return 1;
			} else if (arg0.getClass() == ClosingBrace.class) {
				return 0;
			} else {
				return -1;
			}
		}
	}
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
