
/**
 * Write a description of class GroceryItem here.
 * 
 * @author (Gunnar Holwerda) 
 * @version 1
 */
public class GroceryItem
{
    
    private String name;
    private double cost;
    private int aisleNum;

    /**
     * Constructor for objects of class GroceryItem
     */
    public GroceryItem(String name, double cost, int aisleNum)
    {
        this.name = name;
        this.cost = cost;
        this.aisleNum = aisleNum;
    }

    public String getName() {
        return name;
    }
    
    public double getCost() {
        return cost;
    }
    
    public int getAisle() {
        return aisleNum;
    }
    
    public void changeCost(double newCost) {
        this.cost = newCost;
    }
}
