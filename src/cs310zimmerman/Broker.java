package cs310zimmerman;


import java.util.Objects;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cobyz
 */
public class Broker {
    private String brokerLicense;
    private String firstName;
    private String lastName;
    private String dept;
    private double commissionRate;

    public Broker() {
    }
    
    //Full constructor
    public Broker(String brokerLicense, String firstName, String lastName, 
            String dept, double commissionRate) {
        setBrokerLicense(brokerLicense);
        setFirstName(firstName);
        setLastName(lastName);
        setDept(dept);
        setCommissionRate(commissionRate);
    }

    public void setBrokerLicense(String brokerLicense) {
        this.brokerLicense = brokerLicense;
        System.out.println(this.brokerLicense);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }
    
    
    public String getBrokerLicense() {
        return brokerLicense;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDept() {
        return dept;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

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

    @Override
    public String toString() {
        return "Broker{" + "brokerLicense=" + this.brokerLicense + 
                ", firstName=" + firstName + ", lastName=" + lastName +
                ", dept=" + dept + ", " + "commissionRate=" + commissionRate 
                + '}';
    }
    
    Scanner scnr = new Scanner(System.in);
    
    
    public boolean isValidLicense() {
        boolean licenseValidity = true;
        if (brokerLicense.length() == 8) {
            if (Character.isLetter(brokerLicense.charAt(0)) && 
                    Character.isLetter(brokerLicense.charAt(1)) && 
                    Character.isLetter(brokerLicense.charAt(2))) {
                for (int i = 3; i < 8; i++) {
                    if (!Character.isDigit(brokerLicense.charAt(i))) {
                        return false;
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
    return licenseValidity;      
    }
    
    public boolean isValidDept() {
        boolean deptValidity = true;
        //char[] digitArray = new char[] {'1', '2', '3'};
        String digit = "123";
        if (dept.length() == 8) {
            for (int i = 0; i < 3; i++) {
                if (digit.indexOf(dept.charAt(i)) == -1) {
                    return false;
                }
            }
            if (dept.charAt(3) != '-') {
                return false;
            }
            for (int j = 4; j < 8; j++) {
                if (!Character.isDigit(dept.charAt(j))) {
                    return false;
                }
            }
        }
    return deptValidity;
    }
    
    //add an if statement measuring length before doing loop to check each index
    /* if( length is 8
        loop (through characters (charAt))
        if (0-2)
        else(everything else)
    
       else()
     return true or false
    */
    
    //put input in a new file under file tab
    //csv - comma separated values
    //use while loop with hasNextLine, and then use .split to grab wanted info
    //then us indexes in print statements to pick which ones we want
}
