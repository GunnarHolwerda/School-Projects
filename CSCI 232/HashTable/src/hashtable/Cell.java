package hashtable;

/**
 *
 * @author Gunnar
 */
public class Cell{
    private int key;
    private String value;
    
    public Cell(int key, String value){
        this.value = value;
        this.key = key;
    }
    
    public int getKey(){
        return key;
    }
    
    public String getValue(){
        return value;
    }
    
}
