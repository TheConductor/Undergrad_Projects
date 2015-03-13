import java.util.*;
import java.util.regex.*;
import java.io.*;
public class WordCount {

	public static void main(String[] args) {
		try{
			WordCount obj = new WordCount();
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
		String phrase = "Democrat";
		String phrase1 = "Republican";
		int i = 1;
		int j = 1;
		Pattern pattern =  Pattern.compile(phrase);
		Matcher matcher = null; 
		Pattern pattern1 =  Pattern.compile(phrase1);
		Matcher matcher1 = null; 
		while (filePtr.hasNextLine()){
			String str = filePtr.nextLine();
			matcher = pattern.matcher(str);   
            if(matcher.find()){  
            	//System.out.println(i+": "+str +"matcher");
            	i++;
            }
			matcher1 = pattern1.matcher(str);   
            if(matcher1.find()){  
            	//System.out.println(j+": "+str+"matcher1");
            	j++;
            }  
		}// End While Loop
		System.out.println("Democrat/Democratic is said "+i+" times");
		System.out.println("Republican is said "+j+" times"); 
	}// End printer	
}//End WordCount