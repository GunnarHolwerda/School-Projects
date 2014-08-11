package rolodex;
/**
 * Write a description of class MyList here.
 *
 * @author Brendan
 * @version 1/31/11
 */
public class ContactList {

    private ContactListNode head, tail;

    /**
     * Constructor for objects of class MyList
     */
    public ContactList() {
        
        head = null;
        tail = null;
    }
    
    public ContactListNode getHead(){
        return head;
    }
    
    public ContactListNode getTail(){
        return tail;
    }

    public void insertAtEnd(ContactListNode node) {
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
    
    public void insertAlphabetically(ContactListNode node) {
        if (tail == null) {
            head = node;
            tail = node;
            return;
        }
        // otherwise the list is non-empty:

        ContactListNode curNode = head;
        boolean insertComplete = false;
        
        while (curNode != null){
            if (curNode.getContact().getFirstName().compareTo(node.getContact().getFirstName()) > 0){
                node.setNext(curNode);
                node.setPrevious(curNode.getPrevious());
                
                if(curNode.getPrevious() != null){
                    curNode.getPrevious().setNext(node);
                }
                else {
                    head = node;
                }
                
                curNode.setPrevious(node);
                insertComplete = true;
                break;
            }
            curNode = curNode.getNext();
        }
        
        if (insertComplete != true){
            insertAtEnd(curNode);
        }
    }
    
    public void removeNode(ContactListNode node){
        if (contains(node)){
             if (node != head && node != tail){     //Node to delete is in middle of list
                node.getPrevious().setNext(node.getNext());
                node.getNext().setPrevious(node.getPrevious());
                node.setNext(null);
                node.setPrevious(null);
            }
            if (node == head){                      //Case where the node is at the head
                head = node.getNext();
                node.setNext(null);
                if (head != null){
                    head.setPrevious(null);
                }
            }
            if (node == tail){                      //Case where the node is the tiail
                tail = node.getPrevious();
                node.setPrevious(null);
                if (tail != null){
                    tail.setNext(null);
                }
            }
        }
    }

    public void printList() {
        ContactListNode currentNode = head;
        while (currentNode != null) {
            currentNode.getContact().print();
            currentNode = currentNode.getNext();
        }
        System.out.println();
    }

    public boolean contains(ContactListNode node) {
        ContactListNode currentNode = head;
        while (currentNode != null) {
            if (currentNode == node) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }
    
    public ContactList recReverse(){
        if (head.getNext() == null){
            return this;
        }
        
        ContactList remainder = new ContactList();
        ContactListNode addToEnd = head;
        remainder.insertAtEnd(head.getNext());
        
        remainder = remainder.recReverse();
        
        addToEnd.setNext(null);
        remainder.insertAtEnd(addToEnd);
        
        return remainder; 
    }
}