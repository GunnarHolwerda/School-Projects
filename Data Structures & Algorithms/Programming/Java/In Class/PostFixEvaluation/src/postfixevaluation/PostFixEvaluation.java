package postfixevaluation;

/**
 *
 * @author Gunnar Holwerda
 */
public class PostFixEvaluation {
    
    public static double evaluatePostFix(String expression){
        String[] tokens = expression.split("\\s+");     //Splits the string on on spaces
        StackDemo operandStack = new StackDemo();       //Stack-a-roo
        
        for (int i = 0; i < tokens.length; i++){
            String curToken = tokens[i];
            char firstChar = curToken.charAt(0);
            if(Character.isDigit(firstChar)){   //Current Token is a number
                double value = Double.parseDouble(curToken);
                operandStack.push(new StackNode(value));
            }
            else {
                double rightHandSide = operandStack.pop().getValue();
                double leftHandSide = operandStack.pop().getValue();
                double result = 0;
                switch(firstChar){
                    case '+':
                        result = leftHandSide + rightHandSide;
                        break;
                    case '-':
                        result = leftHandSide - rightHandSide;
                        break;
                    case '*':
                        result = leftHandSide * rightHandSide;
                        break;
                    case '/':
                        result = leftHandSide / rightHandSide;
                        break;
                }
                operandStack.push(new StackNode(result));
            }
        }
        double expressionValue = operandStack.pop().getValue();
        return expressionValue;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(evaluatePostFix("1 2 + 3 *"));
        System.out.println(evaluatePostFix("1 2 + 3 * 2 + 5 - 3 * "));
    }
}
