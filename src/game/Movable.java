package game;

/**
 * The Movable interface defines a contract for objects that can move.
 * Any class implementing this interface must provide an implementation for the move() method.
 */
public interface Movable {
	
    /**
     * Moves the object in a specific way, defined by the implementing class.
     */
	void move();
}
