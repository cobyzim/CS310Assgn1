/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    public void printReport(BrokerLogImpl brokerLogImpl, StockTradeLogImpl stockTradeLogImpl) throws IOException {
        final String OUTPUT_FILENAME = "output/assn2report.txt";
        brokerLog = brokerLogImpl.getBrokerLog();
        stockTradeArray = stockTradeLogImpl.getStockTradeArray();
        numStockTrades = stockTradeLogImpl.getNumStockTrades();
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
        boolean taxable;
        
        
        for (int i = 0; i <= arraySize; i++) {
            Broker brokerLogObj = brokerLog.get(i);
            brokerLicense = brokerLogObj.getBrokerLicense();
            firstName = brokerLogObj.getFirstName();
            lastName = brokerLogObj.getLastName();
            dept = brokerLogObj.getDept();
            commissionRate = brokerLogObj.getCommissionRate();
            
            for (int j = 0; j < numStockTrades; i++) {
                StockTrade stockTradeObj = stockTradeArray[i];
                tradeLicense = stockTradeObj.getBrokerLicense();
                stockSymbol = stockTradeObj.getStockSymbol();
                pricePerShare = stockTradeObj.getPricePerShare();
                wholeShares = stockTradeObj.getWholeShares();
                if (stockTradeObj.isTaxable() == true) {
                    taxable = "YES";
                }
                if (brokerLicense.equals(tradeLicense)) {
                    FileWriter fileWriter = new FileWriter(OUTPUT_FILENAME);
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    
                    printWriter.printf("%s  %s,  %s\n", brokerLicense, 
                        brokerLogObj.getLastName(), brokerLogObj.getFirstName());
                    printWriter.printf("\n\t%s \t.2f \t%d   %s", )
                    
                }
            }
            
        }
        
        
        
    }
}
