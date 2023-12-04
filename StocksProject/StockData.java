/**
 * 
 * @author Kyle Geddes
 *
 */


public class StockData {
	
	private double high;
	private double low;
	private double open;
	private double close;
	
	public StockData(double high, double low, double open, double close) {
		this.high = high;
		this.low = low;
		this.open = open;
		this.close = close;
	}
	public double getHigh() {
		return this.high;
	}
	
	public double getLow() {
		return this.low;
	}
	
	public double getOpen() {
		return this.open;
	}
	
	public double getClose() {
		return this.close;
	}
	
	@Override
	public String toString() {
		return "High: " + this.high + " | Low: " + this.low + "| Open: " + this.open + "| Close: " + this.close;
	}

}
