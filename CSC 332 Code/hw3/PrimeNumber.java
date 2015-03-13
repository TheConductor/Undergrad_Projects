import java.util.Scanner;

public class PrimeNumber {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("What would you like to test for primality? ");
		long startTime = System.nanoTime();
		double userInput = scan.nextInt();
		double absValue = Math.abs(userInput);
		double testValue = Math.sqrt(absValue);
		boolean isPrime = true;
		for (double i = 2; i < testValue; i++) {
			if (absValue % i == 0) {
				isPrime = false;
				i = absValue;
			}// End While
		}// End For
		long endTime = System.nanoTime();
		System.out.println("The statment: '" + userInput
				+ " is a prime number'. Is " + isPrime + " " +(endTime - startTime));
	}// End Main
}// End Class
