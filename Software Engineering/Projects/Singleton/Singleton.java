import java.util.ArrayList;

public class Singleton {
	private Singleton instance;
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
		registry.add(new NameSingletonPair(name, s));
	}

	private static Singleton Lookup(String name) {
		//Find singleton in registry by name and return it
	}
}