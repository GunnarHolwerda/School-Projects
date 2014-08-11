package treedemo;

/**
 *
 * @author Gunnar Holwerda
 */
public class BinaryTree {
    private TreeNode root;
    
    public BinaryTree(){
        root = null;
    }
    
    public TreeNode getRoot(){
        return root;
    }
    
    public void insert(TreeNode node){
        if (root == null){
            root = node;
            return;
        }
        TreeNode currentNode = root;    //root is not null if we get here
        while(true){
            if (node.getData() <= currentNode.getData()){
                if (currentNode.getLeft() == null){
                    currentNode.setLeft(node);
                    return;
                }
                else {
                    currentNode = currentNode.getLeft();
                }
            }
            else {
                if (currentNode.getRight() == null){
                    currentNode.setRight(node);
                    return;
                }
                else {
                    currentNode = currentNode.getRight();
                }
            }
        }
    }
    
    public TreeNode nonRecursiveSearch(int value){
        TreeNode curNode = root;
        while (true){
            if (curNode == null){
                return null;
            }
            if (curNode.getData() == value){
                return curNode;
            }
            else if (curNode.getData() > value){
                curNode = curNode.getRight();
            }
            else if (curNode.getData() < value){
                curNode = curNode.getLeft();
            }
            else {
                return null;
            }
        }
    }
    
    public void preOrderTraversal(TreeNode n){
        if (n != null){
            System.out.print( " " + n.getData());
        }
        
        if (n != null && n.getLeft() != null){
            preOrderTraversal(n.getLeft());
        }
        
        if (n != null && n.getRight() != null){
            preOrderTraversal(n.getRight());
        }
    }
    
    public void inOrderTraversal(TreeNode n){
        if (n != null && n.getLeft() != null){
            inOrderTraversal(n.getLeft());
        }
        
        if (n != null){
            System.out.print(" " + n.getData());
        }
        
        if (n != null && n.getRight() != null){
            inOrderTraversal(n.getRight());
        }
    }
    
    public void postOrderTraversal(TreeNode n){
        if (n != null && n.getLeft() != null){
            postOrderTraversal(n.getLeft());
        }
        
        if (n != null && n.getRight() != null){
            postOrderTraversal(n.getRight());
        }
        
        if (n != null){
            System.out.print(" " + n.getData());
        }
    }
}