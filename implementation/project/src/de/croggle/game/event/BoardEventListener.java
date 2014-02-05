package de.croggle.game.event;

/**
 * 
 * Interface for aggregating all types of listeners for events concerning board
 * objects, so that implementing classes must only implement this interface.
 */
public interface BoardEventListener extends EggHatchEventListener,
		ObjectRecoloredListener, EatEventListener, BoardRebuiltEventListener,
		AgedAlligatorVanishesListener, AlligatorAgesListener,
		BoardEditedListener {
}
