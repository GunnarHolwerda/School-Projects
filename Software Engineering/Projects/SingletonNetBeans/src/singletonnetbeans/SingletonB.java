package singletonnetbeans;

public class SingletonB extends Singleton{
    private static SingletonB instance;
    
    private SingletonB() {
        singletonnetbeans.Singleton.Register("B", this);
    }
    
    public static SingletonB getInstance() {
        if (instance == null) {
            instance = new SingletonB();
        }
        return instance;
    }
    
    protected void dance() {
        System.out.println("We are jiggling");
    }
}
