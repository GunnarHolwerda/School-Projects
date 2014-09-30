package treeinlab;

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
        
        tree.insertNode(tree.getRoot(), new Node(5, "Hello"));
        tree.insertNode(tree.getRoot(), new Node(3, "Hello"));
        tree.insertNode(tree.getRoot(), new Node(7, "Hello"));
        tree.insertNode(tree.getRoot(), new Node(9, "Hello"));
        tree.insertNode(tree.getRoot(), new Node(0, "Hello"));
        tree.insertNode(tree.getRoot(), new Node(8, "Hello"));
        tree.insertNode(tree.getRoot(), new Node(2, "Hello"));
        tree.insertNode(tree.getRoot(), new Node(1, "Hello"));
        
        System.out.println("\nPost-Order:");
        tree.postOrder(tree.getRoot());
        System.out.println("\nIn-Order:");
        tree.inOrder(tree.getRoot());
        System.out.println("\nPre-Order:");
        tree.preOrder(tree.getRoot());
    }
    
}
