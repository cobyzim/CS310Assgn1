/*
 * Program to manage information regarding brokers and their stock trades.
 * Data is read from particular files containing information on brokers and
 * stocks, and the program adds brokers and stockTrades to a hash set and hash
 * map respectively.
 */
package cs310zimmerman;

import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

/**
 *
 * @author Coby Zimmerman
 * @version 1.0
 */
public class CS310Zimmerman {

    static BrokerLogImpl brokerLogImpl = new BrokerLogImpl();
    static StockTradeLogImpl stockTradeLogImpl = new StockTradeLogImpl();
    static PrintImpl printImpl = new PrintImpl();

    /**
     * Main method that calls the processFile method and the printReport method
     *
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException - signals that file denoted by
     * specific pathname has failed to open
     */
    public static void main(String[] args) throws FileNotFoundException {

        processFile();
        System.out.println();
        brokerLogImpl.displayHash();
        stockTradeLogImpl.displayHash();
        System.out.println();
        
        System.out.println("Creating sales report using requests from file "
                + "input/BrokerRequests.txt");
        createReport();
        System.out.println("Sales report is complete -- located in file:"
                + " output/assn5salesReport.txt");

    }

    /**
     * Method used to read from the input file(s) and parse through each line
     * Also calls addBroker and addStockTrade based on the first two elements in
     * each line
     *
     * @throws FileNotFoundException - signals that file denoted by
     * specific pathname has failed to open
     */
    public static void processFile() throws FileNotFoundException {
        final String INPUT_FILENAME = "input/assn5input3.txt";

        try {
            File text = new File(INPUT_FILENAME);
            Scanner scnr = new Scanner(text);

            while (scnr.hasNextLine()) {
                String str = scnr.nextLine();
                String[] arrOfStr = str.split(",", 7);
                if (arrOfStr[0].equals("BROKER")) {
                    if (arrOfStr[1].equals("ADD")) {
                        addBroker(arrOfStr);
                    }
                }
                if (arrOfStr[0].equals("TRADE")) {
                    if (arrOfStr[1].equals("BUY")) {
                        addStockTrade(arrOfStr);
                    }
                }

            }
        } catch (IOException error) {
            System.out.println("Could not find file: " + INPUT_FILENAME);
            System.exit(1);
        }
    }

    /**
     * Method used to add a new broker object to hashSet. Calls addBroker
     * method from BrokerLogImpl class.
     *
     * @param line - passes in array of strings from input file
     */
    public static void addBroker(String[] line) {
        Broker filledBroker = new Broker(line);
        
        brokerLogImpl.addBroker(filledBroker);
    }

    /**
     * Method used to add a new stock trade object to hashMap. Calls
     * addStockTrade method from StockTradeLogImpl class.
     *
     * @param line - passes in array of strings from input file
     */
    public static void addStockTrade(String[] line) {
        StockTrade filledStockTrade = new StockTrade(line);
        
        stockTradeLogImpl.addStockTrade(filledStockTrade);   
    }
    
    /**
     * Method used to create the report of taxable/not taxable stockTrades by
     * calling the printReport method in the printImpl class
     * 
     */
    public static void createReport() {
        try {
            printImpl.printReport(brokerLogImpl, stockTradeLogImpl, 
                "output/assn5salesReport.txt");
        } catch (IOException ex) {
            System.out.println("I/O exception occurred");
        }
    }
    
}