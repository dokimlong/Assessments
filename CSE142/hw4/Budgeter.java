// Kim-Long Do
// 10/27/2020
// CSE142
// TA: Aiden Thaler
// Take-Home Assessment #4
//This program will help a user with their budget by prompting them to enter their total income
//and expenses to determine whether they spent more than they earned. 


import java.util.*;
public class Budgeter {

    public static final int DAYS_IN_MONTH = 31;
   
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        intro();
        double totalIncome = income(console);
        double totalExpense = expenses(console);
        double netIncome = difference(totalIncome-totalExpense);
        total(totalIncome, totalExpense);
        results(netIncome);       
    }

    //The intro will give the users directions to make the program work.
    public static void intro() {
        System.out.println("This program asks for your monthly income and");
        System.out.println("expenses, then tells you your net monthly income.");
        System.out.println();
    }

    //The income method will take in the console parameter to get the number of incomes the user
    //would like to input into the program. Then for each income, this method will go through the
    //userInput method and add the returned munny value to the totalIncome. At the end, 
    // totalIncome will be converted to standard USD conventions and returned to 
    //double totalIncome in main.
    public static double income(Scanner console) {
        System.out.print("How many categories of income? ");
        
        double numIncomes = console.nextDouble();
        double totalIncome = 0; 
        String type = "income";

        for (int i = 1; i<= numIncomes; i++) {
            System.out.print("    Next income amount? $");
            totalIncome += userInput(console, type); 
        }
        System.out.println();
        
        return difference(totalIncome);
    }

    //The expenses method will input a console parameter to get the user's input on what type of
    //expense they're using (1 for monthly and 2 for daily). Then for each expense, this method 
    //will go through the userInput method to get the amount from the user and adding the returned
    //value to totalExpense. At the end, totalExpense will be converted to standard USD 
    //conventions and returned double totalExpense in main.
    public static double expenses(Scanner console) {
        System.out.print("Enter 1) monthly or 2) daily expenses? ");
        int expensetype = console.nextInt();
        
        System.out.print("How many categories of expense? ");
        int categories = console.nextInt();
        
        double totalExpense =0;
        String type ="";
        
        if (expensetype == 1) { 
            type ="expense1";
        } else {
            type ="expense2";
        }
        for ( int i = 1; i<= categories; i++) {
            System.out.print("    Next expense amount? $");
            totalExpense += userInput(console, type);   
        } 
        System.out.println();
        
        return difference(totalExpense);
    }

    //userInput allows a user to put in the amount of income/expense into the program. If the
    //type is expense2 then there will be an additional calculation to extrapolate to the whole
    //month. After the type check the method will return the amount of munny back to the respective
    //income or expenses method. 
    public static double userInput(Scanner console, String type){
        double munny = console.nextDouble();
        if(type.equals("expense2")){
            munny = munny * DAYS_IN_MONTH;
        }
        return munny;
    }

    //This method takes in a double and converts them into a double with only two decimal places.
    //Then the value is returned to their respectively locations from which the parameter was
    //taken from. 
    public static double difference(double num) {
        return Math.round(num * 100.0) / 100.0;
    }

    //The total method will print out the total income and expense and calculate the daily amount
    //for each one.
    //The parameter "income" takes in the returned "totalIncome" in main while "expense" takes in
    // "totalExpense".
    public static void total(double income, double expense){
        double dailyIncome = difference(income / DAYS_IN_MONTH);
        double dailyExpense = difference(expense / DAYS_IN_MONTH);
        
        System.out.println("Total income = $" + income + " ($" + dailyIncome + "/day)" );
        System.out.println("Total expenses = $" + expense + " ($" + dailyExpense + "/day)");
        System.out.println();
    }

    //The method results calculates the net income after expenses and then tells the user
    //if they earned or spent more than than they should've.
    //The parameter "netIncome" is the difference between "totalIncome" and "totalExpense" 
    //rounded to two decimals.
    //The person is also now named Harry because I said so.
    public static void results(double netIncome){
        double absIncome = Math.abs(netIncome);
        
        if (netIncome > 0) {
            System.out.println("You earned $" + netIncome + " more than you spent this month.");
        } else{
            System.out.println("You spent $" + absIncome + " more than you earned this month.");
        }

        if (netIncome > 250) {
            System.out.println("You're a big saver.");
            System.out.println("You're an an accountant now, Harry.");
        } else if (netIncome > 0) {
            System.out.println("You're a saver.");
            System.out.println("You're becoming an adult, Harry.");
        } else if (netIncome <=-250) {
            System.out.println("You're a big spender.");
            System.out.println("Stop buying all the treacle tart, Harry!");
        } else { // -250< netincome <= 0
            System.out.println("You're a spender.");
            System.out.println("You're going broke, Harry.");
        }
    }
}
