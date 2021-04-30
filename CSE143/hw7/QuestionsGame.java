// Kim-Long Do
// 3/2/2021
// CSE143
// TA: Chandan Hegde
// Take-Home Assessment #7
//
// This program plays a guessing game known as 20 questions with the user where questions are 
// asked until a guess is made for the object on the user's mind.


import java.util.*;
import java.io.*;

public class QuestionsGame {
    private QuestionNode overallRoot; 
    private Scanner console;

    // post: Initializes the tree. 
    // The initial tree starts with "computer"
    public QuestionsGame() {
        overallRoot = new QuestionNode("computer");
        console = new Scanner(System.in);
    }

    // para: input -- a file containing a set of questions and answers
    // pre: file is legal and standard preorder formatting
    // post: replaces the current set of questions and answers with the ones within input 
    public void read(Scanner input) {
            overallRoot = readChoices(input);
    }

    // helper method of the read method
    // returns the root node back to read
    private QuestionNode readChoices(Scanner input) {
        String questionType = input.nextLine();
        String data = input.nextLine();
        QuestionNode root = new QuestionNode(data);

        if(questionType.contains("Q:")) {
            root.yes = readChoices(input);
            root.no = readChoices(input); 
        }

        return root;
    }

    // para: output -- a file that is used to store information
    // post: updates output with the current tree in standard preorder format
    public void write(PrintStream output) {
        write(output, overallRoot);
    }

    // helper method of the write method 
    private void write(PrintStream output, QuestionNode root) {
        if(!answer(root)) {
            output.println("Q:");
            output.println(root.data);
            write(output, root.yes);
            write(output, root.no);
        } else {
            output.println("A:");
            output.println(root.data);
        }
    }

    // post: Asks the user questions until a guess for the user's object is made.
    // If the guess is incorrect then user is prompted to help update the game with
    // question and answer for future games
    public void askQuestions() {
        overallRoot = askQuestions(overallRoot);
    }

    // helper method for askQuestions method
    // returns the root node back to public askQuestions
    private QuestionNode askQuestions(QuestionNode root) {
        if(!answer(root)) {
            if(yesTo(root.data)) {
                root.yes = askQuestions(root.yes);
            } else { 
                root.no = askQuestions(root.no);
            }
        } else {
            if(yesTo("Would your object happen to be " + root.data + "?")) {
                System.out.println("Great, I got it right!");
            } else {
                System.out.print("What is the name of your object? ");
                QuestionNode answer = new QuestionNode(console.nextLine());
                System.out.println("Please give me a yes/no question that");
                System.out.println("distinguishes between your object");
                System.out.print("and mine--> ");
                String userQuestion = console.nextLine();
                if(yesTo("And what is the answer for your object?")) {
                    root = new QuestionNode(userQuestion, answer, root);
                } else {
                    root = new QuestionNode(userQuestion, root, answer);
                }
            }
        }
        return root;
    }


    // Do not modify this method in any way
    // post: asks the user a question, forcing an answer of "y" or "n";
    // returns true if the answer was yes, returns false otherwise
    public boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

    // Helper method that checks if the node is an answer or not
    private boolean answer(QuestionNode node) {
        return (node.yes == null || node.no == null);
    }
}
