/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brendan
 */
public class TestStack {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push('a');
        myStack.push('b');
        myStack.push('c');
        
        while (myStack.peak() != ' ') {
            System.out.println(myStack.pop());
        }
    }
}
