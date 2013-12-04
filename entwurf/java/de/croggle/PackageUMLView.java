package de.croggle;

/**
 * @view
 *
 * @match class .*
 * @opt all
 * @opt nodefillcolor LightGray
 * @opt nodefontname Verdana
 * @opt edgefontname Verdana
 * @opt edgecolor #565656
 * @opt edgefontsize 12 
 * 
 * I tried the qualified names option, but it generated java.lang.String
 * opt qualify
 * opt postfixpackage
 * 
 * @match class de.croggle.ui.*
 * @opt nodefillcolor PaleGreen
 *
 * @match class de.croggle.game.*
 * @opt nodefillcolor LemonChiffon
 *
 * @match class java.*|android.*
 * @opt hide
 */
public class PackageUMLView {
}
