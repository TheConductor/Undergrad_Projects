import java.util.*;
public class PrimeNumbers {
	public static void main(String[] args){
		int n = 0;
		do {
			System.out.println("Enter an integer > 2");
			Scanner scan = new Scanner(System.in);
			n = scan.nextInt();
		}while (n<2);
		
		isPrime(n);
		if (s = true){
			System.out.println(n + " is not prime");
		}else{
			System.out.println(n +" is prime");
		}
	}
	public static boolean isPrime(int n) {
		int counter = 1,y = 0, otherCounter = 1;
		boolean s = true;
		while (counter < (n-1)){
			counter++;
			if (n % counter == 0) {
				y++;
			}
		}
		if (y == 1){
			s = true;
		}else {
			s = false;
		}
		return s;
	}
}

