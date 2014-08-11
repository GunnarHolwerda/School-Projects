package dictionary;

/**
 *
 * @author Josh and Gunnar
 */
public class TreeNode {
    
    private Word data;
    private TreeNode parent, left, right;
    protected int column, row, center;
    
    public TreeNode(Word w) {
        data = w;
        parent = null;
        left = null;
        right = null;
    }
    
    public Word getData() {
        return data;
    }
    
    public void setParent(TreeNode n) {
        parent = n;
    }
    
    public TreeNode getParent() {
        return parent;
    }
    
    public void setLeft(TreeNode n) {
        left = n;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setRight(TreeNode n) {
        right = n;
    }

    public TreeNode getRight() {
        return right;
    }
    
    public TreeNode searchFrom(String word) {
        if (word.compareTo(data.getWord()) == 0) {
            return this;
        }
        if (word.compareTo(data.getWord()) < 0) {
            if (left == null) {
                return null;
            }
            return left.searchFrom(word);
        } 
        else {
            if (right == null) {
                return null;
            }
            return right.searchFrom(word);
        }   
    }   
    
}
