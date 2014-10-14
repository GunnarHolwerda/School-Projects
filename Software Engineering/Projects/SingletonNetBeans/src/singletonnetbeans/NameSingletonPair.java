package singletonnetbeans;

import singletonnetbeans.singleton.Singleton;

public class NameSingletonPair {
    private Singleton singleton;
    private String name;

    public NameSingletonPair (String name, Singleton instance) {
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
