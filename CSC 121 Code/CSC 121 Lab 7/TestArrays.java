import java.util.*;
import java.math.*;

public class TestArrays{
	
	public int squareArray(int num){
			num = num*num;
			return num;
	}// End SquareArray
	
	public double avgArray(int[] num){
		int i =0;
		double sum = 0;
		while(i<num.length){
			sum = sum + num[i];
			i++;
		}
		sum = sum/i;
		return sum;	
	}// End avgArray
	
	public double sqrRootArray(double num){
		num = Math.sqrt(num);
		return num;
	}
	
	public static void main(String[] args){
		int[] values, values2;
		double[] sqrRootValues;
		double avg = 0;
		Scanner scan = new Scanner(System.in);
		TestArrays obj = new TestArrays();
		values = new int[10];
		values2 = new int[values.length];
		sqrRootValues = new double[values.length];
		for(int i=0; i < values.length; i++){
			System.out.println("\nEnter value: " + (i + 1));
			values[i] = scan.nextInt();
		}// End for to get user's numbers for values
		for(int i=0; i < values.length; i++){
			System.out.print(values[i] + " ");
		}// End for to print values
		System.out.println("");
		System.out.println("values 2"); 
		for(int i=0; i < values.length; i++){
			values2[i] = values[i];
		}//End for to copy values to values2
		for(int i=0; i < values2.length; i++){
			System.out.print(values2[i] + " ");
		}// End for to print values2
		for(int i=0; i < values.length; i++){
			values[i] = obj.squareArray(values[i]);
		}
		System.out.println("");
		System.out.println("values squared"); 
		for(int i=0; i < values.length; i++){
			System.out.print(values[i] + " ");
		}// End for to print values
		System.out.println("");
		System.out.println("avg of values"); 
		avg = obj.avgArray(values);
		System.out.println(avg);
		System.out.println("avg of values2"); 
		avg = obj.avgArray(values2);
		System.out.println(avg);
		for(int i=0; i < values.length; i++){
			sqrRootValues[i] = obj.sqrRootArray(values[i]);
		}// End for to print values
		System.out.println("Square root of values");
		for(int i=0; i < sqrRootValues.length; i++){
			System.out.print(sqrRootValues[i] + " ");
		}// End for to print values
	}//end of main(..)
}//end of class
