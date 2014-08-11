package dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.FileReader;

/**
 *
 * @author Josh and Gunnar
 */
public class Dictionary extends JFrame {

    private JButton searchButton;
    private JTextField wordField;
    private Tree dictionaryTree;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new Dictionary();
        frame.setVisible(true);
    }
    
    public Dictionary() {
        super("Dictionary");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        Container contentPane = getContentPane();
        dictionaryTree = new Tree();
        input("C:/Users/Gunnar/Desktop/WordFile.txt");
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchPressed());
        buttonPanel.add(searchButton);
        
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(2, 1));
        pane.add(new JLabel("Search for word:"));
        wordField = new JTextField(20);
        wordField.addActionListener(new NewTextFieldValue());
        pane.add(wordField);
        
        contentPane.setLayout(new GridLayout(2, 1));
        contentPane.add(buttonPanel);
        contentPane.add(pane);
        
        pack();       
    }
    
    private void input(String fileName) {
        ArrayList<Word> dictionary = new ArrayList();
        String word = "not initialized word", definition = "not initiliazed definition";
        try {
            Scanner scanner = new Scanner(new FileReader(fileName));
            while(scanner.hasNextLine()) {
                    word = scanner.nextLine();
                    definition = scanner.nextLine();
                    boolean done = false;
                    while (!done){
                        String blank = scanner.nextLine();
                        if (!blank.equals("")){
                            definition += blank;
                        }
                        else{
                            done = true;
                        }
                    }
                    dictionary.add(new Word(word, definition));
            }
        }
        catch (Exception e) {
        }
        while(!dictionary.isEmpty()){
            int wordChoice = (int)(Math.random() * dictionary.size());
            TreeNode node = new TreeNode(dictionary.get(wordChoice));
            dictionaryTree.insert(new TreeNode(dictionary.get(wordChoice)));
            dictionary.remove(wordChoice);
        }
    }
    
    private class SearchPressed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String stringWord = wordField.getText();
            wordField.setText("");
            TreeNode searchedNode = dictionaryTree.searchFor(stringWord);
            if (searchedNode != null){
                JFrame resultFrame = new JFrame("Search Results");
                Container resultContentPane = resultFrame.getContentPane();
                JTextArea resultArea = new JTextArea(20,20);
                resultArea.setEditable(false);
                resultFrame.add(resultArea);
                resultArea.append(searchedNode.getData().getWord());
                resultArea.append("\n\n");
                resultArea.append(searchedNode.getData().getDefinition());
                resultFrame.setVisible(true);
                resultFrame.pack();
            }
            else{
                NewWord enterWord = new NewWord(stringWord, dictionaryTree);
                enterWord.setVisible(true);
            }
        }
    }
    
    private class NewTextFieldValue implements ActionListener {   
        @Override
        public void actionPerformed(ActionEvent e) { }
    }
}
