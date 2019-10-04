/*
 * Implementation Class containing a printReport method to generate a report
 * that shows the broker objects that have stocktrades. It also shows the number
 * of stock trade listings for each broker, the total value of the stock trade
 * listings for each broker, and both of the latter for all of the brokers.
 */
package cs310zimmerman;

import java.util.ArrayList;
import java.io.*;
/**
 *
 * @author cobyz
 */

public class PrintImpl {
    private ArrayList<Broker> brokerLog;
    private StockTrade[] stockTradeArray;
    private  int numStockTrades;
    
    /**
     * Method used to generate the report using the broker and stock trade
     * implementation objects.
     * 
     * @param brokerLogImpl - passes in the broker implementation object
     * @param stockTradeLogImpl - passes in stock trade implementation object
     * @throws IOException - throws exception if I/O operations fail or are
     * interrupted
     */
    public void printReport(BrokerLogImpl brokerLogImpl, StockTradeLogImpl stockTradeLogImpl) throws IOException {
        final String OUTPUT_FILENAME = "output/assn2report.txt";
        brokerLog = brokerLogImpl.getBrokerLog();
        stockTradeArray = stockTradeLogImpl.getStockTradeArray();
        numStockTrades = stockTradeLogImpl.getNumStockTrades();
           System.out.println(numStockTrades);
        int arraySize = brokerLog.size() - 1;
        String brokerLicense;
        String firstName;
        String lastName;
        String dept;
        double commissionRate;
        String tradeLicense;
        String stockSymbol;
        double pricePerShare;
        int wholeShares;
        String taxable;
        int numListings;
        double totalListValue;
        int numListingsAll = 0;
        double totalListValueAll = 0.0;
        int numStockPerBroker = 0;
        
        FileWriter fileWriter = new FileWriter(OUTPUT_FILENAME);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        for (int i = 0; i <= arraySize; i++) {
            System.out.println("In i loop");
            Broker brokerLogObj = brokerLog.get(i);
            brokerLicense = brokerLogObj.getBrokerLicense();
            firstName = brokerLogObj.getFirstName();
            lastName = brokerLogObj.getLastName();
            dept = brokerLogObj.getDept();
            commissionRate = brokerLogObj.getCommissionRate();
            
            System.out.println("numstocktrades: " + numStockTrades);
            System.out.println("stock trade array size = " + stockTradeArray.length);
            for (int j = 0; j < numStockTrades; j++) {
                System.out.println("in j loop");
                StockTrade stockTradeLogObj = stockTradeArray[j];
                tradeLicense = stockTradeLogObj.getBrokerLicense();
                stockSymbol = stockTradeLogObj.getStockSymbol();
                pricePerShare = stockTradeLogObj.getPricePerShare();
                wholeShares = stockTradeLogObj.getWholeShares();
                if (stockTradeLogObj.isTaxable()) {
                    taxable = "YES";
                }
                else {
                    taxable = "NO";
                }
                numListings = numStockTrades;
                totalListValue = stockTradeLogImpl.totalStockTradeValue(brokerLicense);
                numStockPerBroker = stockTradeLogImpl.numberOfBrokerStockTrades(tradeLicense);
                
                if (brokerLicense.equals(tradeLicense)) {
                    System.out.println("In if license compare check");
                    
                    printWriter.printf("%s  %s,  %s\n", brokerLicense, 
                        lastName, firstName);
                    
                    for (int k = 0; k < numStockPerBroker; k++) {
                        printWriter.printf("\n\t%s \t%.2f \t%d   %s\n", stockSymbol, 
                            pricePerShare, wholeShares, taxable);
                    }
                    
                    printWriter.printf("\n   Number of StockTrade Listings for "
                            + "Broker: %d\n", numListings);
                    
                    printWriter.printf("\nTotal sales value of StockTradeListings"
                            + " for Broker %s: $ %.2f\n", brokerLicense, totalListValue);
                    
                    
                }
                numListingsAll = numListingsAll + numListings;
                totalListValueAll = totalListValueAll + totalListValue;
            } 
        }
        printWriter.printf("\nTotal Number of StockTrade Listings for ALL "
                + "Brokers = %d\n", numListingsAll);
        
        printWriter.printf("Total sales value of StockTrade Listings for ALL "
                + "Brokers = $ %.2f", totalListValueAll);
        
        fileWriter.close();
        printWriter.close();
    }
}
