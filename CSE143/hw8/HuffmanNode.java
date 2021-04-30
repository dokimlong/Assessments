// Kim-Long Do
// 3/11/2021
// CSE143
// TA: Chandan Hegde
// Take-Home Assessment #8
//
// HuffmanNode class is used to store the data for one node in a tree
// HuffmanNode uses the Comparable interface to compare it's objects
import java.io.*;
import java.util.*;

public class HuffmanNode implements Comparable<HuffmanNode> {
    public int frequency;
    public int letter;
    public HuffmanNode left;
    public HuffmanNode right;

    //Constructs a leaf with given frequency and letter value
    public HuffmanNode(int frequency, int letter) {
        this(frequency, letter, null, null);
    }

    // constructs a node with given frequency, letter value, and links
    public HuffmanNode(int frequency, int letter, HuffmanNode left, HuffmanNode right) {
        this.frequency = frequency;
        this.letter = letter;
        this.left = left;
        this.right = right;
    }
    
    // post: returns difference between nodes
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}
