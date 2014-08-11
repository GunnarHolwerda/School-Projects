/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rolodex;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Brendan
 */
public class ContactPanel extends JPanel {

    public JTextField firstNameField, lastNameField, phoneField, emailField;

    public ContactPanel() {
        setLayout(new GridLayout(4, 2));

        add(new JLabel("First name:"));
        firstNameField = new JTextField(15);
        firstNameField.addActionListener(new NewTextFieldValue());
        add(firstNameField);
        
        add(new JLabel("Last name:"));
        lastNameField = new JTextField(15);
        lastNameField.addActionListener(new NewTextFieldValue());
        add(lastNameField);
        
        add(new JLabel("Phone:"));
        phoneField = new JTextField(15);
        phoneField.addActionListener(new NewTextFieldValue());
        add(phoneField);
        
        add(new JLabel("Email:"));
        emailField = new JTextField(15);
        emailField.addActionListener(new NewTextFieldValue());
        add(emailField);

    }

    private class NewTextFieldValue implements ActionListener {

        /**
         * Convert the gallons value to corresponding liters value.
         *
         * @param e ActionEvent object - not used
         */
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    public void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }
}
