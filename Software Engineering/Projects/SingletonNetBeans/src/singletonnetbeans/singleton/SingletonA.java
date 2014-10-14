package singletonnetbeans.singleton;

public class SingletonA extends Singleton{
    private static SingletonA instance;
    
    private SingletonA() {};
    
    public static SingletonA getInstance() {
        if (instance == null) {
            instance = new SingletonA();
        }
        return instance;
    }
    
    public static void Register(String name, Singleton s) {
        throw new UnsupportedOperationException();
    }
    
    public static Singleton Lookup(String name) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void speak() {
        System.out.println("I am THE ONE AND ONLY SingletonA");
    }
}
