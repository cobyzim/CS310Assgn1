/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs310zimmerman;
import java.util.LinkedList;
import cs310zimmerman.*;

/**
 *
 * @author cobyz
 */
public class BrokerNode {
    private BrokerNode next;
    private Broker data;

    public BrokerNode(Broker broker) {
        next = null;
        data = broker;
    }
    
    public BrokerNode getNext() {
        return next;
    }
    
    public void setNext(BrokerNode brokerNode) {
        next = brokerNode;
    }
    
    public void setData(Broker broker) {
        data = broker;
    }
    
    public Broker getData() {
        return data;
    }
    
}
    
    