/*
 * Implementation class used to implement the list of stock trades in an 
 * unordered linked list. It creates and manages these objects using eleven 
 * methods.
 */
package cs310zimmerman;
import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author Coby Zimmerman
 */
public class StockTradeLogImpl {
    
    private int numStockTrades = 0;
    final int MAXIMUM_NUM_OBJECTS = 1000;
    LinkedList<StockTrade> stockTradeList = new LinkedList<StockTrade>();
    
    private int MAX_SIZE;
    private int currSize;
    private int numIndex;
    private StockTradeNode[] stockTradeHashSet;
    
    public StockTradeLogImpl() {
        MAX_SIZE = 17;
        currSize = 0;
        numIndex = 0;
        stockTradeHashSet = new StockTradeNode[MAX_SIZE];
        for (int index = 0; index < stockTradeHashSet.length; index++) {
            stockTradeHashSet[index] = null;
        }
    }
    

    /**
     * Method used to return the contents of the list of stock trades.
     * 
     * @return - returns list of stock trades
     */
    public LinkedList<StockTrade> getStockTradeList() {
        return stockTradeList;
    }
    
    /**
     * Method used to return the count for the amount of elements in the
     * stock trade list.
     * 
     * @return - returns the count attribute
     */
    public int getNumStockTrades() {
        return numStockTrades;
    }
    
    /**
     * Method used to add a stock trade object to the list of stock trades if
     * there is room.
     * 
     * @param tradeObj - passes in stock trade object
     * @return - returns true or false based on whether or not a stock trade was
     * added to the list.
     */
    public boolean addStockTrade(StockTrade tradeObj) {
        /*
        boolean successful = false;
        
        if (numStockTrades < MAXIMUM_NUM_OBJECTS) {
            stockTradeList.add(numStockTrades, tradeObj);
            numStockTrades += 1;
            successful = true;
        }
        
        return successful;
        */
        boolean isAdded = false;
        
        StockTrade stockTrade = (StockTrade)tradeObj;
        StockTradeNode newNode = new StockTradeNode(stockTrade);
        int hashCode = tradeObj.hashCode();
        int compressedHashCode = hashCode % MAX_SIZE;
        
        while (!isAdded) {
            
            if (currSize < MAX_SIZE) {
                if (stockTradeHashSet[compressedHashCode] == null) {
                    currSize++;
                    numIndex++;
                    stockTradeHashSet[compressedHashCode] = newNode;
                    isAdded = true;
                    System.out.printf("ADDED: StockTrade with stock symbol %s "
                        + "listed by Broker %s\n", tradeObj.getStockSymbol(), 
                        tradeObj.getBrokerLicense());
                }
                else {
                    for (StockTradeNode currNode = 
                        stockTradeHashSet[compressedHashCode]; 
                        currNode != null; currNode = currNode.getNext()) {
                        if (currNode.getNext() == null) {
                            currNode.setNext(newNode);
                            numIndex++;
                            isAdded = true;
                            System.out.printf("ADDED: StockTrade with stock "
                                + "symbol %s listed by Broker %s\n", 
                                tradeObj.getStockSymbol(), 
                                tradeObj.getBrokerLicense());
                        }
                        
                    }
                }
            }
            else {
                return false;
            }
        }
    
    return isAdded;
    }
    
    /**
     * Method used to delete stock trade objects from list of stock trades if
     * they have the given broker license.
     * 
     * @param license - passes in a broker license string
     * @return - returns true or false based on whether or not any stock trade
     * objects are deleted
     */
    public boolean removeStockTradeByBroker(String license) {
        boolean objectsDeleted = false;
        Iterator<StockTrade> iter = stockTradeList.iterator();
        
        while (iter.hasNext()) {
            StockTrade targetNode = iter.next();
            if (targetNode.getBrokerLicense().equals(license)) {
                iter.remove();
                numStockTrades--;
                objectsDeleted = true;
            }
        }
        return objectsDeleted;
    }
    
