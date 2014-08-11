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
public class CalculatorGUI extends JFrame {
    
    private JButton enterButton, submitButton, clearButton;
    private JLabel nameOnOrderLabel, orderLabel;
    
    public CalculatorGUI() {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        
        //Set layout of the content pane
        contentPane.setLayout(new BorderLayout());    
        
        //Creates and adds the label panel to go on the top of the BorderLayout of the content pane
        JPanel nameOnOrderPanel = new JPanel();
        nameOnOrderPanel.setLayout(new FlowLayout());
        nameOnOrderLabel = new JLabel("");
        contentPane.add(nameOnOrderPanel, BorderLayout.NORTH);
        
        //Adds the Radio buttons to the west on the Borderlayout
        contentPane.add(new RadioButtons(), BorderLayout.WEST);
        
        //Creates and adds a Panel that holds the text frame
        JPanel textFramePanel = new JPanel();
        textFramePanel.setLayout(new BorderLayout()); 
        contentPane.add(textFramePanel, BorderLayout.EAST);
        
        //Creates and adds the button panel to the south of the border layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        enterButton = new JButton("=");
        enterButton.addActionListener(new EnterPressed());
        clearButton = new JButton("CE");
        clearButton.addActionListener(new ClearPressed());
        buttonPanel.add(clearButton);
        buttonPanel.add(enterButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
    }
        
    //command line arguments
    public static void main(String args[]) {
        JFrame aFrame = new CalculatorGUI();
        aFrame.setVisible(true);
    }

    //imported from the TestInfixEval class
    public static double evaluatePostfix(String expression) {
        MyStack evalStack = new MyStack();
        String[] split = expression.split(" ");
        for (int i = 0; i < split.length; i++) {
            //System.out.println("split[" + i + "] = " + split[i]);
            if (split[i].equals("+")) {
                double secondOperand = evalStack.pop().getData();
                double firstOperand = evalStack.pop().getData();
                double result = firstOperand + secondOperand;
                evalStack.push(new StackNode(result));
            } else if (split[i].equals("-")) {
                double secondOperand = evalStack.pop().getData();
                double firstOperand = evalStack.pop().getData();
                double result = firstOperand - secondOperand;
                evalStack.push(new StackNode(result));
            } else if (split[i].equals("*")) {
                double secondOperand = evalStack.pop().getData();
                double firstOperand = evalStack.pop().getData();
                double result = firstOperand * secondOperand;
                evalStack.push(new StackNode(result));
            } else if (split[i].equals("/")) {
                double secondOperand = evalStack.pop().getData();
                double firstOperand = evalStack.pop().getData();
                double result = firstOperand / secondOperand;
                evalStack.push(new StackNode(result));
            } else {
                // split[i] is a number (assumption)
                evalStack.push(new StackNode(Double.parseDouble(split[i])));
            }
        }
        double expressionValue = evalStack.pop().getData();
        return expressionValue;
    }
        
    //evaluate and display the user's expression
    private class EnterPressed implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String expression = RadioButtons.nameOnOrderTextField.getText();
            InfixToPostfixParens converter = new InfixToPostfixParens();
            try {
                String postfix = converter.convert(expression);
                double answer = evaluatePostfix(postfix);
                String theAnswer = String.valueOf(answer);
                RadioButtons.nameOnOrderTextField.setText(theAnswer);
                //System.out.println("equals: " + evaluatePostfix(postfix));
            } catch (Exception ex) {
                System.out.println(ex);
            };        
        }
    }
    
    //clear the expression field
    private class ClearPressed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            RadioButtons.nameOnOrderTextField.setText("");
        }
    }
    
    private class TextFieldEntered implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            
        }
    }
}
