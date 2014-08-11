package calculator;

import calculator.InfixToPostfixParens.SyntaxErrorException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gunnar
 */
public class Calculator extends JFrame {

    JTextArea equationArea;
    JButton numberButtons[] = new JButton[10];
    JButton clearButton, equalsButton, plusButton,
            multiplyButton, divideButton, minusButton,
            openParenthesisButton, closeParenthesisButton;
    JPanel numberButtonPanel, operationButtonPanel;
    InfixToPostfixParens convertString;

    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        Container contentPane = getContentPane();
        convertString = new InfixToPostfixParens();

        //Create the text field where the equation is displayed
        equationArea = new JTextArea(1, 10);
        contentPane.add(equationArea, BorderLayout.NORTH);

        //Create the numbered buttons...
        numberButtonPanel = new JPanel();
        numberButtonPanel.setLayout(new GridLayout(4, 3));
        for (int i = 1; i < 10; i++) {
            numberButtons[i] = new JButton(Integer.toString(i));
            numberButtons[i].setActionCommand(Integer.toString(i));
            numberButtons[i].addActionListener(new ButtonPressed());
            numberButtonPanel.add(numberButtons[i]);
        }
        clearButton = new JButton("CE");
        clearButton.setActionCommand("CE");
        clearButton.addActionListener(new ClearPressed());
        numberButtonPanel.add(clearButton);
        numberButtons[0] = new JButton("0");
        numberButtons[0].setActionCommand("0");
        numberButtons[0].addActionListener(new ButtonPressed());
        numberButtonPanel.add(numberButtons[0]);
        equalsButton = new JButton("=");
        equalsButton.setActionCommand("=");
        equalsButton.addActionListener(new EqualsPressed());
        numberButtonPanel.add(equalsButton);

        contentPane.add(numberButtonPanel, BorderLayout.CENTER);

        //Create the panel for the buttons of operations
        operationButtonPanel = new JPanel();
        operationButtonPanel.setLayout(new GridLayout(6, 1));
        plusButton = new JButton("+");
        plusButton.setActionCommand("+");
        plusButton.addActionListener(new ButtonPressed());
        operationButtonPanel.add(plusButton);
        minusButton = new JButton("-");
        minusButton.setActionCommand("-");
        minusButton.addActionListener(new ButtonPressed());
        operationButtonPanel.add(minusButton);
        multiplyButton = new JButton("*");
        multiplyButton.setActionCommand("*");
        multiplyButton.addActionListener(new ButtonPressed());
        operationButtonPanel.add(multiplyButton);
        divideButton = new JButton("/");
        divideButton.setActionCommand("/");
        divideButton.addActionListener(new ButtonPressed());
        operationButtonPanel.add(divideButton);
        openParenthesisButton = new JButton("(");
        openParenthesisButton.setActionCommand("(");
        openParenthesisButton.addActionListener(new ButtonPressed());
        operationButtonPanel.add(openParenthesisButton);
        closeParenthesisButton = new JButton(")");
        closeParenthesisButton.setActionCommand(")");
        closeParenthesisButton.addActionListener(new ButtonPressed());
        operationButtonPanel.add(closeParenthesisButton);

        contentPane.add(operationButtonPanel, BorderLayout.EAST);



        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    private class ButtonPressed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonString = ((JButton) e.getSource()).getActionCommand();
            equationArea.append(buttonString);
        }
    }
    
    private class EqualsPressed implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                String infixEquation = equationArea.getText();
                String postfixEquation = convertString.convert(infixEquation);
                double answer = TestInfixEval.evaluatePostfix(postfixEquation);
                equationArea.setText(Double.toString(answer));
            } catch (SyntaxErrorException ex) {
                Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private class ClearPressed implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            equationArea.setText("");
        }
    }
}
