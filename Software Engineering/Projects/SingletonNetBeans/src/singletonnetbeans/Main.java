package singletonnetbeans;

//Import the singleton classes
import singletonnetbeans.singletons.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the Singleton world!");
        
        Singleton.Register("A", SingletonA.getInstance());
        Singleton.Register("B", SingletonB.getInstance());
        
        Singleton temp = Singleton.Lookup("A");
        if (temp != null) {
            temp.speak();
        }
        else {
            System.out.println("Could not find singleton with that label");
        }
    }
    
}
