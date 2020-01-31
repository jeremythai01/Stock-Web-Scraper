package root;

import java.util.Scanner;

/**
 * This StockProcessor class handles the input and output of the main program.
 * @author Jeremy Thai
 *
 */

public class StockProcessor {

	/**
	 * Find and print stock quotes as long as user does not stop
	 * @param sc scanner
	 * @param symbol stock symbol
	 */
	public static void processingStock(Scanner sc, String symbol) {

		//keep processing stock ticker until user types in "STOP"
		while(!symbol.equals("STOP")){

			Stock stock = new Stock(symbol);
			Scraper scraper = new Scraper(stock);
			scraper.scrapData(stock);
			stock.printInfo();

			//asks user for another input(s) and converts it to upper case and stores it in an arraylist
			System.out.println("Enter the stock ticker you want the information for. Enter STOP to exit the program: ");
			symbol = sc.nextLine().toUpperCase();

		}
	}

	/**
	 * Terminate program
	 * @param sc scanner
	 */
	public static void closeApplication(Scanner sc) {

		System.out.println("Thank you for using this application! ");
		sc.close();
		System.exit(0);
	}

}
