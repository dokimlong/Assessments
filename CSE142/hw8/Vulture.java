// Kim-Long Do
// 12/7/2020
// CSE142
// TA: Aiden Thaler
// Take-Home Assessment #8
// Vulture is a subclass of Bird, sharing many similar features that is mentioned below
// Constructor - Creates a Vulture 
// Behavior:
//      Color    - A vulture will always be the color black
//      Eating   - A vulture starts off hungry and will eat once after every fight
//      Fighting - A vulture shares the same fighting style as a bird, roaring when against
//                 an ant and pouncing vs. everything else.
//                 Fighting will also make a vulture hungry
//      Movement - A vulture shares the same movement as a bird, moving 3 steps in a clockwise
//                 square, starting at north.
//      String   - A vulture shares the same symbol as a bird, pointing towards the
//                 direction that they are walking along.

import java.util.*;
import java.awt.*;

public class Vulture extends Bird {
    private boolean needFood; // Represents whether or not a vulture should eat

    // Constructs a vulture
    public Vulture() {
        needFood = true;
    }

    // Returns the color black
    public Color getColor() {
        return Color.BLACK;
    }

    // if the vulture needs to eat, starts off hungry or after a fight, then eat will
    // return true, otherwise false.
    public boolean eat() {
        if(needFood) {
            needFood = false;
            return true;
        } else {
            return false;
        }
    }

    // Flips needFood to true so that the vulture is hungry after a fight
    // Super.fight calls on the fight method from bird, using the same fighting techniques
    // for the vultures.
    // Returns roar when the opponent parameter is an ant "%" while pouncing against
    // everything else
    // parameter:
    //      opponent - Takes in the type of opponent that the vulture will fight against
    public Attack fight(String opponent) {
        needFood = true;
        return super.fight(opponent);
    }
}
