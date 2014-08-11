
/**
 * Write a description of class Driver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Driver
{
    public static void main(String[] args)
    {
        FileRead demo = new FileRead("final.in");
        
        System.out.format("%.2f", demo.computeGPA());
    }
}
