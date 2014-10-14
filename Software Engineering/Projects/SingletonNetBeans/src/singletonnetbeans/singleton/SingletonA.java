package singletonnetbeans.singletons;

import singletonnetbeans.Singleton;

public class SingletonA extends Singleton{
    private static SingletonA instance;
    
    private SingletonA() {};
    
    public static SingletonA getInstance() {
        if (instance == null) {
            instance = new SingletonA();
        }
        return instance;
    }
    
    @Override
    protected void speak() {
        System.out.println("I am THE ONE AND ONLY SingletonA");
    }
}
