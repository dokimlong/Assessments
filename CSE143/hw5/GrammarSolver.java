// Kim-Long Do
// 2/10/2021
// CSE143
// TA: Chandan Hegde
// Take-Home Assessment #5
//
// This program takes inputs from a user and create random sentences based on those
// inputs.

import java.util.*;

public class GrammarSolver {
    private SortedMap<String, List<String[]>> mappingRules;

    // post: Initializes a new set of grammar rules for the list of rules passed in
    // pre: list of rules cannot be empty and there are no duplicate entries otherwise
    // pre: list of rules is in Backus-Naur form (BNF)
    // throw new IllegalArgumentException;
    public GrammarSolver(List<String> rules) {
        if(rules.isEmpty()) {
            throw new IllegalArgumentException();
        }

        mappingRules = new TreeMap<String, List<String[]>>();
        for(String lines : rules) {
            String[] nonTerminal = lines.split("::=");
            
            if(mappingRules.containsKey(nonTerminal[0])) {
                throw new IllegalArgumentException();
            }

            String[] removeSymbols = nonTerminal[1].split("\\|");
            List<String[]> terminal = new ArrayList<String[]>();
            for (int i = 0; i < removeSymbols.length; i++) {
                removeSymbols[i] = removeSymbols[i].trim();
                terminal.add(removeSymbols[i].split("\\s+"));
            }
            mappingRules.put(nonTerminal[0], terminal);
        }
    }

    // Returns true if symbol exists 
    public boolean grammarContains(String symbol) {
        return mappingRules.keySet().contains(symbol);
    }

    // Returns a string of symbols
    public String getSymbols() {
        return mappingRules.keySet().toString();
    }

    // pre: symbol exists and times is not negative, otherwise throws
    // new IllegalArgumentException;
    //post: returns a random list of the symbol passed in for a number of 'times' passed in;
    public String[] generate(String symbol, int times) {
        if(times < 0 || !grammarContains(symbol)) {
            throw new IllegalArgumentException();
        }

        String[] symbolString = new String[times];
        for (int i = 0; i < times; i++) {
            symbolString[i] = generateSymbol(symbol);
        }
        return symbolString;
    }

    // Creates a string of random occurrences for a symbol
    // Returns the randomly generated string of a symbol
    private String generateSymbol(String symbol) {
        String result = "";
        if(grammarContains(symbol)) {
            List<String[]> words = mappingRules.get(symbol);
            int rand = (int) (Math.random() * words.size());
            for(int i = 0; i < words.get(rand).length; i++) {
                result += generateSymbol(words.get(rand)[i]) + " ";
            }
            return result.trim();
        } else {
            return symbol;
        }
    }
}
