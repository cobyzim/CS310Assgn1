/*
 * Program to manage information regarding brokers and their stock trades.
 * Data is read from particular files containing information on brokers and
 * stocks, and the program determines whether this info is valid or not, while 
 * also adding or deleting Broker/Stock Trade objects to a log based on the 
 * broker license and stock symbol uniqueness. The broker log contains broker 
 * objects in an ordered arraylist while the stock trade log contains stock
 * trade objects in an unordered array.
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
     * Main method that calls the processFile method and the createReport method
     *
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException - signals that file denoted by
     * specific pathname has failed to open
     */
    public static void main(String[] args) throws FileNotFoundException {

        processFile();
        System.out.println();
        System.out.println("Creating report...");
        System.out.println("Report is located in file: output/assn2report.txt");
        try {
            printImpl.printReport(brokerLogImpl, stockTradeLogImpl);
        } catch (IOException ex) {
            System.out.println("I/O exception occurred");
        }

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
        final String INPUT_FILENAME = "input/assn2input.txt";

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
                        deleteBroker(arrOfStr);
                    }
                }
                if (arrOfStr[0].equals("TRADE")) {
                    if (arrOfStr[1].equals("BUY")) {
                        addStockTrade(arrOfStr);
                    }
                    if (arrOfStr[1].equals("SELL")) {
                        deleteStockTrade(arrOfStr);
                    }
                }

            }
        } catch (IOException error) {
            System.out.println("Could not find file: " + INPUT_FILENAME);
            System.exit(1);
        }
    }

    /**
     * Method used to add a new broker object to the log based on the uniqueness
     * of the broker license. Also checks if the broker object has a valid
     * license and a valid department number, printing an error message if
     * either are invalid. Will still add the object to the log regardless.
     *
     * @param line - passes in array of strings from input file
     */
    public static void addBroker(String[] line) {
        Broker filledBroker = new Broker(line);
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
    }

    /**
     * Method used to add a new stock trade object to the log of stock trade
     * objects based on the broker license not being unique and the stock symbol
     * being unique. Also checks if the stock trade object has a valid stock
     * symbol, a valid price per share, and a valid number of shares. If these
     * are not valid, it will still add the object to the log.
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
            
            System.out.println("addStockTrades: numStockTrades = " + 
                    stockTradeLogImpl.getNumStockTrades());
            
            if (hasStockErrors) {
                System.out.println("ADDED: StockTrade with Stock symbol " + 
                        stockSymbol + "listed by Broker " + line[5] + ", "
                        + "regardless of data errors.");
            }
            else {
                System.out.println("ADDED: StockTrade with Stock symbol "
                    + stockSymbol + " listed by Broker " + license);
            }
            
        } else if (brokerIsUnique && stockSymbolIsUnique) {
            System.out.println("ADD ERROR: StockTrade with Stock Symbol "
                    + stockSymbol + " has Broker with license " + license
                    + ", but there is no such Broker license in the Broker"
                    + " log. StockTrade " + stockSymbol + " will NOT be added to "
                    + "StockTrade log.");
        } else {
            System.out.println("ADD ERROR: StockTrade has Stock Symbol "
                    + "that already exists: " + stockSymbol + "It will NOT "
                    + "be added to StockTrade log.");
        }
    }

    /**
     * Method used to delete a broker from the log of brokers based on if the
     * broker license number is not unique. Will print whether an object has
     * been deleted or not.
     *
     * @param line - passes in array of strings from input file
     */
    public static void deleteBroker(String[] line) {
        String license = line[2];

        if (brokerLogImpl.isLicenseUnique(license)) {
            brokerLogImpl.removeBroker(license);
            System.out.println("DELETED: Broker with license: " + license
                    + " has been removed from the Broker log. All Broker's "
                    + "stocks will also be removed from the StockTrade log.");
            stockTradeLogImpl.removeStockTradeByBroker(license);
            
            System.out.println("deleteBroker: numStockTrades = " + 
                    stockTradeLogImpl.getNumStockTrades());
            
        } else {
            System.out.println("\tERROR: Broker with license " + license
                    + " not found in log.");
        }
    }

    /**
     * Method used to delete a stock trade object from the log of stock trades
     * based on if the stock symbol is not unique. Will print whether an object
     * has been deleted or not.
     *
     * @param line - passes in array of strings from input file.
     */
    public static void deleteStockTrade(String[] line) {
        String stockSymbol = line[2];

        if (!stockTradeLogImpl.isStockSymbolUnique(stockSymbol)) {
            stockTradeLogImpl.removeStockTrade(stockSymbol);
            
            System.out.println("deleteStockTrade: numStockTrades = " + 
                    stockTradeLogImpl.getNumStockTrades());
            
            System.out.println("DELETED: StockTrade with Stock symbol "
                    + stockSymbol);
        } else {
            System.out.println("DEL ERROR: StockTrade with Stock symbol "
                    + stockSymbol + " is not in the StockTrade log, so it "
                    + "cannot be deleted.");
        }
    }
}
