/*
 * Implementation class used to implement the hashMap of stock trades. It 
 * creates and manages these objects using four methods.
 */
package cs310zimmerman;
import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author Coby Zimmerman
 */
public class StockTradeLogImpl {
    
    private final int MAX_SIZE;
    private int currSize;
    private int numNodes;
    private StockTradeNode[] stockTradeHashSet;
    
    /**
     * Constructor that initializes data fields including the hashTable for
     * stockTradeNodes
     *  
     */
    public StockTradeLogImpl() {
        MAX_SIZE = 17;
        currSize = 0;
        numNodes = 0;
        stockTradeHashSet = new StockTradeNode[MAX_SIZE];
        for (int index = 0; index < stockTradeHashSet.length; index++) {
            stockTradeHashSet[index] = null;
        }
    }
    
    /**
     * Method used to add a stock trade object to the hashMap of stockTrades
     * if there is room and if the stockSymbol is unique.
     * 
     * @param tradeObj - passes in stock trade object
     * @return - returns true or false based on whether or not a stock trade was
     * added to the list.
     */
    public boolean addStockTrade(StockTrade tradeObj) {
        boolean isAdded = false;
        
        StockTradeNode newNode = new StockTradeNode(tradeObj);
        int hashCode = tradeObj.hashCode();
        int compressedHashCode = hashCode % MAX_SIZE;
        
        while (!isAdded) {
            
            if (currSize < MAX_SIZE) {
                if (findStockTrade(tradeObj.getStockSymbol()) == null) {
                    if (stockTradeHashSet[compressedHashCode] == null) {
                        currSize++;
                        numNodes++;
                        stockTradeHashSet[compressedHashCode] = newNode;
                        isAdded = true;
                        System.out.printf("ADDED: StockTrade with stock symbol "
                            + "%s listed by Broker %s\n", 
                            tradeObj.getStockSymbol(), 
                            tradeObj.getBrokerLicense());
                    }
                    else {
                        //boolean nodeAdded = false;
                        for (StockTradeNode currNode = 
                            stockTradeHashSet[compressedHashCode]; 
                            currNode != null && !isAdded; currNode = 
                                currNode.getNext()) {
                            if (currNode.getNext() == null) {
                                currNode.setNext(newNode);
                                numNodes++;
                                isAdded = true;
                                System.out.printf("ADDED: StockTrade with stock"
                                    + " symbol %s listed by Broker %s\n", 
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
            else {
                return false;
            }
        }
    
    return isAdded;
    }
    
    /**
     * Method that finds a stockTrade if it is in the hashMap given a certain
     * stockSymbol
     *  
     * @param stockSymbol - passes in stockSymbol
     * @return - returns reference to the stockTrade object or null if it is
     * not in the hashMap.
     */
    public StockTradeNode findStockTrade(String stockSymbol) {
        
        StockTrade dummyStockTrade = new StockTrade();
        dummyStockTrade.setStockSymbol(stockSymbol);
        int hashCode = dummyStockTrade.hashCode();
        int compressedHashCode = hashCode % MAX_SIZE;
        
        if (stockTradeHashSet[compressedHashCode] != null && 
            stockTradeHashSet[compressedHashCode].getData().getStockSymbol().
                equals(stockSymbol)) {
            return stockTradeHashSet[compressedHashCode];
        }
        else {
            return null;
        }
    }
    
    /**
     * Method that displays the contents of the stockTrade hashMap
     *  
     */
    public void displayHash() {
        System.out.println("\nStockTrade Hash Table:");
        
        for (int i = 0; i < MAX_SIZE; i++) {
            if (stockTradeHashSet[i] == null) {
                System.out.printf("\tIndex %d is empty\n", i);
            }
            else {
                System.out.printf("\tIndex %d contains StockTrades: ", i);
                for (StockTradeNode currNode = stockTradeHashSet[i]; currNode !=
                    null; currNode = currNode.getNext()) {
                    System.out.printf("%s ", currNode.getData().
                            getStockSymbol());
                }
                System.out.println();
            }
        }
    }
}
