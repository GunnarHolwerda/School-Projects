package postfixevaluation;

/**
 *
 * @author Gunnar Holwerda
 */
public class StackNode {
    private StackNode previous;
    private double value;
    
    public StackNode(double number) {
        value = number;
        previous = null;
    }
    
    public void setPrevious(StackNode node){
        previous = node;
    }
    
    public StackNode getPrevious() {
        return previous;
    }
    
    public double getValue(){
        return value;
    }
}
