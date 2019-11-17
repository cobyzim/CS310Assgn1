/*
 * Program to manage information regarding brokers and their stock trades.
 * Data is read from particular files containing information on brokers and
 * stocks, and the program determines whether this info is valid or not, while 
 * also adding or deleting Broker/Stock Trade objects to lists based on the 
 * broker license and stock symbol uniqueness. The broker list contains broker 
 * objects in an ordered linked list while the stock trade list contains stock
 * trade objects in an unordered linked list.
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
        brokerLogImpl.traverse();
        stockTradeLogImpl.traverseDisplay();
        System.out.println();
        
        System.out.println("Creating initial report...");
        System.out.println();
        System.out.println("Creating report...");
        System.out.println("Report is complete -- located in file: output/assn3initialReport.txt");
        
        try {
            printImpl.printReport(brokerLogImpl, stockTradeLogImpl, "output/assn3initialReport.txt");
        } catch (IOException ex) {
            System.out.println("I/O exception occurred");
        }
        
        System.out.println();
        System.out.println("Cleaning up broker and stockTrade logs...");
        brokerLogImpl.cleanList(stockTradeLogImpl);
        stockTradeLogImpl.cleanList();
        System.out.println();
        System.out.println("Creating clean report...");
        System.out.println();
        System.out.println("Creating report...");
        System.out.println("Report is complete -- located in file: output/assn3cleanReport.txt");
        
        try {
            printImpl.printReport(brokerLogImpl, stockTradeLogImpl, "output/assn3cleanReport.txt");
        } catch (IOException ex) {
            System.out.println("I/O exception occurred");
        }
        
        //createReport();

    }

    /**
     * Method used to read from the input file(s) and parse through each line
     * Also calls addBroker, deleteBroker, addStockTrade, and deleteStockTrade
     * based on the first two elements in a file line.
     *
     * @throws FileNotFoundException - signals that file denoted by
     * specific pathname has failed to open
     */
    public static void processFile() throws FileNotFoundException {
        final String INPUT_FILENAME = "input/assn3input3.txt";

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
                    if (arrOfStr[1].equals("DEL")) {
                        //deleteBroker(arrOfStr);
                    }
                }
                if (arrOfStr[0].equals("TRADE")) {
                    if (arrOfStr[1].equals("BUY")) {
                        addStockTrade(arrOfStr);
                    }
                    if (arrOfStr[1].equals("SELL")) {
                        //deleteStockTrade(arrOfStr);
                    }
                }

            }
        } catch (IOException error) {
            System.out.println("Could not find file: " + INPUT_FILENAME);
            System.exit(1);
        }
    }

    /**
     * Method used to add a new broker object to the list based on the uniqueness
     * of the broker license. Also checks if the broker object has a valid
     * license and a valid department number, printing an error message if
     * either are invalid. Will still add the object to the list regardless.
     *
     * @param line - passes in array of strings from input file
     */
    public static void addBroker(String[] line) {
        Broker filledBroker = new Broker(line);
        
        brokerLogImpl.addBroker(filledBroker);
        /*
        boolean hasBrokerErrors = false;
       
        if (!filledBroker.isValidLicense()) {
            System.out.println("\tERROR: Broker with license "
                    + filledBroker.getBrokerLicense() + " has invalid license");
            hasBrokerErrors = true;
        }
        if (!filledBroker.isValidDept()) {
            System.out.println("\tERROR: Broker with license "
                    + filledBroker.getBrokerLicense() + " has invalid "
                    + "department " + filledBroker.getDept());
            hasBrokerErrors = true;
        }
        
        if (!brokerLogImpl.isLicenseUnique(line[2])) {
            System.out.println("\tERROR: Broker with license " + line[2]
                    + " is not unique and will NOT be added to the log.");
        }
        if (brokerLogImpl.isLicenseUnique(line[2])) {
            if (brokerLogImpl.addBroker(filledBroker)) {
                 if (hasBrokerErrors) {
                   System.out.println("ADDED: Broker with license " + line[2]
                 + ", regardless of data errors.");
                 }
                 else {
                   System.out.println("ADDED: Broker with license " + line[2]);
                 }
            }
        }
        */
    }

    /**
     * Method used to add a new stock trade object to the list of stock trade
     * objects based on the broker license not being unique and the stock symbol
     * being unique. Also checks if the stock trade object has a valid stock
     * symbol, a valid price per share, and a valid number of shares. If these
     * are not valid, it will still add the object to the list.
     *
     * @param line - passes in array of strings from input file
     */
    public static void addStockTrade(String[] line) {
        StockTrade filledStockTrade = new StockTrade(line);
        boolean hasStockErrors = false;

        if (!filledStockTrade.isValidStockSymbol()) {
            System.out.println(filledStockTrade.toString());
            System.out.println("\tERROR: StockTrade with Stock symbol "
                    + filledStockTrade.getStockSymbol() + " has an invalid "
                    + "Stock symbol.");
            hasStockErrors = true;
        }
        if (!filledStockTrade.isValidPrice()) {
            System.out.println(filledStockTrade.toString());
            System.out.println("\tERROR: StockTrade with Stock symbol "
                    + filledStockTrade.getStockSymbol() + " has invalid price "
                    + filledStockTrade.getPricePerShare());
            hasStockErrors = true;
        }
        if (!filledStockTrade.isValidWholeShares()) {
            System.out.println(filledStockTrade.toString());
            System.out.println("\tERROR: StockTrade with Stock symbol "
                    + filledStockTrade.getStockSymbol() + " has invalid "
                    + "number of shares " + filledStockTrade.getWholeShares());
            hasStockErrors = true;
        }

        String license = line[5];
        String stockSymbol = line[2];
        
        boolean brokerIsUnique = brokerLogImpl.isLicenseUnique(license);
        boolean stockSymbolIsUnique = 
                stockTradeLogImpl.isStockSymbolUnique(stockSymbol);
        if (!brokerIsUnique && stockSymbolIsUnique) {
        
            stockTradeLogImpl.addStockTrade(filledStockTrade);
            
            if (hasStockErrors) {
                System.out.println("ADDED: StockTrade with Stock symbol " + 
                        stockSymbol + " listed by Broker " + line[5] + ", "
                        + "regardless of data errors.");
            }
            else {
                System.out.println("ADDED: StockTrade with Stock symbol "
                    + stockSymbol + " listed by Broker " + license);
            }
            
        } 
        else if (brokerIsUnique && stockSymbolIsUnique) {
            System.out.println("ADD ERROR: StockTrade with Stock Symbol "
                    + stockSymbol + " has Broker with license " + license
                    + ", but there is no such Broker license in the Broker"
                    + " log. StockTrade " + stockSymbol + " will NOT be added to "
                    + "StockTrade log.");
        } 
        else {
            System.out.println("ADD ERROR: StockTrade has Stock Symbol "
                    + "that already exists: " + stockSymbol + "It will NOT "
                    + "be added to StockTrade log.");
        }
        
    }
    
    /*
    public static void createReport() {
        try {
            printImpl.printReport(brokerLogImpl, stockTradeLogImpl, );
        } catch (IOException ex) {
            System.out.println("I/O exception occurred");
        }
    }
    */
}