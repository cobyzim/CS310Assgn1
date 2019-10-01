/*
 * Program to manage information regarding brokers and their stock trades.
 * Data is read from particular files containing information on brokers and
 * stocks, and the program determines whether this info is valid or not.
 * Output is displayed differently based on this validity. The program can also
 * compare broker and stock trade objects to one another and display their
 * contents in a string.
 */
package cs310zimmerman;

import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Coby Zimmerman
 * @version 1.0
 */
public class CS310Zimmerman {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     *
     * Main method that instantiates broker and stock trade objects, comparing
     * them to one another based on their contents, and determining whether or
     * not the information within them is valid.
     */
    static BrokerLogImpl brokerLogImpl = new BrokerLogImpl();
    static StockTradeLogImpl stockTradeLogImpl = new StockTradeLogImpl();
    
    public static void main(String[] args) throws FileNotFoundException {
    
        processFile();
        /*
            StockTrade filledStockTrade = new StockTrade(arrOfStr);

            if (!filledStockTrade.isValidStockSymbol()) {
                System.out.println(filledStockTrade.toString());
                System.out.println("ERROR: Invalid stock symbol format:"
                        + " " + filledStockTrade.getStockSymbol());
                System.out.println();
                hasStockErrors = true;
            }
            if (!filledStockTrade.isValidPrice()) {
                System.out.println(filledStockTrade.toString());
                System.out.println("ERROR: Invalid stock price: "
                        + filledStockTrade.getPricePerShare());
                System.out.println();
                hasStockErrors = true;
            }
            if (!filledStockTrade.isValidWholeShares()) {
                System.out.println(filledStockTrade.toString());
                System.out.println("ERROR: Invalid number of shares: "
                        + filledStockTrade.getWholeShares());
                System.out.println();
                hasStockErrors = true;
            }
        }
    }

    System.out.println (brokerLogImpl.getBrokerLog());
        }
           
    }
    */
    }
    public static void processFile() throws FileNotFoundException {
        final String INPUT_FILENAME = "input/assn1input.txt";
        
        try {
           File text = new File(INPUT_FILENAME);
           Scanner scnr = new Scanner(text);
          
            while(scnr.hasNextLine()) {
                //boolean hasBrokerErrors = false;
                //boolean hasStockErrors = false;
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
        }
        
        catch (IOException error) {
            System.out.println("Could not find file: " + INPUT_FILENAME);
            System.exit(1);
        }
        
        
    }
    
    public static void addBroker(String[] line) {
        Broker filledBroker = new Broker(line);
        boolean hasBrokerErrors = false;
                       
        if (!filledBroker.isValidLicense()) {
            //System.out.println(filledBroker.toString());
            System.out.println("\tERROR: Broker with license " + 
                filledBroker.getBrokerLicense() + " has invalid license");
            System.out.println();
            hasBrokerErrors = true;
        }
        if (!filledBroker.isValidDept()) {
            //System.out.println(filledBroker.toString());
            System.out.println("\tERROR: Broker with license " + 
                filledBroker.getBrokerLicense() + " has invalid department" +
                filledBroker.getDept());
            System.out.println();
            hasBrokerErrors = true;
        }
        //if hasBrokerErrors is true, do the error message
        if (!brokerLogImpl.isLicenseUnique(line[2])) {
            System.out.println("\tERROR: Broker with license " + line[2] +
                        " is not unique and will NOT be added to the log.");
        }
        //need to fix this
        if (brokerLogImpl.isLicenseUnique(line[2])) {
            if (brokerLogImpl.addBroker(filledBroker)) {
                System.out.println("ADDED: Broker with license " + line[2]);
                /*
                if (hasBrokerErrors = true) {
                    System.out.println("ADDED: Broker with license " + line[2]
                    + ", regardless of data errors.");
                }
                if (hasBrokerErrors = false) {
                    System.out.println("ADDED: Broker with license " + line[2]);
                }
                */
                
            }
        }
    }

    public static void addStockTrade(String[] line) {
        StockTrade filledStockTrade = new StockTrade(line);

            if (!filledStockTrade.isValidStockSymbol()) {
                System.out.println(filledStockTrade.toString());
                System.out.println("\tERROR: StockTrade with Stock symbol "
                    + filledStockTrade.getStockSymbol() + " has an invalid "
                    + "Stock symbol.");
                System.out.println();
                //hasStockErrors = true;
            }
            if (!filledStockTrade.isValidPrice()) {
                System.out.println(filledStockTrade.toString());
                System.out.println("\tERROR: StockTrade with Stock symbol " +
                    filledStockTrade.getStockSymbol() + " has invalid price " + 
                    filledStockTrade.getPricePerShare());
                System.out.println();
                //hasStockErrors = true;
            }
            if (!filledStockTrade.isValidWholeShares()) {
                System.out.println(filledStockTrade.toString());
                System.out.println("\tERROR: StockTrade with Stock symbol " +
                    filledStockTrade.getStockSymbol() + " has invalid "
                    + "number of shares " + filledStockTrade.getWholeShares());
                System.out.println();
                //hasStockErrors = true;
            }
            //BrokerLogImpl brokerLogImpl = new BrokerLogImpl();
        
            String license = line[5];
            String stockSymbol = line[2];
            boolean brokerIsUnique = brokerLogImpl.isLicenseUnique(license);
            boolean stockSymbolIsUnique = stockTradeLogImpl.isStockSymbolUnique(stockSymbol);
            if (!brokerIsUnique && stockSymbolIsUnique) {
                stockTradeLogImpl.addStockTrade(filledStockTrade);
                System.out.println("ADDED: StockTrade with Stock symbol " + 
                        stockSymbol + " listed by Broker " + license);
            }
            else if (brokerIsUnique && stockSymbolIsUnique) {
                System.out.println("ADD ERROR: StockTrade with Stock Symbol " + 
                    stockSymbol + " has Broker with license " + license
                    + ", but there is no such Broker license in the Broker"
                    + " log. StockTrade " + stockSymbol + " will NOT be added to "
                    + "StockTrade log.");
            }
            else {
                System.out.println("ADD ERROR: StockTrade has Stock Symbol "
                    + "that already exists: " + stockSymbol + "It will NOT "
                    + "be added to StockTrade log.");
            }
            //might need one more else if for potential of both to be wrong
           
    }
    
    
    //test this (also look at changing it to !unique so it makes more sense
    public static void deleteBroker(String[] line) {
        String license = line[2];
        
        if (brokerLogImpl.isLicenseUnique(license)) {
            brokerLogImpl.removeBroker(license);
            System.out.println("DELETED: Broker with license: " + license + 
                " has been removed from the Broker log. All Broker's stocks"
                + " will also be removed from the StockTrade log.");
            stockTradeLogImpl.removeStockTradeByBroker(license);
        }
        else {
            System.out.println("\tERROR: Broker with license " + license + 
                " not found in log.");
        } 
    }
    
    public static void deleteStockTrade(String[] line) {
        String stockSymbol = line[2];
        
        if(stockTradeLogImpl.isStockSymbolUnique(stockSymbol)) {
            stockTradeLogImpl.removeStockTrade(stockSymbol);
            System.out.println("DELETED: StockTrade with Stock symbol " + 
                stockSymbol);
        }
        else {
            System.out.println("DEL ERROR: StockTrade with Stock symbol " + 
                stockSymbol + " is not in the StockTrade log, so it "
                + "cannot be deleted.");
        }
    }
}
