/*
 * Implementation class used to implement the list of brokers in a linked list. 
 * It creates / manages the ordered list of objects with nine methods.
 */
package cs310zimmerman;

/**
 *
 * @author Coby Zimmerman
 */
public class BrokerLogImpl {

    private BrokerNode top;
    private Broker[] brokerHashTable;
    private int numBrokersInTable;
    private final int BROKER_TABLE_SIZE = 19;
    
    public BrokerLogImpl() {
        brokerHashTable = new Broker[BROKER_TABLE_SIZE];
        numBrokersInTable = 0;
    }
    
    /**
     * Method to retrieve the head (reference to the first node)
     * 
     * @return - returns top (head)
     */
    public BrokerNode getTop() {
        return this.top;
    }
    
    /**
     * Method used to set the value of the first node
     * 
     * @param top - passes in the node to be set to top
     */
    public void setTop(BrokerNode top) {
        this.top = top;
    }
    
    /**
     * Method used to determine if the linked list has any nodes or not
     * 
     * @return - returns true if the list is empty, false if it is not
     */
    public boolean isEmpty() {
        if (top == null) {
            return true;
        }
        return false;
    }
    
    /**
     * Method used to add brokers to list of brokers in ascending order.
     * 
     * @param brokerObj - passes in broker object
     * @return - returns true or false based on if a broker object is added or 
     * not
     */
    public boolean addBroker(Broker brokerObj) {
        boolean isAdded = false;
       
        //if (numBrokersInTable >= BROKER_TABLE_SIZE) {
        //    return false;
        //}
        int hashValue = brokerObj.hashCode();
        int compressedHashValue = hashValue % BROKER_TABLE_SIZE;
       
        while (!isAdded) {
            
            if (numBrokersInTable <= BROKER_TABLE_SIZE) {
                if (findBroker(compressedHashValue) == null) {
                    //if (brokerHashTable[compressedHashValue] == null) {  
                    brokerHashTable[compressedHashValue] = brokerObj;
                    numBrokersInTable++;
                    isAdded = true;
                    System.out.printf("ADDED: Broker with license %s\n", brokerObj.getBrokerLicense());
                }
                    
                else {
                    compressedHashValue++;
                    if (compressedHashValue > brokerHashTable.length) {
                        compressedHashValue = 0;
                    }   
                }
            }
            else {
                return false;
            }
        }      
        return isAdded;
    }
    
    /**
     * Method used to test if a broker with a specific license exists in list
     * of brokers already.
     * 
     * @param license - passes in a broker license 
     * @return - returns true or false based on whether or not the license
     * exists in the log.
     */
    
    public boolean isLicenseUnique(String license) {
        boolean licenseUnique = true;

        for (BrokerNode seek = top; seek != null; seek = seek.getNext()) {
            if (seek.getData().getBrokerLicense().equals(license)) {
                  licenseUnique = false;
            }
        }
        return licenseUnique;
    }
    
    /**
     * Method used to iterate through the linked list of brokers and display
     * each one using the toString method.
     */
    public void traverse() {
        BrokerNode current = top;
        System.out.println("Broker Log: ");
        
        while (current != null) {
            System.out.println(current.getData().toString());
            current = current.getNext();
        }    
    }
    
    /**
     * Method used to clean up the list of brokers based on whether they have
     * valid licenses or not
     * 
     * @param stockTradeLogImpl - passes in stockTradeLogImpl
     */
    public void cleanList(StockTradeLogImpl stockTradeLogImpl) {
        BrokerNode previous = null;
        BrokerNode current = top;
        
        while (current != null) { //&& current.getData().isValidLicense()) {
            previous = current;
            current = current.getNext();
            
            if (!previous.getData().isValidLicense()) {
                System.out.println("Invalid license for broker " + 
                        previous.getData().getBrokerLicense() + " -- Deleting "
                        + "broker and stockTrades for that broker from logs");
                //current = current.getNext();
                //removeBroker(previous.getData().getBrokerLicense());
                stockTradeLogImpl.removeStockTradeByBroker(
                        previous.getData().getBrokerLicense());
            }
        }
    }
    
    /**
     * Method that finds a broker if it is in the log given a certain license.
     * 
     * @param license - passes in broker license
     * @return - returns the brokerNode or null if it is not in the log
     */
    public Broker findBroker(int brokerCompressedHash) {
    
    /*
        BrokerNode brokerNode = null;
        for (BrokerNode seek = top; seek != null; seek = seek.getNext()) {
            if (seek.getData().getBrokerLicense().equals(license)) {
                brokerNode = seek;
            }
        }
        
    return brokerNode;
    */
        
        /*
        String str = license;
        String newString = str.substring(3);
        int hashNumber = Integer.parseInt(newString);
        */
        
        Broker foundBroker = null;
        
        //int brokerCompressedHash = brokerHash % BROKER_TABLE_SIZE;
        if (brokerHashTable[brokerCompressedHash] != null) {
            foundBroker = brokerHashTable[brokerCompressedHash];
        }
        
        return foundBroker;
        
        
    }

}

