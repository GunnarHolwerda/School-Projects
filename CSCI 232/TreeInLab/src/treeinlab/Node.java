package treeinlab;

/**
 *
 * @author Gunnar
 */
public class Node {
    private Node left, right, parent;
    private int num;
    private String word;
    
    public Node(int num, String word){
        this.word = word;
        this.num = num;
        left = null;
        right = null;
        parent = null;
    }
    
    public int getNum(){
        return num;
    }
    
    public String getWord(){
        return word;
    }
    
    public Node getParent(){
        return parent;
    }
    
    public Node getLeft(){
        return left;
    }
    
    public Node getRight(){
        return right;
    }
    
    public void setLeft(Node left){
        this.left = left;
    }
    
    public void setRight(Node right){
        this.right = right;
    }
    
    public void setParent(Node parent){
        this.parent = parent;
    }
}
