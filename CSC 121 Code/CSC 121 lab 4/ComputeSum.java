import java.util.*;
public class ComputeSum{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int stop = 1;
		do {
			System.out.println("Type three zeros and press enter to stop or type one to contiune: ");
        		stop = scan.nextInt();
			if (stop == 000){
				System.out.println("The calculator will stop after this cycle.");
				total();
				average();
			}else{
				total();
				average();
			}
		}while (stop != 000);
	}
	public static void total() {
		Scanner scan = new Scanner(System.in);
		int start, mid, sum = 0;
		System.out.print("Enter first number: ");
        	start = scan.nextInt();
		System.out.print("Enter second number: ");
        	mid = scan.nextInt();
		if (start > mid) {
			for (int i = mid + 1; i < start; i++) {
				if (i % 5 == 0){
					sum = sum + i;
					System.out.println("the sum of start and mid its currently "+sum);
				}
			}
		}

		if (mid > start) {
			for (int i = start + 1; i < mid; i++) {
				if (i % 5 == 0){
					sum = sum + i;
					System.out.println("the sum of start and mid its currently "+sum);
				}
			}
		}
	}

	public static void average() {
		Scanner scan = new Scanner(System.in);
		int mid, last, sum = 0;
		System.out.print("Enter second number: ");
        	mid = scan.nextInt();
		System.out.print("Enter third number: ");
        	last = scan.nextInt();
		if (mid > last) {
			for (int i = last + 1; i < mid; i++) {
				if (i % 5 == 0 && i % 2 == 0){
					sum = sum + i;
					System.out.println("the sum of mid and last is currently "+sum);
				}
			}
		}
		if (last > mid) {
			for (int i = mid + 1; i < last; i++) {
				if (i % 5 == 0 && i % 2 == 0){
					sum = sum + i;
					System.out.println("the sum of mid and last is currently "+sum);
				}
			}
		}
	}

}