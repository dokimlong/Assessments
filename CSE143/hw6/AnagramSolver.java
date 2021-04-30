// Kim-Long Do
// 2/25/2021
// CSE143
// TA: Chandan Hegde
// Take-Home Assessment #6
//
// This program finds all the possible anagrams of a phrase of words with conditions chosen 
// by the user

import java.util.*;

public class AnagramSolver {
    private List<String> givenDict;
    private Map<String, LetterInventory> processedDict;

    // para: Dictionary - list of words
    // post: initializes a list of the words within dictionary
    // There are no duplicate words within dictionary and dictionary will never be empty
    public AnagramSolver(List<String> dictionary) {
        givenDict = dictionary;
        preprocessing();
    }

    // para: String text; int max 
    // pre: max cannot be less than 0. Throws IllegalArgumentException if less than 0
    // post: Prints all the possible phrases that can be made from the text parameter
    // and the most number of words in a phrase allowed based on the max paramter
    // If max is 0 then there is no limit to the number of words allowed
    public void print(String text, int max) {
        if(max < 0) {
            throw new IllegalArgumentException();
        }

        LetterInventory lettersList = new LetterInventory(text);
        List<String> possibilies = new ArrayList<String>();
        List<String> result = new ArrayList<String>();
        pruning(possibilies, lettersList);
        solvePhrase(max, possibilies, result, lettersList);
    }

    // Stores the words within givenDict into a new LetterInventory of processedDict. 
    // Once per word.
    private void preprocessing() {
        processedDict = new HashMap<String, LetterInventory>();

        for(String word : givenDict) {
            processedDict.put(word, new LetterInventory(word));
        }
    }

    // Prunes all the words within the dictionary that doesn't contain the letters within
    // listLetters.
    private void pruning(List<String> possibilies, LetterInventory listLetters) {
        for(String word : givenDict) {
            if(listLetters.subtract(processedDict.get(word)) != null) {
                possibilies.add(word);
            }
        }
    }

    // Prints out the possible combination of anagrams
    private void solvePhrase(int max, List<String> possibilies, List<String> result, 
                                                 LetterInventory lettersList) {
        if(lettersList.isEmpty()) {
            System.out.println(result);
        }

        if(max == 0 || max > result.size()) {
            for(String word : possibilies) {
                LetterInventory newList = lettersList.subtract(processedDict.get(word));

                if(newList != null) {
                    result.add(word);
                    solvePhrase(max, possibilies, result, newList);
                    result.remove(result.size()-1);
                }
            }
        }
    }
}
