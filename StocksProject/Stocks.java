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
	private double rsi;
	private double prevRSI;
	
	/**
	 * Initialize the stocks algorithm.
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

	/*
	 * Returns the balance of the account.
	 */
	public double getBalance(){
		return this.balance;
	}
	
	public int getShares(){
		return this.shares;
	}
	
	/**
	 * Updates the statistical data based on the opening values that have been parsed.
	 */
	public void updateHeuristics() {
		double total = 0;
		ArrayList<Double> nums = new ArrayList<Double>();
		for(int i = 0; i < this.stockData.size(); i++){
			nums.add(this.stockData.get(i).getOpen());
		}
		for(int i = 0; i < this.stockData.size(); i++) {
			total = total + this.stockData.get(i).getOpen();
		}
		this.mean = total / this.stockData.size();
	}

	/**
	 * Calculates the RSI over n days, where n > 14
	 * @param days - Number of days preform calculations on
	 */
	public double calculateRSI(int days){
		double up_total = 0;
		double down_total = 0;
		if(days > 14){
			for(int i = 1; i < this.stockData.size(); i++){
				double change = this.stockData.get(i).getOpen() - this.stockData.get(i - 1).getOpen();
				if(change > 0){
					up_total = up_total + change;
				} else if(change < 0){
					down_total = down_total + Math.abs(change);
				}
				double avgUp = up_total / (double)days;
				double avgDown = down_total / (double)days;
				double rs = avgUp / avgDown;
				this.prevRSI = this.rsi;
				this.rsi = 100 - 100 / (1 + rs);
			}
		}
		return this.rsi;
	}
	
	/**
	 * Using the heuristics calculated previously and the previous days open, determine if the bot should buy, sell, or do nothing.
	 * @param day - Index of line being read
	 * @return - POSITIVE: Bought shares. NEGATIVE: Sold shares. ZERO: Did nothing.
	 */
	public int tradeEvaluator(int day) {
		int trade = 0;
		if(this.stockData.size() >= 2 & this.stockData.size() < 15) {
			this.updateHeuristics();
			StockData dayToEval = stockData.get(day);
			StockData yesterday = stockData.get(day - 1);
			if(dayToEval.getOpen() > yesterday.getOpen() || dayToEval.getOpen() > this.mean && this.shares >= 4) {
				//Probably good to sell!
				for(int i = 0; i < (int)this.shares / 2; i++){
					this.balance = this.balance + dayToEval.getOpen();
				}
				trade = trade - (int)this.shares / 2;
				this.shares = this.shares - (int)this.shares / 2;
			} else if(dayToEval.getOpen() < yesterday.getOpen() || dayToEval.getOpen() < this.mean && this.balance * 0.8 >= dayToEval.getOpen()) {
				//Probably good to buy! 20% of current balance.
				double to_buy = (this.balance * 0.8) / dayToEval.getOpen();
				for(int i = 0; i < (int)to_buy; i++){
					this.shares = this.shares + 1;
				}
				trade = trade + (int)to_buy;
				this.balance = this.balance - (this.balance * 0.8);
			}
		} else if(this.stockData.size() >= 15){
			this.updateHeuristics();
			this.calculateRSI(this.stockData.size());
			StockData dayToEval = stockData.get(day);
			StockData yesterday = stockData.get(day - 1);
			if(dayToEval.getOpen() > yesterday.getOpen() || dayToEval.getOpen() > this.mean || this.rsi > this.prevRSI && this.shares >= 4) {
				//Probably good to sell!
				for(int i = 0; i < (int)this.shares / 2; i++){
					this.balance = this.balance + dayToEval.getOpen();
				}
				trade = trade - (int)this.shares / 2;
				this.shares = this.shares - (int)this.shares / 2;
			} else if((dayToEval.getOpen() <= yesterday.getOpen()) || (dayToEval.getOpen() <= this.mean) || (this.rsi <= this.prevRSI) && this.balance * 0.8 >= dayToEval.getOpen()) {
				//Probably good to buy! 20% of current balance.
				double to_buy = (this.balance * 0.8) / dayToEval.getOpen();
				for(int i = 0; i < (int)to_buy; i++){
					this.shares = this.shares + 1;
				}
				trade = trade + (int)to_buy;
				this.balance = this.balance - (this.balance * 0.8);
			}
		}
		return trade;
	}

	/**
	 * A variation of the .simulation() method that buys or sells EVERYTHING depending only on RSI.
	 * @param day
	 * @return
	 */
	public int unsafeTrader(int day) {
		int trade = 0;
		if(this.stockData.size() >= 2 & this.stockData.size() < 15) {
			this.updateHeuristics();
			StockData dayToEval = stockData.get(day);
			StockData yesterday = stockData.get(day - 1);
			if(dayToEval.getOpen() > yesterday.getOpen() && this.shares >= 4) {
				//Probably good to sell!
				for(int i = 0; i < (int)this.shares; i++){
					this.balance = this.balance + dayToEval.getOpen();
				}
				trade = trade - this.shares;
				this.shares = 0;
			} else if(dayToEval.getOpen() < yesterday.getOpen() && this.balance >= dayToEval.getOpen()) {
				//Probably good to buy! 20% of current balance.
				double to_buy = this.balance / dayToEval.getOpen();
				for(int i = 0; i < (int)to_buy; i++){
					this.shares = this.shares + 1;
				}
				trade = trade + (int)to_buy;
				this.balance = 0;
			}
		} else if(this.stockData.size() >= 15){
			this.updateHeuristics();
			this.calculateRSI(this.stockData.size());
			StockData dayToEval = stockData.get(day);
			if(this.rsi > this.prevRSI && this.shares >= 4) {
				//Probably good to sell!
				for(int i = 0; i < (int)this.shares; i++){
					this.balance = this.balance + dayToEval.getOpen();
				}
				trade = trade - this.shares;
				this.shares = 0;
			} else if((this.rsi <= this.prevRSI) && this.balance >= dayToEval.getOpen()) {
				//Probably good to buy! 20% of current balance.
				double to_buy = this.balance / dayToEval.getOpen();
				for(int i = 0; i < (int)to_buy; i++){
					this.shares = this.shares + 1;
				}
				trade = trade + (int)to_buy;
				this.balance = 0;
			}
		}
		return trade;
	}

	
}
