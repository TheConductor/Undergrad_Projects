import java.util.*;
import java.io.*;

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
	
	public double getAvg(double[][] a){
		double sum = 0;
		int i=0;
		while(i<4){
			int j=0;
			while(j<7){
				sum = sum + a[i][j];
				j++;
			}
			i++;
		}
		sum = sum /28;
		return sum;
	}
	
	public static void main(String[] args) throws IOException{
 	 
		WeatherArrays obj = new WeatherArrays();
		Scanner scan = new Scanner(System.in);
		String city = "Wilmington NC", FileName;
		System.out.println("What is the name of the file you want to get a store the weather data in?");
		FileName = scan.next();
		double temp;
		double[][] temperatures = new double[4][7];
		System.out.println("Welcome to Sweet-Temperature International");
		System.out.println("==========================================");
//		System.out.println("What month is it? ");
//		month = scan.next();
//		System.out.println("Data for "+month+" in "+city);
		
		try{
			  FileInputStream fstream = new FileInputStream(FileName);
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  String month = "test", line = "test", line2 = "test", line3 = "test", line4 = "test";
			  int i = 0,j=0,k=0;
			  while ((strLine = br.readLine()) != null){
				  //System.out.println (strLine);
				  if(i==0){
					 month = strLine;
					 System.out.println(month);
				  }else{
					 temperatures[j][k]= java.lang.Integer.parseInt(strLine);
		    		 //System.out.println(temperatures[j][k]);
		    		 k++;
		    		 if(k==7){
		    			 k=0;
		    			 j++;
		    		 }
				  }
				  i++;
			  }
			  in.close();
			  }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
		}

/*
		int line = 0;
		while(line < 4){
			int row = 0;
			while(row <= 6){
				System.out.println("What was the temperature on week "+ line+", day "+row+" in "+city);
				temp = scan.nextInt();
				temperatures[line][row] = temp;
				System.out.println(temperatures[line][row]);
				String number = (temperatures[line][row]+", ");
				row++;
			}//End While	
			line++;
		}//End While
*/
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
		System.out.println("");
		double avg = obj.getAvg(temperatures);
		System.out.println("Average temperature for the entire month: "+avg);
		System.out.println("==========End of data for "+city+"========");
	}// End Main

}// End Class
