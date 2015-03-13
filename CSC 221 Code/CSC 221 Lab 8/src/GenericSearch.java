import java.util.Random;


public class GenericSearch {

	
	public static void main(String[] args) {
		
		Integer [] intArray = new Integer[100];
		Float [] floatArray = new Float[100];
		Double [] doubleArray = new Double[100];
		Pumpkin [] pumpkinArray = new Pumpkin[100];
		Random r = new Random();
		
		for(int i=0; i < 100; i++ ) {
			int data = r.nextInt(100);
			
			intArray[i] = new Integer(data);
			floatArray[i] = new Float(data);
			doubleArray[i] = new Double(data);
			pumpkinArray[i] = new Pumpkin(data);
		}// End for
		
		int searchValue = r.nextInt(10);
		
		//Either all of the following are true, or they are all false
		System.out.println(find(intArray, new Integer(searchValue))); 
		System.out.println(find(floatArray, new Float(searchValue))); 
		System.out.println(find(doubleArray, new Double(searchValue))); 
		System.out.println(find(pumpkinArray, new Pumpkin(searchValue))); 
		
		
		//All of the following lines should produce the same output
		System.out.println(countGreater(intArray, new Integer(searchValue))); 
		System.out.println(countGreater(floatArray, new Float(searchValue))); 
		System.out.println(countGreater(doubleArray, new Double(searchValue))); 
		System.out.println(countGreater(pumpkinArray, new Pumpkin(searchValue)));
	}// End Main

	//Define ONE find method here that will work with all the arrays above
	private static boolean find(Object [] t, Object p){
		boolean returnValue = false;
		for(int i=0; i<t.length; i++){
			if(t[i].equals(p)){
				System.out.println(p+" was found!");
				returnValue = true;
			}// End If
		}// End For
		if(returnValue==false){
			System.out.println(p+" was not found.");
		}
		return returnValue;
	}// End find
	
	private static int countGreater(Comparable [] t, Comparable p){
		int returnValue = 0;
		for(int i=0; i<t.length; i++){
			if(t[i].compareTo(p) > 0){
				returnValue++;
			}// End If
		}// End for
		return returnValue;
	}// End countGreater

	//Define ONE countGreater method that will work with all the arrays above
}// End Class
