/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs310zimmerman;

import java.util.ArrayList;

/**
 *
 * @author cobyz
 */
public class BrokerLogImpl {
    private ArrayList<Broker> brokerLog = new ArrayList<Broker>();
    
    public ArrayList getBrokerLog() {
        return brokerLog;
    }
    
    public boolean addBroker(Broker brokerObj) {
       boolean successful = true;
       boolean brokerAdded = false;
       int arraySize = brokerLog.size() - 1;
       
       
       if (!brokerLog.isEmpty()) {
           for (int i = 0; i <= arraySize; i++) {
               if(!brokerAdded) {
                   Broker brokerLogObj = brokerLog.get(i);
               
                   System.out.println("BrokerObj: " + brokerObj.getBrokerLicense());
                   int x = brokerLogObj.getBrokerLicense().compareTo(brokerObj.getBrokerLicense());
                   if (x < 0) {
                       System.out.println("I'm bigger than current Broker" + brokerLogObj.getBrokerLicense());
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
           System.out.println("in else");
       }
        //System.out.println(brokerLog);
       System.out.println("Array List has " + brokerLog.size() +
               "entries");
       System.out.println("ARRAY LIST: " + brokerLog);
       return successful; 
    }
    
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
    
    public boolean isLicenseUnique(String license) {
        boolean isUnique = true;
        boolean licenseExists = false;
        int arraySize = brokerLog.size() - 1;
        
        for (int i = 0; i <= arraySize; i++) {
            if (!licenseExists) {
                Broker brokerLogObj = brokerLog.get(i);
                //System.out.println("object license = " + brokerLogObj.getBrokerLicense());
                //System.out.println("license arg = " + license);
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
