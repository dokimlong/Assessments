// Kim-Long Do
// 12/7/2020
// CSE142
// TA: Aiden Thaler
// Take-Home Assessment #8
// Constructor - Creates a hippo with a given amount of hunger
// Behavior:
//      Color    - A hippo is gray if hunger is above 0 and white when at 0.
//      Eating   - A hippo will always eat until hunger falls to 0, at which point the hippo
//                 will never eat again
//      Fighting - A hippo will scratch if hunger is above 0 and pounces when hunger is 0.
//      Movement - A hippo will choose a random direction and walk in that direction 5 times.
//                 The hippo will repeat this process endlessly.
//      String   - A hippo is displayed by the amount of hunger left

import java.util.*;
import java.awt.*;

public class Hippo extends Critter {

    private int hunger; // Represents the amount of hunger a hippo still feels
    private int movesLeft; // Represents the amount of moves a hippo has left in a direction
    private int compass; // Represents the direction a hippo will walk along
    private Random num; // Represents a randomly generated number

    // Constructs a new hippo
    // parameter:
    //      hunger - Each new hippo starts off with a certain number of hunger
    public Hippo(int hunger) {
        this.hunger = hunger;
        movesLeft = 0;
        compass = 0;
        num = new Random();
    }

    // Returns gray if the hippo is still hungry and white when hunger is 0
    public Color getColor() {
        if (hunger > 0) {
            return Color.GRAY;
        } else {
            return Color.WHITE;
        }
    }

    // Returns true if the hippo is still hungry and false when no longer hungry
    // hunger counter decreases by one every time the hippo eats
    public boolean eat() {
        if(hunger > 0) {
            hunger--;
            return true;
        }else {
            return false;
        }
    }

    // Returns scratch if the hippo is still hungry or pounce when no longer hungry.
    // parameter:
    //      opponent - Takes in the type of opponent that the hippo will fight against
    public Attack fight(String opponent) {
        if(hunger > 0) {
            return Attack.SCRATCH;
        } else {
            return Attack.POUNCE;
        }
    }

    // Returns the direction that the hippo will walk along. movesLeft keeps track of how
    // many times a hippo has walked in a certain direction. Once movesLeft reaches 0 the
    // elephant will choose a new direction(can repeat) and the movesLeft is set to 5.
    // Compass will take in a random number that determines which direction a hippo will
    // walk along
    public Direction getMove() {

        
        if(movesLeft == 0) {
            movesLeft = 5;
            compass = num.nextInt(4);
        }
        movesLeft--;

        if(compass == 0) {
            return Direction.NORTH;
        } else if (compass == 1) {
            return Direction.EAST;
        } else if (compass == 2) {
            return Direction.SOUTH;
        } else {
            return Direction.WEST;
        }
    }

    // Returns the amount of hunger a hippo still feels as the symbol displayed for a hippo
    public String toString() {
        return "" + hunger;
    }
}
