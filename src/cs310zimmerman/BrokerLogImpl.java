/*
 * Implementation class used to implement a hashSet of broker objects. 
 * It creates / manages the hashSet array of objects with four methods.
 */
package cs310zimmerman;


import java.io.*;
/**
 *
 * @author Coby Zimmerman
 */
public class BrokerLogImpl {

    private BrokerNode root;
    
    /**
     * Constructor that initializes the hashTable size and provides a counter
     */
    public BrokerLogImpl() {
        root = null;
    }
    
    public BrokerLogImpl(BrokerNode root) {
        this.root = root;
    }
    
    public BrokerNode getRoot() {
        return root;
    }
    
    public void setRoot(BrokerNode brokerNode) {
        root = brokerNode;
    }
    
    /**
     * Method used to add brokers to hashSet of brokers if there is room and
     * the brokerLicense is unique
     * 
     * @param brokerObj - passes in broker object
     * @return - returns true or false based on if a broker object is added or 
     * not
     */
    
    public void addBroker(BrokerNode root, Broker broker) {
        
        if (root != null) {
            if (broker.getBrokerLicense().compareTo(root.getData().getBrokerLicense()) < 0) {
                if (root.getLeft() != null) {
                    addBroker(root.getLeft(), broker);
                }
                else {
                    BrokerNode newNode = new BrokerNode(broker);
                    root.setLeft(newNode);
                }
            }
            else {
                if (root.getRight() != null) {
                    addBroker(root.getRight(), broker);
                }
                else {
                    BrokerNode newNode = new BrokerNode(broker);
                    root.setRight(newNode);
                }
            }
        }
        else {
            BrokerNode newNode = new BrokerNode(broker);
            setRoot(newNode);    
        }
    }
    
    /**
     * Method that finds a broker if it is in the hashSet given a certain 
     * license.
     * 
     * @param license - passes in broker license
     * @return - returns reference to the broker object or null if it is not in 
     * the hashSet
     */
    public Broker findBroker(Broker target) {
        return findBroker(root, target);
    }
    
    private Broker findBroker(BrokerNode localRoot, Broker target) {
        if (localRoot == null) {
            return null;
        }
        
        //Below compares target with data field at the root
        int compResult = target.getBrokerLicense().compareTo(localRoot.getData().getBrokerLicense());
        if (compResult == 0) {
            return localRoot.getData();
        }
        else if (compResult < 0) {
            return findBroker(localRoot.getLeft(), target);
        }
        else {
            return findBroker(localRoot.getRight(), target);
        }
        
    }
    
    public void traverseDisplay() {
        traverseDisplay(root);
    }
    
    private void traverseDisplay(BrokerNode node) {
        if (node != null) {
            traverseDisplay(node.getLeft());
            System.out.println(node.getData().toString());
            traverseDisplay(node.getRight());
        }
    }
    
}

