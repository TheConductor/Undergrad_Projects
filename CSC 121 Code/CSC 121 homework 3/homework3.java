import java.util.Scanner;
public class homework3{
	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
		Double a,b,c,d,e,f,g,h,i,j,mean;
		System.out.println("what is the 1st number? ");
		a = keyboard.nextDouble();	
		System.out.println("what is the 2nd number? ");
		b = keyboard.nextDouble();		
		System.out.println("what is the 3rd number? ");
		c = keyboard.nextDouble();
		System.out.println("what is the 4th number? ");
		d = keyboard.nextDouble();	
		System.out.println("what is the 5th number? ");
		e = keyboard.nextDouble();	
		System.out.println("what is the 6th number? ");
		f = keyboard.nextDouble();
		System.out.println("what is the 7th number? ");
		g = keyboard.nextDouble();				
		System.out.println("what is the 8th number? ");
		h = keyboard.nextDouble();
		System.out.println("what is the 9th number? ");
		i = keyboard.nextDouble();				
		System.out.println("what is the 10th number? ");
		j = keyboard.nextDouble();	
		mean=(a+b+c+d+e+f+g+h+i+j)/10;
		System.out.println("the average of these 10 numbers is "+mean);
	}
}