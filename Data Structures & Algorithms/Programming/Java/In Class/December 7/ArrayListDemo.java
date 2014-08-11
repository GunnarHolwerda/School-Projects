
/**
 * Write a description of class ArrayListDemo here.
 * 
 * @author Gunnar Holwerda 
 * @version December 7
 */
import java.util.ArrayList;

public class ArrayListDemo
{
    ArrayList<String> names;
    
    public ArrayListDemo()
    {
        names = new ArrayList<String>();
    }
    
    public void addName(String name)
    {
        names.add(name);
    }
    
    public String toString()
    {
        int slot = 0;
        String answer = "The names are: \n\n" + toStringAux(slot);
        return answer;
    }
    
    public String toStringAux(int slot)
    {
        if (slot >= names.size())
        {
            return "";
        }
        else
            return slot + ". " + names.get(slot) + "\n" + toStringAux(slot + 1);
    }
}
