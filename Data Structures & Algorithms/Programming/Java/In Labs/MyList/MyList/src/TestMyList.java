
/**
 * Write a description of class TestMyList here.
 *
 * @author Brendan
 * @version 1/31/11
 */

public class TestMyList {

    public static void main(String[] args) {
        MyList myList = new MyList();
        
        ListNode one = new ListNode("one");
        ListNode two = new ListNode("two");
        ListNode three = new ListNode("three");
        ListNode four = new ListNode("four");
        

        myList.insertAtEnd(one);
        myList.insertAtEnd(two);
        myList.insertAtEnd(three);
        myList.insertAtEnd(four);
        
        System.out.println("..deleting node one");
        myList.removeNode(one);
        myList.printList();
        
        System.out.println("Length = " + myList.recLength());
    }
}
