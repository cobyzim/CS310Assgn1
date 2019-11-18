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
    
    private final int MAX_SIZE;
    private int currSize;
    private int numNodes;
    private StockTradeNode[] stockTradeHashSet;
    
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
     * Method used to add a stock trade object to the list of stock trades if
     * there is room.
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
                        System.out.printf("ADDED: StockTrade with stock symbol %s "
                            + "listed by Broker %s\n", tradeObj.getStockSymbol(), 
                            tradeObj.getBrokerLicense());
                    }
                    else {
                        //boolean nodeAdded = false;
                        for (StockTradeNode currNode = 
                            stockTradeHashSet[compressedHashCode]; 
                            currNode != null && !isAdded; currNode = currNode.getNext()) {
                            if (currNode.getNext() == null) {
                                currNode.setNext(newNode);
                                numNodes++;
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
            else {
                return false;
            }
        }
    
    return isAdded;
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
     * Method used to iterate throughout the list of stock trades and display 
     * them using the toString method.
     * 
     */
    public void traverseDisplay() {
        /*
        Iterator<StockTrade> iter = stockTradeList.iterator();
        System.out.println("StockTrade Log: ");
        
        while (iter.hasNext()) {
            StockTrade currentNode = iter.next();
            System.out.println(currentNode.toString());
        }
        */
    }
    
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
    
    public void displayHash() {
        System.out.println("\nStockTrade Hash Table:\n");
        
        for (int i = 0; i < MAX_SIZE; i++) {
            if (stockTradeHashSet[i] == null) {
                System.out.printf("\tIndex %d is empty\n", i);
            }
            else {
                System.out.printf("\tIndex %d contains StockTrades: ", i);
                for (StockTradeNode currNode = stockTradeHashSet[i]; currNode != null; currNode = currNode.getNext()) {
                    System.out.printf("%s ", currNode.getData().getStockSymbol());
                }
                System.out.println();
            }
        }
    }
}
