package singletonnetbeans;

public class NameSingletonPair {
    private Singleton singleton;
    private String name;

    NameSingletonPair (String name, Singleton instance) {
            this.singleton = instance;
            this.name = name;
    }

    public String getName() {
            return name;
    }

    public Singleton getSingleton() {
            return singleton;
    }
}
