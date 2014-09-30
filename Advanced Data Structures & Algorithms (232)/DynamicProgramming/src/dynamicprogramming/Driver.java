package dynamicprogramming;

/**
 * Driver for Coins problem.
 * 
 * @author John Paxton
 * 
 */

import java.io.FileNotFoundException;

public class Driver
{
    public static void main (String [] args) throws FileNotFoundException
    {
        /*
        Change coins = new Change ("coins.in");
        coins.initialize();
        coins.fillTable();
        coins.solveProblems();
        */
        
        MinEditDistance MED = new MinEditDistance("edit.in");
        MED.initialize();
    }
}