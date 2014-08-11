/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
/**
 *
 * @author Gunnar
 */
public class WaiterFrame extends JFrame{
    
    private ArrayList<Order> tableOrders;
    private JButton submitAllOrdersButton, enterTableNumberButton;
    private JTextField tableNumberField;
    private JLabel tableNumberLabel;
    public JTextArea ordersTextArea;
    
    public WaiterFrame(){
        super("Waiter Window");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2,3));
        Container contentPane = getContentPane();
        
        tableOrders = new ArrayList();
        
        tableNumberLabel = new JLabel("Table Number: ");
        tableNumberField = new JTextField(5);
        
        contentPane.add(tableNumberLabel);
        contentPane.add(tableNumberField);
        
        submitAllOrdersButton = new JButton("Submit All Orders");
        submitAllOrdersButton.addActionListener(new submitAllOrdersPressed());
        contentPane.add(submitAllOrdersButton);
        
        enterTableNumberButton = new JButton("Enter table number");
        enterTableNumberButton.addActionListener(new enterTableNumberPressed());
        contentPane.add(enterTableNumberButton);
        
//        ordersTextArea = new JTextArea(10, 10);
//        ordersTextArea.setEditable(false);
//        contentPane.add(ordersTextArea);
        
        pack();
        setVisible(true);
    }
    
    public void addOrder(Order newOrder){
        tableOrders.add(newOrder);
        //ordersTextArea.setText(newOrder.getName() + " " + newOrder.getTableNumber());
    }
    
    private class submitAllOrdersPressed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            for (int i = 0; i < tableOrders.size(); i++){
                tableOrders.get(i).printKitchenOrder();
            }
            tableOrders.clear();
        }
    }
    
    private class enterTableNumberPressed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Restaurant.getOrder().setTableNumber(Integer.parseInt(tableNumberField.getText()));
            tableNumberField.setText("");
        }
    }
}
