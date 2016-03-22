
/**
 * Write a description of class Driver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Driver
{
    public static void main(String[] args) {
        Book book1 = new Book("Dracula", 7.55, 22);
        
        System.out.println("Book name: " + book1.getTitle());
        System.out.println("Book value: " + book1.getValueOfStock());
        
        Book book2 = new Book("Harry Potter", 12.00, 11);
        
        System.out.println("Book name: " + book2.getTitle());
        System.out.println("Book value: " + book2.getValueOfStock());
    }
}
