import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.text.DecimalFormat;
/**
 *
 * @author Gunnar
 */
public class Order{
    
    private ArrayList<Food> order;
    private String name;
    private int tableNumber;
    
    /**
     * Constructor for class Order
     * 
     */
    public Order(){
        order = new ArrayList();
        name = "";
        tableNumber = 0;
    }
    
    public Order(Order orderObject){
        order = orderObject.getOrder();
        name = orderObject.getName();
        tableNumber = orderObject.getTableNumber();
    }
    
    /**
     * Returns the total price of the order
     * 
     * @return  totalPrice  totalPrice of the order 
     */
    public double getOrderPrice(){
        double totalPrice = 0;
        for (int i = 0; i < order.size(); i++){
            totalPrice += order.get(i).getPrice();
        }
        return totalPrice;
    }
    
    public ArrayList<Food> getOrder(){
        return order;
    }
    
    public void add(Food food){
        order.add(food);
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public static void clearOrder(Order orderToClear) {
        orderToClear.getOrder().clear();
        orderToClear.setName("");
        orderToClear.setTableNumber(0);
    }
    
    public void setTableNumber(int num){
        tableNumber = num;
    }
    
    public int getTableNumber(){
        return tableNumber;
    }
    
    public boolean isEmpty(){
        return order.isEmpty();
    }
    
    public Food get(int index){
        return order.get(index);
    }
    
    public void remove(int index){
        order.remove(index);
    }
    
    public int getSize(){
        return order.size();
    }
    
    public void printKitchenOrder(){
        double totalPrice = getOrderPrice();
        DecimalFormat decimal = new DecimalFormat("0.00");
        
        JFrame kitchenOrderFrame = new JFrame();
        kitchenOrderFrame.setTitle("Order Tickets");
        kitchenOrderFrame.setLayout(new BorderLayout());
        kitchenOrderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JTextArea kitchenOrderTextArea = new JTextArea(20, 20);
        kitchenOrderTextArea.setEditable(false);
        
        kitchenOrderTextArea.append("Order for Table #" + tableNumber + "\n");
        while (!order.isEmpty()){
            Food curFood = order.get(0);
            int curFoodCount = 1;
            for (int j = 1; j < order.size(); j++)
            {
                if (curFood.getName().equals(order.get(j).getName())){
                    order.remove(j);
                    curFoodCount++;
                }
            }
            order.remove(0);
            kitchenOrderTextArea.append(curFoodCount + " " + curFood.getName() + "\t" + decimal.format(curFood.getPrice() * curFoodCount) + "\n");
        }
        
        kitchenOrderTextArea.append("\n\nTotal Price: \t" + decimal.format(totalPrice));
        kitchenOrderFrame.getContentPane().add(kitchenOrderTextArea, BorderLayout.CENTER);
        kitchenOrderFrame.pack();
        kitchenOrderFrame.setVisible(true);
    }
    
    public void printCustomerOrder(){
        DecimalFormat decimal = new DecimalFormat("0.00");
        ArrayList<Food> tempOrder = new ArrayList();
        TextPanel.clearText();
        while (!order.isEmpty()){
            Food curFood = order.get(0);
            int curFoodCount = 1;
            for (int j = 1; j < order.size(); j++)
            {
                if (curFood.getName().equals(order.get(j).getName())){
                    order.remove(j);
                    curFoodCount++;
                }
            }
            order.remove(0);
            TextPanel.updateText(curFoodCount + " " + curFood.getName() + "\t" + decimal.format(curFood.getPrice() * curFoodCount) + "\n");
            tempOrder.add(curFood);
        }
        order = tempOrder;
        TextPanel.updateText("\n\n\nTotal Price: \t" + decimal.format(getOrderPrice()));
    }
}
