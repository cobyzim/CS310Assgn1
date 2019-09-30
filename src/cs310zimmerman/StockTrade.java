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
     *
     * @param stockSymbol - passes in stock symbol as a string parameter
     * @param pricePerShare - passes in share price as a double parameter
     * @param wholeShares - passes in number of shares as integer parameter
     * @param brokerLicense - passes in broker license as a string parameter
     * @param taxable - passes in taxability 
     * 
     * Constructor with parameters used for stock trade objects with hard-coded
     * attributes
     */
    /*
    public StockTrade(String stockSymbol, double pricePerShare, int wholeShares,
            String brokerLicense, boolean taxable) {
        setStockSymbol(stockSymbol);
        setPricePerShare(pricePerShare);
        setWholeShares(wholeShares);
        setBrokerLicense(brokerLicense);
        setTaxable(taxable);
    }
    */
    
    public StockTrade(String[] stockTradeArray) {
        this.stockTradeArray = stockTradeArray;
        setStockSymbol(stockTradeArray[2]);
        setPricePerShare(Double.parseDouble(stockTradeArray[3]));
        setWholeShares(Integer.parseInt(stockTradeArray[4]));
        setBrokerLicense(stockTradeArray[5]);
        setTaxable(Boolean.parseBoolean(stockTradeArray[6]));
    }

    /**
     *
     * @param stockSymbol - passes in stockSymbol as a parameter
     * 
     * Setter used to set the value of stockSymbol
     */
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    /**
     *
     * @param pricePerShare - passes in pricePerShare as a parameter
     * 
     * Setter used to set the value of pricePerShare
     */
    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    /**
     *
     * @param wholeShares - passes in wholeShares as a parameter
     * 
     * Setter used to set the value of wholeShares
     */
    public void setWholeShares(int wholeShares) {
        this.wholeShares = wholeShares;
    }

    /**
     *
     * @param brokerLicense - passes in brokerLicense as a parameter
     * 
     * Setter used to set the value of brokerLicense
     */
    public void setBrokerLicense(String brokerLicense) {
        this.brokerLicense = brokerLicense;
    }

    /**
     *
     * @param taxable - passes in taxable as a parameter
     * 
     * Setter used to set the value of taxable
     */
    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    /**
     *
     * @return - returns value of stockSymbol
     * 
     * Getter used to retrieve the value of stockSymbol
     */
    public String getStockSymbol() {
        return stockSymbol;
    }

    /**
     *
     * @return - returns value of pricePerShare
     * 
     * Getter used to retrieve the value of pricePerShare
     */
    public double getPricePerShare() {
        return pricePerShare;
    }

    /**
     *
     * @return - returns the value of wholeShares
     * 
     * Getter used to retrieve the value of wholeShares
     */
    public int getWholeShares() {
        return wholeShares;
    }

    /**
     *
     * @return - returns the value of brokerLicense
     * 
     * Getter used to retrieve the value of brokerLicense
     */
    public String getBrokerLicense() {
        return brokerLicense;
    }

    /**
     *
     * @return - returns value of taxable
     * 
     * Getter used to retrieve the value of taxable (true or false)
     */
    public boolean isTaxable() {
        return taxable;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     *
     * @return - returns true or false
     * 
     * Method returning true or false depending on whether two stock trade
     * objects are the same
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
     *
     * @return - returns string
     * 
     * Method returning a string containing the values given to all the stock 
     * trade data fields
     */
    @Override
    public String toString() {
        return "StockTrade{" + "stockSymbol=" + stockSymbol + ", pricePerShare="
                + pricePerShare + ", wholeShares=" + wholeShares + 
                ", brokerLicense=" + brokerLicense + ", taxable=" + 
                taxable + '}';
    }
    
    /**
     *
     * @return - returns true or false
     * 
     * Method that checks the validity of a stock symbol, returning true if it 
     * is valid and false if it is not
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
     *
     * @return - returns true or false
     * 
     * Method that checks the validity of price per share, returning true if it
     * is valid and false if it is not
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
     *
     * @return - returns true or false
     * 
     * Method that checks the validity of the number of shares, returning true
     * if it is valid and false if it is not
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
