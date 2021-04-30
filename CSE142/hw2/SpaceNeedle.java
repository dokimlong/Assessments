// Kim-Long Do
// 10/13/2020
// CSE142
// TA: Aiden Thaler
// Take-Home Assessment #2
//
//This program prints out a customizeable space needle based on the SIZE constant



public class SpaceNeedle {
	// SIZE is the scaling constant that can be changed to get varying sizes of the Space Needle
	public static final int SIZE = 4;

	public static void main(String[] args) {
		thinpart();
		pyramid();
		bottompyramid();
		thinpart();
		body();
		pyramid();
	}



// Thinpart built the needle of the Space Needle and the neck connecting to the body
	public static void thinpart() {
		for(int i= 1; i<=SIZE;i++) {
			for (int j= 1; j <= SIZE*3; j++) {
				System.out.print(" ");
			}	
			System.out.println("||");
		}
	}

// toppyramid builds the top part of the Space Needle's head, as well as the bottom of
// the Space Needle since it was exactly the same.	
	public static void pyramid() {
		for (int i = 1; i<=SIZE; i++) {
			for (int j = 1; j<= (SIZE - i)*3; j++) {
				System.out.print(" ");
			}
			System.out.print("__/");
			for( int j = 1; j< i; j++) {
				System.out.print(":::");
			}
			System.out.print("||");
			for(int j = 1; j < i; j++) {
				System.out.print(":::");
			}
			System.out.println("\\__");

		}
		System.out.print("|");
		for (int j = 1; j<=6*SIZE; j++) {
			System.out.print("\"");
		}
		System.out.println("|");
		
	}

// bottompyramid built the lower half of the Space Needle's head
	public static void bottompyramid() {
		for (int i = 1; i <= SIZE; i++) {
			for (int j =1; j < i; j++) {
				System.out.print("  ");
			}
			System.out.print("\\_");
			for (int j= 1; j<=(SIZE-i)*3+i+1; j++) {
				System.out.print("/\\");
			}
			System.out.println("_/");

		}
	}
	
// body created the midsection of the Space Needle
	public static void body () {
		for (int i = 1; i<=SIZE*SIZE;i++) {
			for (int j =1; j<= SIZE*2+1;j++) {
				System.out.print(" ");
			}
			System.out.print("|");
			for(int j = 1; j<= SIZE-2;j++) {
				System.out.print("%");
			}
			System.out.print("||");
			for(int j = 1; j<= SIZE-2;j++) {
				System.out.print("%");
			}
			System.out.println("|");
	
		}
	}
}
