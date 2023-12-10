/**
 * Generates random data, spits it out in a .csv -- then can preform salting, and smoothing, and putting them in respective CSVs.
 * @author Kyle Geddes
 */

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PSS {
    
    /**
     * Generates random Integers
     * @param n - Number of integers to generate
     * @param lower - Starting value for range of random numbers
     * @param upper - Ending value for range of random numbers. (lower - upper)
     * @return - An arraylist containing the randomly generated integers.
     */
    public static ArrayList<Integer> generateData(int n, int lower, int upper){
        ArrayList<Integer> data = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            int num = (int)((Math.random() * (upper - lower)) + lower);
            data.add(num);
        }
        return data;
    }

    /**
     * Salts data by adding a random number generated from given lower and upper bounds.
     * @param data - Data to salt
     * @param lower - Lower bound
     * @param upper - Upper bound
     * @return - An ArrayList of Salted data points (Y - values)
     */
    public static ArrayList<Integer> saltData(ArrayList<Integer> data, int lower, int upper){
        ArrayList<Integer> salted = new ArrayList<Integer>();
        for(int i = 0; i < data.size(); i++){
            int rnd = (int)((Math.random() * (upper - lower)) + lower);
            salted.add(data.get(i) + rnd);
        }
        return salted;
    }

    /**
     * Smooths data in a given set (of integers).
     * @param data - ArrayList of integers to smooth.
     * @param range - +- range for any given point. Ex: Taking the left most 2 points and right most 2 points.
     * @return - An ArrayList of new, smoothed data values (Y - values)
     */
    public static ArrayList<Integer> smoothData(ArrayList<Integer> data, int range){
        ArrayList<Integer> smoothed = new ArrayList<Integer>();
        for(int i = 0; i < data.size(); i++){
            int point = data.get(i);
            int lower_total = 0;
            int upper_total = 0;
            int points = 1;
            // Traverse lower and upper, without going out of bounds.
            if(i!= 0 && i != data.size() - 1){
                for(int j = 1; j <= range; j++){
                    if(data.get(i - j) != null){
                        points++;
                        lower_total = lower_total + data.get(i - j);
                    }
                    if(data.get(i + j) != null){
                        points++;
                        upper_total = upper_total + data.get(i + j);
                    }
                }
            } else if(i == 0){
                for(int j = 1; j <= range; j++){
                    if(data.get(i + j) != null){
                        points++;
                        upper_total = upper_total + data.get(i + j);
                    }
                }
            } else if(i == data.size() - 1){
                for(int j = 1; j <= range; j++){
                    if(data.get(i - j) != null){
                        points++;
                        lower_total = lower_total + data.get(i - j);
                    }
                }
            }
            int newVal = upper_total + lower_total + point / points;
            smoothed.add(newVal);
        }
        return smoothed;
    }

    /**
     * Outputs an ArrayList of Integers to a specificed file.
     * @param data - ArrayList of Integers
     * @param fileName - String of the file name.
     * @return - True if and only if the file was written to without error, false otherwise.
     */
    public static boolean outputToFile(ArrayList<Integer> data, String fileName){
        boolean success = false;
        try{
            File file = new File(fileName);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for(int i = 0; i < data.size(); i++){
                writer.write(data.get(i));
            }
            success = true;
            writer.close();
        } catch(IOException e) {
            System.out.println("An error occured");
        }
        return success;
    }

}
