package cs310zimmerman;


import java.util.Objects;

/*
 * Class to define data fields and methods for broker objects which contain 
 * broker licenses, first names, last names, department numbers, and commission
 * rates.
 */

/**
 *
 * @author Coby Zimmerman
 */
public class Broker {
    private String brokerLicense;
    private String firstName;
    private String lastName;
    private String dept;
    private double commissionRate;
    private String[] brokerArray;

    /**
     * Constructor with no parameters used for broker objects with no hard-
     * coded attributes
     */
    public Broker() {
    }
    
    public Broker(String[] brokerArray) {
        this.brokerArray = brokerArray;
        setBrokerLicense(brokerArray[2]);
        setFirstName(brokerArray[3]);
        setLastName(brokerArray[4]);
        setDept(brokerArray[5]);
        setCommissionRate(Double.parseDouble(brokerArray[6]));
    }
    
    /**
     * Setter used to set the value of brokerLicense
     * 
     * @param brokerLicense - passes in brokerLicense as parameter
     */
    public void setBrokerLicense(String brokerLicense) {
        this.brokerLicense = brokerLicense;
    }
    
    /**
     * Setter used to set the value of broker's first name
     * 
     * @param firstName - passes in first name of broker as parameter
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter used to set the value of broker's last name
     * 
     * @param lastName - passes in last name of broker as parameter
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Setter used to set the value of broker's department number
     * 
     * @param dept - passes in broker's department number as parameter
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * Setter used to set the value of commission rate for brokers
     * 
     * @param commissionRate - passes in commission rate as parameter
     */
    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }
    
    /**
     * Getter used to retrieve value of broker license
     * 
     * @return - returns value of broker license
     */
    public String getBrokerLicense() {
        return brokerLicense;
    }

    /**
     * Getter used to retrieve value of broker first name
     * 
     * @return - returns value of first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter used to retrieve value of broker last name
     * 
     * @return - returns value of last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter used to retrieve value of broker department number
     * 
     * @return - returns value of department number
     */
    public String getDept() {
        return dept;
    }

    /**
     * Getter used to retrieve value of broker commission rate
     * 
     * @return - returns value of commission rate
     */
    public double getCommissionRate() {
        return commissionRate;
    }

    /**
     * Method that generates hashCode for brokers based on broker licenses
     * 
     * @return - returns the hashCode
     */
    @Override
    public int hashCode() {
        String license = this.brokerLicense;
        String brokerNumber = license.substring(3);
        int hash = Integer.parseInt(brokerNumber);
        
        return hash;
    }

    /**
     * Method returning true or false depending on whether two broker objects
     * are the same
     * 
     * @return - returns true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Broker other = (Broker) obj;
        if (!Objects.equals(this.brokerLicense, other.brokerLicense)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.dept, other.dept)) {
            return false;
        }
        if (Double.doubleToLongBits(this.commissionRate) != 
                Double.doubleToLongBits(other.commissionRate)) {
            return false;
        }
        return true;
    }

    /**
     * Method returning a string containing the values given to all the broker
     * data fields
     * 
     * @return - returns string
     */
    @Override
    public String toString() {
        return "Broker{" + "brokerLicense=" + this.brokerLicense + 
                ", firstName=" + firstName + ", lastName=" + lastName +
                ", dept=" + dept + ", " + "commissionRate=" + commissionRate 
                + '}';
    }
}
