// Kim-Long Do
// 1/20/2021
// CSE143
// TA: Chandan Hegde
// Take-Home Assessment #2
//
// The GuitarString class simulates the sound a guitar string makes

import java.util.*;

public class GuitarString {
    private Queue<Double> ringBuffer; // Creates a queue that stores simulated sound values
    public static final double DECAY_FACTOR = 0.996; // Karplus-Strong algorithm energy decay factor



    // pre: frequency >= 0; desiredN >= 2 (throws IllegalArgumentException if not)
    // post: Constructs a string that starts at rest
    // desiredN is the number of cycles per sample for a given frequency
    public GuitarString(double frequency){
        int desiredN = (int)(Math.round(StdAudio.SAMPLE_RATE / frequency));
        if(desiredN < 2 || frequency <= 0){
            throw new IllegalArgumentException("desiredN: "+desiredN+"; frequency: "+frequency);
        }
        ringBuffer = new LinkedList<Double>();;
        for(int i = 0; i < desiredN; i++){
            ringBuffer.add(0.0);
        }
    }
  
    // pre: init.length >= 2 (throws IllegalArgumentException if not)
    // post: Fills ringBuffer with elements of init
    public GuitarString(double[] init){
        if(init.length < 2){
            throw new IllegalArgumentException("Bad init length: "+init);
        }
        ringBuffer = new LinkedList<Double>();;
        for(int i = 0; i<init.length; i++){
            ringBuffer.add(init[i]);
        }
    }

    // pre: randomly generated values between -0.5 and 0.5 inclusive
    // post: Adds randomly generated values to ringBuffer
    public void pluck(){
        double randomN = 0;
        for(int i = 0; i<ringBuffer.size(); i++){
            randomN = Math.random() - 0.5;
            ringBuffer.add(randomN);
            ringBuffer.remove();
        }
    }

    // post: Goes through the Karplus-Strong algorithm once 
    public void tic(){
        double removeFront = ringBuffer.remove();
        double secondValue = ringBuffer.peek();
        double average = ((removeFront + secondValue) / 2) * DECAY_FACTOR;
        ringBuffer.add(average);

    }

    // post: returns the current sample
    public double sample(){
        return ringBuffer.peek();
    }
}
