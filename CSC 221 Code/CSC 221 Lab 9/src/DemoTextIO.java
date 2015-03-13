import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class DemoTextIO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data = 65;
		demoTextOutput(data, "textData.txt");
		
		String s = demoTextInput("textData.txt");
		System.out.println("Read in "+s);

	}

	private static String demoTextInput(String fileName) {
		File inFile = new File(fileName);
	    Scanner s = null;
		try {
			s = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	   
		return s.next();
		
	}

	private static void demoTextOutput(int data, String fileName) {
		File outFile = new File(fileName);
		
		PrintWriter textStream;
		try {
			textStream = new PrintWriter(outFile);
			textStream.print(data);
			textStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
