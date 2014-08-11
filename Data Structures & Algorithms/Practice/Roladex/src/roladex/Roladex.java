/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roladex;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author Gunnar
 */
public class Roladex extends JFrame{

    private JTextField firstNameField, lastNameField, emailField, phoneField;
    private JButton searchButton, enterButton;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        JFrame frame = new Roladex();
        frame.setVisible(true);
    }
    
    public Roladex()
    {
        super("Roladex");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(5, 2));
        
        contentPane.add(new JLabel("First Name:"));
        firstNameField = new JTextField(15);
        firstNameField.addActionListener(new NewTextFieldValue());
        contentPane.add(firstNameField);
        
        contentPane.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(15);
        lastNameField.addActionListener(new NewTextFieldValue());
        contentPane.add(lastNameField);
        
        contentPane.add(new JLabel("Email Address:"));
        emailField = new JTextField(15);
        emailField.addActionListener(new NewTextFieldValue());
        contentPane.add(emailField);
        
        contentPane.add(new JLabel("Phone Number"));
        phoneField = new JTextField(15);
        phoneField.addActionListener(new NewTextFieldValue());
        contentPane.add(phoneField);
        
        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchPressed());
        contentPane.add(searchButton);
        
        enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterPressed());
        contentPane.add(enterButton);
        
        pack();
    }
    
    private class NewTextFieldValue implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Something entered into text field");
        }
    }
    
    private class SearchPressed implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Search pressed");
        }
    }
    
    private class EnterPressed implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Enter pressed");
        }
    }
}
