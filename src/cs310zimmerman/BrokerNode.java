/*
 * Class used to set and retrieve values for broker object nodes as well as
 * the next node each node references.
 */
package cs310zimmerman;
import java.util.LinkedList;
import cs310zimmerman.*;

/**
 *
 * @author Coby Zimmerman
 */
public class BrokerNode {

    private Broker data;
    private BrokerNode left;
    private BrokerNode right;
    
    /**
     * Method used to create a broker node using a broker object
     * 
     * @param broker - passes in broker object
     */
    public BrokerNode(Broker data) {
        this.data = data;
        left = null;
        right = null;
    }
    
    public String toString() {
        return data.toString();
    }
    
    /**
     * Method used to retrieve the next node in the list
     * 
     * @return - returns the next node
     */
    public BrokerNode getLeft() {
        return left;
    }
    
    /**
     * Method used to set a broker node's next to another node
     * 
     * @param brokerNode - passes in a broker node
     */
    public void setLeft(BrokerNode brokerNode) {
        left = brokerNode;
    }
    
    public BrokerNode getRight() {
        return right;
    }
    
    public void setRight(BrokerNode brokerNode) {
        right = brokerNode;
    }
    
    /**
     * Method that gives a node the value of a broker object
     * 
     * @param broker - passes in broker object
     */
    public void setData(Broker broker) {
        data = broker;
    }
    
    /**
     * Method used to retrieve the data from a node
     * 
     * @return - returns the data
     */
    public Broker getData() {
        return data;
    }
    
}
    
