package cs310zimmerman;


import java.util.Objects;

/*
 * Class to define data fields and methods for stock trade objects which contain
 * stock symbols, share prices, number of shares, broker licenses, and 
 * taxability.
 */

/**
 *
 * @author Coby Zimmerman
 */
public class StockTrade {
    private String stockSymbol;
    private double pricePerShare;
    private int wholeShares;
    private String brokerLicense;
    private boolean taxable;
    private String[] stockTradeArray;
    
    /**
     * Constructor with no parameters used for stock trade objects with no hard-
     * coded attributes
     */
    public StockTrade() {
        
    }
    
    /**
     * Constructor with stock trade array of strings as a parameter
     * 
     * @param stockTradeArray - passes in stock trade array of strings
     */
  
    
    public StockTrade(String[] stockTradeArray) {
        this.stockTradeArray = stockTradeArray;
        setStockSymbol(stockTradeArray[2]);
        setPricePerShare(Double.parseDouble(stockTradeArray[3]));
        setWholeShares(Integer.parseInt(stockTradeArray[4]));
        setBrokerLicense(stockTradeArray[5]);
        String taxableVal = stockTradeArray[6];
        setTaxable(taxableVal.equals("Y") ? true : false);
    }

    /**
     * Setter used to set the value of stockSymbol
     * 
     * @param stockSymbol - passes in stockSymbol as a parameter
     */
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    /**
     * Setter used to set the value of pricePerShare
     * 
     * @param pricePerShare - passes in pricePerShare as a parameter
     */
    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    /**
     * Setter used to set the value of wholeShares
     * 
     * @param wholeShares - passes in wholeShares as a parameter
     */
    public void setWholeShares(int wholeShares) {
        this.wholeShares = wholeShares;
    }

    /**
     * Setter used to set the value of brokerLicense
     * 
     * @param brokerLicense - passes in brokerLicense as a parameter
     */
    public void setBrokerLicense(String brokerLicense) {
        this.brokerLicense = brokerLicense;
    }

    /**
     * Setter used to set the value of taxable
     * 
     * @param taxable - passes in taxable as a parameter
     */
    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    /**
     * Getter used to retrieve the value of stockSymbol
     * 
     * @return - returns value of stockSymbol
     */
    public String getStockSymbol() {
        return stockSymbol;
    }

    /**
     * Getter used to retrieve the value of pricePerShare
     * 
     * @return - returns value of pricePerShare
     */
    public double getPricePerShare() {
        return pricePerShare;
    }

    /**
     * Getter used to retrieve the value of wholeShares
     * 
     * @return - returns the value of wholeShares
     */
    public int getWholeShares() {
        return wholeShares;
    }

    /**
     * Getter used to retrieve the value of brokerLicense
     * 
     * @return - returns the value of brokerLicense
     */
    public String getBrokerLicense() {
        return brokerLicense;
    }

    /**
     * Getter used to retrieve the value of taxable (true or false)
     * 
     * @return - returns value of taxable
     */
    public boolean isTaxable() {
        return taxable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < stockSymbol.length(); i++) {
            char c = stockSymbol.charAt(i);
            int unicodeVal = (int) c;
            //int unicodeVal = Character.getNumericValue(c);
            hash = hash + unicodeVal;
        }
        
    return hash;
    }

    /**
     * Method returning true or false depending on whether two stock trade
     * objects are the same
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
        final StockTrade other = (StockTrade) obj;
        if (!Objects.equals(this.stockSymbol, other.stockSymbol)) {
            return false;
        }
        if (Double.doubleToLongBits(this.pricePerShare) != 
                Double.doubleToLongBits(other.pricePerShare)) {
            return false;
        }
        if (this.wholeShares != other.wholeShares) {
            return false;
        }
        if (!Objects.equals(this.brokerLicense, other.brokerLicense)) {
            return false;
        }
        if (this.taxable != other.taxable) {
            return false;
        }
        return true;
    }

    /**
     * Method returning a string containing the values given to all the stock 
     * trade data fields
     * 
     * @return - returns string
     */
    @Override
    public String toString() {
        return "StockTrade{" + "stockSymbol=" + stockSymbol + ", pricePerShare="
                + pricePerShare + ", wholeShares=" + wholeShares + 
                ", brokerLicense=" + brokerLicense + ", taxable=" + 
                taxable + '}';
    }
    
    /**
     * Method that checks the validity of a stock symbol, returning true if it 
     * is valid and false if it is not
     * 
     * @return - returns true or false
     */
    public boolean isValidStockSymbol() {
        boolean stockSymbolValidity = true;
        if (stockSymbol.length() == 3 || stockSymbol.length() == 4) {
            for (int i = 0; i < stockSymbol.length(); i++) {
                if (!Character.isLetter(stockSymbol.charAt(i)) || 
                        Character.isLowerCase(stockSymbol.charAt(i))) {
                    return false;
                }
            }
        }
        else {
            return false;
        }
    return stockSymbolValidity;
    }
    
    /**
     * Method that checks the validity of price per share, returning true if it
     * is valid and false if it is not
     * 
     * @return - returns true or false
     */
    public boolean isValidPrice() {
        boolean priceValidity = true;
        double maxPricePerShare = 1000.00;
        if (pricePerShare > maxPricePerShare) {
            return false;
        }
        
    return priceValidity;    
    }
    
    /**
     * Method that checks the validity of the number of shares, returning true
     * if it is valid and false if it is not
     * 
     * @return - returns true or false
     */
    public boolean isValidWholeShares() {
        boolean shareValidity = true;
        int maxShares = 100000;
        if (wholeShares > maxShares) {
            return false;
        }
        
    return shareValidity;
    }
     
}
