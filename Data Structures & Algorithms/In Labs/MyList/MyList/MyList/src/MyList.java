
/**
 * Write a description of class MyList here.
 *
 * @author Brendan
 * @version 1/31/11
 */
public class MyList {
    // instance variables - replace the example below with your own

    private ListNode head;

    /**
     * Constructor for objects of class MyList
     */
    public MyList() {
        // initialise instance variables
        head = null;
    }

    public void insertAtEnd(ListNode node) {
        if (head == null) {
            head = node;
            return;
        }
        // otherwise the list is non-empty:
        ListNode currentNode = head;
        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        // at this point currentNode points to the last element in the list.
        currentNode.setNext(node);

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

    public ListNode find(String str) {
        ListNode currentNode = head;
        while (currentNode != null) {
            if (currentNode.getString().equals(str)) {
                return currentNode;
            }
            currentNode = currentNode.getNext();
        }
        return null;
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
}