
/**
 * Write a description of class Stock here.
 * 
 * @author Gunnar Holwerda
 * @version 9/9/12
 */
public class Stock
{
    private String name;    //name of the stock
    private int shares;     //number of shares of the stock
    private double price;   //price of the stock
    
    /**
     * Constructor for objects of class Stock
     * 
     * @param   inName      the stock's name, e.g. "MSFT"
     * @param   inShares    the number of the shares of the stock, e.g. 100
     * @param   inPrice     the price of the stock e.g. 28.11
     */
    public Stock(String inName, int inShares, double inPrice)
    {
        //initializes instance variables
        name = inName;
        shares = inShares;
        price = inPrice;
    }
    
    /**
     * getName
     * 
     * @return      the name of the stock
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * getShares
     * 
     * @return      the number of shares of the stock
     */
    public int getShares()
    {
        return shares;
    }
    
    /**
     * getPrice
     * 
     * @return      the price of the stock
     */
    public double getPrice()
    {
        return price;
    }
    
    /**
     * getValue
     * 
     * @return      the price of the stock multplied by the number of shares of the stock
     */
    public double getValue()
    {
        return shares * price;
    }
    
    public void printStatistics()
    {
        System.out.println("Stock Name\tPrice\tShares\tValue");
        System.out.println(getName() + "\t" + getPrice() + "\t" + getShares() + "\t" + getValue());
    }
}
