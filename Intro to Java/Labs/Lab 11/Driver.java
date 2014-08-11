/**
 * Driver program for Lab 11.
 * 
 * @author John Paxton 
 * @version November 20, 2012
 */

public class Driver
{
    public static void main (String [] args)
    {   
        int [] numbers1 = {8, 6, 10, 4, 2};
        int [] numbers2 = {9, 1, 7, 3, 5, 6};
        int [] numbers3 = {2, 4, 6, 7, 5, 3, 1, 8};
        
        System.out.println("Lab 11: Recursion");
        System.out.println("-----------------\n");
        
        System.out.println("Binary Conversion Method ... \n");
       
        testBinaryConversion(1);
        testBinaryConversion(2);
        testBinaryConversion(3);
        testBinaryConversion(4);
        testBinaryConversion(256);
        testBinaryConversion(1000);
        testBinaryConversion(10000);
        testBinaryConversion(100000);
        
        System.out.println("\nLargest Array Item Method ... \n");
        
        System.out.println("The largest array item is " + Lab11.largestArrayItem(numbers1));
        System.out.println("The largest array item is " + Lab11.largestArrayItem(numbers2));
        System.out.println("The largest array item is " + Lab11.largestArrayItem(numbers3));
    }
    
    /**
     * A helper method to test the method testBinaryConversion
     * 
     * @param   number     the number to convert from decimal to binary
     */

    private static void testBinaryConversion(int number)
    {
        System.out.println("decimal " + number + " = binary " +
            Lab11.convertToBinary(number));
    }
}