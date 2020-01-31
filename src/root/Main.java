package root;
import java.util.Scanner;

/**
 *  Main program that allows user to enter any stock symbol and returns information about that stock.
 * If the user enters STOP, the application closes.
 * @author Jeremy Thai
 *
 */
public class Main {

	public static void main(String[] args){


		System.out.println("Enter the stock symbol you want the information for. Enter STOP to exit the program: ");

		Scanner scanner = new Scanner(System.in); //Creates a scanner and takes input of stock ticker(s) entered by user. 

		StockProcessor.processingStock(scanner, scanner.nextLine().toUpperCase()); //Converts to upper case to avoid any typing errors

		StockProcessor.closeApplication(scanner);
	}

}
