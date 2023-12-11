/**
 * Using JFreeCharts and Apache Stats Library, salts, smooths, and returns list of Y values.
 * @author Kyle Geddes
 */

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.apache.commons.math3.stat.StatUtils;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class PSSJars {

    /**
     * Using: https://www.tutorialspoint.com/jfreechart/jfreechart_xy_chart.htm -- Spits out a plotted XY graph of the given data set.
     * @param data - Input data (Y - values)
     * @param title - Title of the data set as a String.
     */
    public static void plotData(ArrayList<Integer> data, String title){
        XYSeries dataSet = new XYSeries(title);
        for(int i = 0; i < data.size(); i++){
            dataSet.add(i, data.get(i));
        }
        XYSeriesCollection toGraph = new XYSeriesCollection(dataSet);
        JFreeChart xyLine = ChartFactory.createXYLineChart(title, "X - Axis", "Y - Axis", toGraph, PlotOrientation.VERTICAL, true, true, false);
        try{
            File xyImg = new File("PSS/" + title);
            ChartUtilities.saveChartAsJPEG(xyImg, xyLine, 1920, 1080);
        } catch(IOException e) {
            System.out.println("An error occured");
        }
    }


    /**
     * Adds a random value between two user set bounds to a given data point.
     * @param data - Input data
     * @param upper - Upper bound
     * @param lower - Lower bound
     * @return - Salted data set.
     */
    public static ArrayList<Integer> JarSalt(ArrayList<Integer> data, int upper, int lower){
        ArrayList<Integer> salted = new ArrayList<Integer>();
        for(int i = 0; i < data.size(); i++){
            int rnd = (int)((Math.random() * (upper - lower)) + lower);
            salted.add(data.get(i) + rnd);
        }
        return salted;
    }
    
    /**
     * Uses the StatsUtils class provided by https://commons.apache.org/proper/commons-math/userguide/stat.html
     * @param data - Input data
     * @param range - Range of values to compute a rolling mean on
     * @return - Smoothed data set.
     */
    public static ArrayList<Integer> JarSmooth(ArrayList<Integer> data, int range){
        ArrayList<Integer> smoothed = new ArrayList<Integer>();
        for(int i = 0; i < data.size(); i++){
            double[] values = new double[1 + range * 2];
            values[0] = (double)data.get(i);
            int pointer = 1;
            for(int j = 1; j <= range; j++){
                if(i + j < data.size()){
                    values[pointer] = (double)data.get(i + j);
                    pointer++;
                } else if(i - j >= 0){
                    values[pointer] = (double)data.get(i - j);
                    pointer++;
                }
            }
            int newVal = (int)StatUtils.mean(values);
            smoothed.add(newVal);
        }
        return smoothed;
    }
}
