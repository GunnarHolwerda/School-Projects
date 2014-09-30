package hashtable;
/*
 *
 * @author Gunnar
 */
public class Driver {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = "src/hashtable/input.txt";
        HashTable table = new HashTable(5);
        
        table.inputFromFile(fileName);
        
        table.printTable();        
    }
    
}
