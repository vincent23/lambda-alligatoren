package de.croggle;

/**
 * @view
 *
 * I tried the qualified names option, but it generated java.lang.String
 * opt qualify
 * opt postfixpackage
 * 
 * See http://www.graphviz.org/doc/info/colors.html#brewer for colorscheme set312
 * @match class .*
 * @opt all
 * @opt nodefillcolor 1
 * @opt nodefontname Verdana
 * @opt edgefontname Verdana
 * @opt edgecolor #565656
 * @opt edgefontsize 12 
 * 
 * @match class de.croggle.*
 * @opt nodefillcolor 2
 * 
 * @match class de.croggle.ui.*
 * @opt nodefillcolor 3
 *
 * @match class de.croggle.game.*
 * @opt nodefillcolor 4
 * 
 * @match class de.croggle.data.*
 * @opt nodefillcolor 5
 * 
 * @match class de.croggle.renderer.*
 * @opt nodefillcolor 6
 *
 * @match class java.*|android.*
 * @opt hide
 * 
 * @excludeFromTex
 */
public class PackageUMLView {
}
