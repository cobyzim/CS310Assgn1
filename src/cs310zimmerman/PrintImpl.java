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
        brokerLog = brokerLogImpl.getBrokerLog();
        stockTradeArray = stockTradeLogImpl.getStockTradeArray();
        numStockTrades = stockTradeLogImpl.getNumStockTrades();
        
        for (int i = 0; i < numStockTrades; i++) {
            
        }
        
        
        
    }
    //loop through brokers, grab license, loop through stocktrades, compare if they have same license
}
