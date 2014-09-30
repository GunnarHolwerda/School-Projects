
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
    Change (String inputFile) throws FileNotFoundException
    {
        in = new Scanner (new File (inputFile) );
    }
    
    public void initialize()
    {
        int numberCoins;
        int maxChange;
        
        numberCoins = in.nextInt();
        System.out.println("Number of coins = " + numberCoins);
        coins = new int[numberCoins];
        
        for (int i = 0; i < numberCoins; i++)
        {
            coins[i] = in.nextInt();
            System.out.println("Coin " + i + "'s value = " + coins[i]);
        }
        
        maxChange = in.nextInt();
        System.out.println("Max change = " + maxChange);
        
        solutions = new int [maxChange + 1][numberCoins];
        
        for (int i = 0; i <= maxChange; i++)
        {
            solutions[i][0] = i;
        }
        
        for (int i = 0; i <= maxChange; i++)
        {
            for (int j = 1; j < numberCoins; j++)
            {
                solutions[i][j] = 0;
            }
        }
        
        printTable();
        }
    
    private void printTable()
    {
        System.out.println("Table");
        for (int i = 0; i < solutions.length; i++)
        {
            for (int j = 0; j < coins.length; j++)
            {
                System.out.print(solutions[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
    public void fillTable()
    {
        for (int column = 1; column < coins.length; column++)
        {
            for (int row = 0; row < solutions.length; row++)
            {
                // Dynamic Programming
                if (coins[column] > row)
                {
                    solutions[row][column] = solutions[row][column - 1];
                }
                else
                {
                    solutions[row][column] = Math.min (
                      solutions[row][column - 1],
                      solutions[row - coins[column]][column] + 1 );
                }
            }
        }
        printTable();
    }
    
    public void solveProblems()
    {
        int changeAmount;
        
        while (in.hasNext())
        {
            changeAmount = in.nextInt();
            System.out.println("The least number of coins for " +
               changeAmount + " is " + solutions[changeAmount][coins.length - 1]);
        }
    }
    
    private int coins[];
    private int solutions[][];
    private Scanner in;
}