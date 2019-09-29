/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs310zimmerman;

/**
 *
 * @author cobyz
 */
public class StockTradeLogImpl {
    private StockTrade[] stockTradeArray;
    private int numStockTrades;
    final int MAXIMUM_NUM_OBJECTS = 1000;
    
    public StockTrade[] getStockTradeArray() {
        stockTradeArray = new StockTrade[MAXIMUM_NUM_OBJECTS];
        return stockTradeArray;
    }
    
    public int getNumStockTrades() {
        numStockTrades = stockTradeArray.length;
        return numStockTrades;
    }
    
    public boolean addStockTrade(StockTrade tradeObj) {
        boolean successful = false;
        for (int i = 0; i < stockTradeArray.length; i++) {
            if (stockTradeArray[i] == null) {
                stockTradeArray[i] = tradeObj;
                successful = true;
            }
        }
    return successful;
    }
    
    public boolean removeStockTradeByBroker(String license) {
        boolean objectsDeleted = false;
        for (int i = 0; i <stockTradeArray.length; i++) {
            StockTrade stockTradeObj = stockTradeArray[i];
            if (stockTradeObj.getBrokerLicense().equals(license)) {
                stockTradeObj = null;
            }
        }
        
        
    return objectsDeleted;
    }
    
    
    
    
    
}
