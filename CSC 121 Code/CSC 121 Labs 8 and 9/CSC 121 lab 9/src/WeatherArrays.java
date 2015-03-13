import java.util.*;
public class WeatherArrays {
	public double getRowAverage(double[][] a, int index){
		int day = 0;
		double sum = 0;

		while (day < 7){
			sum = sum + a[index][day];
			day++;
		}//End While
		sum=sum/7;
		System.out.println("Average temperature for week "+index+" was: "+sum);
		return sum;
	}
	
	public double getColAverage(double[][] a, int index){
			int week = 0;
			double sum = 0;
			while(week<4){
				sum = (sum + a[week][index]);
				week++;
			}
			sum = sum/4;
			if (index == 0){
				System.out.println("Average temperature for Mondays: "+sum);
			}
			if (index == 1){
				System.out.println("Average temperature for Tuesday: "+sum);
			}
			if (index == 2){
				System.out.println("Average temperature for Wednesday: "+sum);
			}
			if (index == 3){
				System.out.println("Average temperature for Thursday: "+sum);
			}
			if (index == 4){
				System.out.println("Average temperature for Friday: "+sum);
			}
			if (index == 5){
				System.out.println("Average temperature for Saturday: "+sum);
			}
			if (index == 6){
				System.out.println("Average temperature for Sunday: "+sum);
			}
			return sum;
	}
	public static void main(String[] args) {
		WeatherArrays obj = new WeatherArrays();
		Scanner scan = new Scanner(System.in);
		String city;
		double temp;
		double[][] temperatures = new double[4][7];
		System.out.println("Welcome to Sweet-Temperature International");
		System.out.println("==========================================");
		System.out.println("What city are you in? ");
		city = scan.next();
		System.out.println("Data for "+city);

		int line = 0;
		while(line < 4){
			int row = 0;
			while(row <= 6){
				System.out.println("What was the temperature on week "+ line+", day "+row+" in "+city);
				temp = scan.nextInt();
				temperatures[line][row] = temp;
				System.out.println(temperatures[line][row]);
				row++;
			}//End While	
			line++;
		}//End While
		
		int i = 0;
		while(i < temperatures.length){
			double weekavg = obj.getRowAverage(temperatures, i);
			i++;
		}
		System.out.println("");
		int j = 0;
		while(j < temperatures[0].length){
			double dayavg = obj.getColAverage(temperatures, j);
			j++;
		}
		
		System.out.println("==========End of data for "+city+"========");
	}// End Main

}// End Class
