/*
 * Implementation class used to implement a binary search tree of broker objects 
 * It creates/manages the tree of objects with eight methods.
 */
package cs310zimmerman;

/**
 *
 * @author Coby Zimmerman
 */
public class BrokerLogImpl {

    private BrokerNode root;
    
    /**
     * Constructor that initializes the root data field to null
     */
    public BrokerLogImpl() {
        root = null;
    }
    
    /**
     * Getter method used to get the root of the tree
     * 
     * @return - returns the root of the tree
     */
    public BrokerNode getRoot() {
        return root;
    }
    
    /**
     * Setter method used to set the root of the tree
     * 
     * @param brokerNode - passes in a BrokerNode
     */
    public void setRoot(BrokerNode brokerNode) {
        root = brokerNode;
    }
    
    /**
     * Method used to recursively add brokers to binary search tree of brokers
     * 
     * @param root - passes in the root of the tree
     * @param broker - passes in broker object
     */
    public void addBroker(BrokerNode root, Broker broker) {
        
        if (root != null) {
            if (broker.getBrokerLicense().compareTo(root.getData().
                    getBrokerLicense()) < 0) {
                
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
     * Method that recursively finds a broker if it is in the tree given a 
     * certain license.
     * 
     * @param targetLicense - passes in broker license
     * @return - returns reference to the broker object or null if it is not in 
     * the hashSet
     */
    public Broker findBroker(String targetLicense) {
        return findBroker(root, targetLicense);
    }
    
    private Broker findBroker(BrokerNode localRoot, String license) {
        if (localRoot == null) {
            return null;
        }
        //Below compares target with data field at the root
        int compResult = license.compareTo(localRoot.getData().
                getBrokerLicense());
        
        if (compResult == 0) {
            return localRoot.getData();
        }
        else if (compResult < 0) {
            return findBroker(localRoot.getLeft(), license);
        }
        else {
            return findBroker(localRoot.getRight(), license);
        } 
    }
    
    /**
     * Method used to recursively traverse through the BrokerNodes of the 
     * binary search tree and display them using an in-order traversal
     * 
     */
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

