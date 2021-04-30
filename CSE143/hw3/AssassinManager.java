// Kim-Long Do
// 1/27/2021
// CSE143
// TA: Chandan Hegde
// Take-Home Assessment #3
//
// This program is about the game assassin. Every player still in the game is given a target 
// they're suppose to assassinate and the goal is to be the last person standing. May the odds
// be ever in your favor.

import java.util.*;

public class AssassinManager {
    private AssassinNode killRingFront;
    private AssassinNode graveyardFront;
    

    // The AssassinManager constructor starts the assassin game and the list of people who are 
    //playing based on the names passed in
    //throws new IllegalArgumentException if names is empty
    public AssassinManager(List<String> names) {
        if(names.isEmpty()) {
            throw new IllegalArgumentException();
        }
        
        for(int i = names.size()-1; i>=0; i--) {
            killRingFront = new AssassinNode(names.get(i), killRingFront);
        }
    }

    // printKillRing keeps track of who each person's target is
    // If there is only one person left in the game then they target themself
    public void printKillRing() {
        AssassinNode assassinName = killRingFront;
        while(assassinName != null && assassinName.next != null){
            stalker(assassinName.name, assassinName.next.name);
            assassinName = assassinName.next;
        }
        stalker(assassinName.name, killRingFront.name);
    } 

    // stalker prints a statement about who a stalker is suppose to stalk
    private void stalker(String stalker, String stalked) {
        System.out.println("    "+ stalker+ " is stalking "+ stalked);
    }

    // printGraveyard keeps track of the people killed and their killer
    public void printGraveyard() {
        AssassinNode assassinName = graveyardFront;
        while(assassinName != null){
            System.out.println("    "+ assassinName.name+ " was killed by "+
                                                     assassinName.killer);
            assassinName = assassinName.next;
        }

    } 

    // killRingContains checks if a person still exists within the game
    // The name check is letter case insensitive
    // Returns true if the name exists
    public boolean killRingContains(String name) {
        return nameExists(name, killRingFront);
    }

    // graveyardContains checks if a person is within the graveyard
    // The name check is letter case insensitive
    // Returns true if the name exists
    public boolean graveyardContains(String name) {
        return nameExists(name, graveyardFront);
    }

    // nameExists checks if a passed in name exists within the passed in list of names 
    // The name check is letter case insensitive 
    // Returns true if the name exists within the list
    private boolean nameExists(String name, AssassinNode list) {
        boolean exists = true;
        while (list != null) {
            if(list.name.equalsIgnoreCase(name)) {
                return exists;
            }
            list = list.next;
        }
        return !exists;
    }

    // returns true when the game is over
    public boolean gameOver() {
        return killRingFront.next == null;
    }

    // winner returns the name of the last person standing 
    // Returns null when game isn't over
    public String winner() {
        if(gameOver()){
            return killRingFront.name;
        }
        return null;
    }

    // kill method keeps track of the assassination of a person and their killer
    // kill is letter case insensitive for the name passed in
    // updates kill targets after a kill
    // throws new IllegalStateException if the game is over
    // throws new IllegalArgumentException if a name doesn't exist within those still playing
    public void kill(String name) {
        AssassinNode currAssassin = killRingFront;
        AssassinNode priorAssassin = graveyardFront;

        if(gameOver()) {
            throw new IllegalStateException();
        } else if(!killRingContains(name)) {
            throw new IllegalArgumentException();
        }

        if(currAssassin.name.equalsIgnoreCase(name)) {
            priorAssassin = currAssassin;
            while(currAssassin.next != null) {
                currAssassin = currAssassin.next;
            }
            killRingFront = killRingFront.next;
        } else {
            while(!currAssassin.next.name.equalsIgnoreCase(name)) {
                currAssassin = currAssassin.next;
            }
            priorAssassin = currAssassin.next;
            currAssassin.next = currAssassin.next.next;
        }

        priorAssassin.next = graveyardFront;
        graveyardFront = priorAssassin;
        priorAssassin.killer = currAssassin.name;
    }
}
