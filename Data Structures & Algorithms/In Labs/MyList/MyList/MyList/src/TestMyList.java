
/**
 * Write a description of class TestMyList here.
 *
 * @author Brendan
 * @version 1/31/11
 */

import java.util.Scanner;
import java.util.ArrayList;

public class TestMyList {

    public static void doFind(MyList myList, String string) {
        ListNode node = myList.find(string);
        if (node != null) {
            System.out.println("found!");
            System.out.println(node.getString());
        } else {
            System.out.println("not found!");
        }
    }

    public static void main(String[] args) {
        MyList myList = new MyList();

        myList.insertAtEnd(new ListNode("one"));
        myList.insertAtEnd(new ListNode("two"));
        myList.insertAtEnd(new ListNode("three"));
        myList.insertAtEnd(new ListNode("four"));
        myList.insertAtEnd(new ListNode("five"));
//        System.out.println("printing list using iterative approach...");
//        myList.printList();
//        
//        doFind(myList, "three");
//        doFind(myList, "four");
//        System.out.println("printing list using recursive approach...");
//        myList.recPrintList();
        
        MyList reverse = myList.recReverse();
        reverse.printList();
        
        MyList secondList = new MyList();
        
        secondList.insertAtEnd(new ListNode("Seven"));
        secondList.insertAtEnd(new ListNode("Psychopaths"));
        
        MyList reversedList = secondList.recReverse();
        reversedList.printList();
        


    }
}
