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
    /*
    public boolean removeBroker(String license) {
        boolean successful = false;
        int arraySize = brokerLog.size() - 1;
        
        for (int i = 0; i <= arraySize; i++) {
            Broker brokerLogObj = brokerLog.get(i);
            if (brokerLogObj.getBrokerLicense().equals(license)) {
                brokerLog.remove(i);
                successful = true;
            }
        }
        return successful;
    }
    */
    
    /**
     * Method used to test if a broker with a specific license exists in the log
     * of brokers already.
     * 
     * @param license - passes in a broker license 
     * @return - returns true or false based on whether or not the license
     * exists in the log.
     */
    /*
    public boolean isLicenseUnique(String license) {
        boolean isUnique = true;
        boolean licenseExists = false;
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
    }
    */
    
    //public traverse() {
        
   // }
    
    //public cleanList(StockTradeLogImpl stockTradeLogImpl) {
        
    //}
    
/*
    public String toString() {
            String result = "";
            BrokerNode current = top;
            while(current.getNext() != null){
                System.out.println(current.getData().toString());
                result += current.getData().toString();
                if(current.getNext() != null){
                     result += ", ";
                }
                current = current.getNext();
            }
            return "List: " + result;
    }
    */
}

