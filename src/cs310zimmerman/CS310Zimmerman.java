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
                        System.out.println("ADDING BROKER");
                        addBroker(arrOfStr);
                        
                    }
                    if (arrOfStr[1].equals("DEL")) {
                        System.out.println("DELETING BROKER");
                        //boolean brokerRemoved = brokerLogImpl.removeBroker(arrOfStr[2]);
                    }
                }
                if (arrOfStr[0].equals("TRADE")) {
                    if (arrOfStr[1].equals("BUY")) {
                        System.out.println("BUYING STOCK");
                    }
                    if (arrOfStr[1].equals("SELL")) {
                        System.out.println("SELLING STOCK");
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
        //BrokerLogImpl brokerLogImpl = new BrokerLogImpl();
        Broker filledBroker = new Broker(line);
                       
        if (!filledBroker.isValidLicense()) {
            System.out.println(filledBroker.toString());
            System.out.println("ERROR: Invalid broker license number"
                + " format: " + filledBroker.getBrokerLicense());
            System.out.println();
            //hasBrokerErrors = true;
        }
        if (!filledBroker.isValidDept()) {
            System.out.println(filledBroker.toString());
            System.out.println("ERROR: Invalid department number"
                + " format: " + filledBroker.getDept());
            System.out.println();
            //hasBrokerErrors = true;
        }
        //if hasBrokerErrors is true, do the error message
        if (!brokerLogImpl.isLicenseUnique(line[2])) {
            System.out.println("\tERROR: Broker with license " + line[2] +
                        " is not unique and will NOT be added to the log.");
        }
        if (brokerLogImpl.isLicenseUnique(line[2])) {
            if (brokerLogImpl.addBroker(filledBroker)) {
                System.out.println("ADDED: Broker with license " + line[2]);
            }
        }
    }

    public static void addStockTrade(String[] line) {
        StockTradeLogImpl stockTradeLog = new StockTradeLogImpl();
        
        StockTrade filledStockTrade = new StockTrade(line);

            if (!filledStockTrade.isValidStockSymbol()) {
                System.out.println(filledStockTrade.toString());
                System.out.println("ERROR: Invalid stock symbol format:"
                        + " " + filledStockTrade.getStockSymbol());
                System.out.println();
                //hasStockErrors = true;
            }
            if (!filledStockTrade.isValidPrice()) {
                System.out.println(filledStockTrade.toString());
                System.out.println("ERROR: Invalid stock price: "
                        + filledStockTrade.getPricePerShare());
                System.out.println();
                //hasStockErrors = true;
            }
            if (!filledStockTrade.isValidWholeShares()) {
                System.out.println(filledStockTrade.toString());
                System.out.println("ERROR: Invalid number of shares: "
                        + filledStockTrade.getWholeShares());
                System.out.println();
                //hasStockErrors = true;
            }
            BrokerLogImpl brokerLogImpl = new BrokerLogImpl();
        
            String license = line[5];
            boolean brokerIsUnique = brokerLogImpl.isLicenseUnique(license);
            boolean stockSymbolIsUnique = stockTradeLog.isStockSymbolUnique(line[2]);
            if (!brokerIsUnique && stockSymbolIsUnique) {
                stockTradeLog.addStockTrade(filledStockTrade);
                System.out.println("ADDED: StockTrade with Stock symbol " + 
                        line[2] + " listed by Broker " + line[5]);
            }
            else if (brokerIsUnique && stockSymbolIsUnique) {
                System.out.println("ADD ERROR: StockTrade with Stock Symbol " + 
                        line[2] + "has Broker with license " + line[5]
                        + " , but there is no such Broker license in the Broker"
                        + " log. StockTrade " + line[2] + " will NOT be added to "
                        + "StockTrade log.");
            }
            else {
                System.out.println("ADD ERROR: StockTrade has Stock Symbol "
                        + "that already exists: " + line[2] + "It will NOT "
                        + "be added to StockTrade log.");
            }
            //might need one more else if for potential of both to be wrong
            

            
            
        
    }
    
    
    
    public static void deleteBroker(String[] line) {
        BrokerLogImpl brokerLogImpl = new BrokerLogImpl();
        
        String license = line[2];
        if (!brokerLogImpl.isLicenseUnique(license)) {
           brokerLogImpl.removeBroker(license); 
        }
        
    }
}
