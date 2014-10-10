package singletonnetbeans;

public class SingletonB extends Singleton{
    private static SingletonB instance;
    
    private SingletonB() {
        //Do some constructor things here
    }
    
    public static SingletonB getInstance() {
        if (instance == null) {
            instance = new SingletonB();
        }
        return instance;
    }
    
    public void jiggle() {
        System.out.println("We are currently jiggling");
    }
}
