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
    private int numStockTrades = 0;
    final int MAXIMUM_NUM_OBJECTS = 1000;
    private StockTrade[] stockTradeArray = getStockTradeArray();

    
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
        for (int i = 0; i < numStockTrades; i++) {
            StockTrade stockTradeObj = stockTradeArray[i];
            if (stockTradeObj.getBrokerLicense().equals(license)) {
                stockTradeArray[i] = stockTradeArray[numStockTrades];
                numStockTrades--;
                objectsDeleted = true;
            }
        }
        return objectsDeleted;
    }
    
    public boolean removeStockTrade(String stockSymbol) {
        boolean stockTradeRemoved = false;
        for (int i = 0; i < numStockTrades; i++) {
            StockTrade stockTradeObj = stockTradeArray[i];
            if (stockTradeObj.getStockSymbol().equals(stockSymbol)) {
                stockTradeArray[i] = stockTradeArray[numStockTrades];
                numStockTrades--;
                stockTradeRemoved = true;
            }
        }
        
        return stockTradeRemoved;    
    }
    
    public boolean isStockSymbolUnique(String stockSymbol) {
        boolean isUnique = true;
        boolean stockSymbolExists = false;
        
        for (int i = 0; i < stockTradeArray.length; i++) {
            if (!stockSymbolExists && numStockTrades > 0) {
                StockTrade stockTradeLogObj = stockTradeArray[i];
                isUnique = stockTradeLogObj.getStockSymbol().equals(stockSymbol);
                if (isUnique) {
                    stockSymbolExists = true;
                }
            }  
        }
        if (stockSymbolExists) {
            return false;
        }
        else return true;
    }
    
    public int numberOfBrokerStockTrades(String license) {
        int numberOfTrades = 0;
        
        for(int i = 0; i < numStockTrades; i++) {
            StockTrade stockTradeObj = stockTradeArray[i];
            if (stockTradeObj.getBrokerLicense().equals(license)) {
                numberOfTrades++;
            }
        }
        return numberOfTrades;
    }
    
    public double totalStockTradeValue() {
        double stockHoldingSum = 0.0;
        
        for(int i = 0; i < numStockTrades; i++) {
            StockTrade stockTradeObj = stockTradeArray[i];
            stockHoldingSum = stockHoldingSum + stockTradeObj.getWholeShares();
        }
        return stockHoldingSum;
    }
    
    public double totalStockTradeValue(String license) {
        double stockHoldingSum = 0.0;
        
        for(int i = 0; i < numStockTrades; i++) {
            StockTrade stockTradeObj = stockTradeArray[i];
            if (stockTradeObj.getBrokerLicense().equals(license)) {
                stockHoldingSum = stockHoldingSum + stockTradeObj.getWholeShares();
            }
        }
        
        
        return stockHoldingSum;
    }
    
    
}
