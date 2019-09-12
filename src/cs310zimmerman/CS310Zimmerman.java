/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs310zimmerman;


import java.util.Scanner;
/**
 *
 * @author cobyz
 */
public class CS310Zimmerman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //testing license validity
        Broker broker = new Broker("abc12345", "Coby", "Zimmerman", "123-5555", 80.0);
        Broker brokerTwo = new Broker("123abcde", "Tate", "Zimmerman", "146-1111", 90.0);
        Broker brokerThree = new Broker("abc43cd4", "Cayden", "Zim", "dept3", 100.0);
        
        System.out.println(broker.toString());
        System.out.println(brokerTwo.toString());
        
        /*if (brokerThree.isValidLicense()) {
            System.out.println("License is Valid");
        }
        else {
            System.out.println("License is not Valid");
        }
        */
        
        //testing dept validity
        if (broker.isValidDept()) {
            System.out.println("Dept is Valid");
        }
        else {
            System.out.println("Dept is invalid");
        }
    }
    
}
