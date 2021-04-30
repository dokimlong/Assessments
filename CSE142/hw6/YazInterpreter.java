// Kim-Long Do
// 11/17/2020
// CSE142
// TA: Aiden Thaler
// Take-Home Assessment #6
//YazInterpreter is a program that will read a file, formatted a specific way, and will translate
//every line within the file. Users will input the files they want translated, the file they
//want to put the output in, and then can translate more files (I)/look at previous outputs (V) 
//until they would like to quit the program (Q). 



import java.util.*;
import java.io.*;

public class YazInterpreter {

    public static void main(String[] arg) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        intro();
        String choice = "";

        while (!choice.equals("Q")) {
            choice = makeChoice(console);
            
            if(choice.equals("I") || choice.equals("V")) {
                File checkedFile = checkExist(console);
                Scanner input = new Scanner(checkedFile);
            
                if (choice.toUpperCase().equals("I")) {  
                    System.out.print("Output file name: ");
                    String yazOutput = console.nextLine();
                    PrintStream outputName = new PrintStream(new File(yazOutput));
          
                    interpret(input, outputName);
                    System.out.println("YazLang interpreted and output to a file!");
                    System.out.println();

                } else if (choice.toUpperCase().equals("V")) {
                    readFile(input);
                }
            }
        }       
    }

    //intro message gives the user an introduction to the program and what they can do.
    public static void intro() {
        System.out.println("Welcome to YazInterpreter!");
        System.out.println("You may interpret a YazLang program and output");
        System.out.println("the results to a file or view a previously");
        System.out.println("interpreted YazLang program.");
        System.out.println();
    }

    //This method will take in the scanner console to prompt the user for which category they
    //would like and then return the uppercase version back to "choice" in main. If the user
    //doesn't input I, V, or Q, then the main method will run this method again.
    public static String makeChoice(Scanner console) {
        System.out.print("(I)nterpret YazLang program, (V)iew output, (Q)uit? ");
        String choice = console.nextLine();
        choice = choice.toUpperCase();
        return choice;
    }

    //checkFile will take in the console scanner where it prompts a user to enter the name
    //of a file. If the file does not exist then the method will repprompt the user until
    //an existing file can be found. 
    //At the end, the "check" file will be returned to main in the checkedFile file. 
    public static File checkExist(Scanner console) {
        System.out.print("Input file name: ");
        String yazFile = console.nextLine();
        File check = new File(yazFile);
        while(!check.exists()) {
            System.out.print("File not found. Try again: ");
            yazFile = console.nextLine();
            check = new File(yazFile);
        }
        return check;

    }

    //The interpret method takes in two parameters: input and output. Input is a Scanner that
    //reads a file that has been checked to exist in the directory. The method interpret
    //will then read each line from the input parameter and depending on what the 
    //command(type) is on each line, interpret will read the rest of the line using the respective
    //if statements to call a method that takes in the Scanner "words" and the output file 
    //as parameters. 
    //The output file is created by the user back in the main method, after they chose an 
    //input file that exists. 
    //After going through the appropriate method, the method will print the results into the
    //output file on a new line.
    public static void interpret(Scanner input, PrintStream output) {
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Scanner words = new Scanner(line);
            String type = words.next();
            
            if(type.equals("RANGE")) {
                output.println(range(words));
            
            } else if(type.equals("CONVERT")) {
                output.println(convert(words));
            
            } else if (type.equals("REPEAT")) {
                output.println(repeat(words));
            }
        }
    }

    //The method range will run if the command on the line was "RANGE". This method takes in 
    //the Scanner "words" parameter from the interpret method and renamed it to numRange, 
    //which reads and translate the rest of the line from the scanner. The PrintStream "output"
    //is where the results is printed in.
    //At the end the numSequence is returned to the interpret method where the results will
    //be printed in the output file.
    public static String range(Scanner numRange) {
    int num = numRange.nextInt();
        int maxNum = numRange.nextInt();
        int multiplier = numRange.nextInt();
        String numSequence = "";

        //Just a bit odd but I tried to make it so that I don't end up with an extra
        //space at the end, fenceposting I believe, and I failed the testcases.
        while(num < maxNum) {
            numSequence += num + " ";
            num = num + multiplier;
        }
        return numSequence;    
    }

    //The method range will run if the command on the line was "CONVERT". This method takes in 
    //the Scanner "words" parameter from the interpret method and renamed it to weather, 
    //which reads and translate the rest of the line from the scanner. 
    //At the end convertedTemp will be returned back to the interpret method and
    //printed into the output file.
    public static String convert(Scanner weather) {
        int temp = weather.nextInt();
        String tempUnit = weather.next();
        int convertTemp = 0;
        String convertedTemp = "";

        if (tempUnit.toUpperCase().equals("C")) {
            convertTemp = (temp * 9/5) + 32;
            tempUnit = "F";
        } else {
            convertTemp = (temp -32) * 5/9;
            tempUnit = "C";
        }
        convertedTemp = convertTemp + tempUnit; 
        return convertedTemp;       
    }

    //The method range will run if the command on the line was "REPEAT". This method takes in 
    //the Scanner "words" parameter from the interpret method, retaining the same name in the  
    // method. The repeat method will then read and translate the rest of the line that scanner
    //words started. 
    //At the end sequenceWords will be returned back to the interpret method to be printed
    //in the output file.
    public static String repeat(Scanner words) {
        String sequenceWords = "";
        
        while(words.hasNext()) {
            String repeatWord = words.next();
            int repeatNum = words.nextInt();

            repeatWord = repeatWord.substring(1, repeatWord.length()-1).replace("_"," ");
            int repeatAgain = 0;

            while( repeatAgain != repeatNum) {
                sequenceWords += repeatWord;
                repeatAgain++;
            }                
        }
        return sequenceWords;        
    }

    //The interpret method takes in the parameter input. Input is a Scanner that reads a
    //file that has been checked to exist in the directory. The method interpret will then
    //read each line from the input parameter and print each line out in the terminal.
    public static void readFile(Scanner input) {
        System.out.println();
        while(input.hasNextLine()){
            String line = input.nextLine();
            System.out.println(line);
        }
        System.out.println();
    }
}
