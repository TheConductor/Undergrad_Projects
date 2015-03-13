import java.util.*;
public class ChapterThreeChallengeFour {
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		float test = -1, test1 =-1, test2 =-1, average =0;
		while (test < 00 || test > 110 ){ // 110 allows for extra credit
			System.out.print("Enter the first test score: "); 
			test = scan.nextFloat();
		}
		while (test1 < 00 || test1 > 110 ){ 
			System.out.print("Enter the Second test score: "); 
			test1 = scan.nextFloat();
		}
		while (test2 < 00 || test2 > 110 ){ 
			System.out.print("Enter the Third test score: "); 
			test2 = scan.nextFloat();
		}
		average = (test + test1 + test2)/3;
		if (average < 59){
			System.out.println("The average score is an F");
		}else if (average >60 && average <69){
			System.out.println("The average score is a D");
		}else if (average >70 && average <79){
			System.out.println("The average score is a C");
		}else if (average >80 && average <89){
			System.out.println("The average score is a B");
		}else{
			System.out.println("The average score is an A");	
		}
	}
}