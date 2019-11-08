/*
 * Program to manage information regarding brokers and their stock trades.
 * Data is read from particular files containing information on brokers and
 * stocks, and the program determines whether this info is valid or not, while 
 * also adding or deleting Broker/Stock Trade objects to lists based on the 
 * broker license and stock symbol uniqueness. The broker list contains broker 
 * objects in an ordered linked list while the stock trade list contains stock
 * trade objects in an unordered linked list. The program also handles the
 * broker go-kart event. It places seven go-karts into two different stacks (one
 * for top brokers who are brokers with $5000000 or more in their portfolio and
 * one for standard brokers who have less than that). Top brokers have access to
 * racing karts while standard brokers do not. If all the karts are being used,
 * then the broker requesting a kart is placed into one of the two queues (one
 * for top brokers and the other for standard brokers). As soon as a kart is
 * available, the program assigns this kart to the brokers in the top queue
 * first, and then the bottom queue.
 */
package cs310zimmerman;

import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

/**
 *
 * @author Coby Zimmerman
 * @version 1.0
 */
public class CS310Zimmerman {

    static BrokerLogImpl brokerLogImpl = new BrokerLogImpl();
    static StockTradeLogImpl stockTradeLogImpl = new StockTradeLogImpl();
    static PrintImpl printImpl = new PrintImpl();
    static BrokerQueueImpl brokerQueueImplStandard = new BrokerQueueImpl();
    static BrokerQueueImpl brokerQueueImplTop = new BrokerQueueImpl();
    static GoKartUsageImpl goKartUsageImpl = new GoKartUsageImpl(7);
    static GoKartStackImpl goKartStackImplBasic = new GoKartStackImpl(4, 4);
    static GoKartStackImpl goKartStackImplRacing = new GoKartStackImpl(3, 7);

    /**
     * Main method that calls the processFile method, the printReport method, 
     * and the processGoKartInfo method
     *
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException - signals that file denoted by
     * specific pathname has failed to open
     */
    public static void main(String[] args) throws FileNotFoundException {

        processFile();
        System.out.println();
        brokerLogImpl.traverse();
        stockTradeLogImpl.traverseDisplay();
        System.out.println();
        
        System.out.println("Cleaning up broker and stockTrade logs...");
        brokerLogImpl.cleanList(stockTradeLogImpl);
        stockTradeLogImpl.cleanList();
        System.out.println();
        System.out.println("Creating clean report...");
        System.out.println();
        System.out.println("Creating report...");
        System.out.println("Report is complete -- located in file: "
                + "output/assn3cleanReport.txt");
        
        createReport();
        
        System.out.println();
        processGoKartInfo();
        
        
        try {
            printImpl.printGoKartReport(brokerLogImpl, goKartUsageImpl, 
                "gokartUsageReport.txt");
        }
        catch (IOException exc) {
            System.out.println("I/O exception occurred w/ go-kart file");
        }
        
        
    }

    /**
     * Method used to read from the input file(s) and parse through each line
     * Also calls addBroker, deleteBroker, addStockTrade, and deleteStockTrade
     * based on the first two elements in a file line.
     *
     * @throws FileNotFoundException - signals that file denoted by
     * specific pathname has failed to open
     */
    public static void processFile() throws FileNotFoundException {
        final String INPUT_FILENAME = "input/assn4input7.txt";

        try {
            File text = new File(INPUT_FILENAME);
            Scanner scnr = new Scanner(text);

            while (scnr.hasNextLine()) {
                String str = scnr.nextLine();
                String[] arrOfStr = str.split(",", 7);
                if (arrOfStr[0].equals("BROKER")) {
                    if (arrOfStr[1].equals("ADD")) {
                        addBroker(arrOfStr);

                    }
                    if (arrOfStr[1].equals("DEL")) {
                        deleteBroker(arrOfStr);
                    }
                }
                if (arrOfStr[0].equals("TRADE")) {
                    if (arrOfStr[1].equals("BUY")) {
                        addStockTrade(arrOfStr);
                    }
                    if (arrOfStr[1].equals("SELL")) {
                        deleteStockTrade(arrOfStr);
                    }
                }

            }
        } catch (IOException error) {
            System.out.println("Could not find file: " + INPUT_FILENAME);
            System.exit(1);
        }
    }

