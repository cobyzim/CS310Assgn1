/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs310zimmerman;
import java.util.LinkedList;

/**
 *
 * @author cobyz
 */
public class BrokerNode {
    private BrokerNode next;

    public BrokerNode(Broker broker) {
        next = null;
    }
    
    public BrokerNode getNext() {
        return next;
    }
    
    public BrokerNode setNext(BrokerNode brokerNode) {
        next = brokerNode;
        return next;
    }
}
    
    