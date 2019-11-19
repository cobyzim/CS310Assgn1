/*
 * Implementation Class containing a printReport method to generate a report
 * that shows the stockTrades associated with brokers and whether or not these
 * stockTrades are taxable.
 */
package cs310zimmerman;

import java.util.LinkedList;
import java.util.Iterator;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author Coby Zimmerman
 */

public class PrintImpl {
    
    /**
     * Method used to generate the report using the broker and stock trade
     * implementation objects.
     * 
     * @param brokerLogImpl - passes in the broker implementation object
     * @param stockTradeLogImpl - passes in stock trade implementation object
     * @param fileName - passes in the file name where the report will be 
     * generated
     * @throws IOException - throws exception if I/O operations fail or are
     * interrupted.
     * @throws FileNotFoundException - signals that file denoted by
     * specific pathname has failed to open
     */
    public void printReport(BrokerLogImpl brokerLogImpl, 
            StockTradeLogImpl stockTradeLogImpl, String fileName) throws 
            IOException, FileNotFoundException {
        final String OUTPUT_FILENAME = fileName;
        final String INPUT_FILENAME = "input/BrokerRequests.txt";
        
        File text = new File(INPUT_FILENAME);
        Scanner scnr = new Scanner(text);
        
        FileWriter fileWriter = new FileWriter(OUTPUT_FILENAME);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        while (scnr.hasNext()) {
            String str = scnr.nextLine();
            String[] arrOfStr = str.split(" ");
            String brokerLicense = arrOfStr[0];
            
            if (brokerLogImpl.findBroker(brokerLicense) != null) {
                String brokerFirstName = brokerLogImpl.findBroker(brokerLicense)
                    .getFirstName();
                String brokerLastName = brokerLogImpl.findBroker(brokerLicense).
                    getLastName();
                
                printWriter.printf("Broker %s, %s %s\n", brokerLicense, 
                    brokerFirstName, brokerLastName);
                
                for (int i = 1; i < arrOfStr.length; i++) {
                    
                    if (stockTradeLogImpl.findStockTrade(arrOfStr[i]) != null) {
                        printWriter.printf("\tStockTrade %s is ", arrOfStr[i]);
                        
                        if (stockTradeLogImpl.findStockTrade(arrOfStr[i]).
                            getData().isTaxable()) {
                            printWriter.printf("TAXABLE\n");
                        }
                        else {
                            printWriter.printf("NOT TAXABLE\n");
                        }
                    }
                    else {
                        printWriter.printf("\tStockTrade %s does not exist\n", 
                            arrOfStr[i]);
                    }
                }
            }
            else {
                printWriter.printf("Broker %s does not exist", brokerLicense);
            }
        }
        fileWriter.close();
        printWriter.close();
       
    }
}