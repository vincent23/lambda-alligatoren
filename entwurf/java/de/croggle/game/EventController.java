package de.croggle.game;

import de.croggle.game.model.Achievement;
import java.util.List;
import java.util.Map;

public class EventController {
	
	/**
	 * The handlers for the events passed in the constructor are stored in this map.
	 */
	private Map<Event, AbstractEventHandler> eventHandlers;
	
	/**
	 * The events this class has to process.
	 */
	private List<Event> events;
	
	
	/**
	 * Creates a new EventManager
	 * @param events are the events this class has to manage.
	 */
	public EventController(List<Event> events) {
		
	}
	
	/**
	 * Uses the handlers stored in eventHandlers to process all events.
	 * @return a list of achievements that got unlocked due to the events.
	 */
	public List<Achievement> processEvents() {
		return null;
	}

}
