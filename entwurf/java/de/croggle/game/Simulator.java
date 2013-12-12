package de.croggle.game;

import de.croggle.game.model.Board;
import de.croggle.util.RingBuffer;
import de.croggle.game.events.EatEventListener;

import java.util.List;

/**
 * @has 1 - 0-30 de.croggle.game.model.Board
 * @navassoc 1 - 1 de.croggle.util.RingBuffer
 */
public class Simulator {
    private Board entranceBoard;
    private RingBuffer<Board> history; // 30 elements needed.
    
    private List<EatEventListener> eatListeners;
    
    public Simulator () {
		this.history = new RingBuffer (30);
	}
    
    /**
     * Method that evaluates one step in the Lambda Calculus
     */
    public Board evaluate(Board entranceBoard) {
        // gotta save the old board to history[*rightplace*]
    }
    
    /**
     * Method that reverses the last evaluation step.
     * @return The last 
     */
    public Board undo() {
        
    }
    
    
}
