package calculatorgui;


/**
 * Write a description of class StackNode here.
 * 
 * @author Brendan
 * @version 2/9/11
 */
public class StackNode
{
    // instance variables - replace the example below with your own
    private StackNode previous;
    private double data;

    /**
     * Constructor for objects of class StackNode
     */
    public StackNode(double c)
    {
        // initialise instance variables
        data = c;
    }

    public void setPrevious(StackNode node) {
        previous = node;
    }
    
    public StackNode getPrevious() {
        return previous;
    }
    
    public double getData() {
        return data;
    }
}

