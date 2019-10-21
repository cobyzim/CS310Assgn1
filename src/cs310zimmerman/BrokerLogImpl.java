/*
 * Implementation class used to implement the list of brokers in a linked list. 
 * It creates / manages the ordered list of objects with eight methods.
 */
package cs310zimmerman;

/**
 *
 * @author Coby Zimmerman
 */
public class BrokerLogImpl {

    private BrokerNode top;
    
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
       boolean successful = true;
       boolean brokerAdded = false;
       BrokerNode previous = null;
       BrokerNode current = null;
       BrokerNode newNode = new BrokerNode(brokerObj);
       int compareToResult;
       
       if (isEmpty()) {
           top = newNode;
           return successful;
       }
       
       previous = top;
       
       if (newNode.getData().getBrokerLicense().compareTo(previous.getData().getBrokerLicense()) < 0) {
           top = newNode;
           top.setNext(previous);
           return successful;
       }
       
       current = previous.getNext();
       
       while (current != null) {
           if (newNode.getData().getBrokerLicense().compareTo(current.getData().getBrokerLicense()) < 0) {
               newNode.setNext(current);
               previous.setNext(newNode);
               return successful;
           }
           else {
               previous = current;
               current = previous.getNext();
           }
       }
       
       previous.setNext(newNode);
       
       return successful;
    }
    
    /**
     * Method used to remove a broker object from the log of brokers if the
     * desired license is found.
     * 
     * @param license - passes in a broker license
     * @return - returns true or false based on if a broker object is removed or
     * not
     */
    
    public boolean removeBroker(String license) {
        boolean successful = false;
        BrokerNode previous = null;
        BrokerNode current = top;
        
        while (current != null && current.getData().getBrokerLicense().compareTo(license) != 0) {
            previous = current;
            current = current.getNext();
        }
        
        if (top.getData().getBrokerLicense().compareTo(license) == 0) {
            top = top.getNext();
            successful = true;
        }
        
        else if (current != null && current.getNext() != null) {
            previous.setNext(current.getNext());
            successful = true;
        }
        
        else if (current != null && current.getNext() == null) {
            previous.setNext(current.getNext());
            successful = true;
        }
        
        else if (current == null) {
            successful = false;
        }
        
        
        return successful;
    }
    
    /**
     * Method used to test if a broker with a specific license exists in the log
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
    
    public void traverse() {
        BrokerNode current = top;
        System.out.println("Broker Log: ");
        
        while (current != null) {
            System.out.println(current.getData().toString());
            current = current.getNext();
        }    
    }
    
    public void cleanList(StockTradeLogImpl stockTradeLogImpl) {
        BrokerNode previous = null;
        BrokerNode current = top;
        
        while (current != null) { //&& current.getData().isValidLicense()) {
            previous = current;
            current = current.getNext();
            
            if (!previous.getData().isValidLicense()) {
                current = current.getNext();
            }
        }
        
        
        /*
        if (top.getData().getBrokerLicense().compareTo(license) == 0) {
            top = top.getNext();
        }
        
        else if (current != null && current.getNext() != null) {
            previous.setNext(current.getNext());
        }
        
        else if (current != null && current.getNext() == null) {
            previous.setNext(current.getNext());
        }
        
        else if (current == null) {
        }
        */
        
    }
    
}

