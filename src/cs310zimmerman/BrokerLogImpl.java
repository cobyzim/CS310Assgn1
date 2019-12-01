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
    
    protected static class BrokeNode {
        protected Broker data;
        protected BrokeNode left;
        protected BrokeNode right;
        
        public BrokeNode(Broker data) {
            this.data = data;
            left = null;
            right = null;
        }
    
        public String toString() {
            return data.toString();
        }
   
    }

    protected BrokeNode root;
    protected boolean addReturn; //I think this is right but im not sure
    
    /**
     * Constructor that initializes the hashTable size and provides a counter
     */
    public BrokerLogImpl() {
        
        root = null;
        
    }
    
    protected BrokerLogImpl(BrokeNode root) {
        
        this.root = root;
        
    }
    
    public BrokerLogImpl(Broker data, BrokerLogImpl leftTree, BrokerLogImpl rightTree) {
        
        root = new BrokeNode(data);
        
        if (leftTree != null) {
            root.left = leftTree.root;
        }
        else {
            root.left = null;
        }
        
        if (rightTree != null) {
            root.right = rightTree.root;
        }
        else {
            root.right = null;
        }
        
    }

    public boolean addBroker(Broker broker) {
        root = addBroker(root, broker);
        return addReturn;
    }
    
    /**
     * Method used to add brokers to hashSet of brokers if there is room and
     * the brokerLicense is unique
     * 
     * @param brokerObj - passes in broker object
     * @return - returns true or false based on if a broker object is added or 
     * not
     */
    
    private BrokeNode addBroker(BrokeNode localRoot, Broker broker) {
        
        
        //if tree empty, return new node
        if (localRoot == null) {
            
            //item not in tree - insert it
            addReturn = true;
            System.out.printf("Added broker with license %s\n", broker.getBrokerLicense());
            
            return new BrokeNode(broker);
        }
        
        else if (broker.getBrokerLicense().compareTo(localRoot.data.getBrokerLicense()) == 0) {
            
            //target license is equal to localRoot brokerLicense - do not add it
            addReturn = false;
            
            return localRoot;
        }
        
        else if (broker.getBrokerLicense().compareTo(localRoot.data.getBrokerLicense()) < 0) {
            
            //target license is less than localRoot brokerLicense - add it
            localRoot.left = addBroker(localRoot.left, broker);
            System.out.printf("Added broker with license %s to left subtree\n", broker.getBrokerLicense());
            
            return localRoot;
        }
        
        else {
            
            //item is greater than localRoot brokerLicense - add it
            localRoot.right = addBroker(localRoot.right, broker);
            System.out.printf("Added broker with license %s to right subtree\n", broker.getBrokerLicense());
            
            return localRoot;
        }
        
        /*
        boolean isAdded = false;

        int hashValue = brokerObj.hashCode();
        int compressedHashValue = hashValue % BROKER_TABLE_SIZE;
       
        while (!isAdded) {
            
            if (numBrokersInTable < BROKER_TABLE_SIZE) {
                if (findBroker(brokerObj.getBrokerLicense()) == null) {
                    if (brokerHashTable[compressedHashValue] == null) {  
                        brokerHashTable[compressedHashValue] = brokerObj;
                        numBrokersInTable++;
                        isAdded = true;
                        System.out.printf("ADDED: Broker with license %s\n", brokerObj.getBrokerLicense());
                    }
                    
                    else {
                        compressedHashValue++;
                        if (compressedHashValue == brokerHashTable.length) {
                            compressedHashValue = 0;
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
    */
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
    
    private Broker findBroker(BrokeNode localRoot, Broker target) {
        if (localRoot == null) {
            return null;
        }
        
        //Below compares target with data field at the root
        int compResult = target.getBrokerLicense().compareTo(localRoot.data.getBrokerLicense());
        if (compResult == 0) {
            return localRoot.data;
        }
        else if (compResult < 0) {
            return findBroker(localRoot.left, target);
        }
        else {
            return findBroker(localRoot.right, target);
        }
        
        /*
        Broker dummyBroker = new Broker();
        dummyBroker.setBrokerLicense(brokerLicense);
        int hashCode = dummyBroker.hashCode();
        int compressedHashCode = hashCode % BROKER_TABLE_SIZE;

        if (brokerHashTable[compressedHashCode] != null && 
            brokerHashTable[compressedHashCode].getBrokerLicense().
                equals(brokerLicense)) {
            return brokerHashTable[compressedHashCode];
        }
        else {
            return null;
        }
        */
        
    }
    
}

