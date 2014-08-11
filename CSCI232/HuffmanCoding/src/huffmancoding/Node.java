package huffmancoding;

/**
 *
 * @author Gunnar
 */
public class Node {
    int frequency;
    char character;
    Node left, right;
    
    public Node(int frequency, Node left, Node right, char character){
        this.frequency = frequency;
        this.left = left;
        this.right = right;
        this.character = character;
    }
    
    public Node getRight(){
        return right;
    }
    
    public Node getLeft(){
        return left;
    }
    
    public int getFrequency(){
        return frequency;
    }
    
    public char getCharacter(){
        return character;
    }
    
    public void setLeft(Node left){
        this.left = left;
    }
    
    public void setRigth(Node right){
        this.right = right;
    }
    
    public void setCharacter(char c){
        character = c;
    }
}
