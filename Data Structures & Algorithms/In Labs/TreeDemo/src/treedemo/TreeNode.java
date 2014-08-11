package treedemo;

/**
 *
 * @author Gunnar Holwerda
 */
public class TreeNode {
    private int data;
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;
    
    public TreeNode(int d){
        data = d;
    }
    
    public int getData(){
        return data;
    }
    
    public void setParent(TreeNode n){
        parent = n;
    }
    
    public TreeNode getParent(){
        return parent;
    }
    
    public void setLeft(TreeNode n){
        left = n;
    }
    
    public TreeNode getLeft(){
        return left;
    }
    
    public void setRight(TreeNode n){
        right = n;
    }
    
    public TreeNode getRight(){
        return right;
    }
    
}