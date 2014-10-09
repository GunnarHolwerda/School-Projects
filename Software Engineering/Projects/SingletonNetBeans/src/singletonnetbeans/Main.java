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
        
        Singleton a = SingletonA.getInstance();
        s.Register("A", a);
    }
    
}