    /**
     * Method used to delete stock trade objects from list of stock trades if
     * they have a given stock symbol.
     * 
     * @param stockSymbol - passes in a stock symbol string
     * @return - returns true or false based on whether or not any stock trade
     * objects are deleted
     */
    public boolean removeStockTrade(String stockSymbol) {
        boolean stockTradeRemoved = false;
        Iterator<StockTrade> iter = stockTradeList.iterator();
        
        while (iter.hasNext()) {
            StockTrade targetNode = iter.next();
            if (targetNode.getStockSymbol().equals(stockSymbol)) {
                iter.remove();
                numStockTrades--;
                stockTradeRemoved = true;
            }
        }
        return stockTradeRemoved;
    }
    
    /**
     * Method used to determine whether an object with a given stock symbol has 
     * a unique stock symbol (whether the stock symbol does or doesn't already 
     * exist in the list).
     * 
     * @param stockSymbol - passes in a stock symbol string
     * @return - returns true or false depending on whether or not the stock
     * symbol is unique (does or doesn't already exist in the list)
     */
    public boolean isStockSymbolUnique(String stockSymbol) {
        boolean stockSymbolUnique = true;
        Iterator<StockTrade> iter = stockTradeList.iterator();
        
        while (iter.hasNext()) {
            StockTrade targetNode = iter.next();
            if (targetNode.getStockSymbol().equals(stockSymbol)) {
                stockSymbolUnique = false;
            }
        }
        return stockSymbolUnique;
    }
    
    /**
     * Method used to determine the number of trades for one broker object with 
     * a certain license.
     * 
     * @param license - passes in a broker license as a string
     * @return - returns the number of stock trades by one broker
     */
    public int numberOfBrokerStockTrades(String license) {
        int numberOfTrades = 0;
        Iterator<StockTrade> iter = stockTradeList.iterator();
        
        while (iter.hasNext()) {
            StockTrade currentNode = iter.next();
            if (currentNode.getBrokerLicense().equals(license)) {
                numberOfTrades++;
            }
        }
        
        return numberOfTrades;
    }
    
    /**
     * Method used to calculate and return the total sum of stock holdings in
     * the list.
     * 
     * @return - returns the sum of stock holdings in the log
     */
    public double totalStockTradeValue() {
        double stockHoldingSum = 0.0;
        Iterator<StockTrade> iter = stockTradeList.iterator();
        
        while (iter.hasNext()) {
            StockTrade currentNode = iter.next();
            double holdings = currentNode.getWholeShares() * 
                    currentNode.getPricePerShare();
            stockHoldingSum = stockHoldingSum + holdings;
        }

        return stockHoldingSum;
    }
    
    /**
     * Method used to calculate and return the sum of stock holdings for one
     * specific broker's license.
     * 
     * @param license - passes in a broker license string
     * @return - returns the sum of all stock holdings for a specific broker's
     * license
     */
    public double totalStockTradeValue(String license) {
        double stockHoldingSum = 0.0;
        Iterator<StockTrade> iter = stockTradeList.iterator();
        
        while (iter.hasNext()) {
            StockTrade currentNode = iter.next();
            if (currentNode.getBrokerLicense().equals(license)) {
                double holdings = currentNode.getWholeShares() * 
                        currentNode.getPricePerShare();
                stockHoldingSum = stockHoldingSum + holdings;
            }
        }
        
        return stockHoldingSum;
    }
    
    /**
     * Method used to iterate throughout the list of stock trades and display 
     * them using the toString method.
     * 
     */
    public void traverseDisplay() {
        Iterator<StockTrade> iter = stockTradeList.iterator();
        System.out.println("StockTrade Log: ");
        
        while (iter.hasNext()) {
            StockTrade currentNode = iter.next();
            System.out.println(currentNode.toString());
        }
    }
    
    /**
     * Method used to clean the list of stock trades based on whether they have
     * invalid stock symbols.
     * 
     */
    public void cleanList() {
        Iterator<StockTrade> iter = stockTradeList.iterator();
        
        while (iter.hasNext()) {
            StockTrade currentNode = iter.next();
            if (!currentNode.isValidStockSymbol()) {
                System.out.println("Invalid MLS number for stockTrade " + 
                        currentNode.getStockSymbol() + " -- Deleting stockTrade"
                        + " from log");
                iter.remove();
                numStockTrades--;
            }
        }
    }
}
