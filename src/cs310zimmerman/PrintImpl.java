/*
 * Implementation Class containing a printReport method to generate a report
 * that shows the broker objects that have stocktrades. It also shows the number
 * of stock trade listings for each broker, the total value of the stock trade
 * listings for each broker, and both of the latter for all of the brokers.
 * Moreover, it includes the method that prints the go-kart report which
 * includes which brokers are using which karts, which karts are still 
 * available, and which brokers are in the queue.
 */
package cs310zimmerman;

import java.util.LinkedList;
import java.util.Iterator;
import java.io.*;
/**
 *
 * @author Coby Zimmerman
 */

public class PrintImpl {
    private BrokerNode top;
    private LinkedList<StockTrade> stockTradeList;
    private int numStockTrades;
    private String[] goKartUsage;
    
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
     */
    public void printReport(BrokerLogImpl brokerLogImpl, 
            StockTradeLogImpl stockTradeLogImpl, String fileName) throws 
            IOException {
        final String OUTPUT_FILENAME = fileName;
        top = brokerLogImpl.getTop();
        stockTradeList = stockTradeLogImpl.getStockTradeList();
        numStockTrades = stockTradeLogImpl.getNumStockTrades();

        String brokerLicense;
        String firstName;
        String lastName;
        String dept;
        double commissionRate;
        String tradeLicense;
        String stockSymbol;
        double pricePerShare;
        int wholeShares;
        String taxable;
        int numListings;
        double totalListValue;
        
        FileWriter fileWriter = new FileWriter(OUTPUT_FILENAME);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        BrokerNode current = top;
        while (current != null) {
            brokerLicense = current.getData().getBrokerLicense();
            firstName = current.getData().getFirstName();
            lastName = current.getData().getLastName();
            dept = current.getData().getDept();
            commissionRate = current.getData().getCommissionRate();
            
            if (stockTradeLogImpl.numberOfBrokerStockTrades(brokerLicense) >= 0)
            {
              
                printWriter.printf("\n");
                printWriter.printf("%s  %s,  %s\n", brokerLicense, 
                    lastName, firstName);
            }

            Iterator<StockTrade> iter = stockTradeList.iterator();
            while (iter.hasNext()) {
                StockTrade currentNode = iter.next();
                tradeLicense = currentNode.getBrokerLicense();
                stockSymbol = currentNode.getStockSymbol();
                pricePerShare = currentNode.getPricePerShare();
                wholeShares = currentNode.getWholeShares();
                if (currentNode.isTaxable()) {
                    taxable = "YES";
                }
                else {
                    taxable = "NO";
                }
                numListings = numStockTrades;
                totalListValue = 
                        stockTradeLogImpl.totalStockTradeValue(brokerLicense);
                
                if (brokerLicense.equals(tradeLicense)) {

                    printWriter.printf("\n\t%s \t%.2f \t%d   %s\n", stockSymbol, 
                            pricePerShare, wholeShares, taxable);
                }
            }
            
            if (stockTradeLogImpl.numberOfBrokerStockTrades(brokerLicense) > 0) 
            {
            printWriter.printf("\n\tNumber of StockTrade Listings for "
                + "Broker: %d", stockTradeLogImpl.
                numberOfBrokerStockTrades(brokerLicense));
                                                                                
            printWriter.printf("\n\tTotal sales value of "
                + "StockTradeListings" + " for Broker %s: $ %.2f\n",
                brokerLicense, stockTradeLogImpl.
                totalStockTradeValue(brokerLicense));
            }
            else {
                printWriter.printf("\n\tNumber of StockTradeListings for Broker"
                        + " : 0");
                printWriter.printf("\n\tTotal sales value of "
                        + "StockTradeListings for Broker: $0.00\n");
            }
            current = current.getNext();
        }
        printWriter.printf("\nTotal Number of StockTrade Listings for ALL "
                + "Brokers = %d\n", stockTradeLogImpl.getNumStockTrades());
        
        printWriter.printf("Total sales value of StockTrade Listings for ALL "
                + "Brokers = $ %.2f", stockTradeLogImpl.totalStockTradeValue());
        
        fileWriter.close();
        printWriter.close();
    }
    
    public void printGoKartReport(BrokerLogImpl brokerLogImpl, 
            GoKartUsageImpl goKartUsageImpl, String fileName) throws IOException
    {
        
        final String OUTPUT_FILENAME = fileName;
        FileWriter fileWriter = new FileWriter(OUTPUT_FILENAME);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        printWriter.printf("GO-KART USAGE REPORT\n");
        
        goKartUsage = goKartUsageImpl.getGoKartUsageArray();
        
        
        for (int i = 0; i < goKartUsage.length; i++) {
            if (!goKartUsageImpl.getBrokerLicenseForGoKart(i).equals("")) {
                String brokerLicense = goKartUsageImpl.
                    getBrokerLicenseForGoKart(i);
                String brokerFirstName = brokerLogImpl.findBroker(brokerLicense).
                    getData().getFirstName();
                String brokerLastName = brokerLogImpl.findBroker(brokerLicense).
                    getData().getLastName();
                printWriter.printf("%s %s is using go-kart number %d\n", 
                    brokerFirstName, brokerLastName, i + 1);
            }
        }
        
        printWriter.printf("\nAVAILABLE GO-KARTS\n");
        printWriter.printf("\tBASIC GO-KARTS\n");
        
        //GoKartStackImpl goKartStackImpl, 
            //BrokerQueueImpl brokerQueueImpl,
        //include which broker is using which cart
        //which go karts are available in each stack
        //which brokers are still in the queues
        
        fileWriter.close();
        printWriter.close();
    }
}
