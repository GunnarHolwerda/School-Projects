package rolodex;
/**
 * Write a description of class ListNode here.
 *
 * @author Brendan
 * @version 1/31/11
 */
public class ContactListNode {

    private Contact contact;
    private ContactListNode next, previous;

    /**
     * Constructor for objects of class ListNode
     */
    public ContactListNode(Contact contact) {
        // initialise instance variables
        this.contact = contact;
        next = null;
        previous = null;
    }

    public Contact getContact() {
        return contact;
    }

    public ContactListNode getNext() {
        return next;
    }
    
    public ContactListNode getPrevious(){
        return previous;
    }
    
    public void setPrevious(ContactListNode node){
        this.previous = node;
    }

    public void setNext(ContactListNode next) {
        this.next = next;
    }
}
