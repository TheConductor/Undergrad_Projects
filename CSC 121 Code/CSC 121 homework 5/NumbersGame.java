import java.util.*; // for scanner
public class NumbersGame {
	public static void main (String[] args) {
		int[] compArray, playerArray;
          	compArray = new int[3];
		playerArray = new int[3];     
		Random generator = new Random();
		compArray[0] = generator.nextInt(10);
		compArray[1] = generator.nextInt(10);
		compArray[2] = generator.nextInt(10);
		int guesses = 1, rightNumbers = 0, rightLocation = 0;
		int quit = 1;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to the Numbers Game");
		System.out.println("===========================");
		System.out.println("I have three digits.");
		System.out.println("Try to guess the digits in the order in which I listed them");
		System.out.println("For example, if you entered (1 2 3) and my numbers were (1 2 3),");
		System.out.println("Then, bravo! you are correct.");
		System.out.println("");
		System.out.println("However, if you enter (1 2 4), my response will be (2,2).");
		System.out.println("The first 2 means you have two of my three numbers.");
		System.out.println("The second 2 means you have both numbers in the correct positions.");
		System.out.println("So if you had enter (3 4 1) my answer will be (2 0).");
		System.out.println("If you had entered (5 7 0) my answer would be (0 0).");
		System.out.println("If you had enter (3 2 1) my answer would be (3 0).");
		System.out.println("If you had enter (1 3 2) my answer would be (3 1).");

		while (quit == 1) {
			System.out.println(compArray[0]);
			System.out.println(compArray[1]);
			System.out.println(compArray[2]);
			System.out.println("What is your first number? ");
			playerArray[0] = scan.nextInt();
			System.out.println("What is your second number? ");
			playerArray[1] = scan.nextInt();
			System.out.println("What is your third number? ");
			playerArray[2] = scan.nextInt();	
			if (playerArray[0] == compArray[0] || playerArray[0] == compArray[1] || playerArray[0] == compArray[2]){
				rightNumbers++;
			}
			if (playerArray[1] == compArray[0] || playerArray[1] == compArray[1] || playerArray[1] == compArray[2]){
				rightNumbers++;
			}
			if (playerArray[2] == compArray[0] || playerArray[2] == compArray[1] || playerArray[2] == compArray[2]){
				rightNumbers++;
			}
			if (playerArray[0] == compArray[0]){				
				rightLocation++;
			}
			if (playerArray[1] == compArray[1]){
				rightLocation++;
			}
			if (playerArray[2] == compArray[2]){
				rightLocation++;
			}
			if (rightNumbers == 3 && rightLocation == 3){
				System.out.println("Bravo!!!! "+ compArray[0] +","+ compArray[1] +","+ compArray[2] +" you got it!!! It took "+ guesses +" guess[es].");
				System.out.print("Play again? 1 to play again 0 to stop: ");
				quit = scan.nextInt();
				compArray[0] = generator.nextInt(10);
				compArray[1] = generator.nextInt(10);
				compArray[2] = generator.nextInt(10);
				rightNumbers = 0; 
				rightLocation = 0;		
			}else{
				System.out.println("("+rightNumbers+","+rightLocation+")");
				System.out.print("Play again? 1 to play again 0 to stop:  ");
				quit = scan.nextInt();
				guesses++;
				rightNumbers = 0; 
				rightLocation = 0;
			}
		}
	}
}