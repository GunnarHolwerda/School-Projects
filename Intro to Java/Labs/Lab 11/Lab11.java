/**
 * The recursive methods for Lab 11. 
 * 
 * @author Gunnar Holwerda
 * @version November 20, 2012
 */

public class Lab11
{
    private static int largestItem;
    
    /**
     * Converts a decimal number to a binary one using recursion.
     * Assume the decimal number is >= 1.
     * 
     * @param       number      the decimal number to convert
     * @return      String representation of the binary number
     */
    
    public static String convertToBinary(int number)
    {
        if (number <= 1)
            return "1";
        else
        {
            return convertToBinary(number / 2) + Integer.toString(number % 2);
        }
    }
    
    /**
     * Find the largest item in an array of integers.  
     * Assume the array contains at least one item.
     * 
     * @param       numbers     an array of integers
     * @return      the largest number in the array of intgers
     */
    
    public static int largestArrayItem(int [] numbers)
    {
        largestItem = 0;
        return largestArrayItemAuxiliary(numbers, 0);
    }
    
    /**
     * The helper method for largestArrayItem.
     * 
     * @param       numbers     an array of integers
     * @param       index       the current index in the array to examine
     * @return      the largest number in the array of integers
     */
    
    private static int largestArrayItemAuxiliary(int [] numbers, int index)
    {
        if (numbers[index] > largestItem)
        {
            largestItem = numbers[index];
        }
        index++;
        if (index < numbers.length)
            largestArrayItemAuxiliary(numbers, index);
            
        return largestItem;
    }
}