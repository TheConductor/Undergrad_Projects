import java.util.*;
public class ChapterFourChallengeTwelve {
	public static void main (String[] args){
		float sales = 0, sales1 = 0, sales2 = 0, sales3 = 0, sales4 = 0, counter = 0, counter1 = 0, counter2 = 0, counter3 = 0, counter4 = 0;
		String graph;
		graph = "*";
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter today's sales for store 1: ");
		sales = scan.nextFloat();
		System.out.println("Enter today's sales for store 2: ");
		sales1 = scan.nextFloat();
		System.out.println("Enter today's sales for store 3: ");
		sales2 = scan.nextFloat();
		System.out.println("Enter today's sales for store 4: ");
		sales3 = scan.nextFloat();
		System.out.println("Enter today's sales for store 5: ");
		sales4 = scan.nextFloat();
		System.out.print("Store 1: ");
		while ((sales - counter) > 100) {
			System.out.print(graph);
			counter = counter + 100;
		}
		System.out.println("");
		System.out.print("Store 2: ");
		while ((sales1 - counter1) > 100) {
			System.out.print(graph);
			counter1 = counter1 + 100;
		}
		System.out.println("");
		System.out.print("Store 3: ");
		while ((sales2 - counter2) > 100) {
			System.out.print(graph);
			counter2 = counter2 + 100;
		}
		System.out.println("");
		System.out.print("Store 4: ");
		while ((sales3 - counter3) > 100) {
			System.out.print(graph);
			counter3 = counter3 + 100;
		}
		System.out.println("");
		System.out.print("Store 5: ");
		while ((sales4 - counter4) > 100) {
			System.out.print(graph);
			counter4 = counter4 + 100;
		}
		System.out.println("");
	}
}