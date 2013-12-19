package de.croggle.game.event;

/**
 *
 * @depend - <listens_to> - BoardRebuiltEvent
 */
public interface BoardRebuiltEventListener extends BoardEventListener {

    /**
     *
     */
    public void callback(BoardRebuiltEvent event) {

    }
}