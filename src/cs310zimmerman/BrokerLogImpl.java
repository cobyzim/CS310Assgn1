/*
 * Implementation class used to implement the log of brokers in an arraylist. It
 * creates / manages the ordered list of objects with four methods.
 */
package cs310zimmerman;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Coby Zimmerman
 */
public class BrokerLogImpl {
    
    //BrokerLogImpl brokerLog = new BrokerLogImpl();

    private BrokerNode top;
    
    public BrokerNode getTop() {
        return this.top;
    }
    
    public void setTop(BrokerNode top) {
        this.top = top;
    }
    
    public boolean isEmpty() {
        if (top == null) {
            return true;
        }
        return false;
    }
    
    /**
     * Method used to add brokers to log of brokers in ascending order.
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
       
       /*
       if (!isEmpty()) {
           compareToResult = newNode.getData().getBrokerLicense().compareTo(current.getData().getBrokerLicense());
           while(newNode.getData().getBrokerLicense().compareTo(current.getData().getBrokerLicense()) <= 0) {
               previous = current;
               current = current.getNext();
           }
           current.setNext(newNode);
           //newNode.setNext(current);
       }
       else {
           setTop(newNode);
           previous = current;
       }
       */
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
        //boolean isUnique = true;
        boolean isUnique = true;
        boolean licenseExists = false;
        //BrokerNode seek = top;
        
        //while (seek != null && seek.getData().getBrokerLicense().compareTo(license) != 0) {
        //    seek = seek.getNext();
        //    isUnique = false;
        //}
        
        for (BrokerNode seek = top; seek != null; seek = seek.getNext()) {
            if (seek.getData().getBrokerLicense().equals(license)) {
                isUnique = false;
                if (isUnique = false) {
                    licenseExists = true;
                }
            }
        }
        if (licenseExists) {
            return false;
        }
        else return true;
                
        /*
        int arraySize = brokerLog.size() - 1;
        
        for (int i = 0; i <= arraySize; i++) {
            if (!licenseExists) {
                Broker brokerLogObj = brokerLog.get(i);
                isUnique = brokerLogObj.getBrokerLicense().equals(license);
               if (isUnique == true) {
                    licenseExists = true;
                }
            }
        }
        if (licenseExists) {
            return false;
        }
        else return true;
        */
    }
    
    
    public void traverse() {
        BrokerNode current = top;
        System.out.println("Broker Log: ");
        
        while (current != null) {
            System.out.println(current.getData().toString());
            current = current.getNext();
        }    
    }
    
    //public cleanList(StockTradeLogImpl stockTradeLogImpl) {
        
    //}
    
}

