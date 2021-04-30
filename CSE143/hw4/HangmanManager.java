// Kim-Long Do
// 2/3/2021
// CSE143
// TA: Chandan Hegde
// Take-Home Assessment #4
//
// This program plays a challenging version of the hangman game with the user 

import java.util.*;

public class HangmanManager {
    private String screenPattern;  
    private int numGuess; 
    private SortedSet<Character> lettersGuessed;
    private Set<String> viableWords; 

    // pre: length => 1; max>= 0; thro (throws IllegalArgumentException if not)
    // para: dictionary of words; length of the word in the game; max number of guesses in game
    // post: initializes the hangman game with dashes to represent unguessed letters
    // Dictionary parameter doesn't have to be in alphabetical order
    public HangmanManager(Collection<String> dictionary, int length, int max) {
        if (length < 1 || max < 0) {
            throw new IllegalArgumentException();
        }
        numGuess = max;
        viableWords = new TreeSet<String>();
        lettersGuessed = new TreeSet<Character>();
        for (String word : dictionary) {
            if (word.length() == length) {
                viableWords.add(word);
            }
        }
        screenPattern = "-";
        for (int i = 0; i < length-1; i++) {
            screenPattern += " -";
        }
    }

    // returns the possible words left in the game
    public Set<String> words() {
        return viableWords;
    }

    // returns the number of guesses left for the player
    public int guessesLeft() {
        return numGuess;
    }

    // returns the letters that have been guessed by player
    public Set<Character> guesses() {
        return lettersGuessed;
    }

    // returns the pattern of the secret word
    // unGuessed letters are shown as dashes
    // pre: The possible words for the game is not empty 
    // throws IllegalStateException if not
    public String pattern() {
        if (viableWords.isEmpty()) {
            throw new IllegalStateException();
        }
        return screenPattern;
    }

    // pre1: numGuess >=1; There are still possible words (throws IllegalStateException if not)
    // pre2: !lettersGuessed.contains(guess) (throws IllegalArgumentException if not)
    // pre1 check comes before pre2
    // post: updates pattern on screen, updates the possible words, and number of guesses goes
    //       down by 1 if the letter doesn't exist within the words
    // return: The number of times a letter appears in the secret word
    public int record(char guess) {
        if (numGuess < 1 || viableWords.isEmpty()) {
            throw new IllegalStateException();
        } else if (lettersGuessed.contains(guess)) {
            throw new IllegalArgumentException();
        }
        lettersGuessed.add(guess);
        Map<String, Set<String>> family = new TreeMap<String, Set<String>>();        
        wordFamilies(family, guess);
        biggestSet(family); 
        return countLetters(guess);
    }

    // wordFamilies is a method that takes in the family map and the player's guess
    // Using the map and guess, the method will generate patterns for the possible words and
    // place the words into their own families.
    private void wordFamilies(Map<String, Set<String>> fam, char guess) {
        for(String word : viableWords) {
            String pattern = "";
            int index = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != guess) {
                    pattern += screenPattern.charAt(index);
                } else {
                    pattern += guess;
                }
                index += 2;
                pattern+= " ";
            }
            pattern = pattern.substring(0, pattern.length()-1);
            if (!fam.containsKey(pattern)) {
                Set<String> wordSet = new TreeSet<String>();
                fam.put(pattern, wordSet);
            }
            fam.get(pattern).add(word);
        }
    } 

    // biggestSet takes in the family map and selects the largest family of patterns as the words 
    // that can still be used for the game
    private void biggestSet(Map<String, Set<String>> family) {
        int sizeMax = 0;
        if (!family.isEmpty()) {
            for (String currentPattern : family.keySet()) {
                if (family.get(currentPattern).size() > sizeMax) {
                    viableWords.clear();
                    viableWords.addAll(family.get(currentPattern));
                    screenPattern = currentPattern;
                    sizeMax = family.get(currentPattern).size();
                }
            }
        }
    }   

    // countLetters takes in the character that a player guesses
    // Reduces a player's guesses if the letter doesn't appear within the word
    // returns the number of times a letter appears within the secret word
    private int countLetters(char guess) {
        int maxCount = 0;
        lettersGuessed.add(guess);

        for (int i = 0; i < screenPattern.length(); i++) {
            if (screenPattern.charAt(i) == guess) {
                maxCount++;
            }
        }
        if (maxCount <1) {
            numGuess--;
        }
        return maxCount;
    }
}
