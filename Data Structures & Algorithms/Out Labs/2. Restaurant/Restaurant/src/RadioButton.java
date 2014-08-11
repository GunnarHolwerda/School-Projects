
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

/**
 * RadioButtonDemo generates a simple demonstration of radio buttons.
 *
 * @author Gunnar Holwerda
 *
 */
public class RadioButton extends JPanel {

    // Data Fields
    public static Food[] foodSelections = {new Food("Pancakes", "main", 5.00),
                            new Food("Omelette", "main", 7.00),
                            new Food ("Steak", "main", 13.00)};
    public static Food[] appSelections = {new Food("Chicken", "appetizer", 6.00),
                            new Food("Oatmeal", "appetizer", 3.00),
                            new Food("Fruit", "appetizer", 4.50)};
    public static Food[] drinkSelections = {new Food("Water", "drink", 0.00),
                              new Food("Milk", "drink", 1.50),
                              new Food("Coffee", "drink", 2.25)};
    public static Food[] dessertSelections = {new Food("Snickers", "dessert", 1.75),
                                new Food("Ice Cream", "dessert", 3.75),
                                new Food("Donuts", "dessert", 5.00)};
    
    JRadioButton[] foodRadioButtons = new JRadioButton[foodSelections.length];
    JRadioButton[] appRadioButtons = new JRadioButton[appSelections.length];
    JRadioButton[] drinkRadioButtons = new JRadioButton[drinkSelections.length];
    JRadioButton[] dessertRadioButtons = new JRadioButton[dessertSelections.length];
    
    public static ButtonGroup foodButtonGroup;
    public static ButtonGroup appButtonGroup;
    public static ButtonGroup drinkButtonGroup;
    public static ButtonGroup dessertButtonGroup;
    
    public static Food find(Food[] menu, String str){
        for (int i = 0; i < menu.length; i++){
            if (menu[i].getName() == str){
                return menu[i];
            }
        }
        return null;
    }


    // Constructor

    public RadioButton() {
        setLayout(new GridLayout(4, 1));
        // Create a button group for the buttons.
        foodButtonGroup = new ButtonGroup();
        // Create radio buttons and add them to the panel.
        // Also add them to the button group.
        for (int i = 0; i < foodSelections.length; i++) {
            foodRadioButtons[i] = new JRadioButton(foodSelections[i].getName());
            foodRadioButtons[i].getModel().setActionCommand(foodSelections[i].getName());
            foodRadioButtons[i].addActionListener(new SelectionChangeMade());
            foodButtonGroup.add(foodRadioButtons[i]);
            add(foodRadioButtons[i]);
        }
        setPreferredSize(new Dimension(300, 400));
        setVisible(true);
        
        // Create a button group for the buttons.
        appButtonGroup = new ButtonGroup();
        // Create radio buttons and add them to the panel.
        // Also add them to the button group.
        for (int i = 0; i < appSelections.length; i++) {
            appRadioButtons[i] = new JRadioButton(appSelections[i].getName());
            appRadioButtons[i].getModel().setActionCommand(appSelections[i].getName());
            appRadioButtons[i].addActionListener(new SelectionChangeMade());
            appButtonGroup.add(appRadioButtons[i]);
            add(appRadioButtons[i]);
        }

        // Create a button group for the buttons.
        drinkButtonGroup = new ButtonGroup();
        // Create radio buttons and add them to the panel.
        // Also add them to the button group.
        for (int i = 0; i < drinkSelections.length; i++) {
            drinkRadioButtons[i] = new JRadioButton(drinkSelections[i].getName());
            drinkRadioButtons[i].getModel().setActionCommand(drinkSelections[i].getName());
            drinkRadioButtons[i].addActionListener(new SelectionChangeMade());
            drinkButtonGroup.add(drinkRadioButtons[i]);
            add(drinkRadioButtons[i]);
        }

        // Create a button group for the buttons.
        dessertButtonGroup = new ButtonGroup();
        // Create radio buttons and add them to the panel.
        // Also add them to the button group.
        for (int i = 0; i < dessertSelections.length; i++) {
            dessertRadioButtons[i] = new JRadioButton(dessertSelections[i].getName());
            dessertRadioButtons[i].getModel().setActionCommand(dessertSelections[i].getName());
            dessertRadioButtons[i].addActionListener(new SelectionChangeMade());
            dessertButtonGroup.add(dessertRadioButtons[i]);
            add(dessertRadioButtons[i]);
        }
    }

    // Action Listener Classes
    private class SelectionChangeMade implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           
        }
    }
}
