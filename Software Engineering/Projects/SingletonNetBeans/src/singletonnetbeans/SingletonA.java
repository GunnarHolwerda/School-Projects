package singletonnetbeans;

/**
 *
 * @author Gunnar
 */
public class SingletonA extends Singleton{
    private static SingletonA instance;
    
    private SingletonA() {
        //Do some constructor things here
    }
    
    public static SingletonA getInstance() {
        if (instance == null) {
            instance = new SingletonA();
        }
        return instance;
    }
    
    public void dance() {
        System.out.println("We are dancing.");
    }
}
