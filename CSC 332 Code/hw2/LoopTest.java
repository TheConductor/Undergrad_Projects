// Alex Aziz, 9/7/11, For loop runtime test.
import java.util.Scanner;

public class LoopTest {
	public static void main(String[] args) {
		LoopTest obj = new LoopTest();
		System.out.print("What would you like 'n' to be? ");
		Scanner scan = new Scanner(System.in);
		System.out.println("N" + " Count" + " Time");
		obj.Loop1();
		obj.Loop2();
		obj.Loop3();
		obj.Loop4();
		obj.Loop5();
		obj.Loop6();
	}// End Main

	public void Loop1() {
		long sum = 0;
		for (long n = 1; n < 1000; n += 50) {
			long startTime = System.nanoTime();
			for (int i = 0; i < n; i++) {
				sum++;
			}// End For
			long endTime = System.nanoTime();
			System.out.println(n + " " + sum + " " + (endTime - startTime));
		}// End For
	}// End Loop1

	public void Loop2() {
		long sum = 0;
		for (long n = 1; n < 1000; n += 50) {
			long startTime = System.nanoTime();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					sum++;
				}// End For
			}// End For
			long endTime = System.nanoTime();
			System.out.println(n + " " + sum + " " + (endTime - startTime));
		}// End For
	}// End Loop2

	public void Loop3() {
		long sum = 0;
		for (long n = 1; n < 1000; n += 50) {
			long startTime = System.nanoTime();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n * n; j++) {
					sum++;
				}// End For
			}// End For
			long endTime = System.nanoTime();
			System.out.println(n + " " + sum + " " + (endTime - startTime));
		}// End For
	}// End Loop3

	public void Loop4() {
		long sum = 0;
		for (long n = 1; n < 1000; n += 50) {
			long startTime = System.nanoTime();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					sum++;
				}// End For
			}// End For
			long endTime = System.nanoTime();
			System.out.println(n + " " + sum + " " + (endTime - startTime));
		}// End For
	}// End Loop4

	public void Loop5() {
		long sum = 0;
		for (long n = 1; n < 1000; n += 50) {
			long startTime = System.nanoTime();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i * i; j++) {
					for (int k = 0; k < j; k++) {
						sum++;
					}// End For
				}// End For
			}// End For
			long endTime = System.nanoTime();
			System.out.println(n + " " + sum + " " + (endTime - startTime));
		}// End For
	}// End Loop5

	public void Loop6() {
		long sum = 0;
		for (long n = 1; n < 1000; n += 50) {
			long startTime = System.nanoTime();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i * i; j++) {
					if (j % i == 0) {
						for (int k = 0; k < j; k++) {
							sum++;
						}// End For
					}// End If
				}// End For
			}// End For
			long endTime = System.nanoTime();
			System.out.println(n + " " + sum + " " + (endTime - startTime));
		}// End For
	}// End Loop6
}// End LoopTest
