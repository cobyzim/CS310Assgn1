/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs310zimmerman;


import java.util.Scanner;
import java.io.*;
/**
 *
 * @author cobyz
 */
public class CS310Zimmerman {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        final String INPUT_FILENAME = "input/assn1input.txt";
        
        System.out.println("Running Test 1a:");
        System.out.println("Broker Object 1:");
        Broker broker = new Broker("abc12345", "Coby", "Zimmerman", "123-542", 80.0);
        System.out.println(broker.toString());
        System.out.println();
        
        System.out.println("Running Test 1b:");
        System.out.println("Stock Trade Object 1:");
        StockTrade trader = new StockTrade("XXWW", 20.5, 12, "abc12345", true);
        System.out.println(trader.toString());
        System.out.println();
        
        System.out.println("Running Test 2a:");
        System.out.println("Broker Object 2:");
        Broker brokerTwo = new Broker("abc12345", "Coby", "Zimmerman", "123-542", 80.0);
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
        Broker brokerThree = new Broker("abc67891", "Cayden", "Zim", "dept3", 100.0);
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
        StockTrade traderTwo = new StockTrade("XXWW", 20.5, 12, "abc12345", true);
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
        StockTrade traderThree = new StockTrade("WXYZ", 20.5, 12, "abc12345", false);
        System.out.println(traderThree.toString());
        if (traderThree.equals(trader)) {
            System.out.println("The two stock trade objects ARE equal.");
        }
        else {
            System.out.println("The two stock trade objects ARE NOT equal.");
        }
        System.out.println();
        
        System.out.println("Running Test 3:");
        try {
           File text = new File(INPUT_FILENAME);
           Scanner scnr = new Scanner(text); 
           
           while(scnr.hasNextLine()) {
               boolean hasBrokerErrors = false;
               boolean hasStockErrors = false;
               String str = scnr.nextLine();
               String[] arrOfStr = str.split(",", 7);
               if (arrOfStr[0].equals("BROKER")) {
                   if (arrOfStr[1].equals("ADD")) {
                       System.out.println("ADDING BROKER");
                   }
                   if (arrOfStr[1].equals("DEL")) {
                       System.out.println("DELETING BROKER");
                   }
                   Broker emptyBroker = new Broker();
                   Broker filledBroker = setBrokerAttributes(emptyBroker, arrOfStr);
                   //System.out.println(filledBroker.toString());
                   if (!filledBroker.isValidLicense()) {
                       System.out.println(filledBroker.toString());
                       System.out.println("ERROR: Invalid broker license number format: " + filledBroker.getBrokerLicense());
                       System.out.println();
                       hasBrokerErrors = true;
                   }
                   if (!filledBroker.isValidDept()) {
                       System.out.println(filledBroker.toString());
                       System.out.println("ERROR: Invalid department number format: " + filledBroker.getDept());
                       System.out.println();
                       hasBrokerErrors = true;
                   }
                   
                   if (!hasBrokerErrors) {
                       displayBrokerAttributes(filledBroker);
                   }
                   
               }
               if (arrOfStr[0].equals("TRADE")) {
                   if (arrOfStr[1].equals("BUY")) {
                       System.out.println("BUYING STOCK");
                   }
                   if (arrOfStr[1].equals("SELL")) {
                       System.out.println("SELLING STOCK");
                   }
                   StockTrade emptyStockTrade = new StockTrade();
                   StockTrade filledStockTrade = setStockTradeAttributes(emptyStockTrade, arrOfStr);
                   if (!filledStockTrade.isValidStockSymbol()) {
                       System.out.println(filledStockTrade.toString());
                       System.out.println("ERROR: Invalid stock symbol format: " + filledStockTrade.getStockSymbol());
                       System.out.println();
                       hasStockErrors = true;
                   }
                   if (!filledStockTrade.isValidPrice()) {
                       System.out.println(filledStockTrade.toString());
                       System.out.println("ERROR: Invalid stock price: " + filledStockTrade.getPricePerShare());
                       System.out.println();
                       hasStockErrors = true;
                   }
                   if (!filledStockTrade.isValidWholeShares()) {
                       System.out.println(filledStockTrade.toString());
                       System.out.println("ERROR: Invalid number of shares: " + filledStockTrade.getWholeShares());
                       System.out.println();
                       hasStockErrors = true;
                   }
                   
                   if (!hasStockErrors) {
                       displayStockTradeAttributes(filledStockTrade);
                   }
                   
               }
           }
        }
        
        catch (IOException error) {
            System.out.println("Could not find file: " + INPUT_FILENAME);
            System.exit(1);
        }
        
        
        
        
        
        
        
        
        /*
        //testing license validity
        Broker broker = new Broker("abc12345", "Coby", "Zimmerman", "123-542", 80.0);
        Broker brokerTwo = new Broker("123abcde", "Tate", "Zimmerman", "146-1111", 90.0);
        Broker brokerThree = new Broker("abc43cd4", "Cayden", "Zim", "dept3", 100.0);
        
        System.out.println(broker.toString());
        System.out.println(brokerTwo.toString());
        */
        
        /*if (brokerThree.isValidLicense()) {
            System.out.println("License is Valid");
        }
        else {
            System.out.println("License is not Valid");
        }
        */
        /*
        //testing dept validity
        if (brokerTwo.isValidDept()) {
            System.out.println("Dept is Valid");
        }
        else {
            System.out.println("Dept is invalid");
        }
        */
        
        /*
        StockTrade trader = new StockTrade("XXWW", 20.5, 12, "abc12345", true);
        System.out.println(trader.toString());
        //testing stock symbol validity
        if (trader.isValidStockSymbol()) {
            System.out.println("Stock Symbol is valid");
        }
        else {
            System.out.println("Stock symbol is invalid");
        }
        */
        
        /*
        StockTrade traderTwo = new StockTrade("WWX", 1000.00, 13, "abc12345", false);
        //testing price valiidty
        if (traderTwo.isValidPrice()) {
            System.out.println("Price is valid");
        }
        else {
            System.out.println("Price is invalid");
        }
        */
        
        /*
        StockTrade traderThree = new StockTrade("WXWX", 1000.00, 100000, "abc12345", true);
        //testing validity of number of shares
        if (traderThree.isValidWholeShares()) {
            System.out.println("Valid number of shares");
        }
        else {
            System.out.println("Invalid number of shares");
        }
        */
        
        
        
        
        
        
        
        
    }
    
    public static Broker setBrokerAttributes(Broker broker, String[] brokerAttributes) {
        broker.setBrokerLicense(brokerAttributes[2]);
        broker.setFirstName(brokerAttributes[3]);
        broker.setLastName(brokerAttributes[4]);
        broker.setDept(brokerAttributes[5]);
        broker.setCommissionRate(Double.parseDouble(brokerAttributes[6]));
        
    return broker;
    }
    
    public static void displayBrokerAttributes(Broker broker) {
        System.out.println(broker.getBrokerLicense());
        System.out.println(broker.getFirstName());
        System.out.println(broker.getLastName());
        System.out.println(broker.getDept());
        System.out.println(broker.getCommissionRate());
        System.out.println();
    }
    
    public static StockTrade setStockTradeAttributes(StockTrade stockTrade, String[] stockAttributes) {
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
    
    public static void displayStockTradeAttributes(StockTrade stockTrade) {
        System.out.println(stockTrade.getStockSymbol());
        System.out.println(stockTrade.getPricePerShare());
        System.out.println(stockTrade.getWholeShares());
        System.out.println(stockTrade.getBrokerLicense());
        System.out.println(stockTrade.isTaxable());
        System.out.println();
    }
    
}
