// Kim-Long Do
// 10/13/2020
// CSE142
// TA: Aiden Thaler
// Take-Home Assessment #2
//
// This program prints out a ship from one of those retro galatic invaders games or an 
// upside down heart, whichever way the viewer would like to look at it.

public class AsciiArt {
    public static void main(String[] args) {
        spaceshipbody();
        spaceshipbottom();
    }




    
//spaceshipbody prints out the top of the spaceship
    public static void spaceshipbody()   {
        for (int i = 0; i <= 9; i++) {
            for (int j = 1; j <= 10 - i; j++) {
                System.out.print("  ");
            }
            System.out.print("/");
            for (int j = 0; j <= 4*i - 1; j++) {
                System.out.print(":");
            }
            System.out.print("\\");
            System.out.println();
        }
    }

//spaceshipbottom prints out the bottom part of the spaceship
    public static void spaceshipbottom() {
        for (int i = 0; i <=4; i++) {
            for (int j = 0; j<=i;j++) {
                System.out.print("  ");    
            }
            System.out.print("\\");
            for (int j = 1; j <= 8 - i*2; j++) {
                System.out.print("::");
            }
            System.out.print("/");
            for (int j=0; j<=i*4+1; j++) {
                System.out.print(" ");
            }
            System.out.print("\\");
            for (int j=1; j<=8-i*2; j++) {
                System.out.print("::");
            }
            System.out.print("/");
            System.out.println();
        }
    }        
}
