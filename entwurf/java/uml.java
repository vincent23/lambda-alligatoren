package de.croggle;

/**
 * @opt views
 * @hidden
 * @excludeFromTex
 */
class UMLOptions {
        
}

/**
 * @view
 * 
 * @match class .*
 * @opt !all
 * @opt nodefillcolor 1
 * @opt nodefontname Verdana
 * @opt edgefontname Verdana
 * @opt edgecolor #565656
 * @opt edgefontsize 10
 * 
 * @match class com.badlogic.gdx.*
 * @opt qualify
 * @opt postfixpackage
 *
 * @match class de.croggle.*
 * @opt all
 * @opt nodefillcolor 2
 *
 * @match class de.croggle.controller.*
 * @opt nodefillcolor 3
 * 
 * @match class de.croggle.data.persistence.*
 * @opt nodefillcolor 4
 * 
 * @match class de.croggle.game.*
 * @opt nodefillcolor 5
 * 
 * @match class de.croggle.ui.renderer.*
 * @opt nodefillcolor 6
 * 
 * @match class de.croggle.ui.*
 * @opt nodefillcolor 7
 * 
 * @match class de.croggle.util.*
 * @opt nodefillcolor 8
 * 
 * @match class de.croggle.game.event.*
 * @opt nodefillcolor 9
 * 
 * @match class de.croggle.data.persistence.model.*
 * @opt nodefillcolor 10
 * 
 * @match class de.croggle\..*Exception
 * @opt nodefillcolor #b22222
 * @opt nodefontcolor white
 * 
 * @match class java.*
 * @opt hide
 * 
 * @excludeFromTex
 */
public class uml {
}
