package treedemo;

/**
 *
 * @author Gunnar Holwerda
 */
public class TreeDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BinaryTree t = new BinaryTree();
        
//        for (int i = 0; i < 500; i++){
//            int rand = (int)(Math.random() * 1000);
//            t.insert(new TreeNode(rand));
//        }
        t.insert(new TreeNode(100));
        t.insert(new TreeNode(200));
        t.insert(new TreeNode(300));
        t.insert(new TreeNode(400));
        t.insert(new TreeNode(500));
        
        for (int i = 100; i < 600; i += 100){
            TreeNode searchedNode = t.nonRecursiveSearch(i);
            if (searchedNode != null){
                System.out.println(searchedNode.getData() + " was found in the binary tree...");
            }
            else{
                System.out.println("The value " + i + " was not found in the binary tree...");
            }
        }
    }
}
