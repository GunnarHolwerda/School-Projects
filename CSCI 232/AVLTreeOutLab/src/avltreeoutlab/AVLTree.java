package avltreeoutlab;

 
import java.util.*;

public class AVLTree
{
  private Node root;
  private boolean increase;
  private boolean decrease;
  private boolean addReturn;
  private Scanner stop;

  public AVLTree()
  {
    root = null;
    addReturn = false;
    increase = false;
    stop = new Scanner(System.in);
  }  

public boolean add(int item) {

    System.out.println("Starting to search for a a place to put " + item);
    increase = false;
    root = add(root, item);
    return addReturn;
  }

  private Node add(Node localRoot, int item) {
     
    if (localRoot == null) {
      addReturn = true;
      increase = true;
       System.out.println("Added " + item);
      return new Node(item);
    }
   System.out.println("Add called with " + localRoot.getItem() + " as the root.");
    if (item == localRoot.getItem()) {                                //item is alreday in tree
      increase = false;
      addReturn = false;
      return localRoot;
    }

    else if (item  < localRoot.getItem()) {
       System.out.println("Branching left");						                        // item < data
      localRoot.setLeft(add(localRoot.getLeft(), item));

      if (increase) {
        decrementBalance(localRoot);
        if (localRoot.balance < Node.LEFT_HEAVY) {
          increase = false;
          return rebalanceLeft(localRoot);
        }
      }
      return localRoot;                                            // Rebalance not needed.
    }
    else {
      System.out.println("Branching right");                                                                   // item > data
      localRoot.setRight(add(localRoot.getRight(), item));
      if (increase) {
        incrementBalance(localRoot);
        if (localRoot.balance > Node.RIGHT_HEAVY) {
          return rebalanceRight(localRoot);
        }
        else {
          return localRoot;
        }
      }
      else {
        return localRoot;
      }
    }

  }

    private void decrementBalance(Node node) 
    {
        node.balance--;
         if (node.balance == Node.BALANCED) {
           increase = false;
         }
     }


     
    private Node rebalanceRight(Node localRoot) {
   

        // Obtain reference to right child
        Node right = localRoot.getRight();

         // See if right-left heavy
         if (right.balance < Node.BALANCED){
             System.out.println("Right-left imbalance.");
            // Obtain reference to right-left child
              Node newRoot = right.getLeft();

            /* Adjust the balances to be their new values after
               the rotates are performed.
             */

            if (newRoot.balance > Node.BALANCED) {
                right.balance = Node.RIGHT_HEAVY;
                newRoot.balance = Node.BALANCED;
                localRoot.balance = Node.BALANCED;
            }
            else if (newRoot.balance < Node.BALANCED) {
                right.balance = Node.BALANCED;
                newRoot.balance = Node.BALANCED;
                localRoot.balance = Node.LEFT_HEAVY;
            }
            else {
                right.balance = Node.BALANCED;
                localRoot.balance = Node.BALANCED;
            }
            /** After the rotates the overall height will be
                reduced thus increase is now false, but
                decrease is now true.
             */
            increase = false;
            decrease = true;
            
            // Perform double rotation
            localRoot.setRight(rotateRight(right));               //Sets the right left child of the local root to the right child

            return rotateLeft(localRoot);
         }
         //Right-right heavy
         System.out.println("Right-right imbalance");
         if  (right.balance > Node.BALANCED){
           /* In this case both the rightChild (the new root)
              and the root (new left child) will both be balanced
              after the rotate. Also the overall height will be
              reduced, thus increase will be false, but decrease
              will be true.
            */
             
             increase = false;
             decrease = true;
             right.balance = Node.BALANCED;
             localRoot.balance = Node.BALANCED;
         }
         else {
           /* After the rotate the rightChild (new root) will
              be left heavy, and the local root (new left child)
              will be right heavy. The overall height of the tree
              will not change, thus increase and decrease remain
              unchanged.
            */
             right.balance = Node.LEFT_HEAVY;
             localRoot.balance = Node.RIGHT_HEAVY;
         }
         // Now rotate the
         return rotateLeft(localRoot);
  }

  private Node rebalanceLeft(Node localRoot) {      
      // Obtain reference to left child
        Node left = localRoot.getLeft();

         // See if left-right heavy
         if (left.balance > Node.BALANCED){
             System.out.println("Left-right imbalance");
            // Obtain reference to right-left child
            Node newRoot = left.getRight();

            if (newRoot.balance > Node.BALANCED) {
                left.balance = Node.LEFT_HEAVY;
                newRoot.balance = Node.BALANCED;
                localRoot.balance = Node.BALANCED;
            }
            else if (newRoot.balance < Node.BALANCED) {
                left.balance = Node.BALANCED;
                newRoot.balance = Node.BALANCED;
                localRoot.balance = Node.RIGHT_HEAVY;
            }
            else {
                left.balance = Node.BALANCED;
                localRoot.balance = Node.BALANCED;
            }
            increase = false;
            decrease = true;
            
            // Perform double rotation
            localRoot.setLeft(rotateLeft(left));               //Sets the right left child of the local root to the right child

            return rotateRight(localRoot);
         }
         //Left-left heavy
         System.out.println("Left-left imbalance");
         if  (left.balance > Node.BALANCED){
           /* In this case both the leftChild (the new root)
              and the root (new right child) will both be balanced
              after the rotate. Also the overall height will be
              reduced, thus increase will be false, but decrease
              will be true.
            */
             
             increase = false;
             decrease = true;
             left.balance = Node.BALANCED;
             localRoot.balance = Node.BALANCED;
         }
         else {
           /* After the rotate the leftChild (new root) will
              be left heavy, and the local root (new right child)
              will be right heavy. The overall height of the tree
              will not change, thus increase and decrease remain
              unchanged.
            */
             left.balance = Node.LEFT_HEAVY;
             localRoot.balance = Node.RIGHT_HEAVY;
         }
         // Now rotate the
         return rotateRight(localRoot);
  }

    private void incrementBalance(Node node) {
      node.balance++;
      if (node.balance > Node.BALANCED) {
         /* if now right heavy, the overall height has increased, but
         it has not decreased */
         increase = true;
         decrease = false;
      }
      else {
         /* if now balanced, the overall height has not increased, but
             it has decreased. */
         increase = false;
         decrease = true;
    }
  }
  
   private Node  rotateRight(Node  root) {
    System.out.println("Rotating Right");
   //There is where you set up your references to get the proper rotation
   //see hint in rotateLeft
    Node newLocalRoot = root.getLeft();
    root.setLeft(null);
    newLocalRoot.setRight(root);
    
    return newLocalRoot;
  }


   private Node rotateLeft(Node localRoot) {
     System.out.println("Rotating Left");
     //// hint this was only three lines that I took out.
     Node newLocalRoot = localRoot.getRight();
     localRoot.setRight(null);
     newLocalRoot.setLeft(localRoot);
     
     return newLocalRoot;
   }
   
}