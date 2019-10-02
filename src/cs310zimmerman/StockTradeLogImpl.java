/*
 * Implementation class used to implement the log of stock trades in an 
 * unordered array. It creates and manages these objects using nine methods.
 */
package cs310zimmerman;

/**
 *
 * @author Coby Zimmerman
 */
public class StockTradeLogImpl {
    
    private int numStockTrades = 0;
    final int MAXIMUM_NUM_OBJECTS = 1000;
    private StockTrade[] stockTradeArray = getStockTradeArray();

    /**
     * Method used to return the contents of the log of stock trades.
     * 
     * @return - returns log of stock trades
     */
    public StockTrade[] getStockTradeArray() {
        stockTradeArray = new StockTrade[MAXIMUM_NUM_OBJECTS];
        return stockTradeArray;
    }
    
    /**
     * Method used to return the count for the amount of elements in the
     * stock trade array log.
     * 
     * @return - returns the count attribute
     */
    public int getNumStockTrades() {
        return numStockTrades;
    }
    
    /**
     * Method used to add a stock trade object to the log of stock trades if
     * there is room.
     * 
     * @param tradeObj - passes in stock trade object
     * @return - returns true or false based on whether or not a stock trade was
     * added to the log.
     */
    public boolean addStockTrade(StockTrade tradeObj) {
        boolean successful = false;
        //check if there's room in the array for more trades
        if (numStockTrades < MAXIMUM_NUM_OBJECTS) {
            stockTradeArray[numStockTrades] = tradeObj;
            numStockTrades = numStockTrades++;
        }
        return successful;
    }
    
    /**
     * Method used to delete stock trade objects from the log of stock trades if
     * they have the given broker license.
     * 
     * @param license - passes in a broker license
     * @return - returns true or false based on whether or not any stock trade
     * objects are deleted
     */
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
            double holdings = stockTradeObj.getWholeShares() * stockTradeObj.getPricePerShare();
            stockHoldingSum = stockHoldingSum + holdings;
        }
        return stockHoldingSum;
    }
    
    public double totalStockTradeValue(String license) {
        double stockHoldingSum = 0.0;
        
        for(int i = 0; i < numStockTrades; i++) {
            StockTrade stockTradeObj = stockTradeArray[i];
            if (stockTradeObj.getBrokerLicense().equals(license)) {
                double holdings = stockTradeObj.getWholeShares() * stockTradeObj.getPricePerShare();
                stockHoldingSum = stockHoldingSum + holdings;
            }
        }
        
        
        return stockHoldingSum;
    }
    
    
}
