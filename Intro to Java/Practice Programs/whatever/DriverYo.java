
/**
 * Write a description of class Driver here.
 * 
 * @author Gunnar Holwerda
 * @version 
 */
public class DriverYo
{
    public static void main (String [] args)
    {
        int end = 30;
        int sum = 0;
        
        for (int i = 1; i <= end; i++)
        {
            sum += Math.pow((25 * i + 1), 2);
        }
        System.out.println(sum);
    }
}
