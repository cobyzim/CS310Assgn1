/*
 * Implementation class that acts as a bookkeeping class, associating and 
 * disassociating broker licenses with the go-karts they are using or were using
 */
package cs310zimmerman;

/**
 * 
 * @author Coby Zimmerman
 */
public class GoKartUsageImpl 
{
    String[] goKartUsage;
    final int ARRAY_SIZE;

    /**
     * Constructor method that initializes the array of strings to be filled
     * with broker licenses with a size of the total number of go-karts.
     * 
     * @param numGoKarts - passes in number of go-karts
     */
    public GoKartUsageImpl(int numGoKarts) 
    {
        ARRAY_SIZE = numGoKarts;
        goKartUsage = new String[ARRAY_SIZE];
        
    }
    
    /**
     * Method used to retrieve the array of go-karts associated with broker
     * licenses
     * 
     * @return - returns the string array
     */
    public String[] getGoKartUsageArray() {             //might have to delete this
        return goKartUsage;
    }
 
    /**
     * Associates a broker with a particular go-kart
     * 
     * @param goKartNum - the go-kart number
     * @param brokerLic - the broker license number
     * @param brokerName - the broker full name
     * @param goKartType - the type of go-kart being assigned
     */
    public void assignGoKartToBroker(int goKartNum, String brokerLic, 
            String brokerName, String goKartType) 
    {
        goKartUsage[goKartNum-1] = brokerLic;
        System.out.printf("%s has been assigned %s go-kart number %d\n", 
            brokerName, goKartType, goKartNum);
    }
    
    /**
     * Gets a broker license for a specific go-kart
     * 
     * @param goKartNum - the number of the go-kart to check if it is assigned
     * @return - the broker license or null if not assigned
     */
    public String getBrokerLicenseForGoKart(int goKartNum) 
    {
        if (goKartUsage[goKartNum] != null) {
        return goKartUsage[goKartNum];
        }
        else {
        return null;
        }
    }

    /**
     * Will disassociate a go-kart from a particular broker 
     * 
     * @param brokerLic - the broker license
     * @param brokerName - the broker full name
     * @return - the number of the go-kart that was being used
     */
    public int returnGoKart(String brokerLic, String brokerName) 
    {
        int kartNum = -1;
        
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (goKartUsage[i].equals(brokerLic)) {
                goKartUsage[i] = "";
                kartNum = i + 1;
                System.out.printf("%s has returned go-kart number %d\n", 
                    brokerName, kartNum);
            }
        }
        return kartNum;
    }      
}

