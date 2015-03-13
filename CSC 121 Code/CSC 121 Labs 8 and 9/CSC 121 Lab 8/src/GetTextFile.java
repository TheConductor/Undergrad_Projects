import java.util.*;
import java.io.*;
public class GetTextFile {

	public static void main(String[] args) {
		try{
			GetTextFile obj = new GetTextFile();
			String str = obj.getFileName();
			obj.printFileUsingScanner(str);
		}catch(Exception ex){};
	}// End Main
	
	public String getFileName(){
		Scanner  scan = new Scanner(System.in);
		String fileName;
		System.out.print("Please give a file name: ");
		fileName = scan.next();
		return fileName;
	}// End getFileName

	public void printFileUsingScanner(String myfile) throws FileNotFoundException,IOException{ 
		File aFile = new File(myfile);
		Scanner filePtr = new Scanner(aFile);
		int i = 1;
		while (filePtr.hasNextLine()){
			String str = filePtr.nextLine();
			System.out.println(i+": "+str);
			i++;
		}// End While Loop
	}// End printFileUsingScanner
	
/*	
	public void printFileUsingScanner(String myfile) throws FileNotFoundException,IOException{ 
		File myFile = new File(fileName);
		Scanner filePtr = new Scanner(myFile);
		while (filePtr.hasNextLine()){
			System.out.print(i+": ");
			i++;
		}
*/
}// End class
