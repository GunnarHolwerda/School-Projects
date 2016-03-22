/**
 * Created by Gunnar on 2/2/2016.
 */
public class Cart {

    private String user;
    private GroceryItem item_one, item_two;
    private int item1_count, item2_count;

    public Cart(String user) {
        this.user = user;
    }

    public void addItem1(GroceryItem item, int item_count) {
        item_one = item;
        item1_count = item_count;
        item_one.reduceCount(item_count);
    }

    public void addItem2(GroceryItem item, int item_count) {
        item_two = item;
        item2_count = item_count;
        item_two.reduceCount(item_count);
    }

    private float getItem1Total() {
        return (float) (item_one.getCost() * item1_count);
    }

    private float getItem2Total() {
        return (float) (item_two.getCost() * item2_count);
    }

    private float getCartTotal() {
        return getItem1Total() + getItem2Total();
    }

    private double getSalesTax() {
        return 0.07 * getCartTotal();
    }

    public void printReceipt() {
        System.out.printf("Shopper name: %s\n", user);
        System.out.println("----------------------");
        System.out.printf("%s: %d units at $%f per unit = $%f\n", item_one.getName(), item1_count, item_one.getCost(), getItem1Total());
        System.out.printf("%s: %d units at $%f per unit = $%f\n", item_two.getName(), item2_count, item_two.getCost(), getItem2Total());
        System.out.printf("   ----> Subtotal = $%f\n", getCartTotal());
        System.out.printf("   ----> 7%% tax = $%f\n", getSalesTax());
        System.out.printf("   ----> Total = $%f\n\n", getCartTotal() + getSalesTax());
    }
}
