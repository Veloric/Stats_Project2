/**
 * Tester Class for Project 2
 * @author Kyle Geddes
 */

 import java.util.ArrayList;

public class Tester {

    public static void main(String[] args){
        //Stocks Test
        StockImporter importer = new StockImporter();
        ArrayList<StockData> data = importer.importData("StocksProject/AMZN.csv");
        ArrayList<StockData> by_day = new ArrayList<StockData>();
        ArrayList<Double> rsiValues = new ArrayList<Double>();
        Stocks simulation = new Stocks(by_day, 20, 150000);
        for(int i = 0; i < data.size(); i++){
            by_day.add(data.get(i));
            simulation.updateData(by_day);
            if(i >= 14){
                 System.out.println("RSI: " + simulation.calculateRSI(i));
                 rsiValues.add(simulation.calculateRSI(i));
            }
            System.out.println("Bought / Sold: " + simulation.tradeEvaluator(i) + " shares at " + data.get(i).getOpen() + " | Remaining Balance: $" + simulation.getBalance() + " | Owned Shares: " + simulation.getShares());
            if(simulation.getBalance() < data.get(i).getOpen() && simulation.getShares() <= 0){
                System.out.println("Lost at the Stock Market :(");
                break;
            }
        }
        //PSS tests:
        ArrayList<Integer> PlotData = PSS.generateData(100, 1, 100);
        PSS.outputToFile(PlotData, "PSS\\plotData.csv");
        ArrayList<Integer> saltData = PSS.saltData(PlotData, 1, 10);
        PSS.outputToFile(saltData, "PSS\\saltedPlotData.csv");
        ArrayList<Integer> smoothed = PSS.smoothData(saltData, 3);
        PSS.outputToFile(smoothed, "PSS\\smoothedPlotData.csv");
        
    }
    
}
