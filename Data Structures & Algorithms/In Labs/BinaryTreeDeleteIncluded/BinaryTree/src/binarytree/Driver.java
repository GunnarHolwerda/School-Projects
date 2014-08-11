/**
 *
 * @author Gunnar Holwerda
 */
package binarytree;

import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Driver extends JFrame{
    
    private BinaryTree tree;
    private PrintTree2 print;
    private JLabel insertLabel, deleteLabel, searchLabel;
    private JTextField insertField, deleteField, searchField;
    private JButton quitButton;
    
    public Driver(){
        super("Binary Tree Storage");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,3));
        Container contentPane = getContentPane();
        
        tree = new BinaryTree();
        print = new PrintTree2();
        
        insertLabel = new JLabel("Insert");
        contentPane.add(insertLabel);
        insertField = new JTextField(15);
        insertField.addActionListener(new InsertFieldEntered());
        contentPane.add(insertField);
        
        deleteLabel = new JLabel("Delete");
        contentPane.add(deleteLabel);
        deleteField = new JTextField(15);
        deleteField.addActionListener(new DeleteFieldEntered());
        contentPane.add(deleteField);
        
        searchLabel = new JLabel("Search");
        contentPane.add(searchLabel);
        searchField = new JTextField(15);
        searchField.addActionListener(new SearchFieldEntered());
        contentPane.add(searchField);
        
        quitButton = new JButton("Quit");
        quitButton.addActionListener(new QuitPressed());
        contentPane.add(quitButton);
        
        pack();
    }

    public static void main(String[] args) {
        Driver frame = new Driver();
        frame.setVisible(true);
    }
    
    private class InsertFieldEntered implements ActionListener{
    
        @Override
        public void actionPerformed(ActionEvent e){
            String insertDataString = insertField.getText();
            int insertData = Integer.parseInt(insertDataString);
            tree.insert(new TreeNode(insertData));
            print.printTree(tree);
            insertField.setText("");
        }
        
    }
    
    private class SearchFieldEntered implements ActionListener{
    
        @Override
        public void actionPerformed(ActionEvent e){
            String searchDataString = searchField.getText();
            int searchData = Integer.parseInt(searchDataString);
            TreeNode searchResults = tree.search(searchData);
            print.printTree(tree);
            if (searchResults != null){
                System.out.println("The value " + searchResults.getData() + " was found in the tree.");
            }
            else{
                System.out.println("The value " + searchData + " was not found in the tree");
            }
            searchField.setText("");
        }       
    }
    
    private class DeleteFieldEntered implements ActionListener{
    
        @Override
        public void actionPerformed(ActionEvent e){
            String deleteDataString = deleteField.getText();
            int deleteData = Integer.parseInt(deleteDataString);
            tree.delete(tree.search(deleteData));
            print.printTree(tree);
            deleteField.setText("");
        }
        
    }
    
    private class QuitPressed implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
}
