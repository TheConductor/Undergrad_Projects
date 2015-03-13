import java.util.*;
public class ChapterFourChallengeTwo {
	public static void main (String[] args){
		float speed = -1, time = -1, distance = 0, counter = 0;
		Scanner scan = new Scanner(System.in);
		while (speed < 0) {
			System.out.println("How fast was the vehicle traveling in Miles per Hour? ");
			speed = scan.nextFloat();
		} 

		while(time < 0) {
			System.out.println("How long did the vehicle travel in hours? ");
			time = scan.nextFloat();
		} 
		
		do {
			distance = speed * counter;
			System.out.println(counter+" hours "+distance+" miles.");
			counter++;
		}while ( counter < time + 1);	
	}
}