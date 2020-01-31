package root;

/**
 * This Stock class creates a stock and keep its quotes
 * @author Jeremy Thai
 *
 */
public class Stock {

	private String symbol;
	private String companyName;
	private double valuePerShare;
	private String dayRange;

/**
 * Constructor
 * @param symbol symbol of stock
 */
	public Stock(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * Prints out information about the stock
	 */
	public void printInfo() {

		System.out.println(
				"Company Name: " + companyName +  "\n" +
						"Value Per Share: $" + valuePerShare + "\n" + 
						"This stock changed by: $" +dayRange + " today");
	}


//-----------------------------------GETTERS --------------------------------

	public String getSymbol() {
		return symbol;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public double getValuePerShare() {
		return valuePerShare;
	}
	
	public String getDayRange() {
		return dayRange;
	}
	
	
	//-----------------------------------SETTERS -----------------------------

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public void setValuePerShare(double valuePerShare) {
		this.valuePerShare = valuePerShare;
	}
	
	public void setDayRange(String dayRange) {
		this.dayRange = dayRange;
	}

}
