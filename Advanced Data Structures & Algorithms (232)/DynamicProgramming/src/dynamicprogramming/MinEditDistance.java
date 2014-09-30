package dynamicprogramming;

/**
 *
 * @author Gunnar
 */

import java.util.*;
import java.io.*;


public class MinEditDistance {
    private Scanner in;
    private int solution[][];
    private String str1, str2;
    
    MinEditDistance(String filename) throws FileNotFoundException{
        in = new Scanner(new File(filename));
    }
    
    public void initialize(){
        while (in.hasNextLine()){
            System.out.println("-------------------------------------------------------");
            str1 = "%" + in.nextLine();
            str2 = "%" + in.nextLine();

            System.out.println("String 1 = " + str1);
            System.out.println("String 2 = " + str2);

            solution = new int[str1.length()][str2.length()];
            
            /* DEBUG
            System.out.println("Blank matrix:");
            printSolutionMatrix();
            */
            
            //Fill the first row and first column with the number of changes needed to go from % (null character) to full string
            for (int i = 0; i < solution[0].length; i++){
                solution[0][i] = i;
                if (i < solution.length)
                    solution[i][0] = i;
            }

            //System.out.println("\nFirst row and first column should be filled with numbers");
            //printSolutionMatrix();

            //Find the solution to the minimum edit distance
            System.out.println("\nSolving.....\n");
            fillMatrix();

            System.out.println("Matrix is full now:");
            printSolutionMatrix();
            System.out.printf("\nTo change %s to %s you need %d moves\n\n", str1, str2, solution[str1.length()-1][str2.length()-1]);
        }
             
    }
    
    public void printSolutionMatrix(){
        System.out.print(" " + str2 + "\n");
        for (int i = 0; i < solution.length; i++){
            if (i == 0){
                System.out.print(str1.charAt(i));
            }
            for (int j = 0; j < solution[i].length; j++){
                System.out.print(solution[i][j]);
            }
            System.out.println();
            if (i + 1 < str1.length())
                System.out.print(str1.charAt(i + 1));
        }
    }
    
    public void fillMatrix(){
        int up, left, diagonal;
        for (int col = 1; col < solution.length; col++){
            for (int row = 1; row < solution[0].length; row++){
                up = solution[col - 1][row] + 1;
                left = solution[col][row - 1] + 1;
                //System.out.println("Comparing " + str1.charAt(row - 1) + " to " + str2.charAt(col));
                //System.out.printf("Checking %s @ %d\n", str1, row);
                //System.out.printf("Checking %s @ %d\n", str2, col);
                if (str1.charAt(col) == str2.charAt(row)){
                    diagonal = solution[col - 1][row - 1] + 0;
                }
                else {
                    diagonal = solution[col - 1][row - 1] + 1;
                }
                //System.out.println(" writing out a " + findMin(up, left, diagonal) + " @ " + "(" + col + ", " + row + ")");
                solution[col][row] = findMin(up, left, diagonal);
                
            }
        }
    }
    
    private int findMin(int up, int left, int diagonal){
        //System.out.printf("Comparing %d %d %d\n", up, left, diagonal);        //DEBUG 
        int smallest = up;
        
        if (left < smallest)
            smallest = left;
        if (diagonal < smallest)
            smallest = diagonal;
        
        //System.out.println("The smallest is " + smallest);                    //DEBUG
        return smallest;
        
    }
}
