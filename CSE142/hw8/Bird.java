// Kim-Long Do
// 12/7/2020
// CSE142
// TA: Aiden Thaler
// Take-Home Assessment #8
// Constructor - Creates a bird
// Behavior:
//      Color    - A bird is always blue
//      Eating   - A bird will never eat, there is no need to change the inherited behavior
//      Fighting - A bird will roar if facing an ant "%" or pounce everything else
//      Movement - A bird will move in a square path, taking 3 steps in each direction at a time
//      String   - A bird will be represented by a symbol pointing in the direction that they are
//                 walking along. The default symbol is "^".

import java.util.*;
import java.awt.*;

public class Bird extends Critter {
    private int moves; // Represents the number of moves of a bird

    // Constructs a bird
    public Bird() {
        moves = 0;
    }

    // Returns the color blue
    public Color getColor() {
        return Color.BLUE;
    }

    // Returns roar if the opponent is an ant "%" or pounce against other opponents
    // parameter:
    //      opponent - Takes in the type of opponent that the bird will fight against
    public Attack fight(String opponent) {
        if(opponent.equals("%")) {
            return Attack.ROAR;
        } else {
            return Attack.POUNCE;
        }
    }

    // Returns the direction a bird will walk along
    // Birds walk on a clockwise square path, taking three steps at a time, starting 
    // from north.
    public Direction getMove() {
        moves++;
        if(moves > 12) {
            moves = 1;
        }

        if(moves < 4) {
            return Direction.NORTH;
        } else if (moves < 7) {
            return Direction.EAST;
        } else if (moves < 10) {
            return Direction.SOUTH;
        } else {
            return Direction.WEST;
        }
    }

    // Returns a symbol that points in the direction that the bird is walking along
    // The direction is based on how many moves a bird has taken. If the bird has not 
    // taken a move yet, then the default symbol will be "^".
    public String toString() {
        if(moves < 4) {
            return "^";
        } else if (moves < 7) {
            return ">";
        } else if (moves < 10) {
            return "V";
        } else {
            return "<";
        }
    }
}
