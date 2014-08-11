package calculator;
/**
 * Write a description of class TestPostFixEval here.
 * 
 * @author Brendan
 * @version 2/14/11
 */


public class TestInfixEval
{
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
}
