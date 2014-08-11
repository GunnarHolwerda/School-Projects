
/**
 * Write a description of class ListNode here.
 *
 * @author Brendan
 * @version 1/31/11
 */
public class ListNode {

    private String str;
    private ListNode next, previous;

    /**
     * Constructor for objects of class ListNode
     */
    public ListNode(String str) {
        // initialise instance variables
        this.str = str;
        next = null;
        previous = null;
    }

    public String getString() {
        return str;
    }

    public ListNode getNext() {
        return next;
    }
    
    public ListNode getPrevious(){
        return previous;
    }
    
    public void setPrevious(ListNode node){
        this.previous = node;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
