package de.croggle.game.event;

/**
 * 
 * Base interface for all board event listeners. Provides a callback function
 * which is executed by the event source with a board event as parameter,
 * describing the properties of the event in detail.
 */
public interface BoardEventListener extends ReplaceEventListener, ObjectRecoloredListener, EatEventListener, BoardRebuiltEventListener, AgedAlligatorVanishesListener{
}
