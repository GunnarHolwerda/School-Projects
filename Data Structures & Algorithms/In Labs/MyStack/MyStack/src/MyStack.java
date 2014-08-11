/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
/**
 *
 * @author Brendan
 */
public class MyStack {
    private ArrayList<Character> stack;
    
    public MyStack() {
        stack = new ArrayList();  // empty stack
    }
    
    // stack operations:
    
    public void push(char c) {
        stack.add(0, c);
    }
    
    public char pop() {
        if (stack.isEmpty()) {
            return ' ';
        }
        Character currentTopSlot = stack.get(0);
        stack.remove(0);
        return currentTopSlot;
    }
    
    public char peak(){
        char peakCharacter = pop();
        push(peakCharacter);
        return peakCharacter;
    }
}
