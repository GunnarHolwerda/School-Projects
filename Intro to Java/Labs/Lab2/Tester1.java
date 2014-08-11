
/**
 * Write a description of class Tester1 here.
 * 
 * @author Gunnar Holwerda
 * @version 9/9/12
 */
public class Tester1
{
    public static void main (String [] args)
    {
        Stock MSFT = new Stock("MSFT", 100, 30.95);
        Stock AAPL = new Stock("AAPL", 10, 680.44);
        
        System.out.println("CSCI 111, In Lab 2, Tester1\n");
        
        System.out.println("The stock's symbol is " + MSFT.getName());
        System.out.println("The numbe of shares is " + MSFT.getShares());
        System.out.println("The value of a share is $" + MSFT.getPrice());
        System.out.println("The value of all of the shares is $" + MSFT.getValue() + "\n");
        
        System.out.println("The stock's symbol is " + AAPL.getName());
        System.out.println("The numbe of shares is " + AAPL.getShares());
        System.out.println("The value of a share is $" + AAPL.getPrice());
        System.out.println("The value of all of the shares is $" + AAPL.getValue());
    }
}
