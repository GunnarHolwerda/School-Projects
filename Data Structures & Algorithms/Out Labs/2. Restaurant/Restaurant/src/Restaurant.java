
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gunnar
 */
public class Restaurant extends JFrame {
    
    private JButton enterButton, submitButton, clearButton;
    private JLabel nameOnOrderLabel, orderLabel;
    private JTextField nameOnOrderTextField;
    private static TextPanel textFrame;
    public static Order customerOrder;
    protected WaiterFrame waiter;
    
    public Restaurant(){
        super("Welcome to your favorite restaurant!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerOrder = new Order();
        Container contentPane = getContentPane();
        waiter = new WaiterFrame();
        
        //Set layout of the content pane
        contentPane.setLayout(new BorderLayout());    
        
        //Creates and adds the label panel to go on the top of the BorderLayout of the content pane
        JPanel nameOnOrderPanel = new JPanel();
        nameOnOrderPanel.setLayout(new FlowLayout());
        nameOnOrderLabel = new JLabel("Name on Order: ");
        nameOnOrderTextField = new JTextField(20);
        nameOnOrderTextField.addActionListener(new TextFieldEntered());
        nameOnOrderPanel.add(nameOnOrderLabel);
        nameOnOrderPanel.add(nameOnOrderTextField);
        contentPane.add(nameOnOrderPanel, BorderLayout.NORTH);
        
        //Adds the Radio buttons to the west on the Borderlayout
        contentPane.add(new RadioButton(), BorderLayout.WEST);
        
        //Creates and adds a Panel that holds the text frame for the order
        JPanel textFramePanel = new JPanel();
        textFramePanel.setLayout(new BorderLayout()); 
        orderLabel = new JLabel("                                       Your Order");
        textFramePanel.add(orderLabel, BorderLayout.NORTH);
        textFrame = new TextPanel();
        textFramePanel.add(textFrame, BorderLayout.CENTER);
        contentPane.add(textFramePanel, BorderLayout.EAST);
        
        //Creates and adds the button panel to the south of the border layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterPressed());
        submitButton = new JButton("Order is complete....Submit Order!");
        submitButton.addActionListener(new SubmitPressed()); 
        clearButton = new JButton("Clear Order");
        clearButton.addActionListener(new ClearPressed());
        buttonPanel.add(clearButton);
        buttonPanel.add(submitButton);
        buttonPanel.add(enterButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
    }
    
    public static Order getOrder(){
        return customerOrder;
    }
    
    public void grabOrderStats(){
        String entreeStr = RadioButton.foodButtonGroup.getSelection().getActionCommand();
        String appetizerStr = RadioButton.appButtonGroup.getSelection().getActionCommand();
        String drinkStr = RadioButton.drinkButtonGroup.getSelection().getActionCommand();
        String dessertStr = RadioButton.dessertButtonGroup.getSelection().getActionCommand();

        Food entree = RadioButton.find(RadioButton.foodSelections, entreeStr);
        Food appetizer = RadioButton.find(RadioButton.appSelections, appetizerStr);
        Food drink = RadioButton.find(RadioButton.drinkSelections, drinkStr);
        Food dessert = RadioButton.find(RadioButton.dessertSelections, dessertStr);

        customerOrder.add(entree);
        customerOrder.add(appetizer);
        customerOrder.add(drink);
        customerOrder.add(dessert);

        DecimalFormat decimal = new DecimalFormat("0.00");

        customerOrder.printCustomerOrder();
    }
    
        // Main Method
    public static void main(String args[]) {
        JFrame aFrame = new Restaurant();
        aFrame.setVisible(true);
    }
    
    //Inner Class
    private class EnterPressed implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            grabOrderStats();
            RadioButton.foodButtonGroup.clearSelection();
            RadioButton.appButtonGroup.clearSelection();
            RadioButton.drinkButtonGroup.clearSelection();
            RadioButton.dessertButtonGroup.clearSelection();
        }
    }
    
    private class SubmitPressed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (nameOnOrderTextField.getText().equals("")){
                JFrame nameFrame = new JFrame();
                nameFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                nameFrame.setLayout(new FlowLayout());
                JLabel nameErrorLabel = new JLabel("Please enter a name....");
                nameFrame.getContentPane().add(nameErrorLabel);
                nameFrame.pack();
                nameFrame.setVisible(true);
                return;
            }
            customerOrder.setName(nameOnOrderTextField.getText());
            Order tableOrderTemp = new Order(customerOrder);
            Order tableOrder = new Order(tableOrderTemp);
            waiter.addOrder(tableOrder);
            
            DecimalFormat decimal = new DecimalFormat("0.00");
            RadioButton.foodButtonGroup.clearSelection();
            RadioButton.appButtonGroup.clearSelection();
            RadioButton.drinkButtonGroup.clearSelection();
            RadioButton.dessertButtonGroup.clearSelection();
            
            textFrame.clearText();
            nameOnOrderTextField.setText("");
            customerOrder = new Order();  
        }
    }
    
    private class ClearPressed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Order.clearOrder(customerOrder);
            textFrame.clearText();
            nameOnOrderTextField.setText("");
        }
    }
    
    private class TextFieldEntered implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
        }
    }
}
