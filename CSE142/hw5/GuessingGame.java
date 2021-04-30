// Kim-Long Do
// 11/04/2020
// CSE142
// TA: Aiden Thaler
// Take-Home Assessment #5
//This program is a guessing game that will prompt the user to guess a randomly generated number.
//The user may choose to play again until they no longer wish to, at which point the program
//will print out their results at the end. 



import java.util.*;

public class GuessingGame {
   
    public static final int MAX_VALUE = 100; 

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Random rand = new Random();
        intro();
       
        int totalGame = 0;
        int totalGuess = 0;
        int bestGame = 1000000;
        int currentGuess = 0;
        boolean playAgain = true;

        //Although not a method thought it would be easiest to explain this part
        //The while loop in main will initiate games and end when the user wants to stop playing. 
        //While playing, the loop will update the total number of games, total guesses, and
        //which guess was the lowest. 
        while (playAgain) {
            currentGuess = playGame(console, rand);
            totalGuess += currentGuess;

            if (currentGuess < bestGame) {
                bestGame = currentGuess;
            }          
            totalGame++;
            System.out.print("Do you want to play again? ");
            String decision = console.next();
            System.out.println();

            if(decision.toLowerCase().startsWith("y")) {
                playAgain = true;
            } else {
                playAgain = false;
            }
        }     
        statistics(totalGame, totalGuess, bestGame);
    }

    //This method is an introduction to the program via a haiku 
    public static void intro() {
        System.out.println("Let us play a game");
        System.out.println("You will be guessing numbers");
        System.out.println("Lowest guesses win");
        System.out.println();
    }

    //playGame takes in the Scanner console, which takes in input from a user and the 
    //parameter Random rand, which generates a random number based on the MAX_VALUE constant.
    //playGame will generate a random number from 1 to MAX_VALUE and prompts the user 
    //to guess the number. If their guess is wrong, the program will give a hint as to 
    //whether the value is higher or lower respectively. At the end, guesses will be returned
    //back to main in the currentGuess variable.
    public static int playGame(Scanner console, Random rand){
        System.out.println("I'm thinking of a number between 1 and " + MAX_VALUE + "...");

        int num = rand.nextInt(MAX_VALUE)+1;
        int guesses = 0;
        boolean numGuessed = false;

        while (!numGuessed){
            System.out.print("Your guess? ");
            int guess = console.nextInt();
            guesses++;

            if ( guess == num){
                numGuessed = true;

            } else if( guess < num) {
                System.out.println("It's higher.");
            } else {
                System.out.println("It's lower.");
            }
        }

        if( guesses == 1) {
            System.out.println("You got it right in " + guesses + " guess!");
        } else { 
            System.out.println("You got it right in " + guesses + " guesses!");
        }
        return guesses;
    }

    //statistics takes in parameters from the main method where each variable has the same 
    //respective names. totalGame will be used to calculate the average number of guesses and 
    //number of games, totalGuess will also be used to find the average number of guesses, as
    //well as the total guesses, and the bestGame is the game with the lowest number of guesses
    //needed to get the correct number.
    public static void statistics(int totalGame, int totalGuess, int bestGame) {
        double averageGuess = 1.0 * totalGuess/totalGame;
        System.out.println("Overall results:");
        System.out.println("Total games   = " + totalGame);
        System.out.println("Total guesses = " + totalGuess);
        System.out.println("Guesses/game  = " + round1(averageGuess));
        System.out.println("Best game     = " + bestGame);
    }

    //round1 takes in the averageGuess parameter from the statistics method and rounds to
    //the nearest tenth. The method will then return that rounded number to statistics.
    public static double round1 (double num) {
        return Math.round(num * 10.0 ) / 10.0;
    }
}
