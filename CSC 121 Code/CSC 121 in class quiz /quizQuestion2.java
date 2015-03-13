public class quizQuestion2{
	public static void main(String[] args){
		int i=0, sumEven=0, sumOdd=0;
		while(i<=1000){
			if(i%2==0){
				sumEven= i + sumEven;
				i++;
			}else{
				sumOdd= i + sumOdd;
				i++;
			}
		}
		System.out.println("the sum of the even numbers is "+sumEven);
		System.out.println("the sum of the odd numbers is "+sumOdd);		
	}
}
			