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
    
    public void printReport(BrokerLogImpl brokerLogImpl, StockTradeLogImpl stockTradeLogImpl) {
        final String OUTPUT_FILENAME = "output/assn2report.txt";
        brokerLog = brokerLogImpl.getBrokerLog();
        stockTradeArray = stockTradeLogImpl.getStockTradeArray();
        numStockTrades = stockTradeLogImpl.getNumStockTrades();
        int arraySize = brokerLog.size() - 1;
        String brokerLicense;
        String tradeLicense;
        
        for (int i = 0; i <= arraySize; i++) {
            Broker brokerLogObj = brokerLog.get(i);
            brokerLicense = brokerLogObj.getBrokerLicense();
            
            for (int j = 0; j < numStockTrades; i++) {
                StockTrade stockTradeObj = stockTradeArray[i];
                tradeLicense = stockTradeObj.getBrokerLicense();
                if (brokerLicense.equals(tradeLicense)) {
                    
                }
            }
            
        }
        
        
        
    }
    //loop through brokers, grab license, loop through stocktrades, compare if they have same license
}