    /**
     * Method used to add a new broker object to the list based on uniqueness
     * of the broker license. Also checks if the broker object has a valid
     * license and a valid department number, printing an error message if
     * either are invalid. Will still add the object to the list regardless.
     *
     * @param line - passes in array of strings from input file
     */
    public static void addBroker(String[] line) {
        Broker filledBroker = new Broker(line);
        boolean hasBrokerErrors = false;
       
        if (!filledBroker.isValidLicense()) {
            System.out.println("\tERROR: Broker with license "
                    + filledBroker.getBrokerLicense() + " has invalid license");
            hasBrokerErrors = true;
        }
        if (!filledBroker.isValidDept()) {
            System.out.println("\tERROR: Broker with license "
                    + filledBroker.getBrokerLicense() + " has invalid "
                    + "department " + filledBroker.getDept());
            hasBrokerErrors = true;
        }
        
        if (!brokerLogImpl.isLicenseUnique(line[2])) {
            System.out.println("\tERROR: Broker with license " + line[2]
                    + " is not unique and will NOT be added to the log.");
        }
        if (brokerLogImpl.isLicenseUnique(line[2])) {
            if (brokerLogImpl.addBroker(filledBroker)) {
                 if (hasBrokerErrors) {
                   System.out.println("ADDED: Broker with license " + line[2]
                 + ", regardless of data errors.");
                 }
                 else {
                   System.out.println("ADDED: Broker with license " + line[2]);
                 }
            }
        }  
    }

    /**
     * Method used to add a new stock trade object to the list of stock trade
     * objects based on the broker license not being unique and the stock symbol
     * being unique. Also checks if the stock trade object has a valid stock
     * symbol, a valid price per share, and a valid number of shares. If these
     * are not valid, it will still add the object to the list.
     *
     * @param line - passes in array of strings from input file
     */
    public static void addStockTrade(String[] line) {
        StockTrade filledStockTrade = new StockTrade(line);
        boolean hasStockErrors = false;

        if (!filledStockTrade.isValidStockSymbol()) {
            System.out.println(filledStockTrade.toString());
            System.out.println("\tERROR: StockTrade with Stock symbol "
                    + filledStockTrade.getStockSymbol() + " has an invalid "
                    + "Stock symbol.");
            hasStockErrors = true;
        }
        if (!filledStockTrade.isValidPrice()) {
            System.out.println(filledStockTrade.toString());
            System.out.println("\tERROR: StockTrade with Stock symbol "
                    + filledStockTrade.getStockSymbol() + " has invalid price "
                    + filledStockTrade.getPricePerShare());
            hasStockErrors = true;
        }
        if (!filledStockTrade.isValidWholeShares()) {
            System.out.println(filledStockTrade.toString());
            System.out.println("\tERROR: StockTrade with Stock symbol "
                    + filledStockTrade.getStockSymbol() + " has invalid "
                    + "number of shares " + filledStockTrade.getWholeShares());
            hasStockErrors = true;
        }

        String license = line[5];
        String stockSymbol = line[2];
        
        boolean brokerIsUnique = brokerLogImpl.isLicenseUnique(license);
        boolean stockSymbolIsUnique = 
            stockTradeLogImpl.isStockSymbolUnique(stockSymbol);
        if (!brokerIsUnique && stockSymbolIsUnique) {
        
            stockTradeLogImpl.addStockTrade(filledStockTrade);
            
            if (hasStockErrors) {
                System.out.println("ADDED: StockTrade with Stock symbol " + 
                        stockSymbol + " listed by Broker " + line[5] + ", "
                        + "regardless of data errors.");
            }
            else {
                System.out.println("ADDED: StockTrade with Stock symbol "
                    + stockSymbol + " listed by Broker " + license);
            }
            
        } 
        else if (brokerIsUnique && stockSymbolIsUnique) {
            System.out.println("ADD ERROR: StockTrade with Stock Symbol "
                    + stockSymbol + " has Broker with license " + license
                    + ", but there is no such Broker license in the Broker"
                    + " log. StockTrade " + stockSymbol + " will NOT be added "
                    + "to StockTrade log.");
        } 
        else {
            System.out.println("ADD ERROR: StockTrade has Stock Symbol "
                    + "that already exists: " + stockSymbol + "It will NOT "
                    + "be added to StockTrade log.");
        }
        
    }

    /**
     * Method used to delete a broker from the list of brokers based on if the
     * broker license number is not unique. Will print whether an object has
     * been deleted or not.
     *
     * @param line - passes in array of strings from input file
     */
    public static void deleteBroker(String[] line) {
        String license = line[2];
        
        if (!brokerLogImpl.isLicenseUnique(license)) {
            brokerLogImpl.removeBroker(license);
            System.out.println("DELETED: Broker with license: " + license
                    + " has been removed from the Broker log. All Broker's "
                    + "stocks will also be removed from the StockTrade log.");
            stockTradeLogImpl.removeStockTradeByBroker(license);

        } 
        else {
           System.out.println("\tERROR: Broker with license " + license
                   + " not found in log.");
        } 
    }

