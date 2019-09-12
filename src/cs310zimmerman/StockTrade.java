package cs310zimmerman;


import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cobyz
 */
public class StockTrade {
    private String stockSymbol;
    private double pricePerShare;
    private int wholeShares;
    private String brokerLicense;
    private boolean taxable;
    
    public StockTrade() {
        
    }
    
    public StockTrade(String stockSymbol, double pricePerShare, int wholeShares,
            String brokerLicense, boolean taxable) {
        
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    public void setWholeShares(int wholeShares) {
        this.wholeShares = wholeShares;
    }

    public void setBrokerLicense(String brokerLicense) {
        this.brokerLicense = brokerLicense;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public int getWholeShares() {
        return wholeShares;
    }

    public String getBrokerLicense() {
        return brokerLicense;
    }

    public boolean isTaxable() {
        return taxable;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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

    @Override
    public String toString() {
        return "StockTrade{" + "stockSymbol=" + stockSymbol + ", pricePerShare="
                + pricePerShare + ", wholeShares=" + wholeShares + 
                ", brokerLicense=" + brokerLicense + ", taxable=" + 
                taxable + '}';
    }
    
    
    
    
    
    
    
    
    
}
