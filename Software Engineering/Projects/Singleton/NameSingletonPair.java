public class NameSingletonPair {
	private Singleton singleton;
	private String name;

	NameSingletonPair (String name, Singleton instance) {
		this.instance = instance;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Singleton getInstance() {
		return instance;
	}
}