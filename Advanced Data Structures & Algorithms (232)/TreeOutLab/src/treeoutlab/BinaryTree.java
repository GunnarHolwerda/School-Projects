package treeoutlab;

/**
 *
 * @author Gunnar
 */
public class BinaryTree<E extends Comparable> {
    private Node<E> root;
    
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
        if (root == null){
            System.out.println("The new root is now " + temp.getNum());
            this.setRoot(temp);
            return;
        }
        if (root.compareTo(temp) < 0){
            if (root.getRight() != null){
                insertNode(root.getRight(),temp);
            }
            else{
               root.setRight(temp);
               temp.setParent(root);
            }
        }
        else if (root.compareTo(temp) > 0){
            if (root.getLeft() != null){
                insertNode(root.getLeft(), temp);
            }
            else{
                root.setLeft(temp);
                temp.setParent(root);
            }
        }
        else {
            System.out.println("Node is already in the tree.");
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
            if (root.getLeft() != null)
                inOrder(root.getLeft());
            
            System.out.println(root.getNum() + ": "+ root.getWord());
            
            if (root.getRight() != null)
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
            if (root.getLeft() != null)
                postOrder(root.getLeft());
            if (root.getRight() != null)
                postOrder(root.getRight());
            
            System.out.println(root.getNum() + ": " + root.getWord());
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
            System.out.println(root.getNum() + ": " + root.getWord());
            if (root.getLeft() != null)
                preOrder(root.getLeft());
            if (root.getRight() != null)
                preOrder(root.getRight());
        }
    }
    
    /**
     * search
     * @param root          - current local root of the tree, starts at the root of the tree 
     * @param searchNode    - node that is being searched for
     * 
     * Searches through the tree to attempt to find the searchNode recursively
     * 
     * @return if successful will return the found node, if unsuccessful will return null
     */
    public Node search(Node root, Node searchNode){
        if (root == null){
            System.out.println("Tree is empty, please enter data into tree...");
            return null;
        }
        if (root.compareTo(searchNode) == 0){
            System.out.println("Node with word " + root.getWord() + " and number " + root.getNum() + " was found");
            return root;
        }
        else if(root.compareTo(searchNode) > 0){
            if (root.getLeft() != null)
                return search(root.getLeft(), searchNode);
            else{
                System.out.println("Node was not found in the tree.");
                return null;
            }
        }
        else if (root.compareTo(searchNode) < 0){
            if (root.getRight() != null)
                return search(root.getRight(), searchNode);
            else{
                System.out.println("Node was not found in the tree.");
                return null;
            }
        }
        else {
            return null;
        }
    }
    
    /**
     * deleteNode
     * 
     * @param root          - local root of the tree, will always usually start at the root of the tree
     * @param deleteNode    - Node to be deleted from the tree.
     * 
     * Deletes a node from the tree.
     */
    public void deleteNode(Node root, Node deleteNode){
        boolean deleteRoot = false;
        Node newRoot = null;
        
        if (root == null){                                              //Check to see if tree is empty
            System.out.println("Tree is empty, nothing to delete.");
            return;
        }
        if (root.compareTo(deleteNode) > 0){
            if (root.getLeft() != null)
                deleteNode(root.getLeft(), deleteNode);                 //Node is smaller than current root move left
            else
                System.out.println("The node is not in the tree");
        }
        else if (root.compareTo(deleteNode) < 0){
            if (root.getRight() != null)
                deleteNode(root.getRight(), deleteNode);                //Node is bigger than current root move right
            else
                System.out.println("The node is not in the tree");
        }
        else {                                                     //The node is the same as the local root
            Node parent = root.getParent();
            
            if (parent == null){
                System.out.println("You are attempting to delete the root");
                parent = new Node(999, "ERROR");
                parent.setLeft(root);
                parent.setRight(root);
                deleteRoot = true;
            }
            
            //Print out some info on the deletion
            System.out.println("We are deleting a node with number " + root.getNum());
            
            if (root.getRight() == null && root.getLeft() == null){                                         //The node has no children
                if (parent.getLeft().compareTo(root) == 0){                                                 //The node is the left child of it's parent
                    //Set the left child of the parent to null
                    parent.setLeft(null);   
                }
                else {                                                                                      //The node is the right child of it's parent
                    //Set the right child of the parent to null
                    parent.setRight(null);         
                }
                
                if (deleteRoot){
                    newRoot = null;
                }
            }
            else if (root.getRight() != null && root.getLeft() == null) {                                   //The node has a right child but no left child
                if (parent.getRight().compareTo(root) == 0){                                                //The node is the right child of the parent
                    //Replace the current node with its right child
                    parent.setRight(root.getRight());
                }
                else {                                                                                      //The node is the left child of the parent
                    //Replace the current node with its right child
                    parent.setLeft(root.getRight());
                }
                
                //Set the parent of the right child of the current node to the parent of the current node
                root.getRight().setParent(parent);
                
                if (deleteRoot){
                    newRoot = root.getRight();
                }
            }
            else if (root.getLeft() != null && root.getRight() == null){                                    //The node has a left child but no right child
                if (parent.getLeft().compareTo(root) == 0){                                                 //The current node is the left child of its parent
                    //Replace the current child with its left child
                    parent.setLeft(root.getLeft());
                }
                else {                                                                                      //The current node is the right child of its parent
                    //Replace the current node with its left child
                    parent.setRight(root.getLeft());
                }
                
                //Set the parent of the left child of the current node to the parent of the current node
                root.getLeft().setParent(parent);
                
                if (deleteRoot){
                    newRoot = root.getLeft();
                }
            }
            else{                                                                                           //The current node has two children                           
                    
                Node replacementNode = root.getLeft();
                
                while (replacementNode.getRight() != null){                                                 //Find the rightmost node in the left subtree
                    replacementNode = replacementNode.getRight();
                }

                if (parent.getRight().compareTo(root) == 0){                                                //The node being replaced is the right child of its parent
                    parent.setRight(replacementNode);
                }
                else {                                                                                      //The node being replaced is the left child of its parent
                    parent.setLeft(replacementNode);
                }
                
                //Set the replacementNode's right child to the right child of the node being deleted
                if (root.getRight() != null){
                    replacementNode.setRight(root.getRight());
                }
                
                if (replacementNode.getParent().getRight().compareTo(replacementNode) == 0){                //If the replacement node is the right child of its parent
                    replacementNode.getParent().setRight(replacementNode.getLeft());   //Set the right child of its parent to the replacementNode's left child
                }
                
                replacementNode.setParent(parent);          //Set the parent of the replacement node to the parent of the node being deleted
                root.getRight().setParent(replacementNode); //Set the parent of the right child of deleted node to the replacement
                root.getLeft().setParent(replacementNode);  //Set the parent of the left child of deleted node to the replacement   
                replacementNode.setLeft(root.getLeft());    //Set the left child of the replacement node to the left child of the deleted node
                
                //Get rid of node being replaced
                root.setLeft(null);
                root.setRight(null);
                root.setParent(null);
                
                if (deleteRoot){
                    newRoot = replacementNode;
                }
            }
        }
        
        if (deleteRoot){
            root.setParent(null);
            this.root = newRoot;
        }
    }
}

