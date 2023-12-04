/**
 * 
 * @author Kyle Geddes
 *
 */
import java.util.ArrayList;

public class Stocks {
	private ArrayList<StockData> stockData;
	
	private int shares;
	private double balance;
	
	private double mean;
	private double variance;
	private double stdev;
	
	/**
	 * 
	 * @param data - parsed data from the csv.
	 * @param shares - How many shares we start with
	 * @param balance - How much money we start with
	 */
	public Stocks(ArrayList<StockData> data, int shares, double balance) {
		this.stockData = data;
		this.shares = shares;
		this.balance = balance;
	}
	
	/**
	 * Updates the arraylist containing parsed data.
	 * @param data - ArrayList of Stock data
	 */
	public void updateData(ArrayList<StockData> data) {
		this.stockData = data;
	}
	
	/**
	 * Updates the statistical data based on the opening values that have been parsed.
	 */
	public void updateHeuristics() {
		double total = 0;
		for(int i = 0; i < this.stockData.size(); i++) {
			total = total + this.stockData.get(i).getOpen();
		}
		this.mean = total / this.stockData.size();
		//TODO: Add in statsLibrary and calculate stdev, and variance.
	}
	
	/**
	 * Using the heuristics calculated previously and the previous days open, determine if the bot should buy, sell, or do nothing.
	 * @param day - Index of line being read
	 * @return - POSITIVE: Bought shares. NEGATIVE: Sold shares. ZERO: Did nothing.
	 */
	public int tradeEvaluator(int day) {
		int trade = 0;
		if(this.stockData.size() >= 2) {
			this.updateHeuristics();
			StockData dayToEval = stockData.get(day);
			StockData yesterday = stockData.get(day - 1);
			if(dayToEval.getOpen() > yesterday.getOpen() || dayToEval.getOpen() > this.mean && this.shares > 1) {
				//Probably good to sell!
				//TODO: Sell 50% of shares
			} else if(dayToEval.getOpen() < yesterday.getOpen() || dayToEval.getOpen() < this.mean && this.balance > dayToEval.getOpen()) {
				//Probably good to buy!
				//TODO: Buy shares with a percentage of account.
			} else {
			return trade;
			}
		}
		
		return trade;
	}
	
}
