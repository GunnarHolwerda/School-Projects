
/**
 * Write a description of class Driver here.
 * 
 * @author Gunnar Holwerda
 * @version 
 */
public class Driver
{
    public static void main (String [] args)
    {
        FileDemo demo = new FileDemo("usher.txt", "usher.out");
        
        demo.processFile();
        demo.sortFile();
        demo.showResults();
    }
}
