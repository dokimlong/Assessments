// Kim-Long Do
// 1/14/2021
// CSE143
// TA: Chandan Hegde
// Take-Home Assessment #1
//
// This program keeps track of how many of each letter there are in a String of words that can
// add or subtract other Strings.


import java.util.*;

public class LetterInventory {
    public static final int LETTERS = 26;
    
    private int[] elementData;
    private int size;

    //Constructs an inventory for all the letters from the string of data 
    //LetterInventory is letter case insensitive
    public LetterInventory(String data){
        data = data.toLowerCase();
        elementData = new int[LETTERS];

        for(int i = 0; i<data.length(); i++){
            if(Character.isLetter(data.charAt(i))){
                elementData[data.charAt(i) - 'a']++;
                size++;
            }
        }
    }

    //Takes in a letter and returns the count of a letter within the inventory
    //get is letter case insensitive
    //Throws IllegalArgumentException if letter is nonalphabetic
    public int get(char letter){
        letter = Character.toLowerCase(letter);

        if(!Character.isLetter(letter)){
            throw new IllegalArgumentException("Invalid Letter: " + letter);
        }
        return elementData[letter - 'a'];
    }

    //Takes in a letter and value that sets the count of a letter in the inventory
    //to the given value.
    //set is letter case insensitive
    //Throws IllegalArgumentException when letter is nonalphabetic or value is negative
    public void set(char letter, int value){
        letter = Character.toLowerCase(letter);
        
        if(!Character.isLetter(letter) || value < 0){
            throw new IllegalArgumentException("Invalid Letter: " + letter + "; Value: " + value);
        }
        size += value - elementData[letter - 'a'];
        elementData[letter - 'a'] = value;
    }

    //Returns the number of characters within the inventory
    public int size(){
        return size;
    }

    //Returns true when the inventory is empty
    public boolean isEmpty(){
        return size == 0;
    }

    //Returns the alphabetically sorted inventory of letters within brackets
    public String toString(){
        String result = "[";

        for(int i = 0; i<LETTERS; i++){
            for(int j = 0; j<elementData[i]; j++){
                result += (char)('a' + i);
            }
        }
        return result + "]";
    }

    //Takes in an 'other' inventory of letters that is added to the existing inventory
    //of letters.
    //Returns the count of every letters in both inventories into a new LetterInventory
    public LetterInventory add(LetterInventory other){
        LetterInventory total = new LetterInventory("");

        for(int i = 0; i < LETTERS; i++){
            total.elementData[i] = this.elementData[i] + other.elementData[i];
        }
        total.size = this.size + other.size;
        return total;
    }

    //Takes in an 'other' inventory of letters that is subtracted from the existing inventory 
    //of letters. 
    //Returns the difference of the two inventory of letters
    //returns null if any count of a letter < 0
    public LetterInventory subtract(LetterInventory other){
        LetterInventory outcome = new LetterInventory("");

        for(int i = 0; i<LETTERS; i++){
            outcome.elementData[i] = this.elementData[i] - other.elementData[i];

            if(outcome.elementData[i] < 0){
                return null;
            }
            outcome.size += outcome.elementData[i];
        }
        return outcome;
    }

}
