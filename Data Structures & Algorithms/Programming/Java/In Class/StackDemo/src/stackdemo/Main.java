package stackdemo;

/**
 *
 * @author Gunnar Holwerda
 */
public class Main {
    
    public static boolean checkPalindrome(String str){
        char[] strChars = str.toCharArray();
        StackDemo tempStack = new StackDemo();
        for (int i = 0; i < strChars.length/2; i++){
            tempStack.push(new StackNode(strChars[i]));
        }
        
        for (int i = ((strChars.length + 1) / 2); i < strChars.length; i++){
            if (tempStack.pop().getCharacter() != strChars[i])
                return false;
        }
        return true;
    }
    
    public static boolean checkBalancedParentheses(String str){
        char[] strChars = str.toCharArray();
        StackDemo tempStack = new StackDemo();
        
        for (int i = 0; i < strChars.length; i++){
            if (strChars[i] == '('){
                tempStack.push(new StackNode(strChars[i]));
            }
            else if (strChars[i] == ')'){
                if (tempStack.pop() == null){
                    return false;
                }
            }
        }
        if (tempStack.pop() != null){
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
    
        StackDemo myStack = new StackDemo();

        myStack.push(new StackNode('a'));
        myStack.push(new StackNode('b'));
        myStack.push(new StackNode('c'));
        
        StackNode curNode = myStack.pop();
        while(curNode != null){
            System.out.println(curNode.getCharacter());
            curNode = myStack.pop();
        }
        
        if (checkPalindrome("racecar")){
            System.out.println("Palindrome that is");
        }
        else {
            System.out.println("Not a Palindrome");
        }
        
        if (checkBalancedParentheses("(5+3)*(6*3(9 + 7) - 9)")){
            System.out.println("Balanced parentheses");
        }
        else {
            System.out.println("Not balanced parentheses");
        }
    }
}