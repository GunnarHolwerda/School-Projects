package singletonnetbeans;


import java.util.ArrayList;
/**
 *
 * @author Gunnar
 */
public class Singleton {
    private static Singleton instance;
    private static ArrayList<NameSingletonPair> registry;

    private Singleton() {
       //Do some things in here to initialize singleton
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
            return instance;
        }
        return instance;
    }

    public static void Register(String name, Singleton s) {
        if (registry == null) {
            registry = new ArrayList<>();
        }
        registry.add(new NameSingletonPair(name, s));
    }

    private static Singleton Lookup(String name) {
        for (NameSingletonPair value: registry) {
            if (value.getName().equals(name)) {
                return value.getSingleton();
            }
        }
        return null;
    }
}
