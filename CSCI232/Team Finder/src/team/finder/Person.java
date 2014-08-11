package team.finder;

/**
 *
 * @author Gunnar
 */
public class Person {
    public String name;
    public boolean is_swimmer;
    
    Person(String name){
        this.name = name;
        this.is_swimmer = false;
    }
    
    Person(String name, boolean is_swimmer) {
        this.name = name;
        this.is_swimmer = is_swimmer;
    }
}
