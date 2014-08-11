package calculatorgui;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

/**
 *
 * @author Josh Moellenkamp
 */
public class RadioButtons extends JPanel {
    
    private JLabel nameOnOrderLabel, orderLabel;
    public static JTextArea nameOnOrderTextField;

    public static ButtonGroup mathStuff;
    public static ButtonGroup numberStuff;
    
    JButton[] operations = new JButton[4];
    JButton[] numbers = new JButton[10];
    
    //Constructor
    public RadioButtons() {
        
        setLayout(new GridLayout(4, 1));
        nameOnOrderTextField = new JTextArea(1,10);
        add(nameOnOrderTextField);
        // Create a button group for the buttons.
        operations[0] = new JButton("*");
        operations[0].setActionCommand("*");
        operations[0].addActionListener(new SelectionChangeMade());
        add(operations[0]);
        
        //Create division button
        operations[1] = new JButton("/");
        operations[1].setActionCommand("/");
        operations[1].addActionListener(new SelectionChangeMade());
        add(operations[1]);
        
        //Create add button
        operations[2] = new JButton("+");
        operations[2].setActionCommand("+");
        operations[2].addActionListener(new SelectionChangeMade());
        add(operations[2]);
        
        //Create subtract button
        operations[3] = new JButton("-");
        operations[3].setActionCommand("-");
        operations[3].addActionListener(new SelectionChangeMade());
        add(operations[3]);
        
        setPreferredSize(new Dimension(200, 200));
        setVisible(true);
        
        //button group
        for (int i = 0; i < 10; i++) {
            numbers[i] = new JButton(String.valueOf(i));
            numbers[i].setActionCommand(String.valueOf(i));
            numbers[i].addActionListener(new SelectionChangeMade());
            add(numbers[i]);
        }
    }
    
    // Action Listener Classes
    private class SelectionChangeMade implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String buttonString = ((JButton) e.getSource()).getActionCommand();
            nameOnOrderTextField.append(buttonString);
           
        }
    }
}
