package singletonnetbeans;

import java.util.ArrayList;

public class Singleton {
    private static Singleton instance;
    private static ArrayList<NameSingletonPair> registry;

    protected Singleton() {};

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    
    //No modifier, so that subclasses can't inherit
    static final void Register(String name, Singleton s) {
        if (registry == null) {
            registry = new ArrayList<>();
        }
        registry.add(new NameSingletonPair(name, s));
        System.out.printf("Added new singleton with name %s\n", name);
    }

    //No modifier so sublcasses can't inherit
    static final Singleton Lookup(String name) {
        if (registry != null) {
            for (NameSingletonPair value: registry) {
                if (value.getName().equals(name)) {
                    return value.getSingleton();
                }
            }
        }
        else {
            System.out.println("Your registry has not been set up");
        }
        return null;
    }
    
    protected void speak() {
        System.out.println("I am the container singleton that holds all singletons");
    }
}
