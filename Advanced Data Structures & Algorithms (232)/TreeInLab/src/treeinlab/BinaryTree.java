package treeinlab;

/**
 *
 * @author Gunnar
 */
public class BinaryTree {
    private Node root;
    
    /**
     * Constructor
     * 
     * Sets root to null
     */
    public BinaryTree(){
        this.root = null;
    }
    
    /**
     * getRoot
     * 
     * @return the root
     */
    public Node getRoot(){
        return root;
    }
    
    /**
     * setRoot
     * @param temp - Node to set the root to
     * 
     * set the root of the tree to temp
     */
    private void setRoot(Node temp){
        root = temp;
    }
    
    /**
     * insertNode
     * @param root  - current local root of the tree, starts at the root of the tree
     * @param temp  - node to be inserted into the tree
     * 
     * Inserts a node into the tree
     */
    public void insertNode(Node root, Node temp){
        if (root == null){          // Tree is empty
            System.out.println("The new root is now " + temp.getNum());
            this.setRoot(temp);             //Make the root the node being inserted
        }
        else if (root.getNum() < temp.getNum()){    //Number input is greater than the root number
            if (root.getRight() != null){       //If to the right is not null keep going right
                insertNode(root.getRight(),temp);
            }
            else {
                root.setRight(temp);            //Place the node to the right of local root
                temp.setParent(root);           //Set the parent of the inserted node to the local root
            }
        }
        else if (root.getNum() > temp.getNum()){    //Number input is less than the root number
            if (root.getLeft() != null){    //If to the left is not null keep going left
                insertNode(root.getLeft(), temp);
            }
            else{
                root.setLeft(temp);             //Place the node to the left of local root
                temp.setParent(root);           //Set the parent of the inserted node to the local root
            }
        }
    }
    
    /**
     * inOrder
     * @param root  - current local root of the tree, starts at the root of the tree 
     * 
     * Prints out the In-order traversal of the tree
     */
    public void inOrder(Node root){
        if (this.root == null){
            System.out.println("The tree is empty.");
        }
        else{
            if (root.getLeft() != null)             //Traverse left tree
                inOrder(root.getLeft());
            
            System.out.println(root.getNum() + ": "+ root.getWord());   //Visit
            
            if (root.getRight() != null)            //Traverse right tree
                inOrder(root.getRight());
        }
    }
    
    /**
     * postOrder
     * @param root - current local root of the tree, starts at the root of the tree 
     * 
     * Prints the Post-order traversal of the tree
     */
    public void postOrder(Node root){
        if (this.root == null){
            System.out.println("The tree is empty.");
        }
        else{
            if (root.getLeft() != null)         //Traverse left tree
                postOrder(root.getLeft());
            
            if (root.getRight() != null)        //Traverse right tree
                postOrder(root.getRight());
            
            System.out.println(root.getNum() + ": " + root.getWord());      //Visit
        }
    }
    
    /**
     * preOrder
     * @param root  - current local root of the tree, starts at the root of the tree  
     * 
     * Prints out the Pre-order traversal of the tree
     */
    public void preOrder(Node root){
        if (this.root == null){
            System.out.println("The tree is empty.");
        }
        else{
            System.out.println(root.getNum() + ": " + root.getWord());  //Visit
            
            if (root.getLeft() != null)     //Traverse left tree
                preOrder(root.getLeft());
               
            if (root.getRight() != null)    //Traverse right tree
                preOrder(root.getRight());
        }
    }
}
