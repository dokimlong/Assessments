// Kim-Long Do
// 3/11/2021
// CSE143
// TA: Chandan Hegde
// Take-Home Assessment #8
//
// HuffmanCode is a program that is used to make compressing and decompressing data easier using
// the huffman coding technique invented by the late David Huffman.
import java.util.*;
import java.io.*;

public class HuffmanCode {
    private HuffmanNode overallRoot;

    // para: frequencies -- an array of ints of the count for each character
    // post: constructs a tree based on the frequencies
    // higher frequencies are closer to the root of the tree
    public HuffmanCode(int[] frequencies) {
        Queue<HuffmanNode> tracker = new PriorityQueue<HuffmanNode>();

        for(int i = 0; i< frequencies.length; i++) {
            if(frequencies[i] > 0) {
                HuffmanNode frequencyRate = new HuffmanNode(frequencies[i], i);
                tracker.add(frequencyRate);
            }
        }

        while(tracker.size() > 1) {
            HuffmanNode curr = tracker.remove();
            HuffmanNode next = tracker.remove();
            HuffmanNode sum = new HuffmanNode(curr.frequency + next.frequency, 1, curr, next);
            tracker.add(sum);
        }
        overallRoot = tracker.remove();
    }
    // para: output -- a file that is used to store information
    // post: updates output with the current tree with pre-order travsersal formatting
    public void save(PrintStream output) {
        save(output, "", overallRoot);
    }

    // helper method of the public save method
    private void save(PrintStream output, String code, HuffmanNode root) {
        if(root.right == null && root.left == null) {
            output.println(root.letter);
            output.println(code);
        } else {
            save(output, code +"0" , root.left);
            save(output, code +"1", root.right);
        }
    }

    // para: input -- file that contains constructed code
    // input is assumed to have working data and in pre-order traversal formatting
    // post: constructs a new tree based on the file inputed  
    public HuffmanCode(Scanner input) {
        while(input.hasNextLine()) {
            int value = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
            overallRoot = reconstruct(value, code, overallRoot);
        }
    }

    // helper method for the scanner constructor
    private HuffmanNode reconstruct(int value, String code, HuffmanNode root) {
        if(root == null) {
            root = new HuffmanNode(0, 0);
        }

        char codeVal = code.charAt(0);
        if(code.length() <= 1) {
            if(codeVal == '0') {
                root.left = new HuffmanNode(0, value);
            } else {
                root.right = new HuffmanNode(0, value);
            }
        } else {
            code = code.substring(1);
            if( codeVal == '0') {
                root.left = reconstruct(value, code, root.left);
            } else {
                root.right = reconstruct(value, code, root.right);
            }
        }
        return root;
    }

    // para: input -- file containing information in bits 
    // output -- file that data is stored in
    // input is assumed to be legal with working data
    // both parameters are in pre-order traversal formatting 
    // post: translates the input into english and stores the information in output 
    public void translate(BitInputStream input, PrintStream output) {
        int charVal = input.nextBit();
        while(charVal != -1) {
        charVal = translate(input, output, overallRoot, charVal);  
        } 
    }

    // private helper method of public translate method
    private int translate(BitInputStream input,PrintStream output,HuffmanNode root,int charVal) {
        if(!input.hasNextBit()) { 
            charVal = -1;         
        }
        if(root.left == null && root.right == null) {
            output.write(root.letter);
        } else if(charVal == 0 && input.hasNextBit()) {
            return translate(input, output, root.left, input.nextBit());
        } else if(charVal == 1 && input.hasNextBit()) {
            return translate(input, output, root.right, input.nextBit());
        } 
        return charVal;
    }
}
