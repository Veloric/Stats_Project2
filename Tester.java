/**
 * Tester Class for Project 2
 * @author Kyle Geddes
 */

 import java.util.ArrayList;

public class Tester {

    public static void main(String[] args){
        StockImporter importer = new StockImporter();
        ArrayList<StockData> data = importer.importData("StocksProject/AMZN.csv");
        ArrayList<StockData> by_day = new ArrayList<StockData>();
        Stocks simulation = new Stocks(by_day, 20, 100000);
        for(int i = 0; i < data.size(); i++){
            by_day.add(data.get(i));
            simulation.updateData(by_day);
            System.out.println("RSI: " + simulation.calculateRSI(i));
            System.out.println("Bought / Sold: " + simulation.tradeEvaluator(i) + " shares at " + data.get(i).getOpen() + " | Remaining Balance: $" + simulation.getBalance() + " | Owned Shares: " + simulation.getShares());
            if(simulation.getBalance() < data.get(i).getOpen() && simulation.getShares() <= 0){
                System.out.println("Lost at the Stock Market :(");
                break;
            }
        }
    }
    
}
