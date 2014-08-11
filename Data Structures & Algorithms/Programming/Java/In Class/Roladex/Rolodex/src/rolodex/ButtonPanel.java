/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rolodex;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Brendan
 */
public class ButtonPanel extends JPanel {

    private JButton clearButton, enterButton, searchButton, printButton, deleteButton;

    public ButtonPanel() {
        setLayout(new FlowLayout());
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearPressed());
        add(clearButton);
        enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterPressed());
        add(enterButton);
        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchPressed());
        add(searchButton);
        printButton = new JButton("Print");
        printButton.addActionListener(new PrintPressed());
        add(printButton);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new DeletePressed());
        add(deleteButton);

    }

    // Inner Class
    /**
     * The action listener for the button.
     */
    private class ClearPressed
            implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Rolodex.contactPanel.clearFields();
        }
    }

    private class EnterPressed
            implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Enter button was pressed.");
            Contact newContact = new Contact(Rolodex.contactPanel.firstNameField.getText(),
                    Rolodex.contactPanel.lastNameField.getText(),
                    Integer.parseInt(Rolodex.contactPanel.phoneField.getText()),
                    Rolodex.contactPanel.emailField.getText());
            Rolodex.myContacts.insertAlphabetically(new ContactListNode(newContact));
            Rolodex.contactPanel.clearFields();
        }
    }

    private class SearchPressed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Matchs found:");
            ContactListNode curNode = Rolodex.myContacts.getHead();
            while (curNode != null){
                Contact curContact = curNode.getContact();
                if (curContact.match(Rolodex.contactPanel.firstNameField.getText(),
                        Rolodex.contactPanel.lastNameField.getText(),
                        Rolodex.contactPanel.phoneField.getText(),
                        Rolodex.contactPanel.emailField.getText())) {
                    // print it out
                    curContact.print();
                }
                curNode = curNode.getNext();
            }
            Rolodex.contactPanel.clearFields();
        }
    }
    
    private class PrintPressed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Rolodex.printContacts();
        }
    }
    
    private class DeletePressed implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e){
            ContactListNode curNode = Rolodex.myContacts.getHead();
            ContactListNode nextNode;
            
            while (curNode != null){
                Contact curContact = curNode.getContact();
                
                if (curContact.match(Rolodex.contactPanel.firstNameField.getText(),
                    Rolodex.contactPanel.lastNameField.getText(),
                    Rolodex.contactPanel.phoneField.getText(),
                    Rolodex.contactPanel.emailField.getText())) {

                        //Deletes the node
                        nextNode = curNode.getNext();
                        Rolodex.myContacts.removeNode(curNode);
                        curNode = nextNode;
                }
                else {
                    curNode = curNode.getNext();
                }
            }
            Rolodex.contactPanel.clearFields();
        }
    }
}
