import java.util.Scanner;

public class GCD {
	private int multiplierCounter = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		GCD obj = new GCD();
		System.out.println("What would you like A to be?");
		int input1 = scan.nextInt();
		System.out.println("What would you like B to be?");
		int input2 = scan.nextInt();
		// Insure that A is the larger number
		if (input1 < input2) {
			int c = input2; // Temporary storage variable
			input2 = input1;
			input1 = c;
		}// End If
		obj.gcd(input1, input2);
	}// End Main

	private void gcd(int a, int b) {
		System.out.println("A is " + a);
		System.out.println("B is " + b);
		int returnValue = 1;
		boolean AIsEven = false, BIsEven = false;
		if (a < b) {
			int c = b; // Temporary storage variable
			b = a;
			a = c;
		}// End If
			// Determine if the numbers are negative or positive
		if (a % 2 == 0) {
			AIsEven = true;
		} else {
			AIsEven = false;
		}// End If
		if (b % 2 == 0) {
			BIsEven = true;
		} else {
			BIsEven = false;
		}// End If
			// Rules
		if (b != 0) {
			if (AIsEven == true && BIsEven == true) {
				multiplierCounter = multiplierCounter + 1;
				a = a / 2;
				b = b / 2;
				gcd(a, b);
			}// End
			if (AIsEven == true && BIsEven == false) {
				a = a / 2;
				gcd(a, b);
			}// End
			if (AIsEven == false && BIsEven == true) {
				b = b / 2;
				gcd(a, b);
			}// End
			if (AIsEven == false && BIsEven == false) {
				int d = a;// Temporary storage variable
				a = (a + b) / 2;
				b = (d - b) / 2;
				gcd(a, b);
			}// End
		} else {
			System.out.println("DONE a is " + a + " b is " + b
					+ " multiplierCounter " + multiplierCounter);
			returnValue = a;
			if (multiplierCounter != 0) {
				int finalValue = (int) (Math.pow(2, multiplierCounter) * returnValue);
				System.out.println("The GCD is " + finalValue);
			} else {
				System.out.println("The GCD is " + returnValue);
			}// End If-Else
		}// End If-Else
	}// End gcd
}// End Class
