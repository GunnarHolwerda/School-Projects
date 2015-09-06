
package dynamicprogramming;
/**
 * Use dynamic programming to solve the coins problem.
 * 
 * @author John Paxton
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Change
{
    private int solutions[][];
    private Scanner in;
    
    Change (String inputFile) throws FileNotFoundException
    {
        in = new Scanner (new File (inputFile) );
    }
    
    public void initialize()
    {
        int numberCoins;
        int maxChange;
        
        // Read in the number of coins
        numberCoins = in.nextInt();
        // Read in the max sum we need to calculate for
        maxChange = in.nextInt();
        
        /*
            Initialize the solutions array with numberCoins + 1 rows and 
            maxChange + 1 columns. To leave [0][0] to be 0;
        */
        solutions = new int[numberCoins + 1][maxChange + 2];
        
        // Initialize the first value in each row to the coin value
        for (int i = 1; i <= numberCoins; i++)
        {
            solutions[i][0] = in.nextInt();
        }
        
        // Fill the top column with the values through maxChange
        // Start at 2 to leave a column for 0 and go to maxChange + 1
        // To get to the total maxChange
        for (int i = 2; i <= maxChange + 1; i++) {
            solutions[0][i] = i - 1;
        }
        
        printTable();
    }
    
    private void printTable()
    {
        System.out.println("Table");
        for (int i = 0; i < solutions.length; i++)
        {
            for (int j = 0; j < solutions[0].length; j++)
            {
                System.out.printf("%2d ", solutions[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
    public void fillTable()
    {
        // Iterate over each row, start at 1 to skip the [0][0] = 0 location
        for (int row = 1; row < solutions.length; row++) {
            int currentCoinValue = solutions[row][0];
            // Iterate over each column, start at 1 to skip the [0][0] = 0 location
            for (int col = 2; col < solutions[0].length; col++) {
                int currentSum = solutions[0][col];
                
                if (currentCoinValue > currentSum) {
                    solutions[row][col] = solutions[row - 1][col];
                }
                else {
                    // Get the solution from the above row
                    int aboveColumn = solutions[row - 1][col];
                    // Get the new solution:
                    // Solution for the currentSum - currentCoinValue + 1
                    int newCalculation = solutions[row][col - currentCoinValue] + 1;
                    solutions[row][col] = Math.min(aboveColumn, newCalculation);
                }
            }
        }
        printTable();
    }
}