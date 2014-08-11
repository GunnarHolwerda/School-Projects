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
            if(curNode == null){
                return null;
            }
            else {
                if (curNode.getData() == value){
                    return curNode;
                }
                if (curNode.getData() > value){
                    curNode = curNode.getRight();
                }
                if (curNode.getData() < value){
                    curNode = curNode.getLeft();
                }
            }
        }
    }
}
