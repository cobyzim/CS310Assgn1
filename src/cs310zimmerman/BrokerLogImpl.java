/*
 * Implementation class used to implement the log of brokers in an arraylist. It
 * creates / manages the ordered list of objects with four methods.
 */
package cs310zimmerman;

import java.util.ArrayList;

/**
 *
 * @author Coby Zimmerman
 */
public class BrokerLogImpl {
    
    private ArrayList<Broker> brokerLog = new ArrayList<Broker>();
    
    /**
     * Method used to return the contents of the log of brokers.
     * 
     * @return - returns log of brokers
     */
    public ArrayList getBrokerLog() {
        return brokerLog;
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
       int arraySize = brokerLog.size() - 1;
       
       if (!brokerLog.isEmpty()) {
           for (int i = 0; i <= arraySize; i++) {
               if(!brokerAdded) {
                   Broker brokerLogObj = brokerLog.get(i);
               
                   int x = brokerLogObj.getBrokerLicense().compareTo(brokerObj.getBrokerLicense());
                   if (x < 0) {
                   }
                   else {
                       brokerLog.add(i, brokerObj);
                       brokerAdded = true;
                   }
               }
               
           }
           if(!brokerAdded) {
               brokerLog.add(brokerObj);
           }
           
       }
       else {
           brokerLog.add(brokerObj);
       }
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
    
    /**
     * Method used to test if a broker with a specific license exists in the log
     * of brokers already.
     * 
     * @param license - passes in a broker license 
     * @return - returns true or false based on whether or not the license
     * exists in the log.
     */
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
}
