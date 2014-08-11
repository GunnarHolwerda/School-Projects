/**
 * Driver method for Lab 8.
 *
 * @author John Paxton
 * @version October 23, 2012
 */

public class Driver 
{

    /**
     * Test the two methods of reversing an array provided by the ArrayStuff
     * class.
     *
     * @param args
     */
    
    public static void main(String[] args) 
    {
        System.out.println("Method 1: Using only the original array\n");

        test(7, 1);
        test(12, 1);
        test(0, 1);
        test(1, 1);

        System.out.println("Method 2: Using a second, temporary array\n");

        test(7, 2);
        test(12, 2);
        test(0, 2);
        test(1, 2);
    }

    /**
     * Tests the first method of reversal.
     *
     * @param howMany    the size of the array to be created and tested
     * @param whichTest  the reverse method to call (1 = reverse in place) 
     */
    
    private static void test(int howMany, int whichTest) 
    {
        ArrayStuff myArray;

        myArray = new ArrayStuff(howMany);

        System.out.print("Test with " + howMany + " element");

        if (howMany != 1) {
            System.out.print("s");
        }

        System.out.println("");

        myArray.printArray("Original Array: ");

        if (whichTest == 1)
        {
          myArray.reverseArray();
        }
        else
        {
          myArray.reverseArray2();
        }

        myArray.printArray("Reversed Array: ");

        System.out.println();
    }
}