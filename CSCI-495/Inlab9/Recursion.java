/**
 * The recursive methods for Inlab 9.
 *
 * @author (Gunnar Holwerda)
 * @version April 8, 2016
 */
public class Recursion
{
    /**
     * Converts a decimal number to a binary one using recursion.
     * Assume the decimal number is >= 1.
     *
     * @param       number      the decimal number to convert
     * @return      a String representation of the binary number
     */
    public static String convertToBinary(int number)
    {
        if (number <= 1) {
            return "1";
        }
        else {
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
        return largestArrayItemAuxiliary(numbers, 0, 0);
    }

    /**
     * The helper method for largestArrayItem.
     *
     * @param       numbers     an array of integers
     * @param       index       the current index in the array to examine
     * @return      the largest number in the array of integers
     */
    private static int largestArrayItemAuxiliary(int [] numbers, int index, int largestItem)
    {
        if (index >= numbers.length) {
            return largestItem;
        }
        else if (numbers[index] > largestItem) {
            largestItem = numbers[index];
        }
        return largestArrayItemAuxiliary(numbers, index + 1, largestItem);
    }
}
