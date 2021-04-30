// Kim-Long Do
// 1/20/2021
// CSE143
// TA: Chandan Hegde
// Take-Home Assessment #2
//
// This program creates multiple strings for an entire guitar
import java.util.*;

public class Guitar37 implements Guitar {
    public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
    private int numTics = 0; // Keeps track of the time 
    private GuitarString[] keys; // An array for strings on the guitar


    // post: Constructs new strings on a guitar for the keyboard
    public Guitar37(){
        keys = new GuitarString[KEYBOARD.length()];
        for(int i = 0; i<KEYBOARD.length(); i++){
            keys[i] = new GuitarString(440 * Math.pow(2, (i-24.0)/12.0));
        }
    }

    // pre: pitch is a valid input on guitar
    // post: Plays a given pitch on the Guitar
    // Concert-A has a pitch of 0
    public void playNote(int pitch){
        int note = 0;
        if(pitch >= -24 && pitch <=12){
            note = pitch + 24;
            keys[note].pluck();
        }
    }

    // post: Checks if string exists within the keyboard
    // returns true if the index of string exists
    // returns false otherwise
    public boolean hasString(char string){
        return KEYBOARD.indexOf(string) >= 0 && KEYBOARD.indexOf(string) <= KEYBOARD.length()-1;
    }

    // post: Plucks the given valid string on the Guitar
    // throw: IllegalArgumentException if the input string doesn't exist on the guitar
    public void pluck(char string){
        if(!hasString(string)){
            throw new IllegalArgumentException("Invalid key: " + string);
        }      
        keys[KEYBOARD.indexOf(string)].pluck();
    }

    // post: returns the sum of all the strings
    public double sample(){
        double sumSamples = 0.0;
        for(int i = 0; i<KEYBOARD.length(); i++){
            sumSamples += keys[i].sample();
        }
        return sumSamples;
    }

    // post: advances time forward by one tic
    public void tic(){
        for(int i = 0; i<KEYBOARD.length(); i++){
            keys[i].tic();
            
        }
        numTics++;

    }

    // post: returns the number of time inputs were called
    public int time(){
        return numTics;
    } 

}
