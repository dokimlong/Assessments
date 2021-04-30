// Kim-Long Do
// 11/24/2020
// CSE142
// TA: Aiden Thaler
// Take-Home Assessment #7
//Personality is a program that takes in a file with a list of names with the answers to their
//personality test in the line after a person's name. The program processes the answers, 
//getting the percentage of Bs for each of the four sections of the test and then assigns
//appropriate letters for each percentage. The results are printed into an output of the user's
//choosing.


import java.util.*;
import java.io.*;

public class Personality {

    public static final int DIMENSION = 4;

      
    public static void main(String[] arg) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        intro();

        //Prompts user for a file to process and a file to put the results within.
        System.out.print("input file name? ");
        Scanner inputFile = new Scanner(new File(console.nextLine()));
        System.out.print("output file name? ");
        PrintStream outputFile = new PrintStream(new File(console.nextLine()));

        //reads all the names and answers in the inputFile
        while(inputFile.hasNextLine()) {
            outputFile.print(inputFile.nextLine()+ ": ");
            String answers = inputFile.nextLine();
            int[] answersArray =  getAnswers(answers);
            int[] percentB = getPercentB(answersArray);
            String personalityOfPerson = getPersonality(percentB);
            
            getStatement(percentB, personalityOfPerson, outputFile);
       
        }
    } 

    //The intro method will give a prompt about how the program processes answers from
    //the personality test.
    public static void intro() {
        System.out.println("This program processes a file of answers to the");
        System.out.println("Keirsey Temperament Sorter. It converts the");
        System.out.println("various A and B answers for each person into");
        System.out.println("a sequence of B-percentages and then into a");
        System.out.println("four-letter personality type.");
        System.out.println();
    }

    //getAnswers takes in "answers" as parameter, which is the As and Bs line after a person's
    //name. The method then tallies each answer and put them into an array that is formatted
    //to fit the four sections of the personality test. E.g. questions 1, 8, 15, etc. are the
    //index 0(A) or 1(B) for section 1. Then the next two questions(2,3) will go into indexes 
    //2(A) or 3(B) for section 2, repeating for the two final sections. 
    //moveIndex helps move the index back to the first index in the array after 7 questions.
    //At the end the method will return the sortedAnswers array with the tallied answers into
    //answersArray array in main.
    public static int[] getAnswers(String input) {
        int[] sortedAnswers = new int[DIMENSION*2];
        int moveIndex = 0;
        int index = 0;
        String answers = "";

        for(int i = 0; i<input.length(); i++) {
            if(i % 7 == 0){
                moveIndex = 0;
            }
            index = (moveIndex+1)/2;
            answers = Character.toString(input.charAt(i));

            if(answers.equalsIgnoreCase("A")) {
                sortedAnswers[index*2] = sortedAnswers[index*2]+1;
            } else if (answers.equalsIgnoreCase("B")) {
                sortedAnswers[index*2+1] = sortedAnswers[index*2+1]+1;
            }
            moveIndex++;

        }
        return sortedAnswers;
    }

    //getPercentB takes in the answersArray array parameter, which is the formatted tallied As
    //and Bs. Then the method processes the number of Bs vs. the total questions for each
    //section, finding the nearest percentage that the Bs make up for a given section. 
    //At the end the percentageB array will be returned to main in the PercentB array.
    public static int[] getPercentB(int[] numB) {
        int[] percentageB = new int[DIMENSION];
        
        for(int i = 0; i<percentageB.length; i++) {
            percentageB[i] = (int) Math.round((double) numB[i*2+1] / (numB[i*2]+numB[i*2+1]) * 100);
        }
        return percentageB;
    }

    //getPersonality will take in the percentB array as a paramter. The the method will process
    //each percentage for each section and assign a letter from the "type" array to each 
    //section in the string "personality". Each section will have 2 unique letter options
    //with a third, "X", if the percentage of Bs equals 50. 
    //At the end, the string "personality" will be returned to main in the personalityOfPerson
    // string.
    public static String getPersonality(int[] percentB) {
        String personality = "";
        String[] type = {"E", "I", "S", "N", "T", "F", "J", "P"};

        for(int i = 0; i<percentB.length; i++) {
            if(percentB[i] > 50) {
                personality = personality + type[i*2+1];
            } else if (percentB[i] < 50) {
                personality = personality + type[i*2];
            } else if (percentB[i] == 50) {
                personality = personality + "X";
            }
        }
        return personality;
    }

    //getStatement will take in the percentB array, personalityOfPerson String(renamed 
    //personality), and the output file as parameters. Then the method will turn the array into
    //a string, and finally prints the percentage of B answers and the person's personality
    //in the output file. 
    public static void getStatement(int[] percentB, String personality, PrintStream outputFile) {
        outputFile.println(Arrays.toString(percentB)+ " = "+ personality);
    }
}