    /**
     * Method used to delete a stock trade object from the list of stock trades
     * based on if the stock symbol is not unique. Will print whether an object
     * has been deleted or not.
     *
     * @param line - passes in array of strings from input file.
     */
    public static void deleteStockTrade(String[] line) {
        String stockSymbol = line[2];

        
        if (!stockTradeLogImpl.isStockSymbolUnique(stockSymbol)) {
            stockTradeLogImpl.removeStockTrade(stockSymbol);
            
            System.out.println("DELETED: StockTrade with Stock symbol "
                    + stockSymbol);
        } 
        else {
            System.out.println("DEL ERROR: StockTrade with Stock symbol "
                    + stockSymbol + " is not in the StockTrade log, so it "
                   + "cannot be deleted.");
        }
        
    }
    
    /**
     * Method responsible for generating the report using the printImpl class
     */
    public static void createReport() {
        try {
            printImpl.printReport(brokerLogImpl, stockTradeLogImpl, 
                    "output/assn3cleanReport.txt");
        } catch (IOException ex) {
            System.out.println("I/O exception occurred");
        }
    }

    /**
     * Method that reads through the go-kart input file and parses through each
     * line. Calls processGoKartRequest and processGoKartReturn methods based on
     * the first element in each line.
     * 
     * @throws FileNotFoundException - signals that file denoted by
     * specific pathname has failed to open
     */
    public static void processGoKartInfo() throws FileNotFoundException {
        final String INPUT_FILENAME = "input/gokartInfo3.txt";

        try {
            File text = new File(INPUT_FILENAME);
            Scanner scnr = new Scanner(text);

            while (scnr.hasNextLine()) {
                String str = scnr.nextLine();
                String[] arrOfStr = str.split(" ", 2);
                if (arrOfStr[0].equals("REQUEST")) {
                    processGoKartRequest(arrOfStr);
                }
                if (arrOfStr[0].equals("RETURN")) {
                    processGoKartReturn(arrOfStr);
                }
            }
        }
        catch (IOException error) {
            System.out.println("Could not find file: " + INPUT_FILENAME);
            System.exit(1);
        }
    }
    
    /**
     * Method used to handle broker requests for go-karts. It determines who
     * gets what kart and in what order based on the value of their portfolios
     * and which karts are already being used. If all the karts are being used,
     * brokers are placed into queues based on their total stock values.
     * 
     * @param line - passes in array of strings
     */
    public static void processGoKartRequest(String[] line) {
        final double TOP_BROKER = 5000000.00;
        String basic = "basic";
        String racing = "racing";
        String brokerLicense = line[1];
        
        if (brokerLogImpl.findBroker(brokerLicense) != null) {
            double brokerValue = 
                    stockTradeLogImpl.totalStockTradeValue(brokerLicense);
            if (brokerValue >= TOP_BROKER) {
                if (!goKartStackImplRacing.isEmpty()) {
                    int racingGoKartNum = goKartStackImplRacing.pop();
                    System.out.print("Top broker ");
                    goKartUsageImpl.assignGoKartToBroker(racingGoKartNum, 
                        brokerLicense, brokerLogImpl.findBroker(brokerLicense).
                        getData().getFirstName(), racing);
                }
                else {
                    if (!goKartStackImplBasic.isEmpty()) {
                        int basicGoKartNum = goKartStackImplBasic.pop();
                        System.out.print("Top broker ");
                        goKartUsageImpl.assignGoKartToBroker(basicGoKartNum, 
                            brokerLicense, brokerLogImpl.
                            findBroker(brokerLicense).getData().getFirstName(), 
                            basic);
                    }
                    else {
                        brokerQueueImplTop.add(brokerLogImpl.
                            findBroker(brokerLicense).getData());
                        String firstName = brokerLogImpl.
                            findBroker(brokerLicense).getData().getFirstName();
                        System.out.printf("%s waiting in top broker queue\n", 
                            firstName);
                    }
                }
            }
            else {
                if (!goKartStackImplBasic.isEmpty()) {
                    int basicGoKartNumOne = goKartStackImplBasic.pop();
                    System.out.print("Standard broker ");
                    goKartUsageImpl.assignGoKartToBroker(basicGoKartNumOne, 
                        brokerLicense, brokerLogImpl.findBroker(brokerLicense).
                        getData().getFirstName(), basic);
                }
                else {
                    brokerQueueImplStandard.add(brokerLogImpl.
                        findBroker(brokerLicense).getData());
                    String firstNameTwo = brokerLogImpl.
                        findBroker(brokerLicense).getData().getFirstName();
                        System.out.printf("%s waiting in standard broker "
                            + "queue\n", firstNameTwo);
                }
            }
        }
        else {
            System.out.printf("Unknown broker %s is not allowed access to "
                    + "go-karts. Request ignored.\n", brokerLicense);
        }
    }
    
