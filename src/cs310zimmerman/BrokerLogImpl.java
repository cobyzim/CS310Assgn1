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

    private Broker[] brokerHashTable;
    private int numBrokersInTable;
    private final int BROKER_TABLE_SIZE = 19;
    
    public BrokerLogImpl() {
        brokerHashTable = new Broker[BROKER_TABLE_SIZE];
        numBrokersInTable = 0;
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
    
    /**
     * Method used to iterate through the linked list of brokers and display
     * each one using the toString method.
     */
    public void traverse() {
        /*
        BrokerNode current = top;
        System.out.println("Broker Log: ");
        
        while (current != null) {
            System.out.println(current.getData().toString());
            current = current.getNext();
        }    
        */
    }
    
    /**
     * Method that finds a broker if it is in the log given a certain license.
     * 
     * @param license - passes in broker license
     * @return - returns the brokerNode or null if it is not in the log
     */
    public Broker findBroker(String brokerLicense) {
    
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
        
        Broker dummyBroker = new Broker();
        dummyBroker.setBrokerLicense(brokerLicense);
        int hashCode = dummyBroker.hashCode();
        int compressedHashCode = hashCode % BROKER_TABLE_SIZE;
        
        //Broker foundBroker = null;
        
        //int brokerCompressedHash = brokerHash % BROKER_TABLE_SIZE;
        if (brokerHashTable[compressedHashCode] != null) {
            return brokerHashTable[compressedHashCode];
        }
        else {
            return null;
        }       
    }

}

