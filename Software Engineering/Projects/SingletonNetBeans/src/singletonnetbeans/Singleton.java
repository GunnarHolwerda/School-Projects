package singletonnetbeans;


import java.util.ArrayList;
/**
 *
 * @author Gunnar
 */
public class Singleton {
    private static Singleton instance;
    private static ArrayList<NameSingletonPair> registry;

    public Singleton Singleton() {
       if (instance == null){
           instance = new Singleton();
       }
       return instance;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static void Register(String name, Singleton s) {
        if (registry == null) {
            registry = new ArrayList<>();
        }
        registry.add(new NameSingletonPair(name, s));
        System.out.printf("Added new singleton with name %s\n", name);
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
