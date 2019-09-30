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
    private int numStockTrades = 0;
    final int MAXIMUM_NUM_OBJECTS = 1000;
    
    public StockTrade[] getStockTradeArray() {
        stockTradeArray = new StockTrade[MAXIMUM_NUM_OBJECTS];
        return stockTradeArray;
    }
    
    public int getNumStockTrades() {
        return numStockTrades;
    }
    
    public boolean addStockTrade(StockTrade tradeObj) {
        boolean successful = false;
        //check if there's room in the array for more trades
        if (numStockTrades < MAXIMUM_NUM_OBJECTS) {
            stockTradeArray[numStockTrades] = tradeObj;
            numStockTrades = numStockTrades++;
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
    
    public boolean removeStockTrade(String stockSymbol) {
        boolean stockTradeRemoved = false;
        
    return stockTradeRemoved;    
    }
    
    public boolean isStockSymbolUnique(String stockSymbol) {
        boolean isUnique = false;
        for (int i = 0; i < stockTradeArray.length; i++) {
            StockTrade stockTradeLogObj = stockTradeArray[i];
            isUnique = !stockTradeLogObj.getStockSymbol().equals(stockSymbol);
        }
        
    return isUnique;
    }
    
    public int numberOfBrokerStockTrades(String license) {
        int x = 0;
        
    return x;
    }
    
    public double totalStockTradeValue() {
        double y = 0.0;
        
    return y;
    }
    
    public double totalStockTradeValue(String license) {
        double z = 0.0;
        
    return z;
    }
    
    
}