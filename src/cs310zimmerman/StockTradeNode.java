/*
 * Class used to set and retrieve values for stockTrade object nodes as well as
 * the next node each node references.
 */
package cs310zimmerman;

/**
 *
 * @author Coby Zimmerman
 */
public class StockTradeNode {
    private StockTradeNode next;
    private StockTrade data;
    
    /**
     * Method used to create a stockTradeNode using a stockTrade object
     * 
     * @param stockTrade
     */
    public StockTradeNode(StockTrade stockTrade) {
        next = null;
        data = stockTrade;
    }
    
    /**
     * Method used to retrieve the next node in the list
     * 
     * @return - returns the next node
     */
    public StockTradeNode getNext() {
        return next;
    }
    
    /**
     * Method used to set a stockTrade's next node to another node
     * 
     * @param stockTradeNode - passes in stockTradeNode
     */
    public void setNext(StockTradeNode stockTradeNode) {
        next = stockTradeNode;
    }
    
    /**
     * Method that gives a node the value of a stockTrade object
     * 
     * @param stockTrade
     */
    public void setData(StockTrade stockTrade) {
        data = stockTrade;
    }
    
    /**
     * Method that retrieves data from a given node
     * 
     * @return - returns the data
     */
    public StockTrade getData() {
        return data;
    }

}
