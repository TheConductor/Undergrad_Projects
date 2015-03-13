import java.util.*;
public class ChapterThreeChallengeTwo {
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		int day = 0, month = 0, year = -1;
		while (day < 1 || day > 31){
			System.out.print("Pick a day: "); 
			day = scan.nextInt();
		}
		while (month < 1 || month > 12){
			System.out.print("Pick a month: ");
			month = scan.nextInt();
		}
		while (year < 00 || year > 99 ){
			System.out.print("Pick a year: "); 
			year = scan.nextInt();
		}
		if (day * month == year){
			System.out.println(month+"/"+ day+"/"+year+" is a magic date");
		}else{
			System.out.println(month+"/"+day+"/"+year+" is not a magic date");
		}
	}
}
		 
		
		 
		