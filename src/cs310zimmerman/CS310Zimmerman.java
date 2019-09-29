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
     * them to one another based on their contents, and determining whether
     * or not the information within them is valid.
     */
    public static void main(String[] args) throws FileNotFoundException {
        final String INPUT_FILENAME = "input/assn1input.txt";
        
        /*
        System.out.println("Running Test 1a:");
        System.out.println("Broker Object 1:");
        Broker broker = new Broker("abc12345", "Coby", "Zimmerman", "123-542", 
                80.0);
        System.out.println(broker.toString());
        System.out.println();
        
        System.out.println("Running Test 1b:");
        System.out.println("Stock Trade Object 1:");
        StockTrade trader = new StockTrade("XXWW", 20.5, 12, "abc12345", true);
        System.out.println(trader.toString());
        System.out.println();
        
        System.out.println("Running Test 2a:");
        System.out.println("Broker Object 2:");
        Broker brokerTwo = new Broker("abc12345", "Coby", "Zimmerman", 
                "123-542", 80.0);
        System.out.println(brokerTwo.toString());
        if (brokerTwo.equals(broker)) {
            System.out.println("The two broker objects ARE equal.");
        }
        else {
            System.out.println("The two broker objects ARE NOT equal.");
        }
        System.out.println();
        
        System.out.println("Running Test 2b:");
        System.out.println("Broker Object 2:");
        Broker brokerThree = new Broker("abc67891", "Cayden", "Zim", "dept3",
                100.0);
        System.out.println(brokerThree.toString());
        if (brokerThree.equals(broker)) {
            System.out.println("The two broker objects ARE equal.");
        }
        else {
            System.out.println("The two broker objects ARE NOT equal.");
        }
        System.out.println();
        
        System.out.println("Running Test 2c:");
        System.out.println("Stock Trade Object 2:");
        StockTrade traderTwo = new StockTrade("XXWW", 20.5, 12, "abc12345",
                true);
        System.out.println(traderTwo.toString());
        if (traderTwo.equals(trader)) {
            System.out.println("The two stock trade objects ARE equal.");
        }
        else {
            System.out.println("The two stock trade objects ARE NOT equal.");
        }
        System.out.println();
        
        System.out.println("Running Test 2d:");
        System.out.println("Stock Trade Object 3:");
        StockTrade traderThree = new StockTrade("WXYZ", 20.5, 12, "abc12345",
                false);
        System.out.println(traderThree.toString());
        if (traderThree.equals(trader)) {
            System.out.println("The two stock trade objects ARE equal.");
        }
        else {
            System.out.println("The two stock trade objects ARE NOT equal.");
        }
        System.out.println();
        */
        
        System.out.println("Running Test 3:");
        
        try {
           File text = new File(INPUT_FILENAME);
           Scanner scnr = new Scanner(text);
           BrokerLogImpl brokerLogImpl = new BrokerLogImpl();
           
           while(scnr.hasNextLine()) {
               boolean hasBrokerErrors = false;
               boolean hasStockErrors = false;
               String str = scnr.nextLine();
               String[] arrOfStr = str.split(",", 7);
               if (arrOfStr[0].equals("BROKER")) {
                   if (arrOfStr[1].equals("ADD")) {
                       System.out.println("ADDING BROKER");
                       Broker filledBroker = new Broker(arrOfStr);
                       
                       boolean brokerAdded = brokerLogImpl.addBroker(filledBroker);
                       
                       if (!filledBroker.isValidLicense()) {
                       System.out.println(filledBroker.toString());
                       System.out.println("ERROR: Invalid broker license number"
                               + " format: " + filledBroker.getBrokerLicense());
                       System.out.println();
                       hasBrokerErrors = true;
                   }
                   if (!filledBroker.isValidDept()) {
                       System.out.println(filledBroker.toString());
                       System.out.println("ERROR: Invalid department number"
                               + " format: " + filledBroker.getDept());
                       System.out.println();
                       hasBrokerErrors = true;
                   }
                   
                   if (!hasBrokerErrors) {
                       displayBrokerAttributes(filledBroker);
                   }
                   
                   }
                   if (arrOfStr[1].equals("DEL")) {
                       System.out.println("DELETING BROKER");
                       boolean brokerRemoved = brokerLogImpl.removeBroker(arrOfStr[2]);
                   }
                   
                   /*Broker emptyBroker = new Broker();
                   Broker filledBroker = setBrokerAttributes(emptyBroker,
                           arrOfStr);
                   */
                   
                   
                   
                   
                   
               }
               if (arrOfStr[0].equals("TRADE")) {
                   if (arrOfStr[1].equals("BUY")) {
                       System.out.println("BUYING STOCK");
                   }
                   if (arrOfStr[1].equals("SELL")) {
                       System.out.println("SELLING STOCK");
                   }
                   
                   /*
                   StockTrade emptyStockTrade = new StockTrade();
                   StockTrade filledStockTrade = setStockTradeAttributes(
                           emptyStockTrade, arrOfStr);
                   */
                   
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
                       System.out.println("ERROR: Invalid stock price: " + 
                               filledStockTrade.getPricePerShare());
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
                   
                   if (!hasStockErrors) {
                       displayStockTradeAttributes(filledStockTrade);
                   }
               }
           }
           System.out.println(brokerLogImpl.getBrokerLog());
        }
        
        catch (IOException error) {
            System.out.println("Could not find file: " + INPUT_FILENAME);
            System.exit(1);
        }
        
    }
    

    
    /**
     * 
     * @param broker - passes in broker object as parameter
     * @param brokerAttributes - passes in array of string values as parameter
     * @return - returns the broker object with attributes set
     * 
     * Method using setters from Broker class to give empty broker object in 
     * main values from csv file.
     */
    /*
    public static Broker setBrokerAttributes(Broker broker, 
            String[] brokerAttributes) {
        broker.setBrokerLicense(brokerAttributes[2]);
        broker.setFirstName(brokerAttributes[3]);
        broker.setLastName(brokerAttributes[4]);
        broker.setDept(brokerAttributes[5]);
        broker.setCommissionRate(Double.parseDouble(brokerAttributes[6]));
        
    return broker;
    }
    */
    
    /**
     *
     * @param broker - passes in broker object as parameter
     * 
     * Method that uses getters from Broker class to display the attributes of 
     * a valid broker object one line at a time
     */
    public static void displayBrokerAttributes(Broker broker) {
        System.out.println(broker.getBrokerLicense());
        System.out.println(broker.getFirstName());
        System.out.println(broker.getLastName());
        System.out.println(broker.getDept());
        System.out.println(broker.getCommissionRate());
        System.out.println();
    }
    
    /**
     *
     * @param stockTrade - passes in stockTrade object as a parameter
     * @param stockAttributes - passes in array of string values as parameter
     * @return - returns stockTrade object with attributes set
     * 
     * Method using setters from the StockTrade class to give empty stockTrade
     * object values from csv file.
     */
    /*
    public static StockTrade setStockTradeAttributes(StockTrade stockTrade, 
            String[] stockAttributes) {
        stockTrade.setStockSymbol(stockAttributes[2]);
        stockTrade.setPricePerShare(Double.parseDouble(stockAttributes[3]));
        stockTrade.setWholeShares(Integer.parseInt(stockAttributes[4]));
        stockTrade.setBrokerLicense(stockAttributes[5]);
        if(stockAttributes[6].equals("N")) {
            stockTrade.setTaxable(false);
        }
        if(stockAttributes[6].equals("Y")) {
            stockTrade.setTaxable(true);
        }
        
    return stockTrade;
    }
    */
    
    /**
     *
     * @param stockTrade - passes in stockTrade object as parameter
     * 
     * Method that uses getters from StockTrade class to display attributes of
     * valid stockTrade objects one line at a time.
     */
    public static void displayStockTradeAttributes(StockTrade stockTrade) {
        System.out.println(stockTrade.getStockSymbol());
        System.out.println(stockTrade.getPricePerShare());
        System.out.println(stockTrade.getWholeShares());
        System.out.println(stockTrade.getBrokerLicense());
        System.out.println(stockTrade.isTaxable());
        System.out.println();
    }
    
}
