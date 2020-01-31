package root;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * This Scraper class handles the web scraping of stock data.
 * @author Jeremy Thai
 *
 */

public class Scraper {

	private URL url;
	private URLConnection uc;
	private Scanner sc;
	private String line;

/**
 * Constructor that sets up a connection to the URL on yahoo finance
 * @param stock stock
 */
	public Scraper(Stock stock) {

		try {
			this.url = new URL("https://finance.yahoo.com/quote/" + stock.getSymbol() + "?p=" + stock.getSymbol() + "&.tsrc=fin-srch");
			this.uc = url.openConnection();
		}
		catch(IOException e ) {
			System.out.println(e);
		}
	}

	/**
	 * Extracts company name from yahoo finance
	 * @param stock stock
	 */
	private void scrapCompanyName(Stock stock) {

		try {
			this.sc = new Scanner(url.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.line = sc.nextLine();

		while( line != null) {

			if(line.contains("pageData\":{")) {
				String data = findData(line, "pageData\":{", 20 );

				if (data.equals("Symbol Lookup from Yahoo Finance")) {
					System.out.println("Invalid input");
					System.exit(0);
				}

				stock.setCompanyName(data);	
				sc.close();
				return;
			}
			line = sc.nextLine();
		}

	}


	/**
	 * Extracts value per share from yahoo finance
	 * @param stock stock
	 */
	private void scrapShareValue(Stock stock) {


		try {
			this.sc = new Scanner(url.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.line = sc.nextLine();

		while( line != null) {

			if (line.contains("\"currentPrice\"")) {

				String data = findData(line, "currentPrice", 21 );
				stock.setValuePerShare(Double.parseDouble(data));
				sc.close();
				return;
			}
			line = sc.nextLine();

		}
	}


	/**
	 * Extracts market change of stock from yahoo finance
	 * @param stock stock
	 */
	private void scrapDayRange(Stock stock)  {

		try {
			this.sc = new Scanner(url.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.line = sc.nextLine();

		while(line != null) {
			if (line.contains("regularMarketChange")) {

				String data = findData(line, "regularMarketChange", 28 );
				stock.setDayRange(data);
				sc.close();
				return;
			}

			line = sc.nextLine();
		} 
	}


	/**
	 * Search for data needed line by line in yahoo finance
	 * @param line current line of scanner
	 * @param title title of data needed
	 * @param index index of title
	 * @return
	 */
	private String findData(String line , String title, int index) {

		int target = line.indexOf(title);
		int firstNum = target+index;
		String data = "";

		switch(title) {

		case "pageData\":{":

			while(Character.isLetter(line.charAt(firstNum))|| 
					line.charAt(firstNum)== '.' ||
					line.charAt(firstNum)== '-' || 
					line.charAt(firstNum)== '&' || 
					line.charAt(firstNum)== ',' ||  
					line.charAt(firstNum)== ' ') {

				data += line.charAt(firstNum);
				firstNum ++;
			} 
			break;

		case "currentPrice" :

			while(Character.isDigit(line.charAt(firstNum)) || 
					line.charAt(firstNum) == '.' ) {

				data += line.charAt(firstNum);
				firstNum ++;
			}
			break;


		case "regularMarketChange":

			while(Character.isDigit(line.charAt(firstNum)) || 
					line.charAt(firstNum) == '.' ||  
					line.charAt(firstNum) == '-' ) {

				data += line.charAt(firstNum);
				firstNum ++;
			}
			break;

		}
		return data;
	}


/**
 * Extracts stock quotes from yahoo finance
 * @param stock stock
 */
	public void scrapData(Stock stock) {

		scrapCompanyName(stock);
		scrapShareValue( stock);
		scrapDayRange(stock);
	}
}

