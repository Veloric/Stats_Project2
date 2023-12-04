/**
 * 
 * @author Kyle Geddes
 *
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class StockImporter {
	
	public ArrayList<StockData> importData(String fileName){
		ArrayList<StockData> amazonData = new ArrayList<StockData>();
		try{
			File file = new File(fileName);
			Scanner reader = new Scanner(file);
			while(reader.hasNextLine()){
				String data = reader.nextLine();
				if(data != " "){
					String[] tokens = data.split(",");
					amazonData.add(new StockData(Double.parseDouble(tokens[1]), Double.parseDouble((tokens[2])), Double.parseDouble(tokens[3]), Double.parseDouble((tokens[4]))));
				}
			}
			reader.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		return amazonData;
	}

}
