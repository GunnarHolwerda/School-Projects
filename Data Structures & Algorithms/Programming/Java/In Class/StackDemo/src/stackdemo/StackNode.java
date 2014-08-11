package stackdemo;

/**
 *
 * @author Gunnar Holwerda
 */
public class StackNode {
    private StackNode previous;
    private char character;
    
    public StackNode(char c) {
        character = c;
        previous = null;
    }
    
    public void setPrevious(StackNode node){
        previous = node;
    }
    
    public StackNode getPrevious() {
        return previous;
    }
    
    public char getCharacter(){
        return character;
    }
}