    /**
     * Method responsible for handling Brokers that are returning a go-kart. 
     * After a broker returns the kart, it checks the queues and assigns the 
     * returned kart to the first broker in the correct queue.
     * 
     * @param line - passes in array of strings
     */
    public static void processGoKartReturn(String[] line) {
        String brokerLicense = line[1];
        final double TOP_BROKER = 5000000.00;
        String basic = "basic";
        String racing = "racing";
        
        if (brokerLogImpl.findBroker(brokerLicense) != null) {
            String brokerName = brokerLogImpl.findBroker(brokerLicense).
                getData().getFirstName();
            double brokerValue = stockTradeLogImpl.
                totalStockTradeValue(brokerLicense);
            if (brokerValue >= TOP_BROKER) {
                    
                    //if(!goKartStackImplRacing.isFull()) { //eddie goes into here but never goes into the else. Can't push the kart into racing stack so its never returned
                    int kartNum = goKartUsageImpl.returnGoKart(brokerLicense, brokerName);
                        
                    if (kartNum != -1) {
                        if (kartNum > goKartStackImplRacing.getStackSize() && kartNum <= goKartStackImplRacing.getStartGoKartNumber(7)) {
                        //if (kartNum > 3 && kartNum <= 6) { //try and find a way to fix this
                            goKartStackImplRacing.push(kartNum);
                            if (!brokerQueueImplTop.isEmpty()) {
                                Broker topBroker = brokerQueueImplTop.remove();
                                int racingGoKartNum = goKartStackImplRacing.
                                    pop();
                                System.out.print("Top broker ");
                                goKartUsageImpl.
                                    assignGoKartToBroker(racingGoKartNum, 
                                    topBroker.getBrokerLicense(),
                                    topBroker.getFirstName(), racing);
                            }
                        }
                        else {
                            goKartStackImplBasic.push(kartNum);
                            if (!brokerQueueImplStandard.isEmpty()) {
                                Broker standardBroker = brokerQueueImplStandard.
                                remove();
                                int basicGoKartNum = goKartStackImplBasic.pop();
                                System.out.print("Standard broker ");
                                goKartUsageImpl.
                                    assignGoKartToBroker(basicGoKartNum, 
                                    standardBroker.getBrokerLicense(), 
                                    standardBroker.getFirstName(), basic);
                            }
                        }
                            
                    }
                    else {
                        System.out.println("Broker is not assigned to "
                            + "go-kart");
                    }
                    //}
                    //else { //racing stack is full
                        /*
                        int kartNum = goKartUsageImpl.returnGoKart(brokerLicense, brokerName);
                        
                        if (kartNum != -1) {
                            goKartStackImplBasic.push(kartNum);
                            if (!brokerQueueImplStandard.isEmpty()) {
                                Broker standardBroker = brokerQueueImplStandard.
                                    remove();
                                int basicGoKartNum = goKartStackImplBasic.pop();
                                System.out.print("Standard broker ");
                                goKartUsageImpl.
                                    assignGoKartToBroker(basicGoKartNum, 
                                    standardBroker.getBrokerLicense(), 
                                    standardBroker.getFirstName(), basic);
                            }
                        }
                        else {
                            System.out.println("Broker is not assigned to"
                                + " go-kart");
                        }
                    //}
                        */
                }
                else {
                    int kartNum = goKartUsageImpl.returnGoKart(brokerLicense, brokerName);
                        
                    if(kartNum != -1) {
                        goKartStackImplBasic.push(kartNum);
                        if (!brokerQueueImplStandard.isEmpty()) {
                            Broker standardBroker = brokerQueueImplStandard.
                                remove();
                            int basicGoKartNum = goKartStackImplBasic.pop();
                            System.out.print("Standard broker ");
                            goKartUsageImpl.
                                assignGoKartToBroker(basicGoKartNum, 
                                standardBroker.getBrokerLicense(), 
                                standardBroker.getFirstName(), basic);
                        }
                    }
                    else {
                        System.out.println("Broker is not assigned to "
                            + "go-kart");
                    }
            }
        }
        else {
            System.out.printf("Unknown broker %s is not allowed access to go"
                    + "-karts and cannot return them. Return ignored\n", 
                    brokerLicense);
        }
    }
}
