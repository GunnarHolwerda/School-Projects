package singletonnetbeans;

/**
 *
 * @author Gunnar
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the Singleton world!");
        Singleton s = Singleton.getInstance();
        
        if (s != null) {
            System.out.println("We got an instance from Singleton");
        }
        
        SingletonA a = SingletonA.getInstance();
        SingletonB b = SingletonB.getInstance();
        
        Singleton temp = Singleton.Lookup("B");
        
        if (temp != null) {
            temp.dance();
        }
        else {
            System.out.println("Could not find singleton with that label");
        }
    }
    
}
