// Kim-Long Do
// 12/7/2020
// CSE142
// TA: Aiden Thaler
// Take-Home Assessment #8
// Constructor - Creates a husky 
// Behavior:
//      Color    - A husky will always be magenta because there is no purple option
//      Eating   - A husky will only eat if zoomies is true
//      Fighting - A husky will roar vs. an ant and scratches against everything else. After
//                 each fight the husky will gain one hungerlevel. When hungerlevel reaches 2,
//                 the husky will feel the zoomies (true). 
//      Movement - A husky will move in a random direction when zoomies is true, otherwise  will
//                 walk East, North, then West twice, repeating this pattern.
//      String   - A husky will always display the letters "UW" because I'm basic.

import java.util.*;
import java.awt.*;

public class Husky extends Critter {

    private boolean zoomies; // Represents whether or not the husky will eat and act erractically
    private Random wild; // // Represents a randomly generated number
    private int hungerLevel; // Represents how hungry a husky is feelin
    private int moves; // Represents the number of moves of a husky
    
    // Constructs a husky
    public Husky() {
        zoomies = false;
        wild = new Random();
        hungerLevel = 0;
        moves = 0;
    }

    // Returns the color magenta
    public Color getColor() {
        return Color.MAGENTA;
    }

    // Returns true if zoomies is true, setting zoomies to false in the process, otherwise
    // returns false.
    public boolean eat() {
        if(zoomies) {
            zoomies = false;
            return true;           
        } else {
            return false;
        }
    }

    // Raises the hungerlevel of a husky after a fight. Every two fights will reset hungerlevel
    // 0 but flips zoomies to true, to indicate that the husky is hungry.
    // Returns roar against an ant "%" and scratches everything else
    // parameter:
    //      opponent - Takes in the type of opponent that the husky will fight against
    public Attack fight(String opponent) {
        hungerLevel++;
        if(hungerLevel == 2) {
            hungerLevel = 0;
            zoomies = true;
        }

        if (opponent.equals("%")) {
            return Attack.ROAR;
        } else {
            return Attack.SCRATCH;
        }
    }

    // Returns the direction that the husky will walk along
    // If zoomies is true then wild will generate a random number that replaces the moves of
    // a husky, leading to a random direction being returned
    // If zoomies is false then the husky will walk along a path depending on moves. If moves
    // is 1 then the husky will move East, 2 will move North, and 3 or 4 will move West.
    public Direction getMove() {
        moves++;

        if(moves > 4) {
            moves = 1;
        }

        if(zoomies) {
            moves = wild.nextInt(5)+1;
        }

        if(moves == 1) {
            return Direction.EAST;
        } else if(moves == 2) {
            return Direction.NORTH;
        } else if (moves == 3 || moves == 4) {
            return Direction.WEST;
        } else {
            return Direction.SOUTH;
        }
    }

    // Returns the letters "UW"
    public String toString() {
        return "UW";
    }
}
