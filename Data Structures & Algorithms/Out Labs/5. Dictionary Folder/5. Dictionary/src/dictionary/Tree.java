package dictionary;

/**
 *
 * @author Josh and Gunnar
 */
public class Tree {
    
    private TreeNode root;
    private static int numNodes;
    
    public Tree() {
        root = null;
        numNodes = 0;
    }
    
    public void insert(TreeNode n) {
        numNodes++;
        if(root == null) {
            root = n;
            return;
        }
        
        TreeNode curNode = root; // root is not null
        while (true) {
            if (n.getData().getWord().compareTo(curNode.getData().getWord()) < 0) {
                if (curNode.getLeft() == null) {
                    curNode.setLeft(n);
                    n.setParent(curNode);
                    return;
                }
                curNode = curNode.getLeft();
            }
            else {
                if (curNode.getRight() == null) {
                    curNode.setRight(n);
                    n.setParent(curNode);
                    return;
                }
                curNode = curNode.getRight();   
            }
        }
    }
    
    public TreeNode searchFor(String word){
        if(word.equals(root.getData())){
            return root;
        }
        else{
            return root.searchFrom(word);
        }
    }
        
}
