/**
 * GroceryItem Inlab.
 */
public class GroceryItem
{
    private String name;        //item name
    private double cost;        //cost of 1 unit of item
    private int count;

    // constructor for GroceryItems
    public GroceryItem(String inName, double inCost, int count)
    {
        name = inName;
        cost = inCost;
        this.count = count;
    }

    // returns the item's name
    public String getName()
    {
        return name;
    }

    // returns the cost of 1 item
    public double getCost()
    {
        return cost;
    }

    public void printDetails() {
        System.out.println("There are " + count + " units of " + name + " in stock.");
    }

    public void reduceCount(int purchasedAmount) {
        count -= purchasedAmount;
    }

}