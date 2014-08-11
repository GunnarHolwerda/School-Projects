import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {

    public static JTextArea textArea;

    public TextPanel() {

        textArea = new JTextArea(20, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        setPreferredSize(new Dimension(300, 300));

        add(textArea);
        setVisible(true);
    }
    
    public static void updateText(String s) {
        textArea.append(s);
    }
    
    public static void clearText(){
        textArea.setText("");
    }
    
    public static void setText(String s){
        textArea.setText(s);
    }
}
