/*
 * Implementation class used to implement the treeMap of stock trades. It 
 * creates and manages these objects using three methods.
 */
package cs310zimmerman;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Set;
import java.util.Map;

/**
 *
 * @author Coby Zimmerman
 */
public class StockTradeLogImpl {
    
    TreeMap treeMap = new TreeMap();
    private int numStockTrades = 0;
    final int MAXIMUM_NUM_OBJECTS = 1000;
    private StockTrade[] stockTradeArray = new StockTrade[MAXIMUM_NUM_OBJECTS];
    
    /**
     * Method used to add a stock trade object to the treeMap of stockTrades
     * 
     * @param tradeObj - passes in stock trade object
     */
    public void addStockTrade(StockTrade tradeObj) {
        
        if (numStockTrades < MAXIMUM_NUM_OBJECTS) {
            stockTradeArray[numStockTrades] = tradeObj;
            numStockTrades++;
            String stockSymbol = tradeObj.getStockSymbol();
            
            treeMap.put(stockSymbol, tradeObj);
        }
    }
    
    /**
     * Method that finds a stockTrade if it is in the treeMap given a certain
     * stockSymbol
     *  
     * @param stockSymbol - passes in stockSymbol
     * @return - returns reference to the stockTrade object or null if it is
     * not in the treeMap.
     */
    public StockTrade findStockTrade(String stockSymbol) {
        
        if (treeMap.containsKey(stockSymbol)) {
            return (StockTrade) treeMap.get(stockSymbol);
        }
        else {
            return null;
        }
    }
    
    /**
     * Method used to iterate through the treeMap and display the elements using
     * the toString method.
     * 
     */
    public void traverseDisplay() {
        Set set = treeMap.entrySet();
        
        Iterator iter = set.iterator();
        
        while(iter.hasNext()) {
            Map.Entry me = (Map.Entry)iter.next();
            String stockTrade = me.toString();
            String substringStockTrade = stockTrade.substring(4);
            System.out.println(substringStockTrade);
        }
    }
    
}
