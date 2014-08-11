
/**
 * Write a description of class MyList here.
 *
 * @author Brendan
 * @version 1/31/11
 */
public class MyList {
    // instance variables - replace the example below with your own

    private ListNode head, tail;

    /**
     * Constructor for objects of class MyList
     */
    public MyList() {
        // initialise instance variables
        head = null;
        tail = null;
    }
    
    public ListNode getHead(){
        return head;
    }

    public void insertAtEnd(ListNode node) {
        if (tail == null) {
            head = node;
            tail = node;
            return;
        }
        // otherwise the list is non-empty:
        
        tail.setNext(node);
        node.setPrevious(tail);
        tail = node;

    }
    
    public void removeNode(ListNode node){
        if (contains(node)){
             if (node != head && node != tail){ //Node to delete is in middle of list
                node.getPrevious().setNext(node.getNext());
                node.getNext().setPrevious(node.getPrevious());
                node.setNext(null);
                node.setPrevious(null);
            }
            if (node == head){                  //Case where the node is at the head
                head = node.getNext();
                node.setNext(null);
                if (head != null){
                    head.setPrevious(null);
                }
            }
            if (node == tail){                  //Case where the node is the tiail
                tail = node.getPrevious();
                node.setPrevious(null);
                if (tail != null){
                    tail.setNext(null);
                }
            }
        }
    }

    public void printList() {
        ListNode currentNode = head;
        while (currentNode != null) {
            System.out.println(currentNode.getString());
            currentNode = currentNode.getNext();
        }
    }

    public void recPrintList() {
        if (head == null) {
            System.out.println();
            return;
        }
        System.out.println(head.getString());
        MyList remainder = new MyList();
        remainder.insertAtEnd(head.getNext());
        remainder.recPrintList();
    }

    public boolean contains(ListNode node) {
        ListNode currentNode = head;
        while (currentNode != null) {
            if (currentNode == node) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }
    
    public MyList recReverse(){
        if (head.getNext() == null){
            return this;
        }
        
        MyList remainder = new MyList();
        ListNode addToEnd = head;
        remainder.insertAtEnd(head.getNext());
        
        remainder = remainder.recReverse();
        
        addToEnd.setNext(null);
        remainder.insertAtEnd(addToEnd);
        
        return remainder; 
    }
    
    private int recLengthHelper(ListNode head){
        if (head == null){
            return 0;
        }
        return  1 + recLength();
    }
    
    public int recLength(){
        return  recLengthHelper(head);
    }
}