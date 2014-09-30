package treeoutlab;

/**
 *
 * @author Gunnar
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        
        tree.insertNode(tree.getRoot(), new Node(5, "L"));
        tree.insertNode(tree.getRoot(), new Node(3, "L"));
        tree.insertNode(tree.getRoot(), new Node(4, "L"));
        tree.insertNode(tree.getRoot(), new Node(7, "L"));
        tree.insertNode(tree.getRoot(), new Node(6, "L"));
        tree.insertNode(tree.getRoot(), new Node(9, "L"));
        tree.insertNode(tree.getRoot(), new Node(0, "L"));
        tree.insertNode(tree.getRoot(), new Node(8, "L"));
        tree.insertNode(tree.getRoot(), new Node(2, "L"));
        tree.insertNode(tree.getRoot(), new Node(1, "L"));
        tree.insertNode(tree.getRoot(), new Node(1, "L"));
        
        System.out.println("\nPost-Order:");
        tree.postOrder(tree.getRoot());
        System.out.println("\nIn-Order:");
        tree.inOrder(tree.getRoot());
        System.out.println("\nPre-Order:");
        tree.preOrder(tree.getRoot());
       
        
        System.out.println("\nSearching");
        tree.search(tree.getRoot(), new Node(1, "L"));
        tree.search(tree.getRoot(), new Node(3, "BAM"));
        
        
        System.out.println("\nDeleting a node");
        tree.deleteNode(tree.getRoot(), new Node(5, "L"));
        
        System.out.println("\nAFTER:");     
        System.out.println("Post-Order:");
        tree.postOrder(tree.getRoot());
        System.out.println("\nIn-Order:");
        tree.inOrder(tree.getRoot());
        System.out.println("\nPre-Order:");
        tree.preOrder(tree.getRoot());
        
        }
    
}
