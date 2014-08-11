package dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Gunnar
 */
public class NewWord extends JFrame{
    
    private JButton enterButton;
    private JTextField definitionField;
    private JLabel topLabel, wordLabel;
    private String word;
    private Tree tree;
    
    public NewWord(String word, Tree tree){
        super("Enter New Word");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.word = word;
        this.tree = tree;
        setLayout(new BorderLayout());
        Container contentPane = getContentPane();
        
        topLabel = new JLabel("Exit the frame if you don't wish to add the word to the dictionary...");
        contentPane.add(topLabel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        wordLabel = new JLabel(word.toUpperCase() + ": ");
        centerPanel.add(wordLabel);
        definitionField = new JTextField("Enter your definition here", 25);
        centerPanel.add(definitionField);
        enterButton = new JButton("Enter Word");
        enterButton.addActionListener(new EnterPressed());
        centerPanel.add(enterButton);
        
        contentPane.add(centerPanel, BorderLayout.CENTER);
        
        pack();
    }
    
    private class EnterPressed implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String definition = definitionField.getText();
            Word newWord = new Word(word, definition);
            tree.insert(new TreeNode(newWord));
            setVisible(false);
            dispose();
        }
    }
}
