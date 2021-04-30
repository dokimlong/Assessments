// Kim-Long Do
// 12/7/2020
// CSE142
// TA: Aiden Thaler
// Take-Home Assessment #8
// Constructor - creates an ant with a boolean walkSouth that determines if the ant will walk 
// south or north. 
// Behavior: 
//      Color    - An ant is always red
//      Eating   - An ant will always eat
//      Fighting - An ant will always scratch
//      Movement - An ant determines whether it will walk south or north depending on if 
//                 walkSouth is true or false respectively. Then the ant will alternate between
//                 moving the chosen direction and east.
//      String   - An ant will always be displayed by the symbol "%".

import java.util.*;
import java.awt.*;

public class Ant extends Critter {
    private int moves; // Represents the number of moves of an ant
    private boolean walkSouth; // Represents whether an ant will walk south or north
    private Direction movement;
    // Constructs a new ant
    // parameter:
    //      walkSouth - Determines the direction an ant will walk
    public Ant(boolean walkSouth) {
        this.walkSouth = walkSouth;
        moves = 0;
        movement = Direction.CENTER;
    }

    // Returns the color red
    public Color getColor() {
        return Color.RED;
    }

    // Returns true whenever an ant finds food
    public boolean eat() {
        return true;
    }

    // Returns scratch whenever an ant gets into a fight
    // parameter:
    //      opponent - Takes in the type of opponent that the ant will fight against
    public Attack fight(String opponent) {
        return Attack.SCRATCH;
    }

    // Returns the direction that an ant will walk, based on if walkSouth is true or false and
    // alternating between that and east. 
    public Direction getMove() {
        moves++;
        if(walkSouth && (moves % 2 == 1)) {
            movement = Direction.SOUTH;
        } else if (!walkSouth && (moves % 2 == 1)) {
            movement = Direction.NORTH;
        } else {
            movement = Direction.EAST;
        }
        return movement;
    }

    // Returns % as the symbol that represents an ant
    public String toString() {
        return "%";
    }
}
