/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs310zimmerman;

/**
 *
 * @author Coby Zimmerman
 */
public class StockTradeNode {
    private StockTradeNode next;
    private StockTrade data;
    
    public StockTradeNode(StockTrade stockTrade) {
        next = null;
        data = stockTrade;
    }
    
    public StockTradeNode getNext() {
        return next;
    }
    
    public void setNext(StockTradeNode stockTradeNode) {
        next = stockTradeNode;
    }
    
    public void setData(StockTrade stockTrade) {
        data = stockTrade;
    }
    
    public StockTrade getData() {
        return data;
    }

}
