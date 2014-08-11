package postfixevaluation;

/**
 *
 * @author Gunnar Holwerda
 */
public class StackDemo {
    
    private StackNode topNode;
    
    public StackDemo(){
        topNode = null;     //empty stack
    }
    
    //stack operations
    
    public void push(StackNode node){
        node.setPrevious(topNode);
        topNode = node;
    }
    
    public StackNode pop() {
        if (topNode == null) {
            return null;
        }
        StackNode currentTopNode = topNode;
        topNode = topNode.getPrevious();
        
        return currentTopNode;
    }
    
    public StackNode peak(){
        return topNode;
    }
}
