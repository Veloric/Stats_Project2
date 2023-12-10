/**
 * Using JFreeCharts and Apache Stats Library, salts, smooths, and returns list of Y values.
 * @author Kyle Geddes
 */

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

// Tutoiral used: https://www.tutorialspoint.com/jfreechart/jfreechart_xy_chart.htm
public class PSSJars {

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

}
