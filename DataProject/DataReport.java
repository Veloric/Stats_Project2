/**
 * @author Kyle Geddes
 * Simple class to handle data for the typed report.
 */

 import java.io.File;
 import java.util.ArrayList;
 import java.util.Scanner;
 import java.io.FileNotFoundException;
 
public class DataReport{
    public static ArrayList<NetflixShow> importData(String fileName){
		ArrayList<NetflixShow> amazonData = new ArrayList<NetflixShow>();
		try{
			File file = new File(fileName);
			Scanner reader = new Scanner(file);
			while(reader.hasNextLine()){
                int i = 0;
				String data = reader.nextLine();
				if(data != " "){
					String[] tokens = data.split(",");
					amazonData.add(new NetflixShow(i, Integer.parseInt(tokens[0]), Double.parseDouble(tokens[1])));
				}
                i++;
			}
			reader.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		return amazonData;
	}

    public static void main(String[] args){
        StatsLibrary lib = new StatsLibrary();
    	double result = lib.combine(50, 5);
    	System.out.println("All possible combinations for chosing 5 movies over an hour: " + result);
        

    }
}