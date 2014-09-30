package treeoutlab;

/**
 *
 * @author Gunnar
 */
public class Node<E> implements Comparable{
    private Node left, right, parent;
    private int num;
    private String word;
    
    /**
     * Constructor for Node
     * @param num   - number to hold in the node
     * @param word  - string to hold in the node
     */
    public Node(int num, String word){
        this.word = word;
        this.num = num;
        left = null;
        right = null;
        parent = null;
    }
    
    /**
     * compareTo
     * @param newNode - object being compared to
     * @return  1 if newNode is less than the node
     *          0 if newNode is the same as the node
     *         -1 if newNode is greater than the node
     */
    @Override
    public int compareTo(Object newNode){
        Node otherNode = (Node) newNode;
        
        if (word.compareTo(otherNode.getWord()) < 0){
            return -1;
        }
        else if (word.compareTo(otherNode.getWord()) == 0){
            if (num > otherNode.getNum()){
                return 1;
            }
            else if (num == otherNode.getNum()){
                return 0;
            }
            else{
                return -1;
            }
        }
        else {
            return 1;
        }
    }
    
    public int getNum(){
        return num;
    }
    
    public String getWord(){
        return word;
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
    
    public Node getParent(){
        return parent;
    }
}
