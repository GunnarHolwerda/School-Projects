
/**
 * Write a description of class Book here.
 * 
 * @author Gunnar Holwerda 
 * @version (a version number or a date)
 */
public class Book
{
    // instance variables - replace the example below with your own
    private String title;
    private double cost;
    private int num_in_stock;

    /**
     * Constructor for objects of class Book
     */
    public Book(String title, double cost, int num_in_stock)
    {
        this.title = title;
        this.cost = cost;
        this.num_in_stock = num_in_stock;
    }

    public String getTitle() {
        return title;
    }
    
    public double getCost() {
        return cost;
    }
    
    public int getNumInStock() {
        return num_in_stock;
    }
    
    public double getValueOfStock() {
        return cost * num_in_stock;
    }
}
