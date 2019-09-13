/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs310zimmerman;


import java.util.Scanner;
/**
 *
 * @author cobyz
 */
public class CS310Zimmerman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
    
}
