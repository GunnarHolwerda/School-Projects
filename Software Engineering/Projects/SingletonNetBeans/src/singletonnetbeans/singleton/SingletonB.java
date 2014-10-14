package singletonnetbeans.singleton;

public class SingletonB extends Singleton{
    private static SingletonB instance;
    
    private SingletonB () {};
    
    public static SingletonB getInstance() {
        if (instance == null) {
            instance = new SingletonB();
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
        System.out.println("Yo, call me SingletonB");
    }
}
