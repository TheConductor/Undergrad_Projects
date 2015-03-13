public class quizQuestion3{
	public static void main(String[] args){
		int i = 2,j = 0;
		while(i<=201){
			System.out.print(i+" , ");
			i=i+2;
			j++;
			if(j%25==0){
				System.out.println("");
			}
		}
	}
}