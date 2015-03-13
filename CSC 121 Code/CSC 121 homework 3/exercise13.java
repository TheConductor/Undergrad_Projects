import java.util.Scanner;
public class exercise13{
	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
		Double bill, tax, tip;
		System.out.println("How much was the bill?");
		bill = keyboard.nextDouble();
		tax = bill * .0675;
		tip = (bill + tax) * .15;
		System.out.println ("The tax is "+tax+" and the tip is "+tip);
	}
}