package singletonnetbeans.singletons;

import singletonnetbeans.Singleton;

public class SingletonB extends Singleton{
    private static SingletonB instance;
    
    private SingletonB () {};
    
    public static SingletonB getInstance() {
        if (instance == null) {
            instance = new SingletonB();
        }
        return instance;
    }
    
    @Override
    protected void speak() {
        System.out.println("Yo, call me SingletonB");
    }
}
